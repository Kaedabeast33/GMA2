package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.ClassOutputCreator.templates.ColumnTemplate;
import org.example.ClassOutputCreator.templates.KdbGma;
import org.example.ClassOutputCreator.templates.MAConfigTemplate;
import org.example.ClassOutputCreator.templates.TableTemplate;
import org.example.JsonBuilder.json.GMAJson;
import org.example.JsonBuilder.json.QueryGroupJson;
import org.example.JsonBuilder.json.ma.PipelineJson;
import org.example.JsonBuilder.json.ma.tables.*;
import org.example.JsonBuilder.json.ma.tables.columns.*;
import org.example.JsonBuilder.json.ma.tables.dependencies.DependencyJson;
import org.example.JsonBuilder.json.ref.RefColumnJson;
import org.example.JsonBuilder.json.ref.RefTableJson;
import org.example.JsonBuilder.json.ref.ReferenceColumnJson;
import org.example.bank.OutputClassBank.KDBContext;
import org.example.JsonBuilder.DB.DbToJsonExtractor;
import org.example.JsonBuilder.IDE.JsonBuilder;
import org.example.JsonBuilder.json.ma.MAJson;
import org.example.bank.OutputClassBank.KdbColumnPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.util.*;
import java.util.concurrent.*;

import static org.example.ClassOutputCreator.ClassCreator.getWorkingDirectory;
import static org.example.ClassOutputCreator.ClassCreator.jsonToObjects;
import static org.example.JsonBuilder.IDE.JsonBuilder.createGson;
import static org.example.JsonBuilder.json.GMAJson.createBlankInstance;
import static org.example.bank.OutputClassBank.AppConfig.*;

@Component
public class Actions {
    @Autowired
    @Qualifier("kdbGma")
    KdbGma kdbGma;

    @Autowired
    JsonBuilder jsonBuilder;

    Gson gson = createGson();
    //        System.out.println(gson.toJson(gma));
    String workingDir =
            Paths.get("").toAbsolutePath().toAbsolutePath().toString();

    KDBContext kdbContext = KDBContext.KDB_CONTEXT;





    public void mainRun() throws InvocationTargetException, IllegalAccessException, SQLException, IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            ExecutorService executor = Executors.newSingleThreadExecutor();

            try {
                buildGmaContext();

                // First input
                System.out.println("Would you like to build classes (Y/N): ");
                String input_0 = "N";
                Future<String> future_0 = executor.submit(scanner::nextLine);

                try {
                    input_0 = future_0.get(10, TimeUnit.SECONDS).trim().toUpperCase();
                } catch (TimeoutException e) {
                    System.out.println("No response detected. Proceeding with 'N'.");
                } catch (ExecutionException | InterruptedException e) {
                    System.out.println("An error occurred while reading input: " + e.getMessage());
                }

                if ("Y".equals(input_0)) {
                    buildClasses();
                    System.out.println("Classes built.");
                } else {
                    System.out.println("Skipping class build.");
                }

                // Second input
                System.out.println("Would you like to compare your IDE and DB tables (Y/N): ");
                String input = "N";
                Future<String> future = executor.submit(scanner::nextLine);

                try {
                    input = future.get(10, TimeUnit.SECONDS).trim().toUpperCase();
                } catch (TimeoutException e) {
                    System.out.println("No response detected. Proceeding with 'N'.");
                } catch (ExecutionException | InterruptedException e) {
                    System.out.println("An error occurred while reading input: " + e.getMessage());
                }

                if ("Y".equals(input)) {
                    getDbJson();
                    analyzeJson();
                    System.out.println("Actions completed successfully.");
                } else {
                    System.out.println("Skipping 'getDbJson' and 'analyzeJson'.");
                }
            } finally {
                executor.shutdownNow();
            }
        }
    }

    // ------------------Build GMa Context for Dorm ------------------------
    public void buildGmaContext() throws InvocationTargetException, IllegalAccessException {
        for(KdbGma kdbGma : kdbContext.getGmaConfigList()){
            GMAJson gma = jsonBuilder.buildJsonOfGma(kdbGma);
            kdbContext.addGMA(gma);
        }


        System.out.println("GMA Context built");
    }
