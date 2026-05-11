package org.example.bank.OutputClassBank;

import com.google.gson.Gson;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.example.ClassOutputCreator.templates.MATemplate;
import org.example.ClassOutputCreator.templates.ai.AiColumnTemplate;
import org.example.ClassOutputCreator.templates.ai.AiMATemplate;
import org.example.ClassOutputCreator.templates.KdbGma;
import org.example.JsonBuilder.json.GMAJson;
import org.example.JsonBuilder.json.ma.MAJson;
import org.example.JsonBuilder.json.ma.tables.TableJson;
import org.example.JsonBuilder.json.ma.tables.columns.ColumnJson;
import org.example.ai.AiRagSchemaJson;
//import org.example.ai.VectorQueryResultWrapper;

import org.example.ai.PromptForJsonSchema;
import org.example.ai.bank.ParseValue;
import org.example.ai.registry.parse.ParseFileMethod;
import org.example.bank.AppConfig;
import org.example.bank.JsonBuilderRef.EntityValue;
import org.example.bank.MultiFormatTimestampFormatter;
import org.example.bank.commonMethods.AzureStorage;
import org.example.bank.commonMethods.MultiFormatLocalDateTimeFormatter;
import org.example.bank.commonValues.Identifier;
import org.apache.pdfbox.Loader;
import org.example.bank.commonValues.TableTypes;
import org.example.bank.commonValues.UploadTypes;
import org.example.bank.db.InputJson;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;

import static org.example.bank.AppConfig.*;
import static org.example.bank.OutputClassBank.KdbColumnWrapper.safeGetValue;
import static org.example.bank.commonValues.ColumnConverter.toPersonaJson;
import static org.example.bank.commonValues.ColumnConverter.toPersonaTemplate;

public enum KdbContextAi {
    KDB_CONTEXT_AI;


    final Gson gson = new Gson();
    String containerName = "test";

    final String folderRegex = "[^A-Za-z0-9\\-]";

    final Map<String, GMAJson> gmaJsonMap = new HashMap<>();

    final List<KdbGma> gmaConfigList = new ArrayList<>();

