package org.example.JsonBuilder.IDE;


import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.example.ClassOutputCreator.templates.KdbGma;
import org.example.ClassOutputCreator.templates.MAConfigTemplate;
import org.example.JsonBuilder.bank.ai_meta_cols.ColumnMeta;
import org.example.JsonBuilder.json.GMAJson;
import org.example.JsonBuilder.json.QueryGroupJson;
import org.example.JsonBuilder.json.ma.AiMAJson;
import org.example.JsonBuilder.json.ma.AirMAJson;
import org.example.JsonBuilder.json.ma.MAJson;
import org.example.JsonBuilder.json.ma.tables.*;
import org.example.bank.Annotations.*;
import org.example.bank.Annotations.ai.*;
import org.example.bank.AppConfig;
import org.example.bank.KdbConverter.ClassTypeAdapter;
import org.example.bank.KdbConverter.KdbConverter;
import org.example.bank.commonValues.Identifier;
import org.example.bank.commonValues.TableTypes;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static org.example.JsonBuilder.bank.ai_meta_cols.ColumnMetaGroups.*;
import static org.example.JsonBuilder.bank.ai_meta_cols.ColumnMetaRegistry.INPUTS_SCHEMA;

@Component
public class JsonBuilder {




    static String workingDir;

    static {
        try {
            workingDir = new File(".").getCanonicalPath() + File.separator;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(Class.class, new ClassTypeAdapter())
                .addSerializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        String name = f.getName();
                        return name.equals("columnGroupColumns") || name.equals("tableProcedures");
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .create();
    }

    static String toPakcage ;
    static {
        // Dynamically set `toPakcage` from an environment variable or configuration property
        toPakcage = System.getenv("JAVA_SOURCE_PATH");
        if (toPakcage == null || toPakcage.isEmpty()) {
            toPakcage = "src/main/java/"; // Default value for development
        }
    }


    public GMAJson buildJsonOfGma(KdbGma gma) throws InvocationTargetException, IllegalAccessException {

        GMAJson gmaJson = new GMAJson(gma);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        if (gma.getMa() == null || gma.getMa().isEmpty()) {
            gmaJson.setMa(new ArrayList<>());
            System.out.println("No MA configurations found in GMA config " + gma.getName());
            return gmaJson;
        }

        // Filter only MA entries whose resource folders actually exist in the classpath
        List<MAConfigTemplate> validMa = gma.getMa().stream()
                .filter(ma -> {
                    try {
                        // Your MA folder (coming from ma.getJavaFolderPath()) must match package path
                        // Example: org.example.org.example.inputs/schemas/employeealignment
                        String pathPattern = "classpath*:" + ma.getJavaFolderPath() + "/**/*.class";

                        Resource[] resources = resolver.getResources(pathPattern);
                        boolean exists = resources.length > 0;

                        if (!exists) {
                            System.out.println("MA folder NOT found on classpath, skipping: " + ma.getJavaFolderPath());
                        }

                        return exists;
                    } catch (IOException e) {
                        System.out.println("Error checking MA path: " + ma.getJavaFolderPath());
                        return false;
                    }
                })
                .toList();

        gma.setMa(validMa);

        // Now build MA JSON definitions
        List<MAJson> maJsons = new ArrayList<>();
        List<AirMAJson> airMAJsons = new ArrayList<>();
        List<AiMAJson> aiMAJsons = new ArrayList<>();
        for (MAConfigTemplate db : gma.getMa()) {
            // These are @KdbTable
            if (Objects.equals(db.getMaType(), "airtable")) {
                // if it contains a airtable table value then build an airtable ma json
                System.out.println("Building AIRTABLE MA JSON for: " + db.getName());
                Identifier identifier = new Identifier(gma.getName(),db.getName(),null);
                AirMAJson maJson = new AirMAJson(identifier, db);
                maJson.setTables(buildJsonOfAirTables(db.getJavaFolderPath(), identifier));

                airMAJsons.add(maJson);
            } else {
                // If it contains a default table value
                Identifier identifer = new Identifier(gma.getName(), db.getName(), null);
                MAJson maJson = new MAJson(identifer, db);
                maJson.setEntityManagerBeanName(db.getEntityManagerBeanName());

                maJson.setTables(buildJsonOfTables(db.getJavaFolderPath(), identifer));

                List<ProcedureJson> allProcedures = new ArrayList<>();
                for (TableJson table : maJson.getTables()) {
                    Collections.addAll(allProcedures, table.getTableProcedures());
                }

                maJson.setProcedures(allProcedures.toArray(new ProcedureJson[0]));
                maJsons.add(maJson);
            }
            // These are @KdbAi
            Identifier identifier = new Identifier(gma.getName(), AppConfig.getAiSchema(),null);
            AiMAJson aiMAJson = new AiMAJson(identifier, db);
            aiMAJson.setTables(buildJsonOfAiTables(db.getJavaFolderPath(), identifier));
            aiMAJsons.add(aiMAJson);

        }

        gmaJson.setMa(maJsons);
        gmaJson.setAirMa(airMAJsons);
        gmaJson.setAiMa(aiMAJsons);
        gmaJson.setQueryGroups(getQueryGroups(gmaJson));

        return gmaJson;
    }


    private QueryGroupJson[] getQueryGroups(GMAJson gmaJson) {
        Map<String, QueryGroupJson> groupsMap = new TreeMap<>();

        for (MAJson ma : gmaJson.getMa()) {
            for (TableJson table : ma.getTables()) {
                Map<String, List<BaseQueryJson>> queryMap = table.getQueriesMap();

                for (Map.Entry<String, List<BaseQueryJson>> entry : queryMap.entrySet()) {
                    String key = entry.getKey();
                    List<BaseQueryJson> queries = entry.getValue();

                    // If key is not present, create and add a new GroupJson
                    QueryGroupJson group = groupsMap.computeIfAbsent(key, k -> new QueryGroupJson(k));

                    // Add the queries or merge them as needed
                    group.addQueries(queries); // assuming GroupJson has a method like addQueries(List<BaseQueryJson>)
                }
            }
        }

        return groupsMap.values().toArray(new QueryGroupJson[0]);
    }

    public static List<AiTableComb> getAiClassesInPackage(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> aiClazzes = reflections.getTypesAnnotatedWith(KdbAiSchema.class).stream()
                .filter(clazz -> clazz.getPackageName().equals(packageName)) // <--- filter here
                .collect(Collectors.toSet());

        return getAiTableData(aiClazzes);
    }


    public static List<TableComb> getAllClassesInPackage(String packageName) {
        String packagePath;

        try {
            packagePath = packageName.replace('/', '.');
        } catch (Exception e) {
            System.out.println("Error replacing slashes In " + packageName);
            throw new RuntimeException(e);
        }


        System.out.println("Scanning package: " + packagePath);

        ConfigurationBuilder config = new ConfigurationBuilder()
                .forPackages(packagePath)
                .addScanners(Scanners.TypesAnnotated)
                .addClassLoaders(Thread.currentThread().getContextClassLoader());

        Reflections reflections = new Reflections(config);

        Set<Class<?>> clazzes = reflections.getTypesAnnotatedWith(KdbTable.class).stream()
                .filter(clazz -> clazz.getPackageName().equals(packagePath)) // <--- filter here
                .collect(Collectors.toSet());

        Set<Class<?>> clazzesRef = reflections.getTypesAnnotatedWith(KdbTableRef.class).stream()
                .filter(clazz -> clazz.getPackageName().equals(packagePath)) // <--- and here
                .collect(Collectors.toSet());

        Set<Class<?>> aiClazzesRef = reflections.getTypesAnnotatedWith(KdbAi.class).stream().filter(clazz->clazz.getPackageName().equals(packagePath)).collect(Collectors.toSet());


        return getTableData(clazzes, clazzesRef,aiClazzesRef);
    }

    public static List<AiTableComb> getAiTableData(Set<Class<?>> aiClazzes) {
        List<AiTableComb> tableCombs = new ArrayList<>();
        for (Class<?> clazz : aiClazzes) {

            KdbAi aiSchemaData = clazz.getAnnotation(KdbAi.class);
            for (String type : aiSchemaData.uploadTypes()) {
                AiTableComb tableComb = new AiTableComb();
                String description = "";
                switch (type) {
                    case "inputs" -> description = "This table contains the main input data for the AI model. Each record represents a single data point that the model will process. The fields in this table should capture all relevant features and attributes of the input data, such as numerical values, categorical labels, timestamps, and any other information necessary for the model to make accurate predictions or classifications.";
                    case "raw_inputs" -> description = "This table contains the raw, unprocessed input data for the AI model. Each record represents a single data point in its original form, exactly as collected from the source. The fields in this table may include various types of data, such as text, images, audio, or other unstructured formats, along with any associated metadata. This table serves as the initial repository for all incoming data before any cleaning, transformation, or feature extraction is performed.";
                    case "raw" -> description = "This table contains the raw, unprocessed data for the AI model. Each record represents a single data point in its original form, exactly as collected from the source. The fields in this table may include various types of data, such as text, images, audio, or other unstructured formats, along with any associated metadata. This table serves as the initial repository for all incoming data before any cleaning, transformation, or feature extraction is performed.";
                }
                tableComb.setName(type);
                tableComb.setDescription(description);
                setAiFieldsComb(tableComb, Arrays.asList(clazz.getDeclaredFields()));
                tableCombs.add(tableComb);





            }



        }
        return tableCombs;

    }


    public static List<TableComb> getTableData(Set<Class<?>> clazzes, Set<Class<?>> clazzesRef, Set<Class<?>> aiClazzesRef) {

        Map<String, String> aiTables = Map.ofEntries(
                Map.entry("%s_mtm_parse_groups_parse_input_groups",
                        "Maps parse groups to parse input groups, defining which input groups belong to each parse group."),

                Map.entry("%s_mtm_parse_input_groups_parse_input_types",
                        "Maps parse input groups to parse input types, defining which types are included in each group."),

                Map.entry("%s_mtm_parse_input_names_parse_input_values",
                        "Maps input names to their corresponding values, representing the relationship between identifiers and their data values."),

                Map.entry("%s_mtm_parse_input_types_parse_input_names",
                        "Maps input types to input names, defining which names fall under each type classification."),

                Map.entry("%s_parse_groups",
                        "Defines high-level parsing groups used to organize and categorize input processing workflows."),

                Map.entry("%s_parse_input_groups",
                        "Defines groups of related input fields that are processed together during parsing."),

                Map.entry("%s_parse_input_names",
                        "Stores the individual input field names used in parsing operations."),

                Map.entry("%s_parse_input_types",
                        "Defines the types or categories of inputs, such as text, numeric, or structured data."),

                Map.entry("%s_parse_input_values",
                        "Stores the actual values associated with input names, representing parsed or raw data entries.")
        );


        List<TableComb> tableCombs = new ArrayList<>();
        //Normal Kdb Table and Ref classes
        for (Class<?> clazz : clazzes) {
            TableComb tableComb = new TableComb();
            KdbTable tableData = clazz.getAnnotation(KdbTable.class);
            tableComb.setKdbTable(tableData);
            //set KdbTable table comb methods and fields



            setMethodsComb(tableComb, Arrays.asList(clazz.getDeclaredMethods()));
            setFieldsComb(tableComb, Arrays.asList(clazz.getDeclaredFields()));
//            System.out.println("checking for matches for "+tableData.name());

            //set the same KdbTable if a matching ref class is there
            Map<String, Set<Class<?>>> mapClazz = getClazzesByName(tableData.name(), clazzesRef);
            Set<Class<?>> matching = mapClazz.get("matching");
            for (Class<?> clazzMatched : matching) {
                setMethodsComb(tableComb, Arrays.asList(clazzMatched.getDeclaredMethods()));
                setFieldsComb(tableComb, Arrays.asList(clazzMatched.getDeclaredFields()));

            }

            clazzesRef = mapClazz.get("remaining");
            tableCombs.add(tableComb);


        }


        //KdbAi tables
        for(Class<?> clazz: aiClazzesRef) {

            //Raw tables
            KdbAi aiSchemaData = clazz.getAnnotation(KdbAi.class);
            for (String type : aiSchemaData.uploadTypes()) {
                TableComb tableComb = new TableComb();
                String description = "";
                switch (type) {
                    case "inputs" ->
                            description = "This table contains the main input data for the AI model. Each record represents a single data point that the model will process. The fields in this table should capture all relevant features and attributes of the input data, such as numerical values, categorical labels, timestamps, and any other information necessary for the model to make accurate predictions or classifications.";
                    case "raw_inputs" ->
                            description = "This table contains the raw, unprocessed input data for the AI model. Each record represents a single data point in its original form, exactly as collected from the source. The fields in this table may include various types of data, such as text, images, audio, or other unstructured formats, along with any associated metadata. This table serves as the initial repository for all incoming data before any cleaning, transformation, or feature extraction is performed.";
                    case "raw" ->
                            description = "This table contains the raw, unprocessed data for the AI model. Each record represents a single data point in its original form, exactly as collected from the source. The fields in this table may include various types of data, such as text, images, audio, or other unstructured formats, along with any associated metadata. This table serves as the initial repository for all incoming data before any cleaning, transformation, or feature extraction is performed.";
                }

                tableComb.setKdbTable(translateAiToTable(aiSchemaData, description, type));
                setFieldsCombFromAi(tableComb, Arrays.asList(clazz.getDeclaredFields()));
                //add fields and methods from ref table if exists
                Map<String, Set<Class<?>>> mapClazz = getClazzesByName(tableComb.getKdbTable().name(), clazzesRef);
                Set<Class<?>> matching = mapClazz.get("matching");
                for (Class<?> clazzMatched : matching) {
                    setMethodsComb(tableComb, Arrays.asList(clazzMatched.getDeclaredMethods()));
                    setFieldsComb(tableComb, Arrays.asList(clazzMatched.getDeclaredFields()));

                }

                clazzesRef = mapClazz.get("remaining");
                tableCombs.add(tableComb);


            }


            // MTM tables
            for (String table : aiTables.keySet()) {
                System.out.println("checking for ai table match for "+String.format(table, aiSchemaData.name()));
                TableComb tableComb = new TableComb();
                String tableName = String.format(table, aiSchemaData.name());
                System.out.println(tableName);



                tableComb.setKdbTable(translateAiToTableSchema(aiSchemaData, aiTables.get(table), tableName));
                setFieldsCombFromAi(tableComb, Arrays.asList(clazz.getDeclaredFields()));
                Map<String, Set<Class<?>>> mapClazz = getClazzesByName(tableComb.getKdbTable().name(), clazzesRef);
                Set<Class<?>> matching = mapClazz.get("matching");
                for (Class<?> clazzMatched : matching) {
                    setMethodsComb(tableComb, Arrays.asList(clazzMatched.getDeclaredMethods()));
                    setFieldsComb(tableComb, Arrays.asList(clazzMatched.getDeclaredFields()));


                }
                clazzesRef = mapClazz.get("remaining");
                tableCombs.add(tableComb);


            }
        }

        return tableCombs;

    }

    // NEXT JOB HERE make columns for raw from ai  then make the common raw fields then make the mtm tables fields
    public static void setFieldsCombFromAi(TableComb tableComb, List<Field> fields) {

        for (Field field : fields) {
            FieldsComb fieldsComb = new FieldsComb();


// ------------------------------------------
            KdbColumn kdbColumn = field.getAnnotation(KdbColumn.class);

            KdbIndex kdbIndex = field.getAnnotation(KdbIndex.class);



            if (kdbColumn != null) {
                fieldsComb.setKdbColumn(kdbColumn);
            }

            if (kdbIndex != null) {
                fieldsComb.setKdbIndex(kdbIndex);
            } else {

                fieldsComb.setFieldType(field.getType());
            }
            tableComb.addFieldsComb(fieldsComb);


//-----------------------------------------
        }
        for(FieldsComb comb : getAiFieldCombs(tableComb.getKdbTable().name())){
            tableComb.addFieldsComb(comb);
        }

    }

    Map<String, String> aiTables = Map.ofEntries(
            Map.entry("%s_mtm_parse_groups_parse_input_groups",
                    "Maps parse groups to parse input groups, defining which input groups belong to each parse group."),

            Map.entry("%s_mtm_parse_input_groups_parse_input_types",
                    "Maps parse input groups to parse input types, defining which types are included in each group."),

            Map.entry("%s_mtm_parse_input_names_parse_input_values",
                    "Maps input names to their corresponding values, representing the relationship between identifiers and their data values."),

            Map.entry("%s_mtm_parse_input_types_parse_input_names",
                    "Maps input types to input names, defining which names fall under each type classification."),

            Map.entry("%s_parse_groups",
                    "Defines high-level parsing groups used to organize and categorize input processing workflows."),

            Map.entry("%s_parse_input_groups",
                    "Defines groups of related input fields that are processed together during parsing."),

            Map.entry("%s_parse_input_names",
                    "Stores the individual input field names used in parsing operations."),

            Map.entry("%s_parse_input_types",
                    "Defines the types or categories of inputs, such as text, numeric, or structured data."),

            Map.entry("%s_parse_input_values",
                    "Stores the actual values associated with input names, representing parsed or raw data entries.")
    );

    private static List<FieldsComb> getAiFieldCombs(String tableName) {
        // Add all keys into all of them and regular fields into inputs. primary keys will be apart of the filing system.

        List<FieldsComb> fieldsCombs = new ArrayList<>();



        if(Objects.equals(tableName, "raw_inputs")){

            for(int i = 0; i<RAW_INPUTS_INDEX.getColumns().size(); i++){

                fieldsCombs.add(createFieldsComb(RAW_INPUTS_INDEX.getColumns().get(i), RAW_INPUTS_INDEX.getIndexByIndex(i), RAW_INPUTS_INDEX.getIndexName(),RAW_INPUTS_INDEX.getKeyGroupName(),false));
            }
            for(int i = 0; i<RAW_INPUTS.getColumns().size(); i++){
                fieldsCombs.add(createFieldsComb(RAW_INPUTS.getColumns().get(i), RAW_INPUTS.getIndexByIndex(i), RAW_INPUTS.getIndexName(),RAW_INPUTS.getKeyGroupName(),false));
            }
            for(int i = 0; i<RAW_INPUTS_RAW_ID.getColumns().size(); i++){
                fieldsCombs.add(createFieldsComb(RAW_INPUTS_RAW_ID.getColumns().get(i), RAW_INPUTS_RAW_ID.getIndexByIndex(i), RAW_INPUTS_RAW_ID.getIndexName(),RAW_INPUTS_RAW_ID.getKeyGroupName(),false));
            }
            fieldsCombs.add(createFieldsComb(RAW.getPrimaryKey(), 0, null, null,true));

        }

        if(Objects.equals(tableName, "inputs")){
            for(int i = 0; i<INPUTS_INDEX.getColumns().size(); i++){
                fieldsCombs.add(createFieldsComb(INPUTS_INDEX.getColumns().get(i), INPUTS_INDEX.getIndexByIndex(i), INPUTS_INDEX.getIndexName(),INPUTS_INDEX.getKeyGroupName(),false));
            }
            for(int i = 0; i<INPUTS.getColumns().size(); i++){
                fieldsCombs.add(createFieldsComb(INPUTS.getColumns().get(i), INPUTS.getIndexByIndex(i), INPUTS.getIndexName(),INPUTS.getKeyGroupName(),false));
            }
            for(int i = 0; i<RAW_INPUTS_RAW_ID.getColumns().size(); i++){
                fieldsCombs.add(createFieldsComb(RAW_INPUTS_RAW_ID.getColumns().get(i), RAW_INPUTS_RAW_ID.getIndexByIndex(i), RAW_INPUTS_RAW_ID.getIndexName(),RAW_INPUTS_RAW_ID.getKeyGroupName(),false));
            }
            fieldsCombs.add(createFieldsComb(RAW.getPrimaryKey(), 0, null, null,true));


        }

        if(Objects.equals(tableName, "raw")){
            for(int i = 0; i<RAW_INDEX.getColumns().size(); i++){
                fieldsCombs.add(createFieldsComb(RAW_INDEX.getColumns().get(i), RAW_INDEX.getIndexByIndex(i), RAW_INDEX.getIndexName(),RAW_INDEX.getKeyGroupName(),false));
            }
            for(int i = 0; i<RAW.getColumns().size(); i++){
                fieldsCombs.add(createFieldsComb(RAW.getColumns().get(i), RAW.getIndexByIndex(i), RAW.getIndexName(),RAW.getKeyGroupName(),false));
            }
            fieldsCombs.add(createFieldsComb(RAW.getPrimaryKey(), 0, null, null,true));

        }





        System.out.println(tableName+" here mtm");

        if(tableName.matches(".*mtm_parse_groups_parse_parse_input_groups.*")){
            for(int i = 0; i<MTM_PGIG.getColumns().size(); i++){

                fieldsCombs.add(createFieldsComb(MTM_PGIG.getColumns().get(i), MTM_PGIG.getIndexByIndex(i), MTM_PGIG.getIndexName(),MTM_PGIG.getKeyGroupName(),false));
            }
            fieldsCombs.add(createFieldsComb(RAW_INDEX.getPrimaryKey(), 0, null, null,true));

        }

        if(tableName.matches(".*mtm_parse_input_groups_parse_input_types.*")){
            for(int i = 0; i<MTM_IGIT.getColumns().size(); i++){

                fieldsCombs.add(createFieldsComb(MTM_IGIT.getColumns().get(i), MTM_IGIT.getIndexByIndex(i), MTM_IGIT.getIndexName(),MTM_IGIT.getKeyGroupName(),false));
            }
            fieldsCombs.add(createFieldsComb(RAW_INDEX.getPrimaryKey(), 0, null, null,true));

        }

        if(tableName.matches(".*mtm_parse_input_types_parse_input_names.*")){
            for(int i = 0; i<MTM_ITIN.getColumns().size(); i++){

                fieldsCombs.add(createFieldsComb(MTM_ITIN.getColumns().get(i), MTM_ITIN.getIndexByIndex(i), MTM_ITIN.getIndexName(),MTM_ITIN.getKeyGroupName(),false));
            }
            fieldsCombs.add(createFieldsComb(RAW_INDEX.getPrimaryKey(), 0, null, null,true));

        }

        if(tableName.matches(".*mtm_parse_input_names_parse_input_values.*")){
            for(int i = 0; i<MTM_INIV.getColumns().size(); i++){

                fieldsCombs.add(createFieldsComb(MTM_INIV.getColumns().get(i), MTM_INIV.getIndexByIndex(i), MTM_INIV.getIndexName(),MTM_INIV.getKeyGroupName(),false));
            }
            fieldsCombs.add(createFieldsComb(RAW_INDEX.getPrimaryKey(), 0, null, null,true));

        }
        return fieldsCombs;







    }

    private static FieldsComb createFieldsComb(ColumnMeta columnMeta, int i, String indexName, String keyGroupName,boolean isPrimary) {

        KdbIndex kdbIndex = null;
        KdbKey kdbKey = null;
        KdbPrimaryKey primaryKey = null;

        FieldsComb fieldsComb = new FieldsComb();




        if(indexName!=null) {
            if(i <0){
                indexName = indexName+"_"+columnMeta.name();
            }
            kdbIndex = createKDBIndex(indexName + "_idx", i);
        }
        if(keyGroupName!= null){
            kdbKey = createKdbKey(keyGroupName+"_key");
        }
        if(isPrimary){
            primaryKey = new KdbPrimaryKey () {
                @Override
                public Class<? extends java.lang.annotation.Annotation> annotationType() {
                    return KdbPrimaryKey.class;
                }
            };
        }





            fieldsComb.setKdbIndex(kdbIndex);


            KdbColumn kdbColumn = new KdbColumn() {
                @Override
                public Class<? extends java.lang.annotation.Annotation> annotationType() {
                    return KdbColumn.class;
                }

                @Override
                public String name() {
                    return columnMeta.name();
                }

                @Override
                public String description() {
                    return "";
                }

                @Override
                public String[] tags() {
                    return new String[0];
                }

                @Override
                public String type() {
                    return "";
                }

                @Override
                public boolean isNullable() {
                    return false;
                }

                @Override
                public boolean isEditable() {
                    return true;
                }

                @Override
                public String[] columnGroupNames() {
                    return new String[]{"ai_group"};
                }

                @Override
                public boolean unique() {
                    return false;
                }

                @Override
                public boolean uniqueIdentifier() {
                    return false;
                }

                @Override
                public String[] uniqueIdenftifierGroupNames() {
                    return new String[0];
                }

                @Override
                public boolean isRequired() {
                    return false;
                }

                @Override
                public String defaultValue() {
                    return null;
                }

                @Override
                public int[] uniqueIdentifierGroupNames() {
                    return new int[0];
                }

                @Override
                public Class<?> converter() {
                    return KdbConverter.class;
                }


            };
            fieldsComb.setKdbColumn(kdbColumn);
            fieldsComb.setKdbPrimaryKey(primaryKey);
            try {
                fieldsComb.setFieldType(resolveClass(columnMeta.getJavaType()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


            fieldsComb.setKdbKey( kdbKey);
            return fieldsComb;
    }

    private static KdbKey createKdbKey(String keyGroupName) {
        return new KdbKey() {
            @Override
            public String[] groupName() {
                return new String[]{keyGroupName};
            }

            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return KdbKey.class;
            }
        };
    }


    private static KdbIndex createKDBIndex(String groupName, int order) {
        int[] orderOverride  =new int[]{0};
        if(order>0){
            orderOverride =  new int[]{order};
        }


        int[] finalOrderOverride = orderOverride;
        return new KdbIndex() {
            @Override
            public String[] indexGroups() {
                return new String[]{groupName};
            }

            @Override
            public int[] order() {
                return finalOrderOverride;
            }

            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return KdbIndex.class;
            }


        };

    }






    private static FieldsComb createFieldsComb(Map.Entry<String, String> kv) {
        KdbIndex kdbIndex = null;
        String groupName = "upload_group_index";

        FieldsComb fieldsComb = new FieldsComb();


        if (Objects.equals(kv.getKey(), ColumnMeta.UPLOAD_GROUP.getColumn())) {
            kdbIndex = createKDBIndex(RAW_INDEX.getIndexName(), 0);

        } else if (Objects.equals(kv.getKey(), ColumnMeta.INPUT_GROUP.getColumn())) {
            kdbIndex = createKDBIndex(groupName, 1);
        } else if (Objects.equals(kv.getKey(), ColumnMeta.INPUT_TYPE.getColumn())) {
            kdbIndex = createKDBIndex(groupName, 2);
        } else if (Objects.equals(kv.getKey(), ColumnMeta.INPUT_NAME.getColumn())) {
            kdbIndex = createKDBIndex(groupName, 3);
        } else if (Objects.equals(kv.getKey(), ColumnMeta.INPUT_VALUE.getColumn())) {
            kdbIndex = createKDBIndex(groupName, 4);
        }


            fieldsComb.setKdbIndex(kdbIndex);


            KdbColumn kdbColumn = new KdbColumn() {
                @Override
                public Class<? extends java.lang.annotation.Annotation> annotationType() {
                    return KdbColumn.class;
                }

                @Override
                public String name() {
                    return kv.getKey();
                }

                @Override
                public String description() {
                    return "";
                }

                @Override
                public String[] tags() {
                    return new String[0];
                }

                @Override
                public String type() {
                    return "";
                }

                @Override
                public boolean isNullable() {
                    return false;
                }

                @Override
                public boolean isEditable() {
                    return true;
                }

                @Override
                public String[] columnGroupNames() {
                    return new String[]{"upload_group"};
                }

                @Override
                public boolean unique() {
                    return false;
                }

                @Override
                public boolean uniqueIdentifier() {
                    return false;
                }

                @Override
                public String[] uniqueIdenftifierGroupNames() {
                    return new String[0];
                }

                @Override
                public boolean isRequired() {
                    return false;
                }

                @Override
                public String defaultValue() {
                    return null;
                }

                @Override
                public int[] uniqueIdentifierGroupNames() {
                    return new int[0];
                }

                @Override
                public Class<?> converter() {
                    return null;
                }


            };
            fieldsComb.setKdbColumn(kdbColumn);
            try {
                fieldsComb.setFieldType(resolveClass(kv.getValue()));
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            KdbKey kdbKey = new KdbKey() {
                @Override
                public String[] groupName() {
                    return new String[]{"upload_group"};
                }

                @Override
                public Class<? extends java.lang.annotation.Annotation> annotationType() {
                    return KdbKey.class;
                }
            };

            fieldsComb.setKdbKey(kdbKey);
            return fieldsComb;

    }


    private static Class<?> resolveClass(String typeName) throws ClassNotFoundException {
        if (typeName == null || typeName.isEmpty()) {
            throw new ClassNotFoundException("empty type name");
        }

        // handle array syntax like "java.lang.Double[]" or "int[]"
        if (typeName.endsWith("[]")) {
            String elementType = typeName.substring(0, typeName.length() - 2);
            Class<?> comp = resolveClass(elementType);
            return java.lang.reflect.Array.newInstance(comp, 0).getClass();
        }

        // primitive type shortcuts
        return switch (typeName) {
            case "byte" -> byte.class;
            case "short" -> short.class;
            case "int" -> int.class;
            case "long" -> long.class;
            case "float" -> float.class;
            case "double" -> double.class;
            case "boolean" -> boolean.class;
            case "char" -> char.class;
            case "void" -> void.class;
            default -> Class.forName(typeName);
        };
    }
    private static KdbTable translateAiToTable(KdbAi aiSchemaData,String description,String name) {

        return new KdbTable() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return KdbTable.class;
            }

            @Override
            public String name() {
                return name;
            }

            @Override
            public String description() {
                return description;
            }

            @Override
            public int autoIncrementStart() {
                return 0;
            }

            @Override
            public String type() {
                return TableTypes.RAW;
            }

            @Override
            public String uploadType() {
                return "";
            }

            @Override
            public String airtableId() {
                return "";
            }

            @Override
            public String[] tags() {
                return aiSchemaData.tags();
            }
        };


    }

    private static KdbTable translateAiToTableSchema(KdbAi aiSchemaData,String description,String name) {

        return new KdbTable() {
            @Override
            public Class<? extends java.lang.annotation.Annotation> annotationType() {
                return KdbTable.class;
            }

            @Override
            public String name() {
                return name;
            }

            @Override
            public String description() {
                return description;
            }

            @Override
            public int autoIncrementStart() {
                return 0;
            }

            @Override
            public String type() {
                return "AI_SCHEMA";
            }

            @Override
            public String uploadType() {
                return "";
            }

            @Override
            public String airtableId() {
                return "";
            }

            @Override
            public String[] tags() {
                return aiSchemaData.tags();
            }
        };


    }

    public static Map<String, Set<Class<?>>> getClazzesByName(String simpleName, Set<Class<?>> clazzesRef) {
        Set<Class<?>> matching = clazzesRef.stream()
                .filter(c -> {
//                    System.out.println(c.getAnnotation(KdbTableRef.class).name());
                            return Objects.equals(c.getAnnotation(KdbTableRef.class).name(), simpleName);

                        }
                )
                .collect(Collectors.toSet());

        Set<Class<?>> remaining = clazzesRef.stream()
                .filter(c -> !Objects.equals(c.getAnnotation(KdbTableRef.class).name(), simpleName))
                .collect(Collectors.toSet());


        Map<String, Set<Class<?>>> result = new HashMap<>();
        result.put("matching", matching);
        result.put("remaining", remaining);

//        System.out.println("Matching classes: " + matching);
//        System.out.println("Remaining classes: " + remaining);

        return result;
    }


    public static void setMethodsComb(TableComb tableComb, List<Method> methods) {
        for (Method method : methods) {

            MethodsComb methodsComb = new MethodsComb();
            KdbQuery kdbQuery = method.getAnnotation(KdbQuery.class);
            KdbTrigger kdbTrigger = method.getAnnotation(KdbTrigger.class);
            KdbCustomContraint kdbContraint = method.getAnnotation(KdbCustomContraint.class);
            KdbProcedure kdbProcedure = method.getAnnotation(KdbProcedure.class);

            methodsComb.setMethod(method);

            if (kdbQuery != null) {
                methodsComb.setKdbQuery(kdbQuery);
            }
            if (kdbTrigger != null) {
                methodsComb.setKdbTrigger(kdbTrigger);
            }
            if (kdbContraint != null) {
                methodsComb.setKdbCustomContraint(kdbContraint);
            }
            if (kdbProcedure != null) {
                methodsComb.setKdbProcedure(kdbProcedure);
            }
            tableComb.addMethodsComb(methodsComb);


        }
    }




    public static void setFieldsComb(TableComb tableComb, List<Field> fields) {
        for (Field field : fields) {
            FieldsComb fieldsComb = new FieldsComb();


// ------------------------------------------
            KdbColumn kdbColumn = field.getAnnotation(KdbColumn.class);
            KdbPrimaryKey kdbPrimaryKey = field.getAnnotation(KdbPrimaryKey.class);
            KdbIndex kdbIndex = field.getAnnotation(KdbIndex.class);
            KdbReference kdbReference = field.getAnnotation(KdbReference.class);
            KdbKey kdbKey = field.getAnnotation(KdbKey.class);
            KdbEmbedding kdbEmbedding = field.getAnnotation(KdbEmbedding.class);
            if (kdbColumn != null) {
                fieldsComb.setKdbColumn(kdbColumn);
            }
            if (kdbPrimaryKey != null) {
                fieldsComb.setKdbPrimaryKey(kdbPrimaryKey);
            }
            if (kdbIndex != null) {
                fieldsComb.setKdbIndex(kdbIndex);
            }
            if (kdbReference != null) {
                fieldsComb.setKdbReference(kdbReference);
            }
            if (kdbKey != null) {
                fieldsComb.setKdbKey(kdbKey);
            }
            if(kdbEmbedding!=null){
                fieldsComb.setKdbEmbedding(kdbEmbedding);
                fieldsComb.setFieldType(Double[].class);

            }else {

                fieldsComb.setFieldType(field.getType());
            }
            tableComb.addFieldsComb(fieldsComb);


//-----------------------------------------
        }

    }

    public static void setAiFieldsComb(AiTableComb tableComb, List<Field> fields) {
        for (Field field : fields) {
            AiFieldsComb fieldsComb = new AiFieldsComb();


// ------------------------------------------
            KdbAiColumn kdbColumn = field.getAnnotation(KdbAiColumn.class);
            KdbAiPrimaryKey kdbPrimaryKey = field.getAnnotation(KdbAiPrimaryKey.class);
            KdbAiKey kdbKey = field.getAnnotation(KdbAiKey.class);

            if (kdbColumn != null) {
                fieldsComb.setKdbColumn(kdbColumn);
            }
            if(kdbPrimaryKey!= null){
                fieldsComb.setKdbPrimaryKey(kdbPrimaryKey);
            }
            if(kdbKey!= null){
                fieldsComb.setKdbKey(kdbKey);
            }

            tableComb.addAiFieldsComb(fieldsComb);


//-----------------------------------------
        }

    }

    public AirTableJson[] buildJsonOfAirTables(String packageName, Identifier id) throws InvocationTargetException, IllegalAccessException {
        List<TableComb> tableCombs = getAllClassesInPackage(packageName);
        List<AirTableJson> list = new ArrayList<>();

        for (TableComb table : tableCombs) {

            AirTableJson tableJson = new AirTableJson(id, table);
//            System.out.println(tableJson.toString());
            list.add(tableJson);
        }
        return list.toArray(new AirTableJson[0]);
    }

    public AiTableJson[] buildJsonOfAiTables(String packageName, Identifier id) throws InvocationTargetException, IllegalAccessException {
        List<AiTableComb> tableCombs = getAiClassesInPackage(packageName);
        List<AiTableJson> list = new ArrayList<>();

        for (AiTableComb table : tableCombs) {

            AiTableJson tableJson = new AiTableJson(id,table);
//            System.out.println(tableJson.toString());
            list.add(tableJson);
        }
        return list.toArray(new AiTableJson[0]);
    }

    public TableJson[] buildJsonOfTables(String packageName,Identifier id) throws InvocationTargetException, IllegalAccessException {
        List<TableComb> tableCombs = getAllClassesInPackage(packageName);

        List<TableJson> list = new ArrayList<>();

        TableJson tableJson;

        for (TableComb table : tableCombs) {
            System.out.println(table.getKdbTable().type());
            if(Objects.equals(table.getKdbTable().type(), "AI_SCHEMA")){
                Identifier aiIdentifier = new Identifier(id.getGmaName(),"ai",table.getKdbTable().name());
                tableJson = new TableJson(aiIdentifier, table);
            }else {

                tableJson = new TableJson(id, table);
            }

            list.add(tableJson);
        }


        return list.toArray(new TableJson[0]);
    }

}


