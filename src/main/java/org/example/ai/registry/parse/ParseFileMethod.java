package org.example.ai.registry.parse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.ai.AiRagSchemaJson;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Function;

public abstract class ParseFileMethod {
    private ConcurrentHashMap<String, Future<?>> taskQueue;
    private ExecutorService executorService;
    protected Function<ParseFileObject,AiRagSchemaJson> parseFileFunction;

    protected ParseFileMethod(Function<ParseFileObject,AiRagSchemaJson> parseFileFunction){
        this.parseFileFunction = parseFileFunction;
        this.taskQueue = new ConcurrentHashMap<>();
        this.executorService = java.util.concurrent.Executors.newFixedThreadPool(1);
    }




    public String runParseFile(List<File> files, String uploadGroup, UploadGroupTree uploadGroupTree) throws Exception {
        String prompt = buildPrompt(uploadGroupTree);
        String uuid  = java.util.UUID.randomUUID().toString();

        ParseFileObject parseFileObject = new ParseFileObject();
        parseFileObject.setFiles(files);
        parseFileObject.setUploadGroup(uploadGroup);
        parseFileObject.setPrompt(prompt);

        Future<String> future = executorService.submit(() -> parseFileFunction.apply(parseFileObject).toString());
        taskQueue.put("parse_file_"+uuid, future);
        return future.get();
    }



    private String buildPrompt(UploadGroupTree uploadGroupTree) {
        Map<NameDescription, Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>>> uploadGroupToInputs =
                uploadGroupTree == null ? null : uploadGroupTree.getUploadGroupToInputs();

        if (uploadGroupToInputs == null || uploadGroupToInputs.isEmpty()) {
            System.out.println("uploadGroupToInputs is empty");
            throw new RuntimeException("No upload groups found.");
        }
        List<NameDescription> nameDescriptionsList = new ArrayList<>();
        List<NameDescription> uploadNdList = new ArrayList<>();

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        List<AiRagSchemaJson> uploads = new ArrayList<>();

        for (var uploadEntry : uploadGroupToInputs.entrySet()) {
            NameDescription uploadNd = uploadEntry.getKey();
            uploadNdList.add(uploadNd);

            AiRagSchemaJson upload = new AiRagSchemaJson();
            upload.setUploadName(uploadNd == null ? null : uploadNd.getName());

            List<AiRagSchemaJson.Group> groups = new ArrayList<>();
            Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>> groupsMap = uploadEntry.getValue();
            if (groupsMap != null) {
                for (var groupEntry : groupsMap.entrySet()) {
                    NameDescription groupNd = groupEntry.getKey();
                    nameDescriptionsList.add(groupNd);
                    AiRagSchemaJson.Group group = new AiRagSchemaJson.Group();
                    group.setGroupName(groupNd == null ? null : groupNd.getName());

                    List<AiRagSchemaJson.TypeEntry> types = new ArrayList<>();
                    Map<NameDescription, Map<NameDescription, List<NameDescription>>> typesMap = groupEntry.getValue();
                    if (typesMap != null) {
                        for (var typeEntry : typesMap.entrySet()) {
                            NameDescription typeNd = typeEntry.getKey();
                            nameDescriptionsList.add(typeNd);
                            AiRagSchemaJson.TypeEntry type = new AiRagSchemaJson.TypeEntry();
                            type.setTypeName(typeNd == null ? null : typeNd.getName());

                            List<AiRagSchemaJson.NameEntry> names = new ArrayList<>();
                            Map<NameDescription, List<NameDescription>> nameToValues = typeEntry.getValue();
                            if (nameToValues != null) {
                                for (var nameEntry : nameToValues.entrySet()) {
                                    NameDescription nameNd = nameEntry.getKey();
                                    nameDescriptionsList.add(nameNd);
                                    if (nameNd == null) continue;
                                    AiRagSchemaJson.NameEntry ne = new AiRagSchemaJson.NameEntry();
                                    ne.setInputName(nameNd.getName());

                                    List<NameDescription> valueList = nameEntry.getValue();
                                    List<AiRagSchemaJson.ValueWrapper> vals = new ArrayList<>();
                                    if (valueList != null) {
                                        for (NameDescription vnd : valueList) {
                                            if (vnd == null) continue;
                                            AiRagSchemaJson.ValueWrapper pv = new AiRagSchemaJson.ValueWrapper();
                                            AiRagSchemaJson.MeasurementValue mv = new AiRagSchemaJson.MeasurementValue();
                                            mv.setName(vnd.getName());
                                            pv.setValue(mv);
                                            vals.add(pv);
                                            nameDescriptionsList.add(vnd);
                                        }
                                    }
                                    ne.setValues(vals);
                                    names.add(ne);
                                }
                            }

                            type.setNames(names);
                            types.add(type);
                        }
                    }

                    group.setTypes(types);
                    groups.add(group);
                }
            }

            upload.setGroups(groups);
            uploads.add(upload);
        }

        if (uploads.isEmpty()) throw new RuntimeException("No upload groups found.");

        Object[] arr = new Object[2];
        arr[0] = gson.toJson(uploads.get(0));
        arr[1] = nameDescriptionsList;

        return getPrompt(arr, uploadNdList);
    }

