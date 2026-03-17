package org.example.service.RAG.upload;//package org.example.service.RAG.upload;
//
//import com.azure.storage.blob.BlobClient;
//import com.azure.storage.blob.BlobContainerClient;
//import com.azure.storage.blob.BlobServiceClient;
//import com.azure.storage.blob.BlobServiceClientBuilder;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//
//import com.chipr.APP.schemas.gma.ai.parse_groups.TAB_parse_groups;
//import com.chipr.APP.schemas.gma.ai.parse_input_groups.TAB_parse_input_groups;
//import com.chipr.APP.schemas.gma.ai.parse_input_names.TAB_parse_input_names;
//import com.chipr.APP.schemas.gma.ai.parse_input_types.TAB_parse_input_types;
//import com.chipr.APP.schemas.gma.client.MA_client;
//import com.chipr.APP.schemas.gma.client.inputs.TAB_inputs;
//import com.chipr.APP.schemas.gma.client.raw_inputs.TAB_raw_inputs;
//import org.example.ClassOutputCreator.templates.ColumnTemplate;
//import org.example.bank.MultiFormatTimestampFormatter;
//import org.example.bank.OutputClassBank.EntityInterface;
//import org.example.bank.OutputClassBank.KdbColumnPersona;
//
//import org.example.bank.OutputClassBank.QueryResult;
//import org.example.bank.ai.PromptForJsonSchema;
//import org.example.bank.db.InputJson;
//
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.sql.*;
//import java.text.SimpleDateFormat;
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//import java.util.*;
//
//import static org.example.bank.commonValues.AppConfig.*;
//import static org.example.bank.commonValues.ColumnConverter.toPersonaJson;
//import static org.example.bank.commonValues.ColumnConverter.toPersonaTemplate;
//
//@Service
//public class UploadService {
//
//
//    @PersistenceContext(unitName = "entityManagerFactoryClient")
//    EntityManager entityManager;
//
//
//    public void writeReportsClients(List<File> files, Integer client_id, String groupName, String folder, String container,String uuid) throws IOException {
//        String connectionString = "DefaultEndpointsProtocol=https;AccountName=rawrs;AccountKey=1nvCH/COZCBd9BtzL51D/T31QSgYWL2KY5D/U7ZN7Ndjilic9+0fw69EeNzoa32tKZWTVzcwgJXz+ASt9Nbhbw==;EndpointSuffix=core.windows.net";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String formattedDate = sdf.format(java.util.Date.from(LocalDateTime.now().atZone(java.time.ZoneId.systemDefault()).toInstant()));
//
//        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
//                .connectionString(connectionString)
//                .buildClient();
//
//        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(container);
//        String curr = String.valueOf(System.currentTimeMillis());
//        for (int i = 0; i < files.size(); i++) {
//            File file = files.get(i);
//            if (file == null || !file.exists()) {
//                System.out.println("Skipping missing file at index " + i);
//                continue;
//            }
//
//
//            // build blob name with index (1-based) and original filename
//            // java
//            String name = file.getName();
//            String baseName = name.contains(".") ? name.substring(0, name.lastIndexOf('.')) : name;
//            String indexedFilename = baseName.equalsIgnoreCase("vector") || baseName.toLowerCase().startsWith("vector")
//                    ? "vector"
//                    : uuid + "_" + (i);
//
//            String blobName = folder + "/" + client_id + "/" + groupName + "/" + formattedDate + "/" + curr + "/" + indexedFilename;
//
//            BlobClient blobClient = containerClient.getBlobClient(blobName);
//
//            try (FileInputStream fis = new FileInputStream(file)) {
//                long length = file.length();
//                blobClient.upload(fis, length, true);
//
//                String contentType = Files.probeContentType(file.toPath());
//                if (contentType != null) {
//                    blobClient.setHttpHeaders(new com.azure.storage.blob.models.BlobHttpHeaders().setContentType(contentType));
//                }
//            }
//
//            System.out.println("File uploaded to blob storage at: " + blobName);
//        }
//    }
//
//
//    private LocalDateTime parseReportedAt(String reportedAt) {
//        DateTimeFormatter[] dateTimeFormatters = new DateTimeFormatter[]{
//                DateTimeFormatter.ofPattern("M/d/yyyy h:mm a"),       // 5/21/2024 10:31 AM
//                DateTimeFormatter.ofPattern("M/d/yyyy H:mm"),         // 5/21/2024 14:31
//                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),   // 2024-05-21 10:31:00
//                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"),// 2024-05-21T10:31:00
//                DateTimeFormatter.ofPattern("yyyy-MM-dd"),            // 2024-05-21
//                DateTimeFormatter.ofPattern("MM/dd/yyyy")             // 05/21/2024
//        };
//
//        LocalDateTime reportedAtDateTime = null;
//
//        for (DateTimeFormatter fmt : dateTimeFormatters) {
//            try {
//                reportedAtDateTime = LocalDateTime.parse(reportedAt, fmt);
//                break; // success
//            } catch (DateTimeParseException e) {
//                // try next
//                continue;
//            }
//        }
//
//// Handle case where only date (no time) was provided
//        if (reportedAtDateTime == null) {
//            for (DateTimeFormatter fmt : dateTimeFormatters) {
//                try {
//                    LocalDate date = LocalDate.parse(reportedAt, fmt);
//                    reportedAtDateTime = date.atStartOfDay(); // default time = 00:00
//                    break;
//                } catch (DateTimeParseException e) {
//                    continue;
//                }
//            }
//        }
//
//        if (reportedAtDateTime == null) {
//            throw new IllegalArgumentException("Unknown date format: " + reportedAt);
//        }
//
//        return reportedAtDateTime;
//    }
//
//
//    @Transactional(transactionManager = "transactionManagerClient")
//    public void reloadRawInputs(Integer clientId, String json, String uploadGroup, Double[] vector,String fileId,String fileName) throws Exception {
//
////        System.out.println("json "+json);
////        PromptForJsonSchema jsonSchema = new Gson().fromJson(json, PromptForJsonSchema.class);
////        if(jsonSchema == null || jsonSchema.getReportedAt() == null){
////            throw new IllegalArgumentException("Invalid JSON: missing reported_at field");
////        }
////        System.out.println("jsonSchema "+jsonSchema.getReportedAt());
//
//
//
//
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode root = mapper.readTree(json);
//
//        String reportedAt = root.get("reported_at").asText();
//        System.out.println("reportedAt "+reportedAt);
//
//
//        LocalDateTime reportedAtDateTime = parseReportedAt(reportedAt);
//
//        List<InputJson> inputJsons = buildInputsFromRawInput(clientId,json);
//
//        MA_client maClient = new MA_client();
//
//
////
//        String db_id = UUID.randomUUID().toString();
//        TAB_raw_inputs tabRawInputs = new TAB_raw_inputs();
//        tabRawInputs.getCOL_db_id().setEntityValue(db_id);
//        tabRawInputs.getCOL_client_id().setEntityValue(clientId);
//        tabRawInputs.getCOL_raw_input_json().setEntityValue(json);
//        tabRawInputs.getCOL_upload_group_name().setEntityValue(uploadGroup);
//
//        tabRawInputs.getCOL_reported_at().setEntityValue(reportedAtDateTime);
//        tabRawInputs.getCOL_raw_input_name().setEntityValue(
//                fileName
//        );
//        tabRawInputs.getCOL_db_embedding().setEntityValue(vector);
//        tabRawInputs.getCOL_file_id().setEntityValue(fileId);
//
//
//        List<KdbColumnPersona> byCols = new ArrayList<>();
//        List<KdbColumnPersona> updateCols = new ArrayList<>();
//
//        byCols.add(tabRawInputs.getCOL_client_id());
//        byCols.add(tabRawInputs.getCOL_reported_at());
//        byCols.add(tabRawInputs.getCOL_upload_group_name());
//
//
//        String insert = tabRawInputs.getUploadInsert(byCols, true);
//        String update = tabRawInputs.getUploadUpdate(byCols, true, toPersonaJson(tabRawInputs.getColumnsByGroupName("upload")));
//
//        List<String> upsertStrings = List.of(insert, update);
//
//
//
//        List<EntityInterface> uploadEntities = new ArrayList<>();
//        uploadEntities.add(tabRawInputs);
//
//        maClient.saveAll(tabRawInputs, uploadEntities, entityManager, upsertStrings);
//
//        List<EntityInterface> uploadInputs = new ArrayList<>();
//        Gson gson = new Gson();
//        TAB_inputs tab = new TAB_inputs();
//        System.out.println(inputJsons.size()+"size");
//
//        for (InputJson inputJson : inputJsons) {
//
//
//            TAB_inputs input = new TAB_inputs();
//            input.getCOL_client_id().setEntityValue(inputJson.getClientId());
//            input.getCOL_reported_at().setEntityValue(inputJson.getReportedAt());
//            input.getCOL_upload_group().setEntityValue(inputJson.getUploadGroup());
//            input.getCOL_input_group().setEntityValue(inputJson.getGroupName());
//            input.getCOL_input_type().setEntityValue(inputJson.getTypeName());
//            input.getCOL_input_name().setEntityValue(inputJson.getInputName());
//            input.getCOL_input_value().setEntityValue(inputJson.getValue().getValue().getName());
//            input.getCOL_reported_at().setEntityValue(MultiFormatTimestampFormatter.parseToTimestamp(reportedAt));
//            input.getCOL_input_json().setEntityValue(gson.toJson(inputJson));
//            String measurementDate = inputJson.getValue().getValue().getDate();
//            String measurementTime = inputJson.getValue().getValue().getTime();
//            String measurementDateTime = measurementDate!= null && measurementTime != null ? measurementDate + " " + measurementTime : measurementDate;
//
//            input.getCOL_measurement_date().setEntityValue(MultiFormatTimestampFormatter.parseToTimestamp(measurementDateTime));
//            input.getCOL_value().setEntityValue(gson.toJson(inputJson.getValue()));
//
//            input.getCOL_file_id().setEntityValue(fileId);
//
//            input.getCOL_db_embedding().setEmbedding(List.of(input.getCOL_input_json()));
//
//
//            uploadInputs.add(input);
//
//
//
//
//        }
//        System.out.println(uploadInputs.size());
//        List<KdbColumnPersona> insertByCols = toPersonaTemplate(List.of(tab.getCOL_client_id(),tab.getCOL_reported_at(),tab.getCOL_upload_group(),tab.getCOL_input_name(), tab.getCOL_input_type(),tab.getCOL_input_group(), tab.getCOL_input_value(), tab.getCOL_measurement_date(),tab.getCOL_input_value()));
//        String inputInsert = tab.getUploadInsert(insertByCols, true);
//
//        maClient.saveAll(tab,uploadInputs,entityManager,List.of(inputInsert));
//
//
//
//
//
////Table: raw_files_index
////
////Columns:
////db_id
////varchar(255) PK
////file_type
////varchar(255)
////vector_model
////varchar(255)
////db_insert_date
////timestamp
////db_update_date
////timestamp
////        TAB_raw_files_index filesIndex = new TAB_raw_files_index();
////        filesIndex.getCOL_vector_model().setEntityValue("v1");
//
//
//    }
//
//
//
//    private List<InputJson> buildInputsFromRawInput(Integer clientId, String json) throws SQLException {
//        Gson gson = new Gson();
//        PromptForJsonSchema jsonSchema = gson.fromJson(json, PromptForJsonSchema.class);
//
//        LocalDateTime localDateTime = parseReportedAt(jsonSchema.getReportedAt());
//        Timestamp reportedAtTimestamp = Timestamp.valueOf(localDateTime);
//
//        List<InputJson> inputJsons = new ArrayList<>();
//
//        Map<String, List<String>> groupNameMap = new HashMap<>();
//        Map<String, List<String>> typeNameMap = new HashMap<>();
//        Map<String, List<String>> inputNameMap = new HashMap<>();
//
//        // Build inputs and maps (assign a UUID to each InputJson)
//        for (PromptForJsonSchema.Group group : jsonSchema.getGroups()) {
//            for (PromptForJsonSchema.TypeEntry type : group.getTypes()) {
//                for (PromptForJsonSchema.NameEntry input : type.getNames()) {
//                    for (PromptForJsonSchema.Value value : input.getValues()) {
//                        if(value.getValue()==null||value.getValue().getValue()==null){
//                            continue; // skip if value or value name is null
//                        }
//                        InputJson inputJson = new InputJson();
//
//                        // assign stable uuid
//
//
//                        inputJson.setClientId(clientId);
//                        inputJson.setReportedAt(reportedAtTimestamp.toString());
//                        inputJson.setUploadGroup(jsonSchema.getUploadName());
//                        // leave uploadGroupDescription blank for now; will set in batch below
//                        inputJson.setGroupName(group.getGroupName());
//                        // leave groupNameDescription blank for now
//                        inputJson.setTypeName(type.getTypeName());
//                        // leave typeNameDescription blank for now
//                        inputJson.setInputName(input.getInputName());
//                        // leave inputNameDescription blank for now
//                        inputJson.setValue(value);
//
//                        inputJsons.add(inputJson);
//
//                        // populate maps: map key -> list of uuids
//                        groupNameMap.computeIfAbsent(group.getGroupName(), k -> new ArrayList<>()).add(inputJson.getUuid());
//                        typeNameMap.computeIfAbsent(type.getTypeName(), k -> new ArrayList<>()).add(inputJson.getUuid());
//                        inputNameMap.computeIfAbsent(input.getInputName(), k -> new ArrayList<>()).add(inputJson.getUuid());
//                    }
//                }
//            }
//        }
//
//        // Build a lookup from uuid -> InputJson for fast updates
//        Map<String, InputJson> uuidToInput = new HashMap<>(inputJsons.size());
//        for (InputJson ij : inputJsons) {
//            uuidToInput.put(ij.getUuid(), ij);
//        }
//
//        // open connection once and batch fetch descriptions
//        try (Connection connection = DriverManager.getConnection(getJdbcUrl(), getJdbcUser(), getJdbcPassword())) {
//
//            // upload group description (single value)
//            String uploadDesc = "";
//            try {
//                uploadDesc = getDescriptionUploadGroup(jsonSchema.getUploadName());
//            } catch (SQLException ignored) {}
//
//            // batch fetch group descriptions if any
//            Map<String, String> groupDescriptions = new HashMap<>();
//            if (!groupNameMap.isEmpty()) {
//                TAB_parse_input_groups inputGroups = new TAB_parse_input_groups();
//                List<String> selectS2 = List.of(inputGroups.getCOL_input_group().getName(), inputGroups.getCOL_description().getName());
//                String from2 = inputGroups.getMaName() + "." + inputGroups.getTableName();
//                String whereCol2 = inputGroups.getCOL_input_group().getName();
//                String in2 = String.join("', '", groupNameMap.keySet());
//                groupDescriptions = getDescriptions(selectS2, from2, whereCol2, in2, connection);
//            }
//
//            // batch fetch type descriptions if any
//            Map<String, String> typeDescriptions = new HashMap<>();
//            if (!typeNameMap.isEmpty()) {
//                TAB_parse_input_types inputTypes = new TAB_parse_input_types();
//                List<String> selectS3 = List.of(inputTypes.getCOL_input_type().getName(), inputTypes.getCOL_description().getName());
//                String from3 = inputTypes.getMaName() + "." + inputTypes.getTableName();
//                String whereCol3 = inputTypes.getCOL_input_type().getName();
//                String in3 = String.join("', '", typeNameMap.keySet());
//                typeDescriptions = getDescriptions(selectS3, from3, whereCol3, in3, connection);
//            }
//
//            // batch fetch input name descriptions if any
//            Map<String, String> inputDescriptions = new HashMap<>();
//            if (!inputNameMap.isEmpty()) {
//                TAB_parse_input_names inputNames = new TAB_parse_input_names();
//                List<String> selectS4 = List.of(inputNames.getCOL_input_name().getName(), inputNames.getCOL_description().getName());
//                String from4 = inputNames.getMaName() + "." + inputNames.getTableName();
//                String whereCol4 = inputNames.getCOL_input_name().getName();
//                String in4 = String.join("', '", inputNameMap.keySet());
//                inputDescriptions = getDescriptions(selectS4, from4, whereCol4, in4, connection);
//            }
//
//            // apply upload description to all inputs
//            if (uploadDesc == null) uploadDesc = "";
//            for (InputJson ij : inputJsons) {
//                ij.setUploadGroupDescription(uploadDesc);
//            }
//
//            // apply group descriptions via uuid lists
//            for (Map.Entry<String, List<String>> e : groupNameMap.entrySet()) {
//                String groupName = e.getKey();
//                String desc = groupDescriptions.getOrDefault(groupName, "");
//                for (String uuid : e.getValue()) {
//                    InputJson ij = uuidToInput.get(uuid);
//                    if (ij != null) ij.setGroupNameDescription(desc);
//                }
//            }
//
//            // apply type descriptions via uuid lists
//            for (Map.Entry<String, List<String>> e : typeNameMap.entrySet()) {
//                String typeName = e.getKey();
//                String desc = typeDescriptions.getOrDefault(typeName, "");
//                for (String uuid : e.getValue()) {
//                    InputJson ij = uuidToInput.get(uuid);
//                    if (ij != null) ij.setTypeNameDescription(desc);
//                }
//            }
//
//            // apply input name descriptions via uuid lists
//            for (Map.Entry<String, List<String>> e : inputNameMap.entrySet()) {
//                String inputName = e.getKey();
//                String desc = inputDescriptions.getOrDefault(inputName, "");
//                for (String uuid : e.getValue()) {
//                    InputJson ij = uuidToInput.get(uuid);
//                    if (ij != null) ij.setInputNameDescription(desc);
//                }
//            }
//        }
//
//        return inputJsons;
//    }
//
//
//
//
//
//    private Map<String, String> getDescriptions(List<String> selectS, String from, String whereCol, String in, Connection connection) throws SQLException {
//
//        String query = String.format("""
//                Select
//                    %s
//               from
//                    %s
//                where
//                    %s IN ('%s')
//                 """,String.join(",",selectS),from,whereCol,in
//
//                );
//        QueryResult qr =  QueryResult.getQueryResultObj(query,connection);
//        Map<String, String> descriptions = new HashMap<>();
//        for(int i = 0; i < qr.getResultSize(); i++){
//            descriptions.put(qr.safeGetRow("col1",i).toString(), qr.safeGetRow("col2",i).toString());
//        }
//        return  descriptions;
//
//    }
//
//
//    private String getDescriptionInsertGroup(String groupName) throws SQLException {
//        TAB_parse_input_groups tab = new TAB_parse_input_groups ();
//
//
//        QueryResult qr = tab.getQueryByCols(List.of(tab.getCOL_input_group().setQueryMatchStrings(List.of(groupName))),toPersonaTemplate(List.of(tab.getCOL_description())));
//        Object description =  qr.safeGetRow(tab.getCOL_description().getName(),0);
//        return description != null ? description.toString() : "";
//
//
//    }
//
//
//    private String getDescriptionUploadGroup(String groupName) throws SQLException {
//        TAB_parse_groups tab = new TAB_parse_groups();
//
//
//        QueryResult qr = tab.getQueryByCols(List.of(tab.getCOL_upload_group().setQueryMatchStrings(List.of(groupName))),toPersonaTemplate(List.of(tab.getCOL_description())));
//        Object description =  qr.safeGetRow(tab.getCOL_description().getName(),0);
//        return description != null ? description.toString() : "";
//
//
//    }
//
//    private String getDescriptionInsertType(String groupName) throws SQLException {
//        TAB_parse_input_types tab = new TAB_parse_input_types();
//
//
//        QueryResult qr = tab.getQueryByCols(List.of(tab.getCOL_input_type().setQueryMatchStrings(List.of(groupName))),toPersonaTemplate(List.of(tab.getCOL_description())));
//        Object description =  qr.safeGetRow(tab.getCOL_description().getName(),0);
//        return description != null ? description.toString() : "";
//
//
//    }
//
//    private String getDescriptionInsertName(String groupName) throws SQLException {
//        TAB_parse_input_names tab = new TAB_parse_input_names();
//
//
//        QueryResult qr = tab.getQueryByCols(List.of(tab.getCOL_input_name().setQueryMatchStrings(List.of(groupName))),toPersonaTemplate(List.of(tab.getCOL_description())));
//        Object description =  qr.safeGetRow(tab.getCOL_description().getName(),0);
//        return description != null ? description.toString() : "";
//
//
//    }
//}