package org.example.service.RAG.Parser.ParserGroupJson;//package org.example.service.RAG.Parser.ParserGroupJson;
//
//import com.google.gson.Gson;
//import com.google.gson.annotations.SerializedName;
//import jakarta.persistence.EntityManager;
//import okhttp3.*;
//import com.chipr.APP.schemas.gma.ai.MA_ai;
//import com.chipr.APP.schemas.gma.ai.mtm_parse_groups_parse_input_groups.TAB_mtm_parse_groups_parse_input_groups;
//import com.chipr.APP.schemas.gma.ai.mtm_parse_input_groups_parse_input_types.TAB_mtm_parse_input_groups_parse_input_types;
//import com.chipr.APP.schemas.gma.ai.mtm_parse_input_types_parse_input_names.TAB_mtm_parse_input_types_parse_input_names;
//import com.chipr.APP.schemas.gma.ai.parse_groups.TAB_parse_groups;
//import com.chipr.APP.schemas.gma.ai.parse_input_groups.TAB_parse_input_groups;
//import com.chipr.APP.schemas.gma.ai.parse_input_names.TAB_parse_input_names;
//import com.chipr.APP.schemas.gma.ai.parse_input_types.TAB_parse_input_types;
//import org.example.bank.OutputClassBank.EntityInterface;
//import org.example.bank.OutputClassBank.KdbColumnPersona;
//
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.*;
//
//import static org.example.bank.commonValues.ColumnConverter.toPersonaJson;
//import static org.example.bank.commonValues.ColumnConverter.toPersonaTemplate;
//
///**
// * Root model for the DEXA upload JSON
// */
//
//public class UploadGroupJson {
//
//
//
//
//
//
//    @SerializedName("upload_name")
//    private String uploadName;
//    @SerializedName("upload_description")
//    private String description;
//    // Store reportedAt as a String for JSON binding to avoid reflection into java.time internals.
//    // Public API still uses LocalDateTime via getter/setter.
//    @SerializedName("reported_at")
//    private String reportedAt;
//
//
//    private List<Group> groups;
//
//    // ---------- Getters / Setters ----------
//
//
//    public LocalDateTime getReportedAt() {
//        if (this.reportedAt == null) return null;
//        try {
//            return LocalDateTime.parse(this.reportedAt, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//        } catch (Exception e) {
//            try {
//                return LocalDateTime.parse(this.reportedAt);
//            } catch (Exception ex) {
//                return null;
//            }
//        }
//    }
//
//    public void setReportedAt(LocalDateTime reportedAt) {
//        if (reportedAt == null) this.reportedAt = null;
//        else this.reportedAt = reportedAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
//    }
//    // Allow raw string setter (used by Gson when it binds JSON directly)
//    public void setReportedAtString(String reportedAt) {
//        this.reportedAt = reportedAt;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getUploadName() {
//        return uploadName;
//    }
//
//    public void setUploadName(String uploadName) {
//        this.uploadName = uploadName;
//    }
//
//    public List<Group> getGroups() {
//        return groups;
//    }
//
//    public void setGroups(List<Group> groups) {
//        this.groups = groups;
//    }
//    @Override
//    public String toString() {
//        return "UploadGroupJson{" +
//                "uploadName=" + uploadName +
//                ", groups=" + groups +
//                '}';
//    }
//
//
//
//
////    public void dbInsert(EntityManager entityManager,String uploadName) throws Exception {
////        if (entityManager == null) {
////            throw new IllegalArgumentException("entityManager must not be null");
////        }
////
////        List<EntityInterface> parseGroupEntities = new ArrayList<>();
////        List<EntityInterface> parseInputGroupEntities = new ArrayList<>();
////        List<EntityInterface> parseInputTypeEntities = new ArrayList<>();
////        List<EntityInterface> parseInputNameEntities = new ArrayList<>();
////
////        List<String> parseGroupUpsertStrings = new ArrayList<>();
////        List<String> parseInputGroupUpsertStrings = new ArrayList<>();
////        List<String> parseInputTypeUpsertStrings = new ArrayList<>();
////        List<String> parseInputNameUpsertStrings = new ArrayList<>();
////
////        List<EntityInterface> mtmgigEntities = new ArrayList<>();
////        List<EntityInterface> mtmigitEntities = new ArrayList<>();
////        List<EntityInterface> mtmitinEntities = new ArrayList<>();
////
////        List<String> gigUpsertStrings = new ArrayList<>();
////        List<String> igitUpsertStrings = new ArrayList<>();
////        List<String> itinUpsertStrings = new ArrayList<>();
////
////        MA_ai ma_ai = new MA_ai();
////
////        // top-level upload group (one entry)
////        TAB_parse_groups parseGroupsTemplate = new TAB_parse_groups();
////        String uuid = java.util.UUID.randomUUID().toString();
////        parseGroupsTemplate.getCOL_upload_group().setEntityValue(uploadName);
////        parseGroupsTemplate.getCOL_description().setEntityValue(this.description);
////        parseGroupsTemplate.getCOL_db_id().setEntityValue(uuid);
////        parseGroupsTemplate.getCOL_is_active().setEntityValue("1");
////        parseGroupEntities.add(parseGroupsTemplate);
////
////        if (this.groups == null) {
////            ma_ai.saveAll(parseGroupsTemplate, parseGroupEntities, entityManager, parseGroupUpsertStrings);
////            return;
////        }
////
////        for (Group group : this.groups) {
////            if (group == null) continue;
////            String groupUuid = java.util.UUID.randomUUID().toString();
////
////            // input-group instance per loop with FK to parse_groups
////            TAB_parse_input_groups inputGroup = new TAB_parse_input_groups();
////            inputGroup.getCOL_input_group().setEntityValue(group.getGroupName());
////            inputGroup.getCOL_description().setEntityValue(group.getDescription());
////            inputGroup.getCOL_db_id().setEntityValue(groupUuid);
//////            inputGroup.getCOL_upload_group_id().setEntityValue(uuid);
////            inputGroup.getCOL_is_active().setEntityValue("1");
////            parseInputGroupEntities.add(inputGroup);
////
////            // create MTM row linking parse_groups <-> parse_input_groups
////            TAB_mtm_parse_groups_parse_input_groups mtmGig = new TAB_mtm_parse_groups_parse_input_groups();
////            mtmGig.getCOL_input_group_id().setEntityValue(groupUuid);
////            mtmGig.getCOL_parse_group_id().setEntityValue(uuid);
////            mtmgigEntities.add(mtmGig);
////
////            if (group.getTypes() == null) continue;
////            for (TypeEntry typeEntry : group.getTypes()) {
////                if (typeEntry == null) continue;
////                String typeUuid = java.util.UUID.randomUUID().toString();
////
////                // input-type instance per loop with FK to input_groups
////                TAB_parse_input_types inputType = new TAB_parse_input_types();
////                inputType.getCOL_input_type().setEntityValue(typeEntry.getTypeName());
////                inputType.getCOL_description().setEntityValue(typeEntry.getDescription());
//////                inputType.getCOL_input_group_id().setEntityValue(groupUuid);
////                inputType.getCOL_is_active().setEntityValue("1");
////                inputType.getCOL_db_id().setEntityValue(typeUuid);
////                parseInputTypeEntities.add(inputType);
////
////                // MTM row linking input_groups <-> input_types
////                TAB_mtm_parse_input_groups_parse_input_types mtmIgit = new TAB_mtm_parse_input_groups_parse_input_types();
////                mtmIgit.getCOL_input_type_id().setEntityValue(typeUuid);
////                mtmIgit.getCOL_input_group_id().setEntityValue(groupUuid);
////                mtmigitEntities.add(mtmIgit);
////
////                if (typeEntry.getNames() == null) continue;
////                for (NameEntry nameEntry : typeEntry.getNames()) {
////                    if (nameEntry == null) continue;
////                    String nameUuid = java.util.UUID.randomUUID().toString();
////
////                    // input-name instance per loop with FK to input_types
////                    TAB_parse_input_names inputName = new TAB_parse_input_names();
////                    inputName.getCOL_input_name().setEntityValue(nameEntry.getInputName());
////                    inputName.getCOL_db_id().setEntityValue(nameUuid);
//////                    inputName.getCOL_input_type_id().setEntityValue(typeUuid);
////                    inputName.getCOL_is_active().setEntityValue("1");
////                    inputName.getCOL_description().setEntityValue(nameEntry.getDescription());
////                    parseInputNameEntities.add(inputName);
////
////                    // MTM row linking input_types <-> input_names
////                    TAB_mtm_parse_input_types_parse_input_names mtmItin = new TAB_mtm_parse_input_types_parse_input_names();
////                    mtmItin.getCOL_input_name_id().setEntityValue(nameUuid);
////                    mtmItin.getCOL_input_type_id().setEntityValue(typeUuid);
////                    mtmitinEntities.add(mtmItin);
////                }
////            }
////        }
////
////        // prepare upsert strings for parse tables
////        String insertParseGroup = parseGroupsTemplate.getUploadInsert();
////        String updateParseGroup = parseGroupsTemplate.getUploadUpdate(List.of(parseGroupsTemplate.getCOL_upload_group()), false, toPersonaJson(parseGroupsTemplate.getColumns()));
////        parseGroupUpsertStrings.add(updateParseGroup);
////        parseGroupUpsertStrings.add(insertParseGroup);
////
////        String insertInputGroup = new TAB_parse_input_groups().getUploadInsert();
////        String updateInputGroup = new TAB_parse_input_groups().getUploadUpdate(List.of(new TAB_parse_input_groups().getCOL_input_group()), false, toPersonaJson(new TAB_parse_input_groups().getColumns()));
////        parseInputGroupUpsertStrings.add(updateInputGroup);
////        parseInputGroupUpsertStrings.add(insertInputGroup);
////
////        String insertInputType = new TAB_parse_input_types().getUploadInsert();
////        String updateInputType = new TAB_parse_input_types().getUploadUpdate(List.of(new TAB_parse_input_types().getCOL_input_type()), false, toPersonaJson(new TAB_parse_input_types().getColumns()));
////        parseInputTypeUpsertStrings.add(updateInputType);
////        parseInputTypeUpsertStrings.add(insertInputType);
////
////        String insertInputName = new TAB_parse_input_names().getUploadInsert();
////        String updateInputName = new TAB_parse_input_names().getUploadUpdate(List.of(new TAB_parse_input_names().getCOL_input_name()), false, toPersonaJson(new TAB_parse_input_names().getColumns()));
////        parseInputNameUpsertStrings.add(updateInputName);
////        parseInputNameUpsertStrings.add(insertInputName);
////
////        // save parse tables
////        ma_ai.saveAll(parseGroupsTemplate, parseGroupEntities, entityManager, parseGroupUpsertStrings);
////        ma_ai.saveAll(new TAB_parse_input_groups(), parseInputGroupEntities, entityManager, parseInputGroupUpsertStrings);
////        ma_ai.saveAll(new TAB_parse_input_types(), parseInputTypeEntities, entityManager, parseInputTypeUpsertStrings);
////        ma_ai.saveAll(new TAB_parse_input_names(), parseInputNameEntities, entityManager, parseInputNameUpsertStrings);
////
////        // prepare mtm insert strings (only inserts used previously)
////        TAB_mtm_parse_groups_parse_input_groups mtmgig = new TAB_mtm_parse_groups_parse_input_groups();
////        TAB_mtm_parse_input_groups_parse_input_types mtmigit = new TAB_mtm_parse_input_groups_parse_input_types();
////        TAB_mtm_parse_input_types_parse_input_names mtmitin = new TAB_mtm_parse_input_types_parse_input_names();
////        List<KdbColumnPersona> mtmgigPersonas = mtmgig.getColumns().stream().map(p-> (KdbColumnPersona) p).toList();
////        List<KdbColumnPersona> mtmigitPersonas = mtmigit.getColumns().stream().map(p-> (KdbColumnPersona) p).toList();
////        List<KdbColumnPersona> mtmitinPersonas = mtmitin.getColumns().stream().map(p-> (KdbColumnPersona) p).toList();
////
////
////        String gigInsert = mtmgig.getUploadInsert(List.of(mtmgig.getCOL_input_group_id(), mtmgig.getCOL_parse_group_id()), false,mtmgigPersonas,false);
////        String igitInsert = mtmigit.getUploadInsert(List.of(mtmigit.getCOL_input_type_id(), mtmigit.getCOL_input_group_id()), false,mtmigitPersonas,false);
////        String itinInsert = mtmitin.getUploadInsert(List.of(mtmitin.getCOL_input_name_id(), mtmitin.getCOL_input_type_id()), false,mtmitinPersonas,false);
////        gigUpsertStrings.add(gigInsert);
////        igitUpsertStrings.add(igitInsert);
////        itinUpsertStrings.add(itinInsert);
////
////
////
////        // save mtm tables
////        ma_ai.saveAll(mtmgig, mtmgigEntities, entityManager, gigUpsertStrings);
////        ma_ai.saveAll(mtmigit, mtmigitEntities, entityManager, igitUpsertStrings);
////        ma_ai.saveAll(mtmitin, mtmitinEntities, entityManager, itinUpsertStrings);
////    }
//
//
////@Transactional(transactionManager = "transactionManagerAi")
////public void dbInsert(EntityManager entityManager, String uploadName) throws Exception {
////    if (entityManager == null) {
////        throw new IllegalArgumentException("entityManager must not be null");
////    }
////
////    // Maps to track duplicates: key = entity identifier, value = uuid
////    Map<String, String> parseGroupMap = new HashMap<>();      // key: uploadName
////    Map<String, String> inputGroupMap = new HashMap<>();      // key: groupName
////    Map<String, String> inputTypeMap = new HashMap<>();       // key: groupName + "|" + typeName
////    Map<String, String> inputNameMap = new HashMap<>();       // key: groupName + "|" + typeName + "|" + inputName
////
////    List<EntityInterface> parseGroupEntities = new ArrayList<>();
////    List<EntityInterface> parseInputGroupEntities = new ArrayList<>();
////    List<EntityInterface> parseInputTypeEntities = new ArrayList<>();
////    List<EntityInterface> parseInputNameEntities = new ArrayList<>();
////
////    List<String> parseGroupUpsertStrings = new ArrayList<>();
////    List<String> parseInputGroupUpsertStrings = new ArrayList<>();
////    List<String> parseInputTypeUpsertStrings = new ArrayList<>();
////    List<String> parseInputNameUpsertStrings = new ArrayList<>();
////
////    List<EntityInterface> mtmgigEntities = new ArrayList<>();
////    List<EntityInterface> mtmigitEntities = new ArrayList<>();
////    List<EntityInterface> mtmitinEntities = new ArrayList<>();
////
////    List<String> gigUpsertStrings = new ArrayList<>();
////    List<String> igitUpsertStrings = new ArrayList<>();
////    List<String> itinUpsertStrings = new ArrayList<>();
////
////    MA_ai ma_ai = new MA_ai();
////
////    // top-level upload group (one entry)
////    TAB_parse_groups parseGroupsTemplate = new TAB_parse_groups();
////    String uuid = java.util.UUID.randomUUID().toString();
////    parseGroupsTemplate.getCOL_upload_group().setEntityValue(uploadName);
////    parseGroupsTemplate.getCOL_description().setEntityValue(this.description);
////    parseGroupsTemplate.getCOL_db_id().setEntityValue(uuid);
////    parseGroupsTemplate.getCOL_is_active().setEntityValue("1");
////    parseGroupEntities.add(parseGroupsTemplate);
////    parseGroupMap.put(uploadName, uuid); // Track this parse_group
////
////    if (this.groups == null) {
////        ma_ai.saveAll(parseGroupsTemplate, parseGroupEntities, entityManager, parseGroupUpsertStrings);
////        return;
////    }
////
////    for (Group group : this.groups) {
////        if (group == null) continue;
////
////        // Check if this input_group already exists
////        String inputGroupKey = group.getGroupName();
////        String groupUuid;
////
////        if (inputGroupMap.containsKey(inputGroupKey)) {
////            // Reuse existing UUID
////            groupUuid = inputGroupMap.get(inputGroupKey);
////        } else {
////            // Create new input_group
////            groupUuid = java.util.UUID.randomUUID().toString();
////
////            TAB_parse_input_groups inputGroup = new TAB_parse_input_groups();
////            inputGroup.getCOL_input_group().setEntityValue(group.getGroupName());
////            inputGroup.getCOL_description().setEntityValue(group.getDescription());
////            inputGroup.getCOL_db_id().setEntityValue(groupUuid);
////            inputGroup.getCOL_is_active().setEntityValue("1");
////            parseInputGroupEntities.add(inputGroup);
////            inputGroupMap.put(inputGroupKey, groupUuid); // Track this input_group
////        }
////
////        // create MTM row linking parse_groups <-> parse_input_groups
////        TAB_mtm_parse_groups_parse_input_groups mtmGig = new TAB_mtm_parse_groups_parse_input_groups();
////        mtmGig.getCOL_input_group_id().setEntityValue(groupUuid);
////        mtmGig.getCOL_parse_group_id().setEntityValue(uuid);
////        mtmgigEntities.add(mtmGig);
////
////        if (group.getTypes() == null) continue;
////
////        for (TypeEntry typeEntry : group.getTypes()) {
////            if (typeEntry == null) continue;
////
////            // Check if this input_type already exists
////            String inputTypeKey =  typeEntry.getTypeName();
////            String typeUuid;
////
////            if (inputTypeMap.containsKey(inputTypeKey)) {
////                // Reuse existing UUID
////                typeUuid = inputTypeMap.get(inputTypeKey);
////            } else {
////                // Create new input_type
////                typeUuid = java.util.UUID.randomUUID().toString();
////
////                TAB_parse_input_types inputType = new TAB_parse_input_types();
////                inputType.getCOL_input_type().setEntityValue(typeEntry.getTypeName());
////                inputType.getCOL_description().setEntityValue(typeEntry.getDescription());
////                inputType.getCOL_is_active().setEntityValue("1");
////                inputType.getCOL_db_id().setEntityValue(typeUuid);
////                parseInputTypeEntities.add(inputType);
////                inputTypeMap.put(inputTypeKey, typeUuid); // Track this input_type
////            }
////
////            // MTM row linking input_groups <-> input_types
////            TAB_mtm_parse_input_groups_parse_input_types mtmIgit = new TAB_mtm_parse_input_groups_parse_input_types();
////            mtmIgit.getCOL_input_type_id().setEntityValue(typeUuid);
////            mtmIgit.getCOL_input_group_id().setEntityValue(groupUuid);
////            mtmigitEntities.add(mtmIgit);
////
////            if (typeEntry.getNames() == null) continue;
////
////            for (NameEntry nameEntry : typeEntry.getNames()) {
////                if (nameEntry == null) continue;
////
////                // Check if this input_name already exists
////                String inputNameKey =  nameEntry.getInputName();
////                String nameUuid;
////
////                if (inputNameMap.containsKey(inputNameKey)) {
////                    // Reuse existing UUID
////                    nameUuid = inputNameMap.get(inputNameKey);
////                } else {
////                    // Create new input_name
////                    nameUuid = java.util.UUID.randomUUID().toString();
////
////                    TAB_parse_input_names inputName = new TAB_parse_input_names();
////                    inputName.getCOL_input_name().setEntityValue(nameEntry.getInputName());
////                    inputName.getCOL_db_id().setEntityValue(nameUuid);
////                    inputName.getCOL_is_active().setEntityValue("1");
////                    inputName.getCOL_description().setEntityValue(nameEntry.getDescription());
////                    parseInputNameEntities.add(inputName);
////                    inputNameMap.put(inputNameKey, nameUuid); // Track this input_name
////                }
////
////                // MTM row linking input_types <-> input_names
////                TAB_mtm_parse_input_types_parse_input_names mtmItin = new TAB_mtm_parse_input_types_parse_input_names();
////                mtmItin.getCOL_input_name_id().setEntityValue(nameUuid);
////                mtmItin.getCOL_input_type_id().setEntityValue(typeUuid);
////                mtmitinEntities.add(mtmItin);
////            }
////        }
////    }
////
////    // prepare upsert strings for parse tables
////    String insertParseGroup = parseGroupsTemplate.getUploadInsert();
////    String updateParseGroup = parseGroupsTemplate.getUploadUpdate(List.of(parseGroupsTemplate.getCOL_upload_group()), false, toPersonaJson(parseGroupsTemplate.getColumns()));
////    parseGroupUpsertStrings.add(updateParseGroup);
////    parseGroupUpsertStrings.add(insertParseGroup);
////
////    String insertInputGroup = new TAB_parse_input_groups().getUploadInsert();
////    String updateInputGroup = new TAB_parse_input_groups().getUploadUpdate(List.of(new TAB_parse_input_groups().getCOL_input_group()), false, toPersonaJson(new TAB_parse_input_groups().getColumns()));
////    parseInputGroupUpsertStrings.add(updateInputGroup);
////    parseInputGroupUpsertStrings.add(insertInputGroup);
////
////    String insertInputType = new TAB_parse_input_types().getUploadInsert();
////    String updateInputType = new TAB_parse_input_types().getUploadUpdate(List.of(new TAB_parse_input_types().getCOL_input_type()), false, toPersonaJson(new TAB_parse_input_types().getColumns()));
////    parseInputTypeUpsertStrings.add(updateInputType);
////    parseInputTypeUpsertStrings.add(insertInputType);
////
////    String insertInputName = new TAB_parse_input_names().getUploadInsert();
////    String updateInputName = new TAB_parse_input_names().getUploadUpdate(List.of(new TAB_parse_input_names().getCOL_input_name()), false, toPersonaJson(new TAB_parse_input_names().getColumns()));
////    parseInputNameUpsertStrings.add(updateInputName);
////    parseInputNameUpsertStrings.add(insertInputName);
////
////    // save parse tables
////    ma_ai.saveAll(parseGroupsTemplate, parseGroupEntities, entityManager, parseGroupUpsertStrings);
////    ma_ai.saveAll(new TAB_parse_input_groups(), parseInputGroupEntities, entityManager, parseInputGroupUpsertStrings);
////    ma_ai.saveAll(new TAB_parse_input_types(), parseInputTypeEntities, entityManager, parseInputTypeUpsertStrings);
////    ma_ai.saveAll(new TAB_parse_input_names(), parseInputNameEntities, entityManager, parseInputNameUpsertStrings);
////
////    // prepare mtm insert strings (only inserts used previously)
////    TAB_mtm_parse_groups_parse_input_groups mtmgig = new TAB_mtm_parse_groups_parse_input_groups();
////    TAB_mtm_parse_input_groups_parse_input_types mtmigit = new TAB_mtm_parse_input_groups_parse_input_types();
////    TAB_mtm_parse_input_types_parse_input_names mtmitin = new TAB_mtm_parse_input_types_parse_input_names();
////    List<KdbColumnPersona> mtmgigPersonas = mtmgig.getColumns().stream().map(p-> (KdbColumnPersona) p).toList();
////    List<KdbColumnPersona> mtmigitPersonas = mtmigit.getColumns().stream().map(p-> (KdbColumnPersona) p).toList();
////    List<KdbColumnPersona> mtmitinPersonas = mtmitin.getColumns().stream().map(p-> (KdbColumnPersona) p).toList();
////
////    String gigInsert = mtmgig.getUploadInsert(List.of(mtmgig.getCOL_input_group_id(), mtmgig.getCOL_parse_group_id()), false, mtmgigPersonas, false);
////    String igitInsert = mtmigit.getUploadInsert(List.of(mtmigit.getCOL_input_type_id(), mtmigit.getCOL_input_group_id()), false, mtmigitPersonas, false);
////    String itinInsert = mtmitin.getUploadInsert(List.of(mtmitin.getCOL_input_name_id(), mtmitin.getCOL_input_type_id()), false, mtmitinPersonas, false);
////    gigUpsertStrings.add(gigInsert);
////    igitUpsertStrings.add(igitInsert);
////    itinUpsertStrings.add(itinInsert);
////
////    // save mtm tables
////    ma_ai.saveAll(mtmgig, mtmgigEntities, entityManager, gigUpsertStrings);
////    ma_ai.saveAll(mtmigit, mtmigitEntities, entityManager, igitUpsertStrings);
////    ma_ai.saveAll(mtmitin, mtmitinEntities, entityManager, itinUpsertStrings);
////}
//
//
//
//
//    @Transactional(transactionManager = "transactionManagerAi")
//    public void dbInsert(EntityManager entityManager, String uploadName) throws Exception {
//        if (entityManager == null) {
//            throw new IllegalArgumentException("entityManager must not be null");
//        }
//
//        MA_ai ma_ai = new MA_ai();
//
//    /* =========================================================
//       PHASE 1 — BUILD CANONICAL KEY -> LOCAL DESCRIPTION MAPS
//       ========================================================= */
//        Map<String, String> parseGroupLocalDesc = new LinkedHashMap<>();
//        Map<String, String> inputGroupLocalDesc = new LinkedHashMap<>();
//        Map<String, String> inputTypeLocalDesc  = new LinkedHashMap<>();
//        Map<String, String> inputNameLocalDesc  = new LinkedHashMap<>();
//
//        // top-level upload group
//        parseGroupLocalDesc.put(uploadName, this.description);
//
//        if (this.groups != null) {
//            for (Group group : this.groups) {
//                if (group == null) continue;
//
//                inputGroupLocalDesc.merge(
//                        group.getGroupName(),
//                        group.getDescription(),
//                        (oldV, newV) -> rewriteDescription(oldV, newV)
//                );
//
//                if (group.getTypes() == null) continue;
//                for (TypeEntry type : group.getTypes()) {
//                    if (type == null) continue;
//
//                    inputTypeLocalDesc.merge(
//                            type.getTypeName(),
//                            type.getDescription(),
//                            (oldV, newV) -> rewriteDescription(oldV, newV)
//                    );
//
//                    if (type.getNames() == null) continue;
//                    for (NameEntry name : type.getNames()) {
//                        if (name == null) continue;
//
//                        inputNameLocalDesc.merge(
//                                name.getInputName(),
//                                name.getDescription(),
//                                (oldV, newV) -> rewriteDescription(oldV, newV)
//                        );
//                    }
//                }
//            }
//        }
//
//    /* =========================================================
//       PHASE 2 — RESOLVE UUIDS / DB VALUES VIA dbInsertChecker
//       ========================================================= */
//        Map<String, ParseValue> parseGroupDb = new LinkedHashMap<>();
//        Map<String, ParseValue> inputGroupDb = new LinkedHashMap<>();
//        Map<String, ParseValue> inputTypeDb  = new LinkedHashMap<>();
//        Map<String, ParseValue> inputNameDb  = new LinkedHashMap<>();
//
//        for (String key : parseGroupLocalDesc.keySet()) {
//            parseGroupDb.put(key, dbInsertChecker("parse_groups", key));
//        }
//        for (String key : inputGroupLocalDesc.keySet()) {
//            inputGroupDb.put(key, dbInsertChecker("parse_input_groups", key));
//        }
//        for (String key : inputTypeLocalDesc.keySet()) {
//            inputTypeDb.put(key, dbInsertChecker("parse_input_types", key));
//        }
//        for (String key : inputNameLocalDesc.keySet()) {
//            inputNameDb.put(key, dbInsertChecker("parse_input_names", key));
//        }
//
//    /* =========================================================
//       PHASE 3 — BUILD ENTITY OBJECTS (use combined DB + local descriptions)
//       ========================================================= */
//        List<EntityInterface> parseGroupEntities      = new ArrayList<>();
//        List<EntityInterface> parseInputGroupEntities = new ArrayList<>();
//        List<EntityInterface> parseInputTypeEntities  = new ArrayList<>();
//        List<EntityInterface> parseInputNameEntities  = new ArrayList<>();
//
//        for (Map.Entry<String, ParseValue> e : parseGroupDb.entrySet()) {
//            String key = e.getKey();
//            ParseValue pv = e.getValue();
//            TAB_parse_groups pg = new TAB_parse_groups();
//            pg.getCOL_upload_group().setEntityValue(key);
//            String dbDesc = pv == null ? null : pv.getDescription();
//            String localDesc = parseGroupLocalDesc.get(key);
//            pg.getCOL_description().setEntityValue(rewriteDescription(dbDesc, localDesc));
//            pg.getCOL_db_id().setEntityValue(pv == null ? null : pv.getDb_id());
//            pg.getCOL_is_active().setEntityValue("1");
//            parseGroupEntities.add(pg);
//        }
//
//        for (Map.Entry<String, ParseValue> e : inputGroupDb.entrySet()) {
//            String key = e.getKey();
//            ParseValue pv = e.getValue();
//            TAB_parse_input_groups ig = new TAB_parse_input_groups();
//            ig.getCOL_input_group().setEntityValue(key);
//            String dbDesc = pv == null ? null : pv.getDescription();
//            String localDesc = inputGroupLocalDesc.get(key);
//            ig.getCOL_description().setEntityValue(rewriteDescription(dbDesc, localDesc));
//            ig.getCOL_db_id().setEntityValue(pv == null ? null : pv.getDb_id());
//            ig.getCOL_is_active().setEntityValue("1");
//            parseInputGroupEntities.add(ig);
//        }
//
//        for (Map.Entry<String, ParseValue> e : inputTypeDb.entrySet()) {
//            String key = e.getKey();
//            ParseValue pv = e.getValue();
//            TAB_parse_input_types it = new TAB_parse_input_types();
//            it.getCOL_input_type().setEntityValue(key);
//            String dbDesc = pv == null ? null : pv.getDescription();
//            String localDesc = inputTypeLocalDesc.get(key);
//            it.getCOL_db_id().setEntityValue(pv == null ? null : pv.getDb_id());
//            it.getCOL_description().setEntityValue(rewriteDescription(dbDesc, localDesc));
//            it.getCOL_is_active().setEntityValue("1");
//            parseInputTypeEntities.add(it);
//        }
//
//        for (Map.Entry<String, ParseValue> e : inputNameDb.entrySet()) {
//            String key = e.getKey();
//            ParseValue pv = e.getValue();
//            TAB_parse_input_names in = new TAB_parse_input_names();
//            in.getCOL_input_name().setEntityValue(key);
//            String dbDesc = pv == null ? null : pv.getDescription();
//            String localDesc = inputNameLocalDesc.get(key);
//            in.getCOL_description().setEntityValue(rewriteDescription(dbDesc, localDesc));
//            in.getCOL_db_id().setEntityValue(pv == null ? null : pv.getDb_id());
//            in.getCOL_is_active().setEntityValue("1");
//            parseInputNameEntities.add(in);
//        }
//
//    /* =========================================================
//       PHASE 4 — BUILD MTM ROWS (use DB ids from ParseValue maps)
//       ========================================================= */
//        List<EntityInterface> mtmgigEntities = new ArrayList<>();
//        List<EntityInterface> mtmigitEntities = new ArrayList<>();
//        List<EntityInterface> mtmitinEntities = new ArrayList<>();
//
//        ParseValue pgPv = parseGroupDb.get(uploadName);
//        String parseGroupId = pgPv == null ? null : pgPv.getDb_id();
//
//        if (this.groups != null) {
//            for (Group group : this.groups) {
//                if (group == null) continue;
//                ParseValue groupPv = inputGroupDb.get(group.getGroupName());
//                String groupId = groupPv == null ? null : groupPv.getDb_id();
//
//                TAB_mtm_parse_groups_parse_input_groups gig = new TAB_mtm_parse_groups_parse_input_groups();
//                gig.getCOL_parse_group_id().setEntityValue(parseGroupId);
//                gig.getCOL_input_group_id().setEntityValue(groupId);
//                mtmgigEntities.add(gig);
//
//                if (group.getTypes() == null) continue;
//                for (TypeEntry type : group.getTypes()) {
//                    if (type == null) continue;
//                    ParseValue typePv = inputTypeDb.get(type.getTypeName());
//                    String typeId = typePv == null ? null : typePv.getDb_id();
//
//                    TAB_mtm_parse_input_groups_parse_input_types igit = new TAB_mtm_parse_input_groups_parse_input_types();
//                    igit.getCOL_input_group_id().setEntityValue(groupId);
//                    igit.getCOL_input_type_id().setEntityValue(typeId);
//                    mtmigitEntities.add(igit);
//
//                    if (type.getNames() == null) continue;
//                    for (NameEntry name : type.getNames()) {
//                        if (name == null) continue;
//                        ParseValue namePv = inputNameDb.get(name.getInputName());
//                        String nameId = namePv == null ? null : namePv.getDb_id();
//
//                        TAB_mtm_parse_input_types_parse_input_names itin = new TAB_mtm_parse_input_types_parse_input_names();
//                        itin.getCOL_input_type_id().setEntityValue(typeId);
//                        itin.getCOL_input_name_id().setEntityValue(nameId);
//                        mtmitinEntities.add(itin);
//                    }
//                }
//            }
//        }
//
//    /* =========================================================
//       PHASE 5 — SAVE (ORDER MATTERS) — build upsert/insert strings and call saveAll with 4 args
//       ========================================================= */
//
//        // parse groups upsert strings
//        TAB_parse_groups parseGroupsTemplate = new TAB_parse_groups();
//        String insertParseGroup = parseGroupsTemplate.getUploadInsert(
//                toPersonaTemplate(List.of(parseGroupsTemplate.getCOL_db_id())),false);
//        String updateParseGroup = parseGroupsTemplate.getUploadUpdate(
//                List.of(parseGroupsTemplate.getCOL_db_id()), false, toPersonaJson(parseGroupsTemplate.getColumns()));
//        List<String> parseGroupUpsertStrings = new ArrayList<>();
//        parseGroupUpsertStrings.add(updateParseGroup);
//        parseGroupUpsertStrings.add(insertParseGroup);
//
//        // input groups upsert strings
//        TAB_parse_input_groups inputGroupsTemplate = new TAB_parse_input_groups();
//        String insertInputGroup = inputGroupsTemplate.getUploadInsert(
//                toPersonaTemplate(List.of(inputGroupsTemplate.getCOL_db_id())),false
//        );
//        String updateInputGroup = inputGroupsTemplate.getUploadUpdate(
//                List.of(inputGroupsTemplate.getCOL_db_id()), false, toPersonaJson(inputGroupsTemplate.getColumns()));
//        List<String> parseInputGroupUpsertStrings = new ArrayList<>();
//        parseInputGroupUpsertStrings.add(updateInputGroup);
//        parseInputGroupUpsertStrings.add(insertInputGroup);
//
//        // input types upsert strings
//        TAB_parse_input_types inputTypesTemplate = new TAB_parse_input_types();
//        String insertInputType = inputTypesTemplate.getUploadInsert(
//                toPersonaTemplate(List.of(inputTypesTemplate.getCOL_db_id())),false
//        );
//        String updateInputType = inputTypesTemplate.getUploadUpdate(
//                List.of(inputTypesTemplate.getCOL_db_id()), false, toPersonaJson(inputTypesTemplate.getColumns()));
//        List<String> parseInputTypeUpsertStrings = new ArrayList<>();
//        parseInputTypeUpsertStrings.add(updateInputType);
//        parseInputTypeUpsertStrings.add(insertInputType);
//
//        // input names upsert strings
//        TAB_parse_input_names inputNamesTemplate = new TAB_parse_input_names();
//        String insertInputName = inputNamesTemplate.getUploadInsert(
//                toPersonaTemplate(List.of(inputNamesTemplate.getCOL_db_id())),false
//        );
//        String updateInputName = inputNamesTemplate.getUploadUpdate(
//                List.of(inputNamesTemplate.getCOL_db_id()), false, toPersonaJson(inputNamesTemplate.getColumns()));
//        List<String> parseInputNameUpsertStrings = new ArrayList<>();
//        parseInputNameUpsertStrings.add(updateInputName);
//        parseInputNameUpsertStrings.add(insertInputName);
//
//        // MTM upsert/inserts (inserts only)
//        TAB_mtm_parse_groups_parse_input_groups mtmGigTemplate = new TAB_mtm_parse_groups_parse_input_groups();
//        TAB_mtm_parse_input_groups_parse_input_types mtmIgitTemplate = new TAB_mtm_parse_input_groups_parse_input_types();
//        TAB_mtm_parse_input_types_parse_input_names mtmItinTemplate = new TAB_mtm_parse_input_types_parse_input_names();
//
//        List<KdbColumnPersona> mtmgigPersonas = mtmGigTemplate.getColumns().stream().map(p -> (KdbColumnPersona) p).toList();
//        List<KdbColumnPersona> mtmigitPersonas = mtmIgitTemplate.getColumns().stream().map(p -> (KdbColumnPersona) p).toList();
//        List<KdbColumnPersona> mtmitinPersonas = mtmItinTemplate.getColumns().stream().map(p -> (KdbColumnPersona) p).toList();
//
//        String gigInsert = mtmGigTemplate.getUploadInsert(
//                List.of(mtmGigTemplate.getCOL_input_group_id(), mtmGigTemplate.getCOL_parse_group_id()), false, mtmgigPersonas, false);
//        String igitInsert = mtmIgitTemplate.getUploadInsert(
//                List.of(mtmIgitTemplate.getCOL_input_type_id(), mtmIgitTemplate.getCOL_input_group_id()), false, mtmigitPersonas, false);
//        String itinInsert = mtmItinTemplate.getUploadInsert(
//                List.of(mtmItinTemplate.getCOL_input_name_id(), mtmItinTemplate.getCOL_input_type_id()), false, mtmitinPersonas, false);
//
//        List<String> gigUpsertStrings = List.of(gigInsert);
//        List<String> igitUpsertStrings = List.of(igitInsert);
//        List<String> itinUpsertStrings = List.of(itinInsert);
//
//        // Save parse tables
//        ma_ai.saveAll(parseGroupsTemplate, parseGroupEntities, entityManager, parseGroupUpsertStrings);
//        ma_ai.saveAll(inputGroupsTemplate, parseInputGroupEntities, entityManager, parseInputGroupUpsertStrings);
//        ma_ai.saveAll(inputTypesTemplate, parseInputTypeEntities, entityManager, parseInputTypeUpsertStrings);
//        ma_ai.saveAll(inputNamesTemplate, parseInputNameEntities, entityManager, parseInputNameUpsertStrings);
//
//        // Save mtm tables
//        ma_ai.saveAll(mtmGigTemplate, mtmgigEntities, entityManager, gigUpsertStrings);
//        ma_ai.saveAll(mtmIgitTemplate, mtmigitEntities, entityManager, igitUpsertStrings);
//        ma_ai.saveAll(mtmItinTemplate, mtmitinEntities, entityManager, itinUpsertStrings);
//    }
//
//    private String rewriteDescription(String oldDesc, String newDesc) {
//        if ((oldDesc == null || oldDesc.isBlank()) && (newDesc == null || newDesc.isBlank())) return null;
//        if (oldDesc == null || oldDesc.isBlank()) return newDesc;
//        if (newDesc == null || newDesc.isBlank()) return oldDesc;
//        // simple concatenation; adjust separator as desired
//        if (oldDesc.contains(newDesc)) return oldDesc;
//        return oldDesc + " | " + newDesc;
//    }
//
//
//    private String coalesce(String primary, String fallback) {
//        return primary != null ? primary : fallback;
//    }
//
//    private ParseValue dbInsertChecker(String tableName, String parse_name) throws SQLException {
//        TAB_parse_groups parseGroups = new TAB_parse_groups();
//        TAB_parse_input_groups parseInputGroups = new TAB_parse_input_groups();
//        TAB_parse_input_types parseInputTypes = new TAB_parse_input_types();
//        TAB_parse_input_names parseInputNames = new TAB_parse_input_names();
//
//        switch (tableName){
//            case "parse_groups":
//                return new ParseValue(
//                        parseGroups.getQueryByCols(
//                                List.of(parseGroups.getCOL_upload_group().setQueryMatchStrings(List.of(parse_name))),
//                                toPersonaTemplate(List.of(parseGroups.getCOL_description(),parseGroups.getCOL_db_id(),parseGroups.getCOL_upload_group()))
//                        ),
//                        parseGroups.getCOL_upload_group().getName()
//                );
//            case "parse_input_groups":
//                return new ParseValue(
//                        parseInputGroups.getQueryByCols(
//                                List.of(parseInputGroups.getCOL_input_group().setQueryMatchStrings(List.of(parse_name))),
//                                toPersonaTemplate(List.of(parseInputGroups.getCOL_description(),parseInputGroups.getCOL_db_id(),parseInputGroups.getCOL_input_group()))
//                        ),
//                        parseInputGroups.getCOL_input_group().getName()
//                );
//            case "parse_input_types":
//                return new ParseValue(
//                        parseInputTypes.getQueryByCols(
//                                List.of(parseInputTypes.getCOL_input_type().setQueryMatchStrings(List.of(parse_name))),
//                                toPersonaTemplate(List.of(parseInputTypes.getCOL_description(),parseInputTypes.getCOL_db_id(),parseInputTypes.getCOL_input_type()))
//                        ),
//                        parseInputTypes.getCOL_input_type().getName()
//                );
//            case "parse_input_names":
//                return new ParseValue(
//                        parseInputNames.getQueryByCols(
//                                List.of(parseInputNames.getCOL_input_name().setQueryMatchStrings(List.of(parse_name))),
//                                toPersonaTemplate(List.of(parseInputNames.getCOL_description(),parseInputNames.getCOL_db_id(),parseInputNames.getCOL_input_name()))
//                        ),
//                        parseInputNames.getCOL_input_name().getName()
//                );
//            default:
//                throw new IllegalArgumentException("Unknown table name for dbInsertChecker: " + tableName);
//        }
//    }
//
//    public String rewriteDescription(List<String> descriptions,String name){
//        String url = "http://127.0.0.1:5000/claude/simple_request";
//        OkHttpClient client = new OkHttpClient();
//        Gson gson = new Gson();
//        String userPrompt = "Combine the two definitions of "+name+" into a single description: " + String.join("; ", descriptions);
//        String systemPrompt = "You are a helpful assistant that combines multiple descriptions of a database column into one concise description. Focus on the most important details and avoid redundancy.";
//
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        Map<String, Object> payload = new HashMap<>();
//        payload.put("system_prompt", systemPrompt);
//        payload.put("user_prompt", userPrompt);
//        String jsonPayload = gson.toJson(payload);
//
//        RequestBody body = RequestBody.create(JSON, jsonPayload);
//
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) {
//                throw new IOException("Unexpected code " + response);
//            }
//            return response.body() != null ? response.body().string().trim() : null;
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to rewrite description", e);
//        }
//
//    }
//    private String combineDescriptions(String dbDescription, String jsonDescription, String name) {
//        if (dbDescription == null && jsonDescription == null) {
//            return null;
//        }
//        if (dbDescription == null) return jsonDescription;
//        if (jsonDescription == null) return dbDescription;
//        if (dbDescription.equals(jsonDescription)) return dbDescription;
//
//        // If they differ, call rewriteDescription
//        return rewriteDescription(List.of(dbDescription, jsonDescription), name);
//    }
//
////
////
////    private ParseValue dbInsertChecker(String tableName, String parse_name) throws SQLException {
////        TAB_parse_groups parseGroups = new TAB_parse_groups();
////        TAB_parse_input_groups parseInputGroups = new TAB_parse_input_groups();
////        TAB_parse_input_types parseInputTypes = new TAB_parse_input_types();
////        TAB_parse_input_names parseInputNames = new TAB_parse_input_names();
////
////        switch (tableName){
////            case "parse_groups":
////                return new ParseValue(parseGroups.getQueryByCols(List.of(parseGroups.getCOL_upload_group().setQueryMatchStrings(List.of(parse_name))),toPersonaTemplate(List.of(parseGroups.getCOL_description(),parseGroups.getCOL_db_id(),parseGroups.getCOL_upload_group()))),parseGroups.getCOL_upload_group().getName());
////            case "parse_input_groups":
////                return new ParseValue(parseInputGroups.getQueryByCols(List.of(parseInputGroups.getCOL_input_group().setQueryMatchStrings(List.of(parse_name))),toPersonaTemplate(List.of(parseInputGroups.getCOL_description(),parseInputGroups.getCOL_db_id(),parseInputGroups.getCOL_input_group()))),parseInputGroups.getCOL_input_group().getName());
////            case "parse_input_types":
////                return new ParseValue(parseInputTypes.getQueryByCols(List.of(parseInputTypes.getCOL_input_type().setQueryMatchStrings(List.of(parse_name))),toPersonaTemplate(List.of(parseInputTypes.getCOL_description(),parseInputTypes.getCOL_db_id(),parseInputTypes.getCOL_input_type()))),parseInputTypes.getCOL_input_type().getName());
////            case "parse_input_names":
////                return new ParseValue(parseInputNames.getQueryByCols(List.of(parseInputNames.getCOL_input_name().setQueryMatchStrings(List.of(parse_name))),toPersonaTemplate(List.of(parseInputNames.getCOL_description(),parseInputNames.getCOL_db_id(),parseInputNames.getCOL_input_name()))),parseInputNames.getCOL_input_name().getName());
////            default:
////                throw new IllegalArgumentException("Unknown table name for dbInsertChecker: " + tableName);
////        }
////
////    }
//
//
//    // ============================================================
//    // Nested Classes
//    // ============================================================
//
//    public static class Group {
//
//        @SerializedName("group_name")
//        private String groupName;
//
//        private String description;
//
//        private List<TypeEntry> types;
//
//        public String getGroupName() {
//            return groupName;
//        }
//
//        public void setGroupName(String groupName) {
//            this.groupName = groupName;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//        public List<TypeEntry> getTypes() {
//            return types;
//        }
//
//        public void setTypes(List<TypeEntry> types) {
//            this.types = types;
//        }
//        @Override
//        public String toString() {
//            return "Group{" +
//                    "groupName=" + groupName +
//                    ", description=" + description +
//                    ", types=" + types +
//                    '}';
//        }
//
//    }
//
//    // ------------------------------------------------------------
//
//    public static class TypeEntry {
//
//        @SerializedName("type_name")
//        private String typeName;
//
//        private String description;
//
//        private List<NameEntry> names;
//
//        public String getTypeName() {
//            return typeName;
//        }
//
//        public void setTypeName(String typeName) {
//            this.typeName = typeName;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//        public List<NameEntry> getNames() {
//            return names;
//        }
//
//        public void setNames(List<NameEntry> names) {
//            this.names = names;
//        }
//        @Override
//        public String toString() {
//            return "TypeEntry{" +
//                    "typeName=" + typeName +
//                    ", description=" + description +
//                    ", names=" + names +
//                    '}';
//        }
//
//    }
//
//    // ------------------------------------------------------------
//
//    public static class NameEntry {
//
//        @SerializedName("input_name")
//        private String inputName;
//        private String description;
//        private List<Value> values;
//
////        private List<ValueWrapper> values;
//
//
//        public List<Value> getValues() {
//            return values;
//        }
//
//        public void setValues(List<Value> values) {
//            this.values = values;
//        }
//
//        public String getDescription() {
//            return description;
//        }
//
//        public void setDescription(String description) {
//            this.description = description;
//        }
//
//        public String getInputName() {
//            return inputName;
//        }
//
//        public void setInputName(String inputName) {
//            this.inputName = inputName;
//        }
//
//        public NameEntry() {
//            this.values = List.of(new Value());
//        }
//
//        @Override
//        public String toString() {
//            return "NameEntry{" +
//                    "inputName=" + inputName +
//                    '}';
//        }
//
////
////        public List<ValueWrapper> getValues() {
////            return values;
////        }
////
////        public void setValues(List<ValueWrapper> values) {
////            this.values = values;
////        }
//    }
//
//    // ------------------------------------------------------------
//
//    public static class Value {
//
//        private MeasurementValue value;
//
//        private Double confidence;
//
//        private String notes;
//
//        public MeasurementValue getValue() {
//            return value;
//        }
//
//        public void setValue(MeasurementValue value) {
//            this.value = value;
//        }
//
//        public Double getConfidence() {
//            return confidence;
//        }
//
//        public void setConfidence(Double confidence) {
//            this.confidence = confidence;
//        }
//
//        public String getNotes() {
//            return notes;
//        }
//
//        public void setNotes(String notes) {
//            this.notes = notes;
//        }
//
//        public Value() {
//            this.value = new MeasurementValue();
//        }
//
//        @Override
//        public String toString() {
//            return "ValueWrapper{" +
//                    "value=" + value +
//                    ", confidence=" + confidence +
//                    ", notes=" + notes +
//                    '}';
//        }
//
//    }
//
//    // ------------------------------------------------------------
//
//    public static class MeasurementValue {
//
//        private String name;
//
//        /**
//         * Can be String or Number → use Object for Gson flexibility
//         */
//        private Object value;
//
//        private String unit;
//
//        @SerializedName("normalized_value")
//        private Object normalizedValue;
//
//        @SerializedName("normalized_unit")
//        private String normalizedUnit;
//
//        @SerializedName("measurement_type")
//        private String measurementType;
//
//        private String date;
//        private String time;
//        private String frequency;
//
//        private Source source;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public Object getValue() {
//            return value;
//        }
//
//        public void setValue(Object value) {
//            this.value = value;
//        }
//
//        public String getUnit() {
//            return unit;
//        }
//
//        public void setUnit(String unit) {
//            this.unit = unit;
//        }
//
//        public Object getNormalizedValue() {
//            return normalizedValue;
//        }
//
//        public void setNormalizedValue(Object normalizedValue) {
//            this.normalizedValue = normalizedValue;
//        }
//
//        public String getNormalizedUnit() {
//            return normalizedUnit;
//        }
//
//        public void setNormalizedUnit(String normalizedUnit) {
//            this.normalizedUnit = normalizedUnit;
//        }
//
//        public String getMeasurementType() {
//            return measurementType;
//        }
//
//        public void setMeasurementType(String measurementType) {
//            this.measurementType = measurementType;
//        }
//
//        public String getDate() {
//            return date;
//        }
//
//        public void setDate(String date) {
//            this.date = date;
//        }
//
//        public String getTime() {
//            return time;
//        }
//
//        public void setTime(String time) {
//            this.time = time;
//        }
//
//        public String getFrequency() {
//            return frequency;
//        }
//
//        public void setFrequency(String frequency) {
//            this.frequency = frequency;
//        }
//
//        public Source getSource() {
//            return source;
//        }
//
//        public void setSource(Source source) {
//            this.source = source;
//        }
//
//        @Override
//        public String toString() {
//            return "MeasurementValue{" +
//                    "name=" + name +
//                    ", value=" + value +
//                    ", unit=" + unit +
//                    ", normalizedValue=" + normalizedValue +
//                    ", normalizedUnit=" + normalizedUnit +
//                    ", measurementType=" + measurementType +
//                    ", date=" + date +
//                    ", time=" + time +
//                    ", frequency=" + frequency +
//                    ", source=" + source +
//                    '}';
//        }
//
//    }
//
//    // ------------------------------------------------------------
//
//    public static class Source {
//
//        private String type;
//        private String name;
//        private Integer page;
//
//        public String getType() {
//            return type;
//        }
//
//        public void setType(String type) {
//            this.type = type;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public Integer getPage() {
//            return page;
//        }
//
//        public void setPage(Integer page) {
//            this.page = page;
//        }
//        @Override
//        public String toString() {
//            return "Source{" +
//                    "type=" + type +
//                    ", name=" + name +
//                    ", page=" + page +
//                    '}';
//        }
//
//    }
//}