    public void addGmaConfig(KdbGma kdbGma) {
        gmaConfigList.add(kdbGma);
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getJdbcUrl(), getJdbcUser(), getJdbcPassword());
    }

    private String appendDescription(String oldDesc, String newDesc) {
        if ((oldDesc == null || oldDesc.isBlank()) && (newDesc == null || newDesc.isBlank())) return null;
        if (oldDesc == null || oldDesc.isBlank()) return newDesc;
        if (newDesc == null || newDesc.isBlank()) return oldDesc;
        // simple concatenation; adjust separator as desired
        if (oldDesc.contains(newDesc)) return oldDesc;
        return oldDesc + " | " + newDesc;
    }

    private ParseValue dbInsertChecker(AiMATemplate rawMa,TableJson parseTable, String parse_name) throws Exception {

        KDBContext kdbContext = KDBContext.KDB_CONTEXT;

        String parseGroupName = rawMa.getPgTableName();
        String inputGroupName = rawMa.getIgTableName();
        String inputTypeName = rawMa.getItTableName();
        String inputNameName = rawMa.getInTableName();
        String inputValueName = rawMa.getIvTableName();


        String tableName = parseTable.getTableName();


        if (tableName.equalsIgnoreCase(parseGroupName)) {
            return new ParseValue(
                    Objects.requireNonNull(kdbContext.getQueryByColumns(getGmaName(), parseTable.getMaName(), parseTable.getName(),
                            List.of(parseTable.getColumnByName("upload_group").setQueryMatchStrings(List.of(parse_name))),
                            toPersonaJson(List.of(parseTable.getColumnByName("description"), parseTable.getColumnByName("db_id"), parseTable.getColumnByName("upload_group"))))),
                    parseTable.getColumnByName("upload_group").getName()
            );
        } else if (tableName.equalsIgnoreCase(inputGroupName)) {
            return new ParseValue(
                    Objects.requireNonNull(kdbContext.getQueryByColumns(getGmaName(), parseTable.getMaName(), parseTable.getName(),
                            List.of(parseTable.getColumnByName("input_group").setQueryMatchStrings(List.of(parse_name))),
                            toPersonaJson(List.of(parseTable.getColumnByName("description"), parseTable.getColumnByName("db_id"), parseTable.getColumnByName("input_group"))))),
                    parseTable.getColumnByName("input_group").getName()
            );

        } else if (tableName.equalsIgnoreCase(inputTypeName)) {
            return new ParseValue(
                    Objects.requireNonNull(kdbContext.getQueryByColumns(getGmaName(), parseTable.getMaName(), parseTable.getName(),
                            List.of(parseTable.getColumnByName("input_type").setQueryMatchStrings(List.of(parse_name))),
                            toPersonaJson(List.of(parseTable.getColumnByName("description"), parseTable.getColumnByName("db_id"), parseTable.getColumnByName("input_type"))))),
                    parseTable.getColumnByName("input_type").getName()
            );

        } else if (tableName.equalsIgnoreCase(inputNameName)) {


            return new ParseValue(
                    Objects.requireNonNull(kdbContext.getQueryByColumns(getGmaName(), parseTable.getMaName(), parseTable.getName(),
                            List.of(parseTable.getColumnByName("input_name").setQueryMatchStrings(List.of(parse_name))),
                            toPersonaJson(List.of(parseTable.getColumnByName("description"), parseTable.getColumnByName("db_id"), parseTable.getColumnByName("input_name"))))),
                    parseTable.getColumnByName("input_name").getName()
            );
        } else if (tableName.equalsIgnoreCase(inputValueName)) {
            System.out.println("here "+inputValueName);
            return new ParseValue(
                    Objects.requireNonNull(kdbContext.getQueryByColumns(getGmaName(), parseTable.getMaName(), parseTable.getName(),
                            List.of(parseTable.getColumnByName("input_value").setQueryMatchStrings(List.of(parse_name))),
                            toPersonaJson(List.of(parseTable.getColumnByName("description"), parseTable.getColumnByName("db_id"), parseTable.getColumnByName("input_value"))))),
                    parseTable.getColumnByName("input_value").getName()
            );

        } else {
            throw new IllegalArgumentException("Unknown table name for dbInsertChecker: " + tableName);
        }


    }




    public void addDbSkeleton(AiMATemplate aiMATemplate, List<File> files,AiRagSchemaJson json, String uploadGroup,  EntityManager entityManager)  {
        String mimeType = getMimeType(files);

        KDBContext kdbContext = KDBContext.KDB_CONTEXT;

        if (!Objects.equals(mimeType, "application/pdf") && !Objects.equals(mimeType, "text/plain")){
            throw new RuntimeException("Only PDF files are currently supported for upload. Detected mime type: "+getMimeType(files));
        }


        System.out.println(gson.toJson(json));

        try{
            if (entityManager == null) {
                throw new IllegalArgumentException("entityManager must not be null");
            }

//            MA_ai ma_ai = new MA_ai();

    /* =========================================================
       PHASE 1 — BUILD CANONICAL KEY -> LOCAL DESCRIPTION MAPS
       ========================================================= */
            Map<String, String> parseGroupLocalDesc = new LinkedHashMap<>();
            Map<String, String> inputGroupLocalDesc = new LinkedHashMap<>();
            Map<String, String> inputTypeLocalDesc  = new LinkedHashMap<>();
            Map<String, String> inputNameLocalDesc  = new LinkedHashMap<>();
            Map<String,String> inputValueLocalDesc = new LinkedHashMap<>();

            // top-level upload group
            parseGroupLocalDesc.put(uploadGroup, json.getUploadDescription());

            if (json.getGroups() != null) {
                for (AiRagSchemaJson.Group group : json.getGroups()) {
                    if (group == null) continue;

                    inputGroupLocalDesc.merge(
                            group.getGroupName(),
                            group.getDescription(),
                            this::appendDescription
                    );

                    if (group.getTypes() == null) continue;
                    for (AiRagSchemaJson.TypeEntry type : group.getTypes()) {
                        if (type == null) continue;

                        inputTypeLocalDesc.merge(
                                type.getTypeName(),
                                type.getDescription(),
                                this::appendDescription
                        );

                        if (type.getNames() == null) continue;
                        for (AiRagSchemaJson.NameEntry name : type.getNames()) {
                            if (name == null) continue;

                            inputNameLocalDesc.merge(
                                    name.getInputName(),
                                    name.getDescription(),
                                    this::appendDescription
                            );
                            if(name.getValues() == null) continue;
                            for(AiRagSchemaJson.ValueWrapper ive : name.getValues()){
                                if(ive == null) continue;
                                // guard against null value names — use empty string as canonical key when missing
                                String vKey = ive.getValue() == null ? "" : ive.getValue().getName();
                                if (vKey == null) vKey = "";
                                inputValueLocalDesc.merge(
                                        vKey,
                                        ive.getValue() == null ? null : ive.getValue().getDescription(),
                                        this::appendDescription
                                );
                            }
                        }
                    }
                }
            }

    /* =========================================================
       PHASE 2 — RESOLVE UUIDS / DB VALUES VIA dbInsertChecker
       ========================================================= */
            Map<String, ParseValue> parseGroupDb = new LinkedHashMap<>();
            Map<String, ParseValue> inputGroupDb = new LinkedHashMap<>();
            Map<String, ParseValue> inputTypeDb  = new LinkedHashMap<>();
            Map<String, ParseValue> inputNameDb  = new LinkedHashMap<>();
            Map<String, ParseValue> inputValueDb = new LinkedHashMap<>();



            // if name exists then grab the db id else make a new one parseValue is name db_id and description and set it to the name as the map
            int i= 0;
            for (String key : parseGroupLocalDesc.keySet()) {
                i++;
                TableJson pgTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getPgTableName());
                parseGroupDb.put(key, dbInsertChecker(aiMATemplate,pgTable, key));
                if(i==20){
                    Thread.sleep(6000); // brief pause every 100 iterations to mitigate potential DB overload in large uploads
                    i=0;
                }

            }
            i=0;
            for (String key : inputGroupLocalDesc.keySet()) {
                i++;
                TableJson igTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getIgTableName());
                inputGroupDb.put(key, dbInsertChecker(aiMATemplate,igTable, key));
                if(i==20){
                    Thread.sleep(6000); // brief pause every 100 iterations to mitigate potential DB overload in large uploads
                    i=0;
                }
            }
            i=0;
            for (String key : inputTypeLocalDesc.keySet()) {
                i++;
                TableJson itTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getItTableName());
                inputTypeDb.put(key, dbInsertChecker(aiMATemplate,itTable, key));
                if(i==20){
                    Thread.sleep(6000); // brief pause every 100 iterations to mitigate potential DB overload in large uploads
                    i=0;
                }
            }
            i=0;
            for (String key : inputNameLocalDesc.keySet()) {
                i++;
                TableJson inTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getInTableName());
                inputNameDb.put(key, dbInsertChecker(aiMATemplate,inTable, key));
                if(i==20){
                    Thread.sleep(6000); // brief pause every 100 iterations to mitigate potential DB overload in large uploads
                    i=0;
                }
            }
            i=0;
            // resolve input values (may include empty-string key for unnamed values)
            for (String key : inputValueLocalDesc.keySet()) {
                i++;
                TableJson ivTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getIvTableName());
                inputValueDb.put(key, dbInsertChecker(aiMATemplate,ivTable, key));
                if(i==20){
                    Thread.sleep(6000); // brief pause every 100 iterations to mitigate potential DB overload in large uploads
                    i=0;
                }
            }

    /* =========================================================
       PHASE 3 — BUILD ENTITY OBJECTS (use combined DB + local descriptions)
       ========================================================= */
            List<SaveInterface> parseGroupEntities      = new ArrayList<>();
            List<SaveInterface> parseInputGroupEntities = new ArrayList<>();
            List<SaveInterface> parseInputTypeEntities  = new ArrayList<>();
            List<SaveInterface> parseInputNameEntities  = new ArrayList<>();
            List<SaveInterface> parseInputValueEntities = new ArrayList<>();

            for (Map.Entry<String, ParseValue> e : parseGroupDb.entrySet()) {

                String key = e.getKey();
                ParseValue pv = e.getValue();
                System.out.println(pv.getDb_id() + " parseGroup db_id" +key+ " key ");
                TableJson pgTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getPgTableName());

                pgTable.setColEntityValue("upload_group",key);
                String dbDesc = pv.getDescription();
                String localDesc = parseGroupLocalDesc.get(key);
                pgTable.setColEntityValue("description",appendDescription(dbDesc, localDesc));
                pgTable.setColEntityValue("db_id",pv.getDb_id());
                pgTable.setColEntityValue("is_active","1");
                parseGroupEntities.add(pgTable);
            }

            for (Map.Entry<String, ParseValue> e : inputGroupDb.entrySet()) {

                String key = e.getKey();
                ParseValue pv = e.getValue();

                TableJson igTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getIgTableName());

                igTable.setColEntityValue("input_group",key);
                String dbDesc = pv.getDescription();
                String localDesc = inputGroupLocalDesc.get(key);
                igTable.setColEntityValue("description",appendDescription(dbDesc, localDesc));
                igTable.setColEntityValue("db_id",pv.getDb_id());
                igTable.setColEntityValue("is_active","1");
                parseInputGroupEntities.add(igTable);
            }

            for (Map.Entry<String, ParseValue> e : inputTypeDb.entrySet()) {

                String key = e.getKey();
                ParseValue pv = e.getValue();

                TableJson pgTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getItTableName());

                pgTable.setColEntityValue("input_type",key);
                String dbDesc = pv.getDescription();
                String localDesc = inputTypeLocalDesc.get(key);
                pgTable.setColEntityValue("description",appendDescription(dbDesc, localDesc));
                pgTable.setColEntityValue("db_id",pv.getDb_id());
                pgTable.setColEntityValue("is_active","1");
                parseInputTypeEntities.add(pgTable);
            }



            for (Map.Entry<String, ParseValue> e : inputNameDb.entrySet()) {

                String key = e.getKey();
                ParseValue pv = e.getValue();

                TableJson pgTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getInTableName());

                pgTable.setColEntityValue("input_name",key);
                String dbDesc = pv.getDescription();
                String localDesc = inputNameLocalDesc.get(key);
                pgTable.setColEntityValue("description",appendDescription(dbDesc, localDesc));
                pgTable.setColEntityValue("db_id",pv.getDb_id());
                pgTable.setColEntityValue("is_active","1");
                parseInputNameEntities.add(pgTable);
            }

            for (Map.Entry<String, ParseValue> e : inputValueDb.entrySet()) {

                String key = e.getKey();
                ParseValue pv = e.getValue();

                TableJson igTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getIgTableName());



                String dbDesc = pv.getDescription();
                String localDesc = inputNameLocalDesc.get(key);
                igTable.setColEntityValue("description",appendDescription(dbDesc, localDesc));
                igTable.setColEntityValue("db_id",pv.getDb_id());
                igTable.setColEntityValue("is_active","1");
                parseInputValueEntities.add(igTable);
            }

            // Build parse input value entities


    /* =========================================================
       PHASE 4 — BUILD MTM ROWS (use DB ids from ParseValue maps)
       ========================================================= */
            List<SaveInterface> mtmgigEntities = new ArrayList<>();
            List<SaveInterface> mtmigitEntities = new ArrayList<>();
            List<SaveInterface> mtmitinEntities = new ArrayList<>();
            List<SaveInterface> mtminvEntities = new ArrayList<>(); // name <-> value mtm

            ParseValue pgPv = parseGroupDb.get(uploadGroup);
            String parseGroupId = pgPv == null ? null : pgPv.getDb_id();

            if (json.getGroups() != null) {
                for (AiRagSchemaJson.Group group : json.getGroups()) {
                    if (group == null) continue;
                    ParseValue groupPv = inputGroupDb.get(group.getGroupName());
                    String groupId = groupPv == null ? null : groupPv.getDb_id();

                    TableJson mtm_pgig = getCurrentTableJson(getAiSchema(), aiMATemplate.getMtmPgIgTableName());
                    mtm_pgig.setColEntityValue("parse_group_id",parseGroupId);
                    mtm_pgig.setColEntityValue("input_group_id",groupId);

                    mtmgigEntities.add(mtm_pgig);

                    if (group.getTypes() == null) continue;
                    for (AiRagSchemaJson.TypeEntry type : group.getTypes()) {
                        if (type == null) continue;
                        ParseValue typePv = inputTypeDb.get(type.getTypeName());
                        String typeId = typePv == null ? null : typePv.getDb_id();

                        TableJson mtm_igit = getCurrentTableJson(getAiSchema(), aiMATemplate.getMtmIgItTableName());
                        mtm_igit.setColEntityValue("input_group_id",groupId);
                        mtm_igit.setColEntityValue("input_type_id",typeId);



                        mtmigitEntities.add(mtm_igit);

                        if (type.getNames() == null) continue;
                        for (AiRagSchemaJson.NameEntry name : type.getNames()) {
                            if (name == null) continue;
                            ParseValue namePv = inputNameDb.get(name.getInputName());
                            String nameId = namePv == null ? null : namePv.getDb_id();

                            TableJson mtm_itin = getCurrentTableJson(getAiSchema(), aiMATemplate.getMtmItInTableName());
                            mtm_itin.setColEntityValue("input_type_id",typeId);
                            mtm_itin.setColEntityValue("input_name_id",nameId);

                            mtmitinEntities.add(mtm_itin);

                            // Build name<->value mtm rows for each value under this name
                            if (name.getValues() != null) {
                                Set<String> seenValueNames = new HashSet<>();
                                for (AiRagSchemaJson.ValueWrapper v : name.getValues()) {
                                    if (v == null) continue;

                                    String rawValueName = v.getValue() == null ? "" : v.getValue().getName();
                                    String normalized = (rawValueName == null ? "" : rawValueName.trim().toLowerCase());

                                    if (seenValueNames.contains(normalized)) {
                                        continue; // duplicate name for this input name — skip
                                    }
                                    seenValueNames.add(normalized);

                                    ParseValue valuePv = inputValueDb.get(rawValueName);
                                    String valueId = valuePv == null ? null : valuePv.getDb_id();

                                    TableJson mtm_iniv = getCurrentTableJson(getAiSchema(), aiMATemplate.getMtmInIvTableName());

                                    mtm_iniv.setColEntityValue("input_name_id", nameId);
                                    mtm_iniv.setColEntityValue("input_value_id", valueId);


                                    mtminvEntities.add(mtm_iniv);
                                }
                            }
                        }
                    }
                }
            }

    /* =========================================================
       PHASE 5 — SAVE (ORDER MATTERS) — build upsert/insert strings and call saveAll with 4 args
       ========================================================= */

            // parse groups upsert strings

            TableJson pgTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getPgTableName());

            String insertParseGroup = kdbContext.getUploadInsertGma(getGmaName(),getAiSchema(),pgTable.getTableName(),
                    toPersonaJson(List.of(pgTable.getColumnByName("db_id") )),false, toPersonaJson(pgTable.getColumnsList()),true);
            String updateParseGroup = kdbContext.getUploadUpdateGma(getGmaName(),getAiSchema(),pgTable.getTableName(),
                    List.of(pgTable.getColumnByName("db_id")), false, toPersonaJson(pgTable.getColumnsList()));
            List<String> parseGroupUpsertStrings = new ArrayList<>();
            parseGroupUpsertStrings.add(updateParseGroup);
            parseGroupUpsertStrings.add(insertParseGroup);

            // input groups upsert strings

            TableJson igTable = getCurrentTableJson(getAiSchema(),aiMATemplate.getIgTableName());
            String insertInputGroup = kdbContext.getUploadInsertGma( getGmaName(),getAiSchema(),igTable.getTableName(),
                    toPersonaJson(List.of(igTable.getColumnByName("db_id"))),false, toPersonaJson(igTable.getColumnsList()),true
            );
            String updateInputGroup = kdbContext.getUploadUpdateGma( getGmaName(),getAiSchema(),igTable.getTableName(),
                    toPersonaJson( List.of(igTable.getColumnByName("db_id"))), false, toPersonaJson(igTable.getColumnsList()));

            List<String> parseInputGroupUpsertStrings = new ArrayList<>();
            parseInputGroupUpsertStrings.add(updateInputGroup);
            parseInputGroupUpsertStrings.add(insertInputGroup);

            // input types upsert strings


            TableJson itTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getItTableName());

            String insertInputType = kdbContext.getUploadInsertGma(getGmaName(),getAiSchema(),itTable.getTableName(),
                    toPersonaJson(List.of(itTable.getColumnByName("db_id"))),false, toPersonaJson(itTable.getColumnsList()),true
            );
            String updateInputType = kdbContext.getUploadUpdateGma(getGmaName(),getAiSchema(),itTable.getTableName(),
                    toPersonaJson(
                            List.of(itTable.getColumnByName("db_id"))), false, toPersonaJson(itTable.getColumnsList()));

            List<String> parseInputTypeUpsertStrings = new ArrayList<>();
            parseInputTypeUpsertStrings.add(updateInputType);
            parseInputTypeUpsertStrings.add(insertInputType);

            // input names upsert strings

            TableJson inTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getInTableName());
            String insertInputName = kdbContext.getUploadInsertGma(getGmaName(),getAiSchema(),inTable.getTableName(),
                    toPersonaJson(List.of(inTable.getColumnByName("db_id"))),false, toPersonaJson(inTable.getColumnsList()),true
            );

            String updateInputName = kdbContext.getUploadUpdateGma( getGmaName(),getAiSchema(),inTable.getTableName(),
                    toPersonaJson(
                            List.of(inTable.getColumnByName("db_id"))), false, toPersonaJson(inTable.getColumnsList()));
            List<String> parseInputNameUpsertStrings = new ArrayList<>();
            parseInputNameUpsertStrings.add(updateInputName);
            parseInputNameUpsertStrings.add(insertInputName);

            // input values upsert strings

            TableJson ivTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getIvTableName());
            String insertInputValue = kdbContext.getUploadInsertGma(getGmaName(),getAiSchema(),ivTable.getTableName(),
                    toPersonaJson(List.of(ivTable.getColumnByName("db_id"))),false, toPersonaJson(ivTable.getColumnsList()),true
            );
            String updateInputValue = kdbContext.getUploadUpdateGma(getGmaName(),getAiSchema(),ivTable.getTableName(),
                    toPersonaJson(List.of(ivTable.getColumnByName("db_id"))), false, toPersonaJson(ivTable.getColumnsList()));
            List<String> parseInputValueUpsertStrings = new ArrayList<>();
            parseInputValueUpsertStrings.add(updateInputValue);
            parseInputValueUpsertStrings.add(insertInputValue);

            // MTM upsert/inserts (inserts only)
            TableJson mtmPgIgTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getMtmPgIgTableName());
            TableJson mtmIgItTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getMtmIgItTableName());
            TableJson mtmItInTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getMtmItInTableName());
            TableJson mtmInIvTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getMtmInIvTableName());


            List<KdbColumnPersona> mtmgigPersonas = mtmPgIgTable.getColumnsList().stream().map(p -> (KdbColumnPersona) p).toList();
            List<KdbColumnPersona> mtmigitPersonas = mtmIgItTable.getColumnsList().stream().map(p -> (KdbColumnPersona) p).toList();
            List<KdbColumnPersona> mtmitinPersonas = mtmItInTable.getColumnsList().stream().map(p -> (KdbColumnPersona) p).toList();
            List<KdbColumnPersona> mtminvPersonas = mtmInIvTable.getColumnsList().stream().map(p -> (KdbColumnPersona) p).toList();

            String gigInsert = kdbContext.getUploadInsertGma(getGmaName(),getAiSchema(),mtmPgIgTable.getTableName(),
                    List.of(mtmPgIgTable.getColumnByName("input_group_id"), mtmPgIgTable.getColumnByName("parse_group_id")), false, mtmgigPersonas, false);
            String igitInsert = kdbContext.getUploadInsertGma(getGmaName(),getAiSchema(),mtmIgItTable.getTableName(),
                    List.of(mtmIgItTable.getColumnByName("input_type_id") , mtmIgItTable.getColumnByName("input_group_id")), false, mtmigitPersonas, false);
            String itinInsert = kdbContext.getUploadInsertGma(getGmaName(),getAiSchema(),mtmItInTable.getTableName(),
                    List.of(mtmItInTable.getColumnByName("input_name_id") , mtmItInTable.getColumnByName("input_type_id")), false, mtmitinPersonas, false);
            String invInsert = kdbContext.getUploadInsertGma(getGmaName(),getAiSchema(),mtmInIvTable.getTableName(),
                    List.of(mtmInIvTable.getColumnByName("input_name_id"), mtmInIvTable.getColumnByName("input_value_id")), false, mtminvPersonas, false);

            List<String> gigUpsertStrings = List.of(gigInsert);
            List<String> igitUpsertStrings = List.of(igitInsert);
            List<String> itinUpsertStrings = List.of(itinInsert);
            List<String> invUpsertStrings = List.of(invInsert);

            // Save parse tables
            kdbContext.saveAllGma(pgTable, parseGroupEntities, entityManager, parseGroupUpsertStrings);
            kdbContext.saveAllGma(igTable, parseInputGroupEntities, entityManager, parseInputGroupUpsertStrings);
            kdbContext.saveAllGma(itTable, parseInputTypeEntities, entityManager, parseInputTypeUpsertStrings);
            kdbContext.saveAllGma(inTable, parseInputNameEntities, entityManager, parseInputNameUpsertStrings);
            kdbContext.saveAllGma(ivTable, parseInputValueEntities, entityManager, parseInputValueUpsertStrings);

            // Save mtm tables
            kdbContext.saveAllGma(mtmPgIgTable, mtmgigEntities, entityManager, gigUpsertStrings);
            kdbContext.saveAllGma(mtmIgItTable, mtmigitEntities, entityManager, igitUpsertStrings);
            kdbContext.saveAllGma(mtmItInTable, mtmitinEntities, entityManager, itinUpsertStrings);
            kdbContext.saveAllGma(mtmInIvTable, mtminvEntities, entityManager, invUpsertStrings);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

    public void addDbSkeleton(AiMATemplate aiMATemplate, List<File> files,String uploadGroup,  EntityManager entityManager) throws ExecutionException, InterruptedException {
        String mimeType = getMimeType(files);

        KDBContext kdbContext = KDBContext.KDB_CONTEXT;

        if (!Objects.equals(mimeType, "application/pdf") && !Objects.equals(mimeType, "text/plain")){
            throw new RuntimeException("Only PDF files are currently supported for upload. Detected mime type: "+getMimeType(files));
        }

        AiRagSchemaJson json  =  aiMATemplate.runSkeletonUploadMethod(files,uploadGroup,mimeType,aiMATemplate.getName()).get();
        System.out.println(gson.toJson(json));

        try{
            if (entityManager == null) {
                throw new IllegalArgumentException("entityManager must not be null");
            }

//            MA_ai ma_ai = new MA_ai();

    /* =========================================================
       PHASE 1 — BUILD CANONICAL KEY -> LOCAL DESCRIPTION MAPS
       ========================================================= */
            Map<String, String> parseGroupLocalDesc = new LinkedHashMap<>();
            Map<String, String> inputGroupLocalDesc = new LinkedHashMap<>();
            Map<String, String> inputTypeLocalDesc  = new LinkedHashMap<>();
            Map<String, String> inputNameLocalDesc  = new LinkedHashMap<>();
            Map<String,String> inputValueLocalDesc = new LinkedHashMap<>();

            // top-level upload group
            parseGroupLocalDesc.put(uploadGroup, json.getUploadDescription());

            if (json.getGroups() != null) {
                for (AiRagSchemaJson.Group group : json.getGroups()) {
                    if (group == null) continue;

                    inputGroupLocalDesc.merge(
                            group.getGroupName(),
                            group.getDescription(),
                            this::appendDescription
                    );

                    if (group.getTypes() == null) continue;
                    for (AiRagSchemaJson.TypeEntry type : group.getTypes()) {
                        if (type == null) continue;

                        inputTypeLocalDesc.merge(
                                type.getTypeName(),
                                type.getDescription(),
                                this::appendDescription
                        );

                        if (type.getNames() == null) continue;
                        for (AiRagSchemaJson.NameEntry name : type.getNames()) {
                            if (name == null) continue;

                            inputNameLocalDesc.merge(
                                    name.getInputName(),
                                    name.getDescription(),
                                    this::appendDescription
                            );
                            if(name.getValues() == null) continue;
                            for(AiRagSchemaJson.ValueWrapper ive : name.getValues()){
                                if(ive == null) continue;
                                // guard against null value names — use empty string as canonical key when missing
                                String vKey = ive.getValue() == null ? "" : ive.getValue().getName();
                                if (vKey == null) vKey = "";
                                inputValueLocalDesc.merge(
                                        vKey,
                                        ive.getValue() == null ? null : ive.getValue().getDescription(),
                                        this::appendDescription
                                );
                            }
                        }
                    }
                }
            }

    /* =========================================================
       PHASE 2 — RESOLVE UUIDS / DB VALUES VIA dbInsertChecker
       ========================================================= */
            Map<String, ParseValue> parseGroupDb = new LinkedHashMap<>();
            Map<String, ParseValue> inputGroupDb = new LinkedHashMap<>();
            Map<String, ParseValue> inputTypeDb  = new LinkedHashMap<>();
            Map<String, ParseValue> inputNameDb  = new LinkedHashMap<>();
            Map<String, ParseValue> inputValueDb = new LinkedHashMap<>();



            // if name exists then grab the db id else make a new one parseValue is name db_id and description and set it to the name as the map
            int i= 0;
            for (String key : parseGroupLocalDesc.keySet()) {
                i++;
                TableJson pgTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getPgTableName());
                parseGroupDb.put(key, dbInsertChecker(aiMATemplate,pgTable, key));
                if(i==20){
                    Thread.sleep(6000); // brief pause every 100 iterations to mitigate potential DB overload in large uploads
                    i=0;
                }

            }
            i=0;
            for (String key : inputGroupLocalDesc.keySet()) {
                i++;
                TableJson igTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getIgTableName());
                inputGroupDb.put(key, dbInsertChecker(aiMATemplate,igTable, key));
                if(i==20){
                    Thread.sleep(6000); // brief pause every 100 iterations to mitigate potential DB overload in large uploads
                    i=0;
                }
            }
            i=0;
            for (String key : inputTypeLocalDesc.keySet()) {
                i++;
                TableJson itTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getItTableName());
                inputTypeDb.put(key, dbInsertChecker(aiMATemplate,itTable, key));
                if(i==20){
                    Thread.sleep(6000); // brief pause every 100 iterations to mitigate potential DB overload in large uploads
                    i=0;
                }
            }
            i=0;
            for (String key : inputNameLocalDesc.keySet()) {
                i++;
                TableJson inTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getInTableName());
                inputNameDb.put(key, dbInsertChecker(aiMATemplate,inTable, key));
                if(i==20){
                    Thread.sleep(6000); // brief pause every 100 iterations to mitigate potential DB overload in large uploads
                    i=0;
                }
            }
            i=0;
            // resolve input values (may include empty-string key for unnamed values)
            for (String key : inputValueLocalDesc.keySet()) {
                i++;
                TableJson ivTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getIvTableName());
                inputValueDb.put(key, dbInsertChecker(aiMATemplate,ivTable, key));
                if(i==20){
                    Thread.sleep(6000); // brief pause every 100 iterations to mitigate potential DB overload in large uploads
                    i=0;
                }
            }

    /* =========================================================
       PHASE 3 — BUILD ENTITY OBJECTS (use combined DB + local descriptions)
       ========================================================= */
            List<SaveInterface> parseGroupEntities      = new ArrayList<>();
            List<SaveInterface> parseInputGroupEntities = new ArrayList<>();
            List<SaveInterface> parseInputTypeEntities  = new ArrayList<>();
            List<SaveInterface> parseInputNameEntities  = new ArrayList<>();
            List<SaveInterface> parseInputValueEntities = new ArrayList<>();

            for (Map.Entry<String, ParseValue> e : parseGroupDb.entrySet()) {

                String key = e.getKey();
                ParseValue pv = e.getValue();
                System.out.println(pv.getDb_id() + " parseGroup db_id" +key+ " key ");
                TableJson pgTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getPgTableName());

                pgTable.setColEntityValue("upload_group",key);
                String dbDesc = pv.getDescription();
                String localDesc = parseGroupLocalDesc.get(key);
                pgTable.setColEntityValue("description",appendDescription(dbDesc, localDesc));
                pgTable.setColEntityValue("db_id",pv.getDb_id());
                pgTable.setColEntityValue("is_active","1");
                parseGroupEntities.add(pgTable);
            }

            for (Map.Entry<String, ParseValue> e : inputGroupDb.entrySet()) {

                String key = e.getKey();
                ParseValue pv = e.getValue();

                TableJson igTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getIgTableName());

                igTable.setColEntityValue("input_group",key);
                String dbDesc = pv.getDescription();
                String localDesc = inputGroupLocalDesc.get(key);
                igTable.setColEntityValue("description",appendDescription(dbDesc, localDesc));
                igTable.setColEntityValue("db_id",pv.getDb_id());
                igTable.setColEntityValue("is_active","1");
                parseInputGroupEntities.add(igTable);
            }

            for (Map.Entry<String, ParseValue> e : inputTypeDb.entrySet()) {

                String key = e.getKey();
                ParseValue pv = e.getValue();

                TableJson pgTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getItTableName());

                pgTable.setColEntityValue("input_type",key);
                String dbDesc = pv.getDescription();
                String localDesc = inputTypeLocalDesc.get(key);
                pgTable.setColEntityValue("description",appendDescription(dbDesc, localDesc));
                pgTable.setColEntityValue("db_id",pv.getDb_id());
                pgTable.setColEntityValue("is_active","1");
                parseInputTypeEntities.add(pgTable);
            }



            for (Map.Entry<String, ParseValue> e : inputNameDb.entrySet()) {

                String key = e.getKey();
                ParseValue pv = e.getValue();

                TableJson inTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getInTableName());

                inTable.setColEntityValue("input_name",key);
                String dbDesc = pv.getDescription();
                String localDesc = inputNameLocalDesc.get(key);
                inTable.setColEntityValue("description",appendDescription(dbDesc, localDesc));
                inTable.setColEntityValue("db_id",pv.getDb_id());
                inTable.setColEntityValue("is_active","1");
                parseInputNameEntities.add(inTable);
            }

            for (Map.Entry<String, ParseValue> e : inputValueDb.entrySet()) {

                String key = e.getKey();
                ParseValue pv = e.getValue();

                TableJson ivTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getIvTableName());

                ivTable.setColEntityValue("input_value",key);

                String dbDesc = pv.getDescription();
                String localDesc = inputNameLocalDesc.get(key);
                ivTable.setColEntityValue("description",appendDescription(dbDesc, localDesc));
                ivTable.setColEntityValue("db_id",pv.getDb_id());
                ivTable.setColEntityValue("is_active","1");
                parseInputValueEntities.add(ivTable);
            }

            // Build parse input value entities


    /* =========================================================
       PHASE 4 — BUILD MTM ROWS (use DB ids from ParseValue maps)
       ========================================================= */
            List<SaveInterface> mtmgigEntities = new ArrayList<>();
            List<SaveInterface> mtmigitEntities = new ArrayList<>();
            List<SaveInterface> mtmitinEntities = new ArrayList<>();
            List<SaveInterface> mtminvEntities = new ArrayList<>(); // name <-> value mtm

            ParseValue pgPv = parseGroupDb.get(uploadGroup);
            String parseGroupId = pgPv == null ? null : pgPv.getDb_id();

            if (json.getGroups() != null) {
                for (AiRagSchemaJson.Group group : json.getGroups()) {
                    if (group == null) continue;
                    ParseValue groupPv = inputGroupDb.get(group.getGroupName());
                    String groupId = groupPv == null ? null : groupPv.getDb_id();

                    TableJson mtm_pgig = getCurrentTableJson(getAiSchema(), aiMATemplate.getMtmPgIgTableName());
                    mtm_pgig.setColEntityValue("parse_group_id",parseGroupId);
                    mtm_pgig.setColEntityValue("input_group_id",groupId);

                    mtmgigEntities.add(mtm_pgig);

                    if (group.getTypes() == null) continue;
                    for (AiRagSchemaJson.TypeEntry type : group.getTypes()) {
                        if (type == null) continue;
                        ParseValue typePv = inputTypeDb.get(type.getTypeName());
                        String typeId = typePv == null ? null : typePv.getDb_id();

                        TableJson mtm_igit = getCurrentTableJson(getAiSchema(), aiMATemplate.getMtmIgItTableName());
                        mtm_igit.setColEntityValue("input_group_id",groupId);
                        mtm_igit.setColEntityValue("input_type_id",typeId);



                        mtmigitEntities.add(mtm_igit);

                        if (type.getNames() == null) continue;
                        for (AiRagSchemaJson.NameEntry name : type.getNames()) {
                            if (name == null) continue;
                            ParseValue namePv = inputNameDb.get(name.getInputName());
                            String nameId = namePv == null ? null : namePv.getDb_id();

                            TableJson mtm_itin = getCurrentTableJson(getAiSchema(), aiMATemplate.getMtmItInTableName());
                            mtm_itin.setColEntityValue("input_type_id",typeId);
                            mtm_itin.setColEntityValue("input_name_id",nameId);

                            mtmitinEntities.add(mtm_itin);

                            // Build name<->value mtm rows for each value under this name
                            if (name.getValues() != null) {
                                Set<String> seenValueNames = new HashSet<>();
                                for (AiRagSchemaJson.ValueWrapper v : name.getValues()) {
                                    if (v == null) continue;

                                    String rawValueName = v.getValue() == null ? "" : v.getValue().getName();
                                    String normalized = (rawValueName == null ? "" : rawValueName.trim().toLowerCase());

                                    if (seenValueNames.contains(normalized)) {
                                        continue; // duplicate name for this input name — skip
                                    }
                                    seenValueNames.add(normalized);

                                    ParseValue valuePv = inputValueDb.get(rawValueName);
                                    String valueId = valuePv == null ? null : valuePv.getDb_id();

                                    TableJson mtm_iniv = getCurrentTableJson(getAiSchema(), aiMATemplate.getMtmInIvTableName());

                                    mtm_iniv.setColEntityValue("input_name_id", nameId);
                                    mtm_iniv.setColEntityValue("input_value_id", valueId);


                                    mtminvEntities.add(mtm_iniv);
                                }
                            }
                        }
                    }
                }
            }

    /* =========================================================
       PHASE 5 — SAVE (ORDER MATTERS) — build upsert/insert strings and call saveAll with 4 args
       ========================================================= */

            // parse groups upsert strings

            TableJson pgTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getPgTableName());

            String insertParseGroup = kdbContext.getUploadInsertGma(getGmaName(),getAiSchema(),pgTable.getTableName(),
                    toPersonaJson(List.of(pgTable.getColumnByName("db_id") )),false, toPersonaJson(pgTable.getColumnsList()),true);
            String updateParseGroup = kdbContext.getUploadUpdateGma(getGmaName(),getAiSchema(),pgTable.getTableName(),
                    List.of(pgTable.getColumnByName("db_id")), false, toPersonaJson(pgTable.getColumnsList()));
            List<String> parseGroupUpsertStrings = new ArrayList<>();
            parseGroupUpsertStrings.add(updateParseGroup);
            parseGroupUpsertStrings.add(insertParseGroup);

            // input groups upsert strings

            TableJson igTable = getCurrentTableJson(getAiSchema(),aiMATemplate.getIgTableName());
            String insertInputGroup = kdbContext.getUploadInsertGma( getGmaName(),getAiSchema(),igTable.getTableName(),
                    toPersonaJson(List.of(igTable.getColumnByName("db_id"))),false, toPersonaJson(igTable.getColumnsList()),true
            );
            String updateInputGroup = kdbContext.getUploadUpdateGma( getGmaName(),getAiSchema(),igTable.getTableName(),
                    toPersonaJson( List.of(igTable.getColumnByName("db_id"))), false, toPersonaJson(igTable.getColumnsList()));

            List<String> parseInputGroupUpsertStrings = new ArrayList<>();
            parseInputGroupUpsertStrings.add(updateInputGroup);
            parseInputGroupUpsertStrings.add(insertInputGroup);

            // input types upsert strings


            TableJson itTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getItTableName());

            String insertInputType = kdbContext.getUploadInsertGma(getGmaName(),getAiSchema(),itTable.getTableName(),
                    toPersonaJson(List.of(itTable.getColumnByName("db_id"))),false, toPersonaJson(itTable.getColumnsList()),true
            );
            String updateInputType = kdbContext.getUploadUpdateGma(getGmaName(),getAiSchema(),itTable.getTableName(),
                    toPersonaJson(
                            List.of(itTable.getColumnByName("db_id"))), false, toPersonaJson(itTable.getColumnsList()));

            List<String> parseInputTypeUpsertStrings = new ArrayList<>();
            parseInputTypeUpsertStrings.add(updateInputType);
            parseInputTypeUpsertStrings.add(insertInputType);

            // input names upsert strings

            TableJson inTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getInTableName());
            String insertInputName = kdbContext.getUploadInsertGma(getGmaName(),getAiSchema(),inTable.getTableName(),
                    toPersonaJson(List.of(inTable.getColumnByName("db_id"))),false, toPersonaJson(inTable.getColumnsList()),true
            );

            String updateInputName = kdbContext.getUploadUpdateGma( getGmaName(),getAiSchema(),inTable.getTableName(),
                    toPersonaJson(
                            List.of(inTable.getColumnByName("db_id"))), false, toPersonaJson(inTable.getColumnsList()));
            List<String> parseInputNameUpsertStrings = new ArrayList<>();
            parseInputNameUpsertStrings.add(updateInputName);
            parseInputNameUpsertStrings.add(insertInputName);

            // input values upsert strings

            TableJson ivTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getIvTableName());
            String insertInputValue = kdbContext.getUploadInsertGma(getGmaName(),getAiSchema(),ivTable.getTableName(),
                    toPersonaJson(List.of(ivTable.getColumnByName("db_id"))),false, toPersonaJson(ivTable.getColumnsList()),true
            );
            String updateInputValue = kdbContext.getUploadUpdateGma(getGmaName(),getAiSchema(),ivTable.getTableName(),
                    toPersonaJson(List.of(ivTable.getColumnByName("db_id"))), false, toPersonaJson(ivTable.getColumnsList()));
            List<String> parseInputValueUpsertStrings = new ArrayList<>();
            parseInputValueUpsertStrings.add(updateInputValue);
            parseInputValueUpsertStrings.add(insertInputValue);

            // MTM upsert/inserts (inserts only)
            TableJson mtmPgIgTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getMtmPgIgTableName());
            TableJson mtmIgItTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getMtmIgItTableName());
            TableJson mtmItInTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getMtmItInTableName());
            TableJson mtmInIvTable = getCurrentTableJson(getAiSchema(), aiMATemplate.getMtmInIvTableName());


            List<KdbColumnPersona> mtmgigPersonas = mtmPgIgTable.getColumnsList().stream().map(p -> (KdbColumnPersona) p).toList();
            List<KdbColumnPersona> mtmigitPersonas = mtmIgItTable.getColumnsList().stream().map(p -> (KdbColumnPersona) p).toList();
            List<KdbColumnPersona> mtmitinPersonas = mtmItInTable.getColumnsList().stream().map(p -> (KdbColumnPersona) p).toList();
            List<KdbColumnPersona> mtminvPersonas = mtmInIvTable.getColumnsList().stream().map(p -> (KdbColumnPersona) p).toList();

            String gigInsert = kdbContext.getUploadInsertGma(getGmaName(),getAiSchema(),mtmPgIgTable.getTableName(),
                    List.of(mtmPgIgTable.getColumnByName("input_group_id"), mtmPgIgTable.getColumnByName("parse_group_id")), false, mtmgigPersonas, false);
            String igitInsert = kdbContext.getUploadInsertGma(getGmaName(),getAiSchema(),mtmIgItTable.getTableName(),
                    List.of(mtmIgItTable.getColumnByName("input_type_id") , mtmIgItTable.getColumnByName("input_group_id")), false, mtmigitPersonas, false);
            String itinInsert = kdbContext.getUploadInsertGma(getGmaName(),getAiSchema(),mtmItInTable.getTableName(),
                    List.of(mtmItInTable.getColumnByName("input_name_id") , mtmItInTable.getColumnByName("input_type_id")), false, mtmitinPersonas, false);
            String invInsert = kdbContext.getUploadInsertGma(getGmaName(),getAiSchema(),mtmInIvTable.getTableName(),
                    List.of(mtmInIvTable.getColumnByName("input_name_id"), mtmInIvTable.getColumnByName("input_value_id")), false, mtminvPersonas, false);

            List<String> gigUpsertStrings = List.of(gigInsert);
            List<String> igitUpsertStrings = List.of(igitInsert);
            List<String> itinUpsertStrings = List.of(itinInsert);
            List<String> invUpsertStrings = List.of(invInsert);

            // Save parse tables
            kdbContext.saveAllGma(pgTable, parseGroupEntities, entityManager, parseGroupUpsertStrings);
            kdbContext.saveAllGma(igTable, parseInputGroupEntities, entityManager, parseInputGroupUpsertStrings);
            kdbContext.saveAllGma(itTable, parseInputTypeEntities, entityManager, parseInputTypeUpsertStrings);
            kdbContext.saveAllGma(inTable, parseInputNameEntities, entityManager, parseInputNameUpsertStrings);
            kdbContext.saveAllGma(ivTable, parseInputValueEntities, entityManager, parseInputValueUpsertStrings);

            // Save mtm tables
            kdbContext.saveAllGma(mtmPgIgTable, mtmgigEntities, entityManager, gigUpsertStrings);
            kdbContext.saveAllGma(mtmIgItTable, mtmigitEntities, entityManager, igitUpsertStrings);
            kdbContext.saveAllGma(mtmItInTable, mtmitinEntities, entityManager, itinUpsertStrings);
            kdbContext.saveAllGma(mtmInIvTable, mtminvEntities, entityManager, invUpsertStrings);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }






    // java
    public ParseFileMethod.UploadGroupTree getRagSchemaTree(AiMATemplate aiMATemplate, String uploadGroup, EntityManager entityManager) throws Exception {
        String baseQuery = String.format("""
        SELECT
          `pg`.`db_id` AS `db_id`,
          `pg`.`upload_group` AS `upload_group`,
          `pg`.`description` AS `upload_group_description`,

          `g`.`input_group` AS `input_group`,
          `g`.`description` AS `input_group_description`,

          `t`.`input_type` AS `input_type`,
          `t`.`description` AS `input_type_description`,

          `n`.`input_name` AS `input_name`,
          `n`.`description` AS `input_name_description`,

          `v`.`input_value` AS `input_value`,
          `v`.`description` AS `input_value_description`

        FROM `%s`.`%s` `pg`

        LEFT JOIN `%s` `pg_g`
          ON (`pg`.`db_id` = `pg_g`.`parse_group_id`)
        LEFT JOIN `%s` `g`
          ON (`pg_g`.`input_group_id` = `g`.`db_id`)

        LEFT JOIN `%s` `g_t`
          ON (`g`.`db_id` = `g_t`.`input_group_id`)
        LEFT JOIN `%s` `t`
          ON (`g_t`.`input_type_id` = `t`.`db_id`)

        LEFT JOIN `%s` `t_n`
          ON (`t`.`db_id` = `t_n`.`input_type_id`)
        LEFT JOIN `%s` `n`
          ON (`n`.`db_id` = `t_n`.`input_name_id`)

        LEFT JOIN `%s` `n_v`
          ON (`n`.`db_id` = `n_v`.`input_name_id`)
        LEFT JOIN `%s` `v`
          ON (`v`.`db_id` = `n_v`.`input_value_id`)
        where upload_group = '%s'""",
                getAiSchema(),
                aiMATemplate.getPgTableName(),

                aiMATemplate.getMtmPgIgTableName(),
                aiMATemplate.getIgTableName(),

                aiMATemplate.getMtmIgItTableName(),
                aiMATemplate.getItTableName(),

                aiMATemplate.getMtmItInTableName(),
                aiMATemplate.getInTableName(),

                aiMATemplate.getMtmInIvTableName(),
                aiMATemplate.getIvTableName(),

                uploadGroup
        );

        System.out.println(baseQuery);
        QueryResult queryResult = QueryResult.getQueryResultObj(baseQuery, entityManager);
        if (queryResult.getResultSize() == 0) {
            throw new RuntimeException("No data found for the specified upload group.");
        }
        System.out.println(queryResult.getData().toString());

        ParseFileMethod.UploadGroupTree tree = new ParseFileMethod.UploadGroupTree();

        for (int i = 0; i < queryResult.getResultSize(); i++) {

           ParseFileMethod.NameDescription uploadNd = new ParseFileMethod.NameDescription(
                    safeToString(queryResult.safeGetRow("col1", i)),
                    safeToString(queryResult.safeGetRow("col2", i))
            );
           ParseFileMethod.NameDescription inputGroupNd = new ParseFileMethod.NameDescription(
                    safeToString(queryResult.safeGetRow("col3", i)),
                    safeToString(queryResult.safeGetRow("col4", i))
            );
           ParseFileMethod.NameDescription inputTypeNd = new ParseFileMethod.NameDescription(
                    safeToString(queryResult.safeGetRow("col5", i)),
                    safeToString(queryResult.safeGetRow("col6", i))
            );
           ParseFileMethod.NameDescription inputNameNd = new ParseFileMethod.NameDescription(
                    safeToString(queryResult.safeGetRow("col7", i)),
                    safeToString(queryResult.safeGetRow("col8", i))
            );
           ParseFileMethod.NameDescription valueNd = new ParseFileMethod.NameDescription(
                    safeToString(queryResult.safeGetRow("col9", i)),
                    safeToString(queryResult.safeGetRow("col10", i))
            );

            boolean uploadGroupEmpty = uploadNd.getName() == null && uploadNd.getDescription() == null;
            boolean inputGroupEmpty = inputGroupNd.getName() == null && inputGroupNd.getDescription() == null;
            boolean inputTypeEmpty = inputTypeNd.getName() == null && inputTypeNd.getDescription() == null;
            boolean inputNameEmpty = inputNameNd.getName() == null && inputNameNd.getDescription() == null;
            if (uploadGroupEmpty || inputGroupEmpty || inputTypeEmpty || inputNameEmpty) {
                continue;
            }

            // ensure the name entry exists; add the value if present
            if (valueNd.getName() != null || valueNd.getDescription() != null) {
                tree.addValue(uploadNd, inputGroupNd, inputTypeNd, inputNameNd, valueNd);
            } else {
                // add an empty name entry so the tree contains the name even when no values exist
                tree.addName(uploadNd, inputGroupNd, inputTypeNd, inputNameNd, Collections.emptyList());
            }
        }

        System.out.println("uploadGroupToInputs size: " + (tree.getUploadGroupToInputs() == null ? 0 : tree.getUploadGroupToInputs().size()));
        System.out.println(tree.getUploadGroupToInputs());

        return tree;
    }


    private  String safeToString(Object o) {
        return o == null ? null : o.toString();
    }




    public void reloadRag(AiMATemplate aiMATemplate, List<File> files, AiRagSchemaJson json, String uploadGroup, EntityManager entityManager) throws Exception {
        //        @TODO maTemplate from the gma ma name... will have columns annotated with primary key and key. Primary key is neccessary for the query and so is neccessary for the upload to inputs, primary keys then  keys will be put into the storage file system.
        //        @TODO An error will signal if ay of the keys are blank or null and the upload will fail. This is to ensure that the data is queryable and usable for the ai.

        String fileId = UUID.randomUUID().toString();
//        System.out.println("here2");
//

        if(!aiMATemplate.checkKeys()) throw new RuntimeException("Missing key values for ma: "+aiMATemplate.getName());

        for (int i = 0; i < aiMATemplate.getTypes().length; i++) {


//            System.out.println("Processing type: "+aiMATemplate.getTypes()[i]);
            if (aiMATemplate.getTypes()[i].matches(".*raw")) {
                reloadRaw(aiMATemplate,files, json,uploadGroup, fileId,entityManager);
            }
            if (aiMATemplate.getTypes()[i].matches(".*inputs") && !aiMATemplate.getTypes()[i].matches(".*raw")) {

                reloadInputs(aiMATemplate, json,uploadGroup, fileId,entityManager);
            }
            if (aiMATemplate.getTypes()[i].matches(".*raw_inputs")) {
                reloadRawInputs(aiMATemplate,files,uploadGroup,fileId,entityManager);
            }





        }
    }

    private void reloadRaw(AiMATemplate rawMa, List<File> tempFiles, AiRagSchemaJson json,String uploadGroup,String fileId, EntityManager entityManager) throws Exception {
        AzureStorage azureStorage  = new AzureStorage();

        System.out.println("reload Raw");
        rawMa.getAllCols().forEach(c-> System.out.println("Column: "+c.getName()+" with value "+safeGetValue(c)));
        KDBContext kdbContext = KDBContext.KDB_CONTEXT;
        String fileType = UploadTypes.RAW;
        int fileSize = getFileSize(tempFiles);


        String mimeType = getMimeType(tempFiles);
        String ext = getExtensionFromMimeType(mimeType);
        String originalFileName = getOriginalFileName(tempFiles);

        String blobName = buildBlobName(rawMa,uploadGroup ,fileId, fileType,originalFileName);








        TableJson rawTable = getCurrentTableJson(rawMa.getSchema(),rawMa.getRawTableName());



        String rawJson= gson.toJson(json);

        int i = 0;
        for (File file : tempFiles) {
            i++;
            azureStorage.uploadReport(file, blobName + "_file_"+i+ext, containerName);

        }

        rawTable.setColEntityValue("db_id",fileId);
        rawTable.setColEntityValue("raw_input_json",rawJson);
        rawTable.setColEntityValue("upload_group",uploadGroup);
        rawTable.setColEntityValue("file_path",blobName);
        rawTable.setColEntityValue("extension_type",ext);
        rawTable.setColEntityValue("original_name",originalFileName);
        rawTable.setColEntityValue("stored_name",originalFileName);
        rawTable.setColEntityValue("mime_type",mimeType);
        rawTable.setColEntityValue("source_system","vyta_web");
        rawTable.setColEntityValue("file_size",fileSize);

        for(AiColumnTemplate cols: rawMa.getAllCols()){
            rawTable.setColEntityValue(cols.getName(),cols.getEntityValue().getValue());
            System.out.println("Setting value for column: "+cols.getName()+" with value "+cols.getEntityValue().getValue());
        }




        List<KdbColumnPersona> byCols = new ArrayList<>();
        byCols.add(rawTable.findCol("upload_group"));
        for(AiColumnTemplate col : rawMa.getAllKeys()){
            byCols.add(rawTable.findCol(col.getName()));
        }

        String insert = kdbContext.getUploadInsertGma(rawTable.getIdentifier().getGmaName(),rawTable.getIdentifier().getMaName(),rawTable.getName(),byCols,true,toPersonaJson(Arrays.stream(rawTable.getColumns()).toList()),true);
        String update = kdbContext.getUploadUpdateGma(rawTable.getIdentifier().getGmaName(),rawTable.getIdentifier().getMaName(),rawTable.getName(),byCols,true,toPersonaJson(rawTable.getColumnsList()));
        System.out.println("BLob name: "+blobName);
        System.out.println("upsert with insert: "+insert);
        System.out.println(insert);
//        System.out.println(update);

        kdbContext.saveAllGma(rawTable,List.of(rawTable),entityManager,List.of(insert));

    }

    private String buildBlobName(AiMATemplate rawMa,String uploadGroup, String fileId, String fileType, String originalFileName) {


        String folder = rawMa.getName();
        List<String> primaryKeys = rawMa.getPrimaryKeys().stream().map(p->getFolderValue(p.getEntityValue())).toList();
        List<String> keys = rawMa.getKeys().stream().map(p->getFolderValue(p.getEntityValue())).toList();

        return "%s/%s/%s/%s/%s/%s/%s ".formatted(
                folder,String.join("/",primaryKeys),uploadGroup,String.join("/",keys),fileId,fileType,originalFileName);


    }

    private String getFolderValue(EntityValue<?> entityValue) {
        System.out.println("Getting folder value for entity value with type "+entityValue.getType()+" and value "+entityValue.getValue());
        if(entityValue.getType().equals(String.class)){
            String value =  (String) entityValue.getValue();

            return value.replaceAll(folderRegex, "-");
        } else if(entityValue.getType().equals(Timestamp.class)) {
            Timestamp value =entityValue.getValue();

            LocalDateTime dateTime = value.toLocalDateTime();

            String year = String.valueOf(dateTime.getYear());
            String month = String.format("%02d", dateTime.getMonthValue());
            String day = String.format("%02d", dateTime.getDayOfMonth());
            return year+"/"+month+"/"+day;
        } else if (entityValue.getType().equals(LocalDateTime.class)){
            System.out.println();
            LocalDateTime value = entityValue.getValue();

            String year = String.valueOf(value.getYear());
            String month = String.format("%02d", value.getMonthValue());
            String day = String.format("%02d", value.getDayOfMonth());
            return year+"/"+month+"/"+day;
        } else {
            System.out.println("Unsupported type for folder value: "+entityValue.getType()+", defaulting to toString with regex replace");
            return String.valueOf(entityValue.getValue()).replaceAll(folderRegex, "-");
        }

    }

    ;

    private void reloadInputs(AiMATemplate rawMa,   AiRagSchemaJson json,String uploadGroup,String fileId,EntityManager entityManager) throws Exception {
        System.out.println("reload inputs");


        KDBContext kdbContext = KDBContext.KDB_CONTEXT;
        String fileType = UploadTypes.RAW_INPUTS;














//        TableJson rawTable = rawTables.get(0);
        TableJson inputsTable = getCurrentTableJson(rawMa.getSchema(),rawMa.getInputsTableName());


        for(AiColumnTemplate col : rawMa.getAllCols()){
            inputsTable.setColEntityValue(col.getName(),safeGetValue(col));
        }

        List<InputJson> inputJsons = buildInputsFromRaw(rawMa,json);

        Map<String, InputJson> deduped = new LinkedHashMap<>();
        for (InputJson ij : inputJsons) {
            String pk = ij.getUploadGroup() + "|"
                    + ij.getGroupName() + "|"
                    + ij.getTypeName() + "|"
                    + ij.getInputName() + "|"
                    + ij.getValue().getValue().getName();
            if (deduped.containsKey(pk)) {
                System.out.println("[WARN duplicate input] " + pk);
            }
            deduped.put(pk, ij); // last one wins, consistent with your DB rule
        }
        inputJsons = new ArrayList<>(deduped.values());



        System.out.println(inputJsons.size());
        List<SaveInterface> inputsList = new ArrayList<>();

        for(InputJson inputJson: inputJsons){
            TableJson inputs = gson.fromJson(
                    gson.toJson(getCurrentTableJson(rawMa.getSchema(), rawMa.getInputsTableName())),
                    TableJson.class
            );
            inputs.setColEntityValue("upload_group",inputJson.getUploadGroup());
            inputs.setColEntityValue("input_group",uploadGroup);
            inputs.setColEntityValue("input_type",inputJson.getTypeName());
            inputs.setColEntityValue("input_name",inputJson.getInputName());
            inputs.setColEntityValue("input_value",inputJson.getValue().getValue().getName());
            inputs.setColEntityValue("input_json",gson.toJson(inputJson));
            inputs.setColEntityValue("value", gson.toJson(inputJson.getValue()));
            inputs.setColEntityValue("raw_id",fileId);
//            inputs.setColEntityValue("db_embedding",Arrays.toString(rawMa.embedValue(gson.toJson(inputJson.getValue())).get()));

            for(AiColumnTemplate col: rawMa.getAllCols()){
                inputs.setColEntityValue(col.getName(),safeGetValue(col));
            }
            // BEFORE inputsList.add(inputs):


            inputsList.add(inputs);



        }

        TableJson inputs = getCurrentTableJson(rawMa.getSchema(),rawMa.getInputsTableName());

        List<KdbColumnPersona> core = toPersonaJson(List.of(
                inputs.getColumnByName("upload_group"),
                inputs.getColumnByName("input_name"),
                inputs.getColumnByName("input_type") ,
                inputs.getColumnByName("input_group"),
                inputs.getColumnByName("input_value")
        ));
        List<KdbColumnPersona> insertByCols = new ArrayList<>(core);

        for(AiColumnTemplate col: rawMa.getAllKeys()){
            insertByCols.add(inputs.findCol(col.getName()));
            System.out.println("Adding key column for insert: "+col.getName());
        }




        String inputInsert =  kdbContext.getUploadInsertGma(getGmaName(),rawMa.getSchema(),rawMa.getInputsTableName(),insertByCols, true);

        System.out.println("Upsert for inputs: "+inputInsert);
        kdbContext.saveAllGma(
                inputs,inputsList,
                entityManager,
                List.of(inputInsert));







    };

    private String getDescriptionPg(AiMATemplate aiMaTemplate , String groupName) throws Exception {

        TableJson tab = getCurrentTableJson(getAiSchema(),aiMaTemplate.getPgTableName());


        KDBContext kdbContext = KDBContext.KDB_CONTEXT;
        ColumnJson uploadGroupCol = tab.getColumnByName("upload_group").setQueryMatchStrings(List.of(groupName));
        List<KdbColumnPersona> cols = toPersonaJson(List.of(uploadGroupCol));

        QueryResult qr =   kdbContext.getQueryByColumns(
                getGmaName(),
                aiMaTemplate.getSchema(),
                tab.getName(),
                cols,
                cols

        );




        Object description =  qr.safeGetRow("upload_group",0);
        System.out.println("Description for upload group "+groupName+": "+description);
        return description != null ? description.toString() : "";


    }

    public  TableJson getCurrentTableJson(String schemaName, String tableName) throws Exception {
        KDBContext kdbContext = KDBContext.KDB_CONTEXT;
        GMAJson gma = kdbContext.getGmaByName(getGmaName());
        if (gma == null) {
            throw new Exception("GMA not registered: " + getGmaName() + ". Registered GMAs: " + gmaJsonMap.keySet());
        }

        Identifier identifier = new Identifier(getGmaName(), schemaName, tableName);

        TableJson t = gson.fromJson(
                gson.toJson(gma.getGmaObject(identifier, TableJson.class)),
                TableJson.class
        );

        if (t == null) {
            throw new Exception("Table not found in GMA: " + identifier + ". Ensure the MA/schema/table exist and were loaded into the GMA.");
        }
        return t;
    }


    private Map<String, String> getDescriptions(List<String> selectS, String from, String whereCol, String in, Connection connection) throws SQLException {

        String query = String.format("""
                Select
                    %s
               from
                    %s
                where
                    %s IN ('%s')
                 """,String.join(",",selectS),from,whereCol,in

        );
        QueryResult qr =  QueryResult.getQueryResultObj(query,connection);
        Map<String, String> descriptions = new HashMap<>();
        for(int i = 0; i < qr.getResultSize(); i++){
            descriptions.put(qr.safeGetRow("col1",i).toString(), qr.safeGetRow("col2",i).toString());
        }
        return  descriptions;

    }

    private List<InputJson> buildInputsFromRaw(AiMATemplate aiMATemplate, AiRagSchemaJson jsonSchema) throws SQLException {

//        AiRagSchemaJson jsonSchema = gson.fromJson(json, AiRagSchemaJson.class);
        List<InputJson> inputJsons = new ArrayList<>();

        Map<String, List<String>> groupNameMap = new HashMap<>();
        Map<String, List<String>> typeNameMap = new HashMap<>();
        Map<String, List<String>> inputNameMap = new HashMap<>();
        Map<String, List<String>> inputValueMap = new HashMap<>();

        // Build inputs and maps (assign a UUID to each InputJson)
        for (AiRagSchemaJson.Group group : jsonSchema.getGroups()) {
            for (AiRagSchemaJson.TypeEntry type : group.getTypes()) {
                for (AiRagSchemaJson.NameEntry input : type.getNames()) {
                    for (AiRagSchemaJson.ValueWrapper value : input.getValues()) {
                        if(value.getValue()==null||value.getValue().getValue()==null){
                            continue; // skip if value or value name is null
                        }
                        InputJson inputJson = new InputJson();

                        // assign stable uuid



                        inputJson.setUploadGroup(jsonSchema.getUploadName());
                        // leave uploadGroupDescription blank for now; will set in batch below
                        inputJson.setGroupName(group.getGroupName());
                        // leave groupNameDescription blank for now
                        inputJson.setTypeName(type.getTypeName());
                        // leave typeNameDescription blank for now
                        inputJson.setInputName(input.getInputName());
                        inputJson.setValueName(value.getValue().getName());

                        // leave inputNameDescription blank for now
                        inputJson.setValue(value);

                        inputJsons.add(inputJson);

                        // populate maps: map key -> list of uuids

                        groupNameMap.computeIfAbsent(group.getGroupName(), k -> new ArrayList<>()).add(inputJson.getUuid());
                        typeNameMap.computeIfAbsent(type.getTypeName(), k -> new ArrayList<>()).add(inputJson.getUuid());
                        inputNameMap.computeIfAbsent(input.getInputName(), k -> new ArrayList<>()).add(inputJson.getUuid());
                        inputValueMap.computeIfAbsent(value.getValue().getName(), k -> new ArrayList<>()).add(inputJson.getUuid());

                    }
                }
            }
        }

        // Build a lookup from uuid -> InputJson for fast updates
        Map<String, InputJson> uuidToInput = new HashMap<>(inputJsons.size());
        for (InputJson ij : inputJsons) {
            uuidToInput.put(ij.getUuid(), ij);
        }

        // open connection once and batch fetch descriptions
        try (Connection connection = DriverManager.getConnection(getJdbcUrl(), getJdbcUser(), getJdbcPassword())) {

            // upload group description (single value)
            String uploadDesc = "";
            try {
                uploadDesc = getDescriptionPg(aiMATemplate,jsonSchema.getUploadName());
            } catch (Exception ignored) {
            }

            // batch fetch group descriptions if any
            Map<String, String> groupDescriptions = new HashMap<>();
            if (!groupNameMap.isEmpty()) {
                TableJson currentTable = getCurrentTableJson(getAiSchema(),aiMATemplate.getIgTableName());

                List<String> selectS2 = List.of(currentTable.getColumnByName("input_group").getName(), currentTable.getColumnByName("description").getName());
                String from2 = currentTable.getMaName() + "." + currentTable.getTableName();
                String whereCol2 = currentTable.getColumnByName("input_group").getName();
                String in2 = String.join("', '", groupNameMap.keySet());
                groupDescriptions = getDescriptions(selectS2, from2, whereCol2, in2, connection);
            }

            // batch fetch type descriptions if any
            Map<String, String> typeDescriptions = new HashMap<>();
            if (!typeNameMap.isEmpty()) {
                TableJson currentTable = getCurrentTableJson(getAiSchema(),aiMATemplate.getItTableName());

                List<String> selectS2 = List.of(currentTable.getColumnByName("input_type").getName(), currentTable.getColumnByName("description").getName());
                String from2 = currentTable.getMaName() + "." + currentTable.getTableName();
                String whereCol2 = currentTable.getColumnByName("input_type").getName();
                String in2 = String.join("', '", groupNameMap.keySet());
                typeDescriptions = getDescriptions(selectS2, from2, whereCol2, in2, connection);
            }

            // batch fetch input name descriptions if any
            Map<String, String> inputDescriptions = new HashMap<>();
            if (!inputNameMap.isEmpty()) {
                TableJson currentTable = getCurrentTableJson(getAiSchema(),aiMATemplate.getInTableName());
                List<String> selectS2 = List.of(currentTable.getColumnByName("input_name").getName(), currentTable.getColumnByName("description").getName());
                String from2 = currentTable.getMaName() + "." + currentTable.getTableName();
                String whereCol2 = currentTable.getColumnByName("input_name").getName();
                String in2 = String.join("', '", groupNameMap.keySet());
                inputDescriptions = getDescriptions(selectS2, from2, whereCol2, in2, connection);
            }


            Map<String, String> valueDescriptions = new HashMap<>();

            if (!inputValueMap.isEmpty()) {
                TableJson currentTable = getCurrentTableJson(getAiSchema(),aiMATemplate.getIvTableName());
                List<String> selectS2 = List.of(currentTable.getColumnByName("input_value").getName(), currentTable.getColumnByName("description").getName());
                String from2 = currentTable.getMaName() + "." + currentTable.getTableName();
                String whereCol2 = currentTable.getColumnByName("input_value").getName();
                String in2 = String.join("', '", groupNameMap.keySet());
                valueDescriptions = getDescriptions(selectS2, from2, whereCol2, in2, connection);


            }


            // apply upload description to all inputs
            if (uploadDesc == null) uploadDesc = "";
            for (InputJson ij : inputJsons) {
                ij.setUploadGroupDescription(uploadDesc);
            }

            // apply group descriptions via uuid lists
            for (Map.Entry<String, List<String>> e : groupNameMap.entrySet()) {
                String groupName = e.getKey();
                String desc = groupDescriptions.getOrDefault(groupName, "");
                for (String uuid : e.getValue()) {
                    InputJson ij = uuidToInput.get(uuid);
                    if (ij != null) ij.setGroupNameDescription(desc);

                }
            }

            // apply type descriptions via uuid lists
            for (Map.Entry<String, List<String>> e : typeNameMap.entrySet()) {
                String typeName = e.getKey();
                String desc = typeDescriptions.getOrDefault(typeName, "");
                for (String uuid : e.getValue()) {
                    InputJson ij = uuidToInput.get(uuid);
                    if (ij != null) ij.setTypeNameDescription(desc);
                }
            }

            // apply input name descriptions via uuid lists
            for (Map.Entry<String, List<String>> e : inputNameMap.entrySet()) {
                String inputName = e.getKey();
                String desc = inputDescriptions.getOrDefault(inputName, "");
                for (String uuid : e.getValue()) {
                    InputJson ij = uuidToInput.get(uuid);
                    if (ij != null) ij.setInputNameDescription(desc);
                }
            }


            for (Map.Entry<String, List<String>> e : inputValueMap.entrySet()) {
                String inputName = e.getKey();
                String desc = valueDescriptions.getOrDefault(inputName, "");
                for (String uuid : e.getValue()) {
                    InputJson ij = uuidToInput.get(uuid);
                    if (ij != null) ij.setInputValueDescription(desc);
                    assert ij != null;
                    ij.getValue().getValue().setDescription(desc);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return inputJsons;
    }

    private void uploadChunks(AiMATemplate rawMa, String uploadGroup, String fileId, List<File> files,String blobname,EntityManager entityManager, String container,int chunkSize, int overlap) throws Exception {
        List<File> chunkFiles;
        AzureStorage azureStorage  = new AzureStorage();
        String mimeType = getMimeType(files);
        String ext = getExtensionFromMimeType(mimeType);
        String originalFileName = getOriginalFileName(files);

        TableJson rawInputsTable = getCurrentTableJson(rawMa.getSchema(),rawMa.getRawInputsTableName());
        KDBContext kdbContext = KDBContext.KDB_CONTEXT;

        long fileSize;
        Double[] embedding;
        String chunkName;

        if(ext.equals(".pdf")){

            chunkFiles =  chunkPdf(files);
        } else if (mimeType.startsWith("text/")){

            chunkFiles = chunkText(files,blobname,chunkSize,overlap);
        } else {
            chunkFiles = new ArrayList<>(files);
        }

        int i = 0;
        for(File f: chunkFiles) {
            i++;
            System.out.println("Chunk file: " + f.getName());
            chunkName = blobname+"_chunk_"+i+ext;
            fileSize = f.length();

            try {
                embedding = rawMa.embedFile(f, ext).get();
            }catch ( Exception e){
                e.printStackTrace();
                throw new RuntimeException("Failed to generate embedding for file chunk: "+f.getName());
            }
            String savePath = blobname + "_chunk_" + i + ext;
            try {
                azureStorage.uploadReport(f, savePath, container);
                Timestamp now = new Timestamp(System.currentTimeMillis());
                try{
                    rawInputsTable.setColEntityValue("upload_group",uploadGroup);
                    rawInputsTable.setColEntityValue("raw_id",fileId);
                    rawInputsTable.setColEntityValue("original_name",originalFileName);
                    rawInputsTable.setColEntityValue("stored_name",chunkName);
                    rawInputsTable.setColEntityValue("file_path",savePath);
                    rawInputsTable.setColEntityValue("mime_type",mimeType);
                    rawInputsTable.setColEntityValue("file_size",fileSize);
                    rawInputsTable.setColEntityValue("db_embedding",Arrays.toString(embedding) );
                    rawInputsTable.setColEntityValue("extension_type",ext);
                    rawInputsTable.setColEntityValue("source_system","vyta_web");
                    rawInputsTable.setColEntityValue("processed_at",now);
                    rawInputsTable.setColEntityValue("processing_status","processed");
                    rawInputsTable.setColEntityValue("checksum",0);

                    for(AiColumnTemplate cols: rawMa.getAllCols()){
                        rawInputsTable.setColEntityValue(cols.getName(),cols.getEntityValue().getValue());
                        System.out.println("Setting value for column: "+cols.getName()+" with value "+cols.getEntityValue().getValue());
                    }




                    List<KdbColumnPersona> byCols = new ArrayList<>();
                    byCols.add(rawInputsTable.findCol("upload_group"));
                    for(AiColumnTemplate col : rawMa.getAllKeys()){
                        byCols.add(rawInputsTable.findCol(col.getName()));
                    }

                    String insert = kdbContext.getUploadInsertGma(rawInputsTable.getIdentifier().getGmaName(),rawInputsTable.getIdentifier().getMaName(),rawInputsTable.getName(),byCols,true,toPersonaJson(Arrays.stream(rawInputsTable.getColumns()).toList()),true);
                    kdbContext.saveAllGma(rawInputsTable,List.of(rawInputsTable),entityManager,List.of(insert));


                }catch (Exception e){
                    e.printStackTrace();
                    throw new RuntimeException("Failed to save rawInputs table to db for file chunk: "+f.getName());
                }
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("Failed to upload file chunk to Azure Storage: "+f.getName());
            }



        }





    }

    private List<File> chunkText(List<File> files, String blobName, int chunkSize, int overlap) throws IOException {
        List<File> chunkFiles = new ArrayList<>();
        String mimeType = getMimeType(files);
        String ext = getExtensionFromMimeType(mimeType);
        int chunkNumber=0;
        for(File file :files){

            try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder window = new StringBuilder();
                int ch;

                while ((ch = reader.read()) != -1) {
                    window.append(ch);
                    if (window.length() >= chunkSize) {

                        chunkNumber++;

                        String chunk = window.toString();

                        String chunkName = blobName + "_" + chunkNumber + ext;

                        Path tempFile = Files.createTempFile("temp-" + System.currentTimeMillis(), ext);
                        try {
                            Files.writeString(tempFile, chunk);
                            System.out.println(chunk);
                            File temp = tempFile.toFile();
                            chunkFiles.add(temp);


                        } catch (Exception e) {

                            e.printStackTrace();
                            throw new RuntimeException("Failed to generate embedding for file chunk", e);
                        }
                        String overlapText =
                                chunk.substring(Math.max(0, chunk.length() - overlap));

                        window = new StringBuilder(overlapText);


                    }
                    // keep overflow


                }
                if (!window.isEmpty()) {
                    chunkNumber++;
                    String chunk = window.toString();
                    Path tempFile = Files.createTempFile("temp-" + System.currentTimeMillis(), ext);
                    Files.writeString(tempFile, chunk);
                    chunkFiles.add(tempFile.toFile());
                }
            }

        }
        return chunkFiles;
    }

    private List<File> chunkPdf(List<File> files) {
        List<File> chunkFiles = new ArrayList<>();
        for (File file : files) {
            try (PDDocument doc = Loader.loadPDF(file)) {
                for (int i = 0; i < doc.getNumberOfPages(); i++) {
                    try (PDDocument pageDoc = new PDDocument()) {
                        PDPage page = doc.getPage(i);
                        pageDoc.addPage(page);

                        String pageName = file.getName().replace(".pdf", "") + "_chunk_" + (i + 1) + ".pdf";
                        File pageFile = new File(file.getParent(), pageName);
                        pageDoc.save(pageFile);
                        chunkFiles.add(pageFile);

                        System.out.println("[chunkPdf] saved page " + (i + 1) + " -> " + pageFile.getName());
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException("Failed to chunk PDF: " + file.getName(), e);
            }
        }
        return chunkFiles;

    }


    private void reloadRawInputs(AiMATemplate rawMa,List<File> tempFiles,String uploadGroup,String fileId,EntityManager entityManager) throws Exception {
        final int chunkSize  = 800;
        final int overlap = 150;

        final String containerName = "test";
        System.out.println("reload Raw Inputs");


        String fileType = UploadTypes.RAW_INPUTS;
        String originalFileName = getOriginalFileName(tempFiles);
        String blobName = buildBlobName(rawMa,uploadGroup ,fileId, fileType,originalFileName);

        uploadChunks(rawMa, uploadGroup,fileId,tempFiles,blobName,entityManager,containerName,chunkSize,overlap);







    };

    private String  getOriginalFileName(List<File> files) {
        return String.join(" || ",
                files.stream()
                        .map(f -> {
                            String name = f.getName();
                            int dotIndex = name.lastIndexOf(".");
                            return (dotIndex == -1) ? name : name.substring(0, dotIndex);
                        })
                        .toList()
        );
    }

    private String getExtensionFromMimeType(String mimeType) {
        String ext = "";
        try {
            ext = switch (mimeType) {
                case "text/csv" -> ".csv";
                case "application/pdf" -> ".pdf";
                case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" -> ".xlsx";
                case "application/vnd.ms-excel" -> ".xls";
                case "text/plain" -> ".txt";
                case "application/json" -> ".json";
                case "application/zip" -> ".zip";

                default -> {
                    String guessedExt = Files.probeContentType(new File("dummy").toPath());
                    if (guessedExt != null) {
                        yield guessedExt;
                    } else {
                        yield "";
                    }
                }
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ext;
    }

    private String getMimeType(List<File> files) {
        Set<String> mimeTypes = new HashSet<>();
        for (File file : files) {
            try {
                String mimeType = Files.probeContentType(file.toPath());
                if (mimeType != null) {
                    mimeTypes.add(mimeType);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (mimeTypes.size() > 1) {
            throw new RuntimeException("Multiple MIME types detected: " + mimeTypes);
        }
        return mimeTypes.isEmpty() ? "application/octet-stream" : mimeTypes.iterator().next();
    }

    private int getFileSize(List<File> files) {
        return files.stream().mapToInt(f -> {
            try {
                return (int) Files.size(f.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).sum();
    }

    public void updateInputValues(AiMATemplate aiMATemplate, String tableName,EntityManager entityManager) throws Exception {
        Gson gson = new Gson();
        List<KdbColumnPersona> columns = new ArrayList<>();


        TableJson inputs  = getCurrentTableJson(aiMATemplate.getSchema(),tableName);




        columns.add(inputs.getColumnByName("value"));
        columns.add(inputs.getColumnByName("input_json"));

        String q = """
                SELECT
                    %s
                FROM %s
                """.formatted(String.join(",",columns.stream().map(KdbColumnPersona::getName).toList()),aiMATemplate.getSchema()+"."+tableName);

        QueryResult qr = QueryResult.getQueryResultObj(columns,q,entityManager);
        for(int i = 0; i < qr.getResultSize(); i++) {
            String valueString = (String) qr.safeGetRow("value", i);
            String inputJson = (String) qr.safeGetRow("input_json", i);

            AiRagSchemaJson.ValueWrapper value =  gson.fromJson(valueString, AiRagSchemaJson.ValueWrapper.class);
            InputJson inputJsonObj =  gson.fromJson(inputJson, InputJson.class);
            System.out.println(gson.toJson(inputJsonObj.getValue()));
            System.out.println("value for value at index "+i+" is "+valueString);
        }
    }

    // java
    @Transactional()
    public void updateValueNames(AiMATemplate aiMATemplate, String tableName,EntityManager entityManager) throws Exception {
        // lookup the parse table via KdbContextAi (match approach used in updateInputValues)
        TableJson parseInputValues = getCurrentTableJson(aiMATemplate.getSchema(), tableName);
        if (parseInputValues == null) {
            throw new IllegalStateException("Table client_med_parse_input_values not found for MA: " + aiMATemplate.getSchema());
        }

        // build the columns list from the TableJson (replace TAB_* usage)
        List<KdbColumnPersona> cols = new ArrayList<>();
        cols.add(parseInputValues.getColumnByName("input_value"));
        cols.add(parseInputValues.getColumnByName("db_id"));
        // add more columns if you need them in the query/result handling:
        // cols.add(parseInputValues.getColumnByName("description"));
        // cols.add(...);

        String tableRef = aiMATemplate.getMATemplate() + "." + tableName;

        String inputValueNameQuery = """
        select %s
        from %s
        where input_value like '%%bradshaw%%';
        """.formatted(
                String.join(",", cols.stream().map(KdbColumnPersona::getName).toList()),
                tableRef
        );

        QueryResult qr = QueryResult.getQueryResultObj(cols, inputValueNameQuery, entityManager);
        for (int i = 0; i < qr.getResultSize(); i++) {
            String valueName = (String) qr.safeGetRow("input_value", i);
            String replaceValueName = valueName.replace("_bradshaw", "");

            String dup = """
            SELECT * from %s where input_value = '%s';
            """.formatted(tableRef, replaceValueName);
            QueryResult dupCheck = QueryResult.getQueryResultObj(cols, dup, entityManager);
            if (dupCheck.getResultSize() > 0) {
                System.out.println("Duplicate replace value found for value name " + valueName + ", deleting the duplicates");
                String deleteDup = """
                delete from %s where input_value = '%s' and db_id not in (
                    select db_id from (
                        select db_id from %s where input_value = '%s' limit 1
                    ) as subquery
                );
                """.formatted(tableRef, valueName, tableRef, valueName);

                // existing client_med.inputs delete kept as-is (different schema/table)
                String deleteDup2 = """
                delete from client_med.inputs where input_value = '%s' and db_id not in (
                    select db_id from (
                        select db_id from client_med.inputs where input_value = '%s' limit 1
                    ) as subquery
                );
                """.formatted(valueName, valueName);

                entityManager.createNativeQuery(deleteDup).executeUpdate();
                entityManager.createNativeQuery(deleteDup2).executeUpdate();
                continue;
            }

            String update = """
            update %s set input_value = '%s' where input_value = '%s';
            """.formatted(tableRef, replaceValueName, valueName);

            String update2 = """
            update client_med.inputs set input_value = '%s' where input_value = '%s';
            """.formatted(replaceValueName, valueName);

            System.out.println(update);
            System.out.println(update2);
            System.out.println("\n\n\n");

            entityManager.createNativeQuery(update2).executeUpdate();
            entityManager.createNativeQuery(update).executeUpdate();
        }
    }

    private List<File> convertMultipartFilesToFiles(List<MultipartFile> files) {
        List<File> tempFiles = new ArrayList<>();


        try {
            if (files != null) {
                for (MultipartFile mf : files) {
                    if (mf == null || mf.isEmpty()) continue;

                    String original = mf.getOriginalFilename() != null ? mf.getOriginalFilename() : "uploaded_file.pdf";

                    String filename = java.nio.file.Paths.get(original).getFileName().toString();

                    int dot = filename.lastIndexOf('.');
                    String base = dot > 0 ? filename.substring(0, dot) : filename;
                    String ext = dot > 0 ? filename.substring(dot) : ".tmp";

                    String safeBase = base.replaceAll("[^A-Za-z0-9_\\-]", "_");
                    if (safeBase.length() < 3) safeBase = (safeBase + "___").substring(0, 3);
                    if (safeBase.length() > 50) safeBase = safeBase.substring(0, 50);

                    String uniqueId = java.util.UUID.randomUUID().toString();

                    Path tempPath = Files.createTempFile(safeBase + "-" + uniqueId + "-", ext);

                    File temp = tempPath.toFile();

                    try (InputStream is = mf.getInputStream()) {
                        Files.copy(is, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }

                    tempFiles.add(temp);
                }
            }
            return tempFiles;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // delete temp files on JVM exit
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                for (File f : tempFiles) {
                    try {
                        Files.deleteIfExists(f.toPath());
                    } catch (IOException e) {
                        // log error if needed
                    }
                }
            }));
        }
    }

//    @Todo register an embedding function for the ma in a map format to the ma
    // @Todo register a vector query function (getConfidentDbIds) to the ma



//    public VectorQueryResultWrapper queryRag(String gmaName, String maName, String query, double confidenceThreshold){
//        // @TODO get the maTemplate from the gmaName and maName. This will have the index columns and primary key columns needed for the query and for the upload to inputs. The query will be used to query the raw table and the results will be filtered by the confidence threshold. The db ids from the filtered results will then be used to query the inputs table for the values which will then be returned as a list of strings.
//        return null;
//    }






}