    private String getPrompt(Object[] arr,List<NameDescription> uploadGroupND) {
        StringBuilder uploadDefStr = new StringBuilder();
        if(uploadGroupND!=null && !uploadGroupND.isEmpty()) {
            for(NameDescription nd:uploadGroupND){
                String s;
                if(nd.getDescription()==null || nd.getDescription().isEmpty()){
                    s = String.format("%s is the information that has data relating to %s ", nd.getName(), nd.getName());
                }
                else {
                    s = String.format("%s is the information that has data relating to %s ", nd.getName(), nd.getDescription());
                }
                uploadDefStr.append(s);
            }

        }
        String objective = "Parse the documents and populate the provided JSON schemas with the information that relates to it.\n"+uploadDefStr+"\nReturn the schema fully formed, filling values only when explicitly present in the document.";
        String jsonSchema = arr[0].toString();
        List<NameDescription> ndList = (List<NameDescription>) arr[1];
        StringBuilder groupingDef = new StringBuilder();

        System.out.println(jsonSchema+" is the json schema");

        for (NameDescription nd :  ndList){
            groupingDef.append(nd.toString());
        }



        String prompt = String.format(
                """
                        You are a medical document parsing engine. Your task is to extract structured data from the provided PDF or text and populate the JSON schema below exactly.

                        You must follow all rules strictly.

                        Objective

                        %s

                        Strict Rules

                        Populate fields only if explicitly present in the document.

                        If a value cannot be determined → use null.

                        If multiple metrics exist for the same anatomical site (e.g., BMD, T-Score, Z-Score), create multiple objects inside the values array.

                        Units must be preserved exactly as written (case, symbols, spacing).

                        Only populate normalized_value and normalized_unit if the conversion is obvious and unambiguous; otherwise set both to null.

                        Use only the following measurement_type values when appropriate:

                        density (e.g., g/cm²)

                        score (T-Score, Z-Score)

                        percentage (e.g., body fat %%)

                        ratio

                        quantity

                        If present or clearly implied, include these extra fields inside value:

                        "reference_range": { "min": null, "max": null, "unit": null },
                        "classification": null,
                        "change_from_baseline": null,
                        "clinical_interpretation": null




                        Otherwise omit them.

                        If any expected measurement or section is missing from the document, leave its values as null.

                        Populate source only if the document explicitly states source information (document name, page number, etc.).

                        notes should only include direct quotes from the document that are relevant to the measurement; Any relevant context must be preserved and inserted into notes.

                        Output Requirements (MANDATORY)

                        Output ONLY valid JSON

                        No markdown

                        No explanations

                        No comments

                        No trailing text

                        Preserve the hierarchy exactly

                        Maintain consistent snake_case naming

                        Grouping Definitions

                        Use these groupings strictly:
                        this shows what grouping name relates to what description, all groupings and values inside should be related to that description.

                        %s


                        Final JSON Schema to Populate

                        (Use this exact structure and field order)

                        reported_at should be populated only if a specific date is mentioned in the document about when the report was generated. Otherwise, set to null. Dates should always be in this format 'yyyy-MM-dd' times should always be in this format 'HH:mm:ss'.

                        %s

                        Final Validation Checklist (must satisfy)

                        All arrays and objects exist even if values are null

                        Multiple measurements produce multiple values entries

                        No invented data

                        No formatting changes

                        No field renaming

                        JSON parses successfully
                        
                        Before returning the JSON, verify:
                        - Every "names" array contains ALL its sibling inputs — never split across levels
                        - The JSON parses successfully with no orphaned objects
                        - No {"input_name": ...} object appears directly inside a "types" array

                        """,objective,groupingDef,jsonSchema);

        return prompt;
    }

    // java
    public static class UploadGroupTree {
        private Map<NameDescription, Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>>> uploadGroupToInputs;

        public UploadGroupTree() {
            this.uploadGroupToInputs = new LinkedHashMap<>();
        }

        public UploadGroupTree(Map<NameDescription, Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>>> uploadGroupToInputs) {
            this.uploadGroupToInputs = uploadGroupToInputs == null ? new LinkedHashMap<>() : uploadGroupToInputs;
        }

        public Map<NameDescription, Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>>> getUploadGroupToInputs() {
            return uploadGroupToInputs;
        }

