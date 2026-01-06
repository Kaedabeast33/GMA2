package org.example.JsonBuilder.IDE;


import jakarta.persistence.Id;
import org.example.ClassOutputCreator.templates.KdbGma;
import org.example.ClassOutputCreator.templates.MAConfigTemplate;
import org.example.JsonBuilder.json.GMAJson;
import org.example.JsonBuilder.json.QueryGroupJson;
import org.example.JsonBuilder.json.ma.MAJson;
import org.example.JsonBuilder.json.ma.tables.BaseQueryJson;
import org.example.JsonBuilder.json.ma.tables.ProcedureJson;
import org.example.JsonBuilder.json.ma.tables.TableJson;
import org.example.bank.Annotations.*;
import org.example.bank.KdbConverter.ClassTypeAdapter;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.example.bank.commonValues.Identifier;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

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
                        // Example: com/chipr/GMA/org.example.inputs/schemas/employeealignment
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
        for (MAConfigTemplate db : gma.getMa()) {
            Identifier identifer = new Identifier(gma.getName(),db.getName(),null);
            MAJson maJson = new MAJson(identifer,db);
            maJson.setTables(buildJsonOfTables(db.getJavaFolderPath(),identifer));

            List<ProcedureJson> allProcedures = new ArrayList<>();
            for (TableJson table : maJson.getTables()) {
                Collections.addAll(allProcedures, table.getTableProcedures());
            }

            maJson.setProcedures(allProcedures.toArray(new ProcedureJson[0]));
            maJsons.add(maJson);
        }

        gmaJson.setMa(maJsons);
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

//        for (Class<?> clazz : clazzes) {
//            System.out.println("This is a class in this package: " + clazz.getAnnotation(KdbTable.class).name());
//        }
//
//        for (Class<?> clazz : clazzesRef) {
//            System.out.println("This is a ref class in this package: " + clazz.getAnnotation(KdbTableRef.class).name());
//        }

        return getTableData(clazzes, clazzesRef);
    }


    public static List<TableComb> getTableData(Set<Class<?>> clazzes, Set<Class<?>> clazzesRef) {


        List<TableComb> tableCombs = new ArrayList<>();
        for (Class<?> clazz : clazzes) {
            TableComb tableComb = new TableComb();
            KdbTable tableData = clazz.getAnnotation(KdbTable.class);
            tableComb.setKdbTable(tableData);


            setMethodsComb(tableComb, Arrays.asList(clazz.getDeclaredMethods()));
            setFieldsComb(tableComb, Arrays.asList(clazz.getDeclaredFields()));
//            System.out.println("checking for matches for "+tableData.name());
            Map<String, Set<Class<?>>> mapClazz = getClazzesByName(tableData.name(), clazzesRef);
            Set<Class<?>> matching = mapClazz.get("matching");
            for (Class<?> clazzMatched : matching) {
                setMethodsComb(tableComb, Arrays.asList(clazzMatched.getDeclaredMethods()));
                setFieldsComb(tableComb, Arrays.asList(clazzMatched.getDeclaredFields()));

            }

            clazzesRef = mapClazz.get("remaining");
            tableCombs.add(tableComb);


        }

        return tableCombs;

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
            fieldsComb.setFieldType(field.getType());
            tableComb.addFieldsComb(fieldsComb);


//-----------------------------------------
        }

    }


    public TableJson[] buildJsonOfTables(String packageName,Identifier id) throws InvocationTargetException, IllegalAccessException {
        List<TableComb> tableCombs = getAllClassesInPackage(packageName);
        List<TableJson> list = new ArrayList<>();

        for (TableComb table : tableCombs) {

            TableJson tableJson = new TableJson(id,table);
//            System.out.println(tableJson.toString());
            list.add(tableJson);
        }

        return list.toArray(new TableJson[0]);
    }

}