//        //----------Build Classes ---------

    public void buildClasses() {
        try {
            GMAJson gma = kdbContext.getGmaByName("dorm");
            jsonToObjects(gma);
            Files.writeString(
                    Paths.get(workingDir, "db.json"),
                    gson.toJson(gma),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
            System.out.println("Classes built");
            String sql = "INSERT INTO gma_configs (name, config_json) " +
                    "VALUES (?, ?) " +
                    "ON DUPLICATE KEY UPDATE config_json = VALUES(config_json)";

            String sql2 = """
                    INSERT INTO gma_structure (db_name,level_name,key_name)
                    VALUES(?,?,?)
                    ON DUPLICATE KEY UPDATE level_name,key_name  = VALUES(level_name,key_name)
                   
                    """;

            try (Connection connection = DriverManager.getConnection(getJdbcUrl(), getJdbcUser(), getJdbcPassword());


                 PreparedStatement ps = connection.prepareStatement(sql);
                 PreparedStatement ps2 = connection.prepareStatement(sql2)
                 )

            {


                ps.setString(1, gma.getName());
                ps.setString(2, gson.toJson(gma));
                ps.executeUpdate();
            }

            catch (Exception e){
                System.out.println("Failed to save GMA config to database.");
//                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("json written");
    }


//
//        ----------------Getting Json from DB ----------------


    public void getDbJson() throws SQLException, InvocationTargetException, IllegalAccessException, IOException {

        System.out.println("Connecting to the database.");
        GMAJson gma = kdbContext.getGmaByName("dorm");
        for (MAJson ma : gma.getMa()) {

            Connection connection;
            try {
                connection = DriverManager.getConnection(
                        ma.getJdbcUrl(),
                        ma.getUser(),
                        ma.getPass()
                );
            }catch (Exception e){
                System.out.println("Failed to connect to database " + ma.getName());
                throw new RuntimeException(e);
            }
            System.out.println("Connected to the database " + ma.getName());
            List<TableJson> tables = DbToJsonExtractor.extractTables(connection, ma.getName());
            TableJson[] t = new TableJson[tables.size()];
            tables.toArray(t);

            ma.setTables(t);

            List<ProcedureJson> procedures = new ArrayList<>();
            for (TableJson table : tables) {
                ProcedureJson[] procs = table.getTableProcedures();
                procedures.addAll(List.of(procs));
            }
            ProcedureJson[] p = new ProcedureJson[procedures.size()];
            procedures.toArray(p);
            ma.setProcedures(p);

        }

//        MAJson ma = gma.getMa().stream().filter(maJson -> maJson.getName().equalsIgnoreCase("commissions")).findFirst().orElse(null);
//        List<TableJson> tables = DbToJsonExtractor.extractTables(connection, "commissions");
//        TableJson[] t = new TableJson[tables.size()];
//        tables.toArray(t);
//
//        assert ma != null;
//        ma.setTables(t);
//
//        List<ProcedureJson> procedures = new ArrayList<>();
//        for (TableJson table : tables) {
//            ProcedureJson[] procs = table.getTableProcedures();
//            procedures.addAll(List.of(procs));
//        }
//        ProcedureJson[] p = new ProcedureJson[procedures.size()];
//        procedures.toArray(p);
//        ma.setProcedures(p);

        System.out.println(gson.toJson(gma));


        Path outputFile = Path.of(getWorkingDirectory(), "db_from_db.json");

        Files.writeString(
                outputFile,
                gson.toJson(gma),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );

        gma.setName("dorm_db");
        kdbContext.addGMA(gma);
    }

    public void analyzeJson() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("db.json"));
        BufferedReader readerF = new BufferedReader(new FileReader("db_from_db.json"));
        GMAJson dormGma = gson.fromJson(reader, GMAJson.class);
        GMAJson dormDbGma = gson.fromJson(readerF, GMAJson.class);

        GmaChecker checker = new GmaChecker(dormGma, dormDbGma);
        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson1.toJson(checker));
        checker.reviewDifferencesWithPrompt();


//        System.out.println(checker.toString());


    }

    public void writeDbGmaStructure() throws IOException {
        QueryGroupDTO queryGroupDTO = new QueryGroupDTO();
        queryGroupDTO.setQueries(List.of(new BaseQueryJson()).toArray(new BaseQueryJson[0]));





//        Gson gson = new Gson();
//
//        System.out.println("\n=== Using Reflection ===");



        ColumnJson columns = createBlankInstance(ColumnJson.class);
        GroupDTO groupDTO = createBlankInstance(GroupDTO.class);
        ColumnJson groupColumn = createBlankInstance(ColumnJson.class);
        groupDTO.setColumnGroupColumns(new ColumnJson[]{groupColumn});

        columns.setColumnGroups(new GroupDTO[]{groupDTO});
        columns.setUniqueIdentifierGroups(new GroupDTO[]{groupDTO});
        columns.setIndexGroups(new GroupDTO[]{groupDTO});
        ReferenceColumnJson referenceColumnJson = createBlankInstance(ReferenceColumnJson.class);
        RefColumnJson refColumnJson = createBlankInstance(RefColumnJson.class);
        refColumnJson.setReferenceTable(createBlankInstance(RefTableJson.class));
        referenceColumnJson.setReferenceColumns(new RefColumnJson[]{refColumnJson});










        TableJson reflectionTable = createBlankInstance(TableJson.class);
        DependencyJson dependencies = createBlankInstance(DependencyJson.class);
        ColumnGroupJson columnGroups = createBlankInstance(ColumnGroupJson.class);
        ColumnDTO columnDTO = createBlankInstance(ColumnDTO.class);
        columnGroups.setgroupColumns(List.of(columnDTO).toArray(new ColumnDTO[0]));
        UniqueColumnGroupJson uniqueColumnGroups = createBlankInstance(UniqueColumnGroupJson.class);
        uniqueColumnGroups.setColumns(List.of(columnDTO).toArray(new ColumnDTO[0]));
        IndexJson indexes = createBlankInstance(IndexJson.class);
        indexes.setColumns(List.of(columnDTO));
        QueryJson tableQueries = createBlankInstance(QueryJson.class);
        tableQueries.setGroups(List.of(queryGroupDTO));
        ProcedureJson tableProcedures = createBlankInstance(ProcedureJson.class);
        tableProcedures.setGroups(List.of(queryGroupDTO));
        TriggerJson triggers = createBlankInstance(TriggerJson.class);
        CustomContraintJson customConstraints = createBlankInstance(CustomContraintJson.class);
        RefColumnJson refColumnJsonTable= createBlankInstance(RefColumnJson.class);
        customConstraints.setColumns(List.of(refColumnJsonTable).toArray(new RefColumnJson[0]));
        UniqueKeyJson uniqueKeys = createBlankInstance(UniqueKeyJson.class);
        uniqueKeys.setColumns(new ColumnDTO[]{columnDTO});





        reflectionTable.setUniqueColumnGroups(List.of(uniqueColumnGroups).toArray(new UniqueColumnGroupJson[0]));
        reflectionTable.setColumnGroups(List.of(columnGroups).toArray(new ColumnGroupJson[0]));
        reflectionTable.setIndexes(List.of(indexes).toArray(new IndexJson[0]));
        reflectionTable.setTableQueries(List.of(tableQueries).toArray(new QueryJson[0]));
        reflectionTable.setTableProcedures(List.of(tableProcedures).toArray(new ProcedureJson[0]));
        reflectionTable.setTriggers(List.of(triggers).toArray(new TriggerJson[0]));
        reflectionTable.setCustomConstraints(List.of(customConstraints).toArray(new CustomContraintJson[0]));
        reflectionTable.setUniqueKeys(List.of(uniqueKeys).toArray(new UniqueKeyJson[0]));
        reflectionTable.setColumns(List.of(columns).toArray(new ColumnJson[0]));
        reflectionTable.setDependencies(List.of(dependencies).toArray(new DependencyJson[0]));





        MAJson reflectionMa = createBlankInstance(MAJson.class);
        QueryJson queryJson  =  createBlankInstance(QueryJson.class);
        ProcedureJson procedureJson  =  createBlankInstance(ProcedureJson.class);
        PipelineJson pipelineJson = createBlankInstance(PipelineJson.class);

        pipelineJson.setQueries(List.of(queryJson).toArray(new QueryJson[0]));
        reflectionMa.setProcedures(List.of(procedureJson).toArray(new ProcedureJson[0]));
        reflectionMa.setPipelines(List.of(pipelineJson).toArray(new PipelineJson[]{}));


        GMAJson reflectionGma = createBlankInstance(GMAJson.class);
        QueryGroupJson queryGroupJson = createBlankInstance(QueryGroupJson.class);
        BaseQueryDTO baseQueryDTO = createBlankInstance(BaseQueryDTO.class);
        queryGroupJson.setQueries(List.of(baseQueryDTO));
        reflectionGma.setQueryGroups(List.of(queryGroupJson).toArray(new QueryGroupJson[0]));






        reflectionMa.setTables(List.of(reflectionTable).toArray(new TableJson[0]));
        reflectionGma.setMa(List.of(reflectionMa));


        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        Path path = Paths.get(workingDir,"gmaStructure.json");
        Files.deleteIfExists(path);
        Files.createFile(path);
        Files.writeString(path,gson.toJson(reflectionGma));
    }



}