        public void setUploadGroupToInputs(Map<NameDescription, Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>>> uploadGroupToInputs) {
            this.uploadGroupToInputs = uploadGroupToInputs == null ? new LinkedHashMap<>() : uploadGroupToInputs;
        }

        // Put a whole upload group (replaces existing entry for the same NameDescription key)
        public void putUploadGroup(NameDescription uploadGroupKey, Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>> groups) {
            if (uploadGroupKey == null) throw new IllegalArgumentException("uploadGroupKey cannot be null");
            uploadGroupToInputs.put(uploadGroupKey, groups == null ? new LinkedHashMap<>() : groups);
        }

        // Convenience: put by strings (creates or reuses upload-group key by name)
        public void putUploadGroup(String groupName, String groupDescription, Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>> groups) {
            NameDescription key = findOrCreateGroupKey(groupName, groupDescription);
            putUploadGroup(key, groups);
        }

        // Add or replace a group (the second-level key) inside an upload group
        public void addGroup(NameDescription uploadGroupKey, NameDescription groupKey, Map<NameDescription, Map<NameDescription, List<NameDescription>>> types) {
            if (uploadGroupKey == null || groupKey == null) throw new IllegalArgumentException("keys cannot be null");
            // groupsMap = uploadGroupToInputs.get(uploadGroupKey) (Map<GroupND, TypesMap>)
            Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>> groupsMap =
                    uploadGroupToInputs.computeIfAbsent(uploadGroupKey, k -> new LinkedHashMap<>());
            groupsMap.put(groupKey, types == null ? new LinkedHashMap<>() : types);
        }

        public void addGroup(String uploadName, String uploadDesc, String groupName, String groupDesc, Map<NameDescription, Map<NameDescription, List<NameDescription>>> types) {
            NameDescription upload = findOrCreateGroupKey(uploadName, uploadDesc);
            NameDescription group = findOrCreateKeyInMap(uploadGroupToInputs.get(upload), groupName, groupDesc);
            addGroup(upload, group, types == null ? new LinkedHashMap<>() : types);
        }

        // Add or replace a type (third-level) inside a specific group
        public void addType(NameDescription uploadGroupKey, NameDescription groupKey, NameDescription typeKey, Map<NameDescription, List<NameDescription>> names) {
            if (uploadGroupKey == null || groupKey == null || typeKey == null) throw new IllegalArgumentException("keys cannot be null");
            Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>> groupsMap =
                    uploadGroupToInputs.computeIfAbsent(uploadGroupKey, k -> new LinkedHashMap<>());
            Map<NameDescription, Map<NameDescription, List<NameDescription>>> typesMap =
                    groupsMap.computeIfAbsent(groupKey, k -> new LinkedHashMap<>());
            typesMap.put(typeKey, names == null ? new LinkedHashMap<>() : names);
        }

        public void addType(String uploadName, String uploadDesc, String groupName, String groupDesc, String typeName, String typeDesc, Map<NameDescription, List<NameDescription>> names) {
            NameDescription upload = findOrCreateGroupKey(uploadName, uploadDesc);
            Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>> groupsMap =
                    uploadGroupToInputs.computeIfAbsent(upload, k -> new LinkedHashMap<>());
            NameDescription grp = findOrCreateKeyInMap(groupsMap, groupName, groupDesc);
            addType(upload, grp, findOrCreateKeyInMap(groupsMap.get(grp), typeName, typeDesc), names == null ? new LinkedHashMap<>() : names);
        }

        // Add or replace a name (fourth-level) inside a specific type
        public void addName(NameDescription uploadGroupKey, NameDescription groupKey, NameDescription typeKey, NameDescription nameKey, List<NameDescription> values) {
            if (uploadGroupKey == null || groupKey == null || typeKey == null || nameKey == null) throw new IllegalArgumentException("keys cannot be null");
            Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>> groupsMap =
                    uploadGroupToInputs.computeIfAbsent(uploadGroupKey, k -> new LinkedHashMap<>());
            Map<NameDescription, Map<NameDescription, List<NameDescription>>> typesMap =
                    groupsMap.computeIfAbsent(groupKey, k -> new LinkedHashMap<>());
            Map<NameDescription, List<NameDescription>> namesMap =
                    typesMap.computeIfAbsent(typeKey, k -> new LinkedHashMap<>());
            namesMap.put(nameKey, values == null ? new ArrayList<>() : values);
        }

