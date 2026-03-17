package org.example.service.RAG.Parser;//package org.example.service.RAG.Parser;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import com.chipr.APP.schemas.gma.ai.parse_groups.TAB_parse_groups;
//import org.example.ClassOutputCreator.templates.ColumnTemplate;
//import org.example.bank.OutputClassBank.KdbColumnPersona;
//import org.example.bank.OutputClassBank.QueryResult;
//
//
//
//import org.example.bank.ai.NameDescription;
//import org.example.bank.ai.PromptForJsonSchema;
//import org.example.service.RAG.Parser.ParserGroupJson.UploadGroupJson;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//
//@Service
//public class AiUploadGroupInferenceService {
//    @PersistenceContext(unitName = "entityManagerFactoryAi")
//    EntityManager entityManager;
//
//    public String getGroupInference(){
//
//        return "";
//    }
//
//    public String getUploadGroups() throws SQLException {
//        TAB_parse_groups parseGroups =  new TAB_parse_groups();
//        List<KdbColumnPersona> getCols = List.of(parseGroups.getCOL_upload_group(),
//                                                 parseGroups.getCOL_description()
//                                                 );
//        List<ColumnTemplate> byCols = List.of(parseGroups.getCOL_is_active().setQueryMatchStrings(List.of("1")));
//
//        QueryResult q = parseGroups.getQueryByCols(byCols,getCols);
//
//        System.out.println(q.getData());
//        return q.getData().toString();
//    }
//
//    public String getPromptSchema(Boolean onlyActive,String uploadGroupName) {
//        String baseQuery = """
//                SELECT
//                    upload_group,
//                    upload_group_description,
//                    input_group,
//                    input_group_description,
//                    input_type,
//                    input_type_description,
//                    input_name,
//                    input_name_description
//                from ai.parse_groups_vw
//                where upload_group = '%s'""";
//
//        String q = String.format(baseQuery, uploadGroupName);
//        // optionally filter by active flag
////        if (Boolean.TRUE.equals(onlyActive)) {
////            q = q + " AND is_active = 1";
////        }
//
//        QueryResult queryResult = QueryResult.getQueryResultObj(q,entityManager);
//        System.out.println( queryResult.getData().toString());
//
//        // Map structure: uploadGroup -> inputGroup -> inputType -> list of inputNames
//        Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>> uploadGroupToInputs = new LinkedHashMap<>();
//
//        for(int i = 0; i<queryResult.getResultSize();i++){
//
//                NameDescription uploadGroup = new NameDescription(
//                        safeToString(queryResult.safeGetRow("col1",i)),
//                        safeToString(queryResult.safeGetRow("col2",i))
//                );
//                NameDescription inputGroup = new NameDescription(
//                        safeToString(queryResult.safeGetRow("col3",i)),
//                        safeToString(queryResult.safeGetRow("col4",i))
//                );
//                NameDescription inputType = new NameDescription(
//                        safeToString(queryResult.safeGetRow("col5",i)),
//                        safeToString(queryResult.safeGetRow("col6",i))
//                );
//                NameDescription inputName = new NameDescription(
//                        safeToString(queryResult.safeGetRow("col7",i)),
//                        safeToString(queryResult.safeGetRow("col8",i))
//                );
//
//                // skip rows that don't have at least an uploadGroup, inputGroup and inputType
//                boolean uploadGroupEmpty = uploadGroup.getName() == null && uploadGroup.getDescription() == null;
//                boolean inputGroupEmpty = inputGroup.getName() == null && inputGroup.getDescription() == null;
//                boolean inputTypeEmpty = inputType.getName() == null && inputType.getDescription() == null;
//                if (uploadGroupEmpty || inputGroupEmpty || inputTypeEmpty) {
//                    // nothing meaningful to add for this row
//                    continue;
//                }
//
//                // populate nested maps, preserving insertion order with LinkedHashMap
//                Map<NameDescription, Map<NameDescription, List<NameDescription>>> inputGroupMap =
//                        uploadGroupToInputs.computeIfAbsent(uploadGroup, k -> new LinkedHashMap<>());
//
//                Map<NameDescription, List<NameDescription>> typeMap =
//                        inputGroupMap.computeIfAbsent(inputGroup, k -> new LinkedHashMap<>());
//
//                List<NameDescription> names = typeMap.computeIfAbsent(inputType, k -> new ArrayList<>());
//
//                // avoid adding entries where both name and description are null
//                if (inputName.getName() != null || inputName.getDescription() != null) {
//                    names.add(inputName);
//                }
//
//        }
//
//        System.out.println("uploadGroupToInputs size: " + uploadGroupToInputs.size());
//        System.out.println(uploadGroupToInputs);
//        String prompt = buildPrompt(uploadGroupToInputs);
//
//
//        System.out.println( prompt);
//
//
//        return prompt;
//
//
//    }
//
//    private String buildPrompt(Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>> uploadGroupToInputs) {
//        if (uploadGroupToInputs == null || uploadGroupToInputs.isEmpty()) {
//            System.out.println("uploadGroupToInputs is empty");
//            throw new RuntimeException("No upload groups found.");
//        }
//        List<NameDescription> nameDescriptionsList = new ArrayList<>();
//        List<NameDescription> uploadNdList = new ArrayList<>();
//
//        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
//        List<PromptForJsonSchema> uploads = new ArrayList<>();
//
//
//        for (Map.Entry<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>> uploadEntry : uploadGroupToInputs.entrySet()) {
//            NameDescription uploadNd = uploadEntry.getKey();
//            uploadNdList.add(uploadNd);
//
//            PromptForJsonSchema upload = new PromptForJsonSchema();
//            upload.setUploadName(uploadNd == null ? null : uploadNd.getName());
////            upload.setDescription(uploadNd == null ? null : uploadNd.getDescription());
//
//            List<PromptForJsonSchema.Group> groups = new ArrayList<>();
//            Map<NameDescription, Map<NameDescription, List<NameDescription>>> groupsMap = uploadEntry.getValue();
//            if (groupsMap != null) {
//                for (Map.Entry<NameDescription, Map<NameDescription, List<NameDescription>>> groupEntry : groupsMap.entrySet()) {
//                    NameDescription groupNd = groupEntry.getKey();
//                    nameDescriptionsList.add(groupNd);
//                    PromptForJsonSchema.Group group = new PromptForJsonSchema.Group();
//                    group.setGroupName(groupNd == null ? null : groupNd.getName());
////                    group.setDescription(groupNd == null ? null : groupNd.getDescription());
//
//                    List<PromptForJsonSchema.TypeEntry> types = new ArrayList<>();
//                    Map<NameDescription, List<NameDescription>> typesMap = groupEntry.getValue();
//                    if (typesMap != null) {
//                        for (Map.Entry<NameDescription, List<NameDescription>> typeEntry : typesMap.entrySet()) {
//                            NameDescription typeNd = typeEntry.getKey();
//                            nameDescriptionsList.add(typeNd);
//                            PromptForJsonSchema.TypeEntry type = new PromptForJsonSchema.TypeEntry();
//                            type.setTypeName(typeNd == null ? null : typeNd.getName());
////                            type.setDescription(typeNd == null ? null : typeNd.getDescription());
//
//                            List<PromptForJsonSchema.NameEntry> names = new ArrayList<>();
//                            List<NameDescription> nameList = typeEntry.getValue();
//                            if (nameList != null) {
//                                for (NameDescription nameNd : nameList) {
//                                    nameDescriptionsList.add(nameNd);
//                                    if (nameNd == null) continue;
//                                    PromptForJsonSchema.NameEntry ne = new PromptForJsonSchema.NameEntry();
//                                    ne.setInputName(nameNd.getName());
////                                    ne.setDescription(nameNd.getDescription());
//                                    names.add(ne);
//                                }
//                            }
//
//                            type.setNames(names);
//                            types.add(type);
//                        }
//                    }
//
//                    group.setTypes(types);
//                    groups.add(group);
//                }
//            }
//
//            upload.setGroups(groups);
//            uploads.add(upload);
//        }
//
//        if (uploads.isEmpty()) throw new RuntimeException("No upload groups found.");
//
//        Object[] arr = new Object[2];
//        // if only 1 upload group
//        arr[0]=gson.toJson(uploads.get(0));
//        arr[1] = nameDescriptionsList;
//
//        return getPrompt(arr,uploadNdList);
//
//    }
//
//    private String getPrompt(Object[] arr,List<NameDescription> uploadGroupND) {
//        StringBuilder uploadDefStr = new StringBuilder();
//        if(uploadGroupND!=null && !uploadGroupND.isEmpty()) {
//            for(NameDescription nd:uploadGroupND){
//                String s;
//                if(nd.getDescription()==null || nd.getDescription().isEmpty()){
//                    s = String.format("%s is the information that has data relating to %s ", nd.getName(), nd.getName());
//                }
//                else {
//                    s = String.format("%s is the information that has data relating to %s ", nd.getName(), nd.getDescription());
//                }
//                uploadDefStr.append(s);
//            }
//
//        }
//        String objective = "Parse the documents and populate the provided JSON schemas with the information that relates to it.\n"+uploadDefStr+"\nReturn the schema fully formed, filling values only when explicitly present in the document.";
//        String jsonSchema = arr[0].toString();
//        List<NameDescription> ndList = (List<NameDescription>) arr[1];
//        StringBuilder groupingDef = new StringBuilder();
//
//        for (NameDescription nd :  ndList){
//         groupingDef.append(nd.toString());
//        }
//
//
//
//        String prompt = String.format(
//                """
//                        You are a medical document parsing engine. Your task is to extract structured data from the provided PDF or text and populate the JSON schema below exactly.
//
//                        You must follow all rules strictly.
//
//                        Objective
//
//                        %s
//
//                        Strict Rules
//
//                        Populate fields only if explicitly present in the document.
//
//                        If a value cannot be determined → use null.
//
//                        If multiple metrics exist for the same anatomical site (e.g., BMD, T-Score, Z-Score), create multiple objects inside the values array.
//
//                        Units must be preserved exactly as written (case, symbols, spacing).
//
//                        Only populate normalized_value and normalized_unit if the conversion is obvious and unambiguous; otherwise set both to null.
//
//                        Use only the following measurement_type values when appropriate:
//
//                        density (e.g., g/cm²)
//
//                        score (T-Score, Z-Score)
//
//                        percentage (e.g., body fat %%)
//
//                        ratio
//
//                        quantity
//
//                        If present or clearly implied, include these extra fields inside value:
//
//                        "reference_range": { "min": null, "max": null, "unit": null },
//                        "classification": null,
//                        "change_from_baseline": null,
//                        "clinical_interpretation": null
//
//
//
//
//                        Otherwise omit them.
//
//                        If any expected measurement or section is missing from the document, leave its values as null.
//
//                        Populate source only if the document explicitly states source information (document name, page number, etc.).
//
//                        notes should only include direct quotes from the document that are relevant to the measurement; Any relevant context must be preserved and inserted into notes.
//
//                        Output Requirements (MANDATORY)
//
//                        Output ONLY valid JSON
//
//                        No markdown
//
//                        No explanations
//
//                        No comments
//
//                        No trailing text
//
//                        Preserve the hierarchy exactly
//
//                        Maintain consistent snake_case naming
//
//                        Grouping Definitions
//
//                        Use these groupings strictly:
//                        this shows what grouping name relates to what description, all groupings and values inside should be related to that description.
//
//                        %s
//
//
//                        Final JSON Schema to Populate
//
//                        (Use this exact structure and field order)
//
//                        reported_at should be populated only if a specific date is mentioned in the document about when the report was generated. Otherwise, set to null. Dates should always be in this format 'yyyy-MM-dd' times should always be in this format 'HH:mm:ss'.
//
//                        %s
//
//                        Final Validation Checklist (must satisfy)
//
//                        All arrays and objects exist even if values are null
//
//                        Multiple measurements produce multiple values entries
//
//                        No invented data
//
//                        No formatting changes
//
//                        No field renaming
//
//                        JSON parses successfully
//
//                        """,objective,groupingDef,jsonSchema);
//
//        return prompt;
//    }
//
//    private static String safeToString(Object o) {
//        return o == null ? null : o.toString();
//    }
//
//
//    @Transactional(transactionManager = "transactionManagerAi")
//    public void postUploadGroup(UploadGroupJson uploadGroupJson,String uploadName) throws Exception {
//
//        uploadGroupJson.dbInsert(entityManager,uploadName);
//    }
//}