        public void addName(String uploadName, String uploadDesc, String groupName, String groupDesc, String typeName, String typeDesc, String name, String nameDesc, List<NameDescription> values) {
            NameDescription upload = findOrCreateGroupKey(uploadName, uploadDesc);
            Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>> groupsMap =
                    uploadGroupToInputs.computeIfAbsent(upload, k -> new LinkedHashMap<>());
            NameDescription grp = findOrCreateKeyInMap(groupsMap, groupName, groupDesc);
            Map<NameDescription, Map<NameDescription, List<NameDescription>>> typesMap =
                    groupsMap.computeIfAbsent(grp, k -> new LinkedHashMap<>());
            NameDescription type = findOrCreateKeyInMap(typesMap, typeName, typeDesc);
            Map<NameDescription, List<NameDescription>> namesMap =
                    typesMap.computeIfAbsent(type, k -> new LinkedHashMap<>());
            NameDescription n = findOrCreateKeyInMap(namesMap, name, nameDesc);
            namesMap.put(n, values == null ? new ArrayList<>() : values);
        }

        // Add a single value entry to a specific name
        public void addValue(NameDescription uploadGroupKey, NameDescription groupKey, NameDescription typeKey, NameDescription nameKey, NameDescription value) {
            if (uploadGroupKey == null || groupKey == null || typeKey == null || nameKey == null || value == null)
                throw new IllegalArgumentException("keys and value cannot be null");
            Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>> groupsMap =
                    uploadGroupToInputs.computeIfAbsent(uploadGroupKey, k -> new LinkedHashMap<>());
            Map<NameDescription, Map<NameDescription, List<NameDescription>>> typesMap =
                    groupsMap.computeIfAbsent(groupKey, k -> new LinkedHashMap<>());
            Map<NameDescription, List<NameDescription>> namesMap =
                    typesMap.computeIfAbsent(typeKey, k -> new LinkedHashMap<>());
            List<NameDescription> vals = namesMap.computeIfAbsent(nameKey, k -> new ArrayList<>());
            vals.add(value);
        }

        public void addValue(String uploadName, String uploadDesc, String groupName, String groupDesc, String typeName, String typeDesc, String name, String nameDesc, String valueName, String valueDesc) {
            NameDescription upload = findOrCreateGroupKey(uploadName, uploadDesc);
            Map<NameDescription, Map<NameDescription, Map<NameDescription, List<NameDescription>>>> groupsMap =
                    uploadGroupToInputs.computeIfAbsent(upload, k -> new LinkedHashMap<>());
            NameDescription grp = findOrCreateKeyInMap(groupsMap, groupName, groupDesc);
            Map<NameDescription, Map<NameDescription, List<NameDescription>>> typesMap = groupsMap.computeIfAbsent(grp, k -> new LinkedHashMap<>());
            NameDescription type = findOrCreateKeyInMap(typesMap, typeName, typeDesc);
            Map<NameDescription, List<NameDescription>> namesMap = typesMap.computeIfAbsent(type, k -> new LinkedHashMap<>());
            NameDescription n = findOrCreateKeyInMap(namesMap, name, nameDesc);
            List<NameDescription> vals = namesMap.computeIfAbsent(n, k -> new ArrayList<>());
            vals.add(new NameDescription(valueName, valueDesc));
        }

        // Helpers: find existing key by name, else create & register in the provided map
        private NameDescription findOrCreateGroupKey(String name, String description) {
            for (NameDescription k : uploadGroupToInputs.keySet()) {
                if (k != null && Objects.equals(k.getName(), name)) return k;
            }
            NameDescription k = new NameDescription(name, description);
            uploadGroupToInputs.put(k, new LinkedHashMap<>());
            return k;
        }

        private <V> NameDescription findOrCreateKeyInMap(Map<NameDescription, V> map, String name, String description) {
            if (map == null) return new NameDescription(name, description);
            for (NameDescription k : map.keySet()) {
                if (k != null && Objects.equals(k.getName(), name)) return k;
            }
            NameDescription k = new NameDescription(name, description);
            map.put(k, null);
            return k;
        }
    }




    public static class NameDescription {
        private String name;
        private String description;

        public NameDescription() {}

        public NameDescription(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        @Override
        public String toString() {
            return name + " relates to " + description + ";\n";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NameDescription that = (NameDescription) o;
            return Objects.equals(name, that.name) &&
                    Objects.equals(description, that.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, description);
        }
    }

    public static class ParseFileObject {

        String prompt;
         List<File> files;
         String uploadGroup;
        String mimeType;

        public String getMimeType() {
            return mimeType;
        }

        public void setMimeType(String mimeType) {
            this.mimeType = mimeType;
        }

        public String getPrompt() {
            return prompt;
        }

        public void setPrompt(String prompt) {
            this.prompt = prompt;
        }

        public List<File> getFiles() {
            return files;
        }

        public void setFiles(List<File> files) {
            this.files = files;
        }

        public String getUploadGroup() {
            return uploadGroup;
        }

        public void setUploadGroup(String uploadGroup) {
            this.uploadGroup = uploadGroup;
        }


    }



}
