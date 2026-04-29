package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.example.ClassOutputCreator.templates.KdbGma;
import org.example.JsonBuilder.DB.DbToJsonExtractor;
import org.example.JsonBuilder.IDE.JsonBuilder;
import org.example.JsonBuilder.json.GMAJson;
import org.example.JsonBuilder.json.QueryGroupJson;
import org.example.JsonBuilder.json.ma.MAJson;
import org.example.JsonBuilder.json.ma.PipelineJson;
import org.example.JsonBuilder.json.ma.tables.*;
import org.example.JsonBuilder.json.ma.tables.columns.*;
import org.example.JsonBuilder.json.ma.tables.dependencies.DependencyJson;
import org.example.JsonBuilder.json.ref.RefColumnJson;
import org.example.JsonBuilder.json.ref.RefTableJson;
import org.example.JsonBuilder.json.ref.ReferenceColumnJson;
import org.example.bank.OutputClassBank.KDBContext;
import org.example.bank.db.ItemJson;
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
import static org.example.bank.AppConfig.*;


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
        Scanner scanner = new Scanner(System.in); // Do NOT close System.in
        ExecutorService executor = Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true); // So JVM is not blocked
            return t;
        });

        try {






            // First input: build classes
            String inputBuildClasses = promptWithTimeout(scanner, executor,
                    "Would you like to build classes (Y/N): ", 10);

            if ("Y".equals(inputBuildClasses)) {
                buildClasses();
                System.out.println("Classes built.");
            } else {
                System.out.println("Skipping class build.");
            }

            // Second input: compare IDE vs DB
            String inputCompare = promptWithTimeout(scanner, executor,
                    "Would you like to compare your IDE and DB tables (Y/N): ", 10);

            if ("Y".equals(inputCompare)) {
                getDbJson();
                analyzeJson();
                System.out.println("Actions completed successfully.");
            } else {
                System.out.println("Skipping 'getDbJson' and 'analyzeJson'.");
            }

            String inputInsertTables = promptWithTimeout(scanner, executor,
                    "Would you like to ensure insert tables are built (Y/N): ", 10);
            if("Y".equals(inputInsertTables)){
                buildInsertTables(kdbContext.getGmaByName(getGmaName()));

                System.out.println("Insert tables built/updated.");
            }else {
                System.out.println("Skipping 'buildInsertTables'.");
            }



        } finally {
            executor.shutdownNow();
        }
    }

    /**
     * Prompts the user for input with a timeout.
     * Returns default "N" if no response.
     */
    private String promptWithTimeout(Scanner scanner, ExecutorService executor,
                                     String prompt, int timeoutSeconds) {
        System.out.print(prompt);
        Future<String> future = executor.submit(scanner::nextLine);
        try {
            return future.get(timeoutSeconds, TimeUnit.SECONDS).trim().toUpperCase();
        } catch (TimeoutException e) {
            future.cancel(true); // stop waiting
            System.out.println("No response detected. Proceeding with 'N'.");
            return "N";
        } catch (ExecutionException | InterruptedException e) {
            System.out.println("Error reading input: " + e.getMessage() + ". Proceeding with 'N'.");
            return "N";
        }
    }

    // java
    // java
    public void buildInsertTables(GMAJson gma) {
        // Collect all DDL statements then execute them as a batch for speed
        List<String> statements = new ArrayList<>();

        // Ensure gma_resources schema exists first
        statements.add("CREATE SCHEMA IF NOT EXISTS gma_resources");

        for (MAJson ma : gma.getMa()) {
            for (TableJson table : ma.getTables()) {
                System.out.println("Ensuring insert table for %s.%s".formatted(ma.getName(), table.getName()));
                String insertTableName = ma.getName() + "_" + table.getName() + "_insert";
                String sourceSchema = ma.getName();
                String sourceTable = table.getName();

                // Drop existing insert table then recreate it from the source table structure
                String drop = String.format("DROP TABLE IF EXISTS gma_resources.%s", insertTableName);
                String create = String.format("CREATE TABLE gma_resources.%s LIKE %s.%s", insertTableName, sourceSchema, sourceTable);

                // Add batch_id column (use IF NOT EXISTS when available)
                // Using MySQL 8+ syntax 'ADD COLUMN IF NOT EXISTS' to avoid extra queries.
                String addBatch = String.format("ALTER TABLE gma_resources.%s ADD COLUMN  batch_id varchar(55)", insertTableName);
                String addIndex = String.format("ALTER TABLE gma_resources.%s ADD INDEX (batch_id)", insertTableName);

                statements.add(drop);
                statements.add(create);
                statements.add(addBatch);
                statements.add(addIndex);
            }
        }

        // Execute all statements as a JDBC batch in one connection/transaction
        try (Connection connection = DriverManager.getConnection(getJdbcUrl(), getJdbcUser(), getJdbcPassword())) {
            boolean originalAutoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
            try (Statement stmt = connection.createStatement()) {
                for (String sql : statements) {
                    // Add each statement to the JDBC batch
                    stmt.addBatch(sql);
                }

                // Execute batch and commit
                int[] results = stmt.executeBatch();
                connection.commit();
                System.out.println("Executed " + results.length + " DDL statements for insert tables.");
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    System.out.println("Failed to rollback after DDL batch failure: " + ex.getMessage());
                }
                System.out.println("\u001B[91mFailed to build/refresh insert tables\u001B[0m");
                e.printStackTrace();
            } finally {
                try {
                    connection.setAutoCommit(originalAutoCommit);
                } catch (SQLException ignored) {
                }
            }
        } catch (SQLException e) {
            System.out.println("\u001B[91mFailed to connect to database when building insert tables\u001B[0m");
            e.printStackTrace();
        }
    }



    // ------------------Build GMa Context for vyta ------------------------
    public void buildGmaContext() throws InvocationTargetException, IllegalAccessException {
        for(KdbGma kdbGma : kdbContext.getGmaConfigList()){
            GMAJson gma = jsonBuilder.buildJsonOfGma(kdbGma);
            kdbContext.addGMA(gma);


        }








        System.out.println("GMA Context built "+kdbGma.getName());

    }
//        //----------Build Classes ---------

    public void buildClasses() {
        try {
            GMAJson gma = kdbContext.getGmaByName(getGmaName());
            System.out.println("GSON GMA"+gson.toJson(gma));
            jsonToObjects(gma);
            Files.writeString(
                    Paths.get(workingDir, "db.json"),
                    gson.toJson(gma),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING
            );
            System.out.println("Classes built");
            String createValhalla = " create schema if not exists gma_resources";

            String createGmaConfigs = """
                    CREATE TABLE if not exists gma_resources.gma_configs (
                      `id` bigint NOT NULL AUTO_INCREMENT,
                      `name` varchar(255) NOT NULL,
                      `config_json` json NOT NULL,
                      `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                      PRIMARY KEY (`id`),
                      UNIQUE KEY `uk_gma_name` (`name`)
                    ) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
                    """;
            String createGmaItems= """
                    
                    create table if not exists  gma_resources.gma_items (
                    
                        db_ma varchar(150) not null,
                        db_table varchar(150) not null,
                        db_column varchar(150) not null,
                        column_json json not null,
                        Primary key (db_ma,db_table,db_column)
                    )
                    """;
            String sql = "INSERT INTO gma_resources.gma_configs (name, config_json) " +
                    "VALUES (?, ?) " +
                    "ON DUPLICATE KEY UPDATE config_json = VALUES(config_json)";

            String sql2 = """
                    INSERT INTO gma_resources.gma_structure (db_name,level_name,key_name)
                    VALUES(?,?,?)
                    ON DUPLICATE KEY UPDATE level_name,key_name  = VALUES(level_name,key_name)
                   
                    """;

            String columnFilter = """
                    
                    INSERT INTO gma_resources.gma_items (db_ma,db_table,db_column,column_json)
                    VALUES(?)
                    ON DUPLICATE KEY UPDATE column_json  = VALUES(column_json)
                   
                    """;

            System.out.println(getJdbcPassword()+" "+getJdbcUser()+" "+getJdbcUrl());
            try (Connection connection = DriverManager.getConnection(getJdbcUrl(), getJdbcUser(), getJdbcPassword());


                 PreparedStatement ps = connection.prepareStatement(sql);
                 PreparedStatement ps2 = connection.prepareStatement(sql2);

            )

            {
                connection.createStatement().execute(createValhalla);
                connection.createStatement().execute(createGmaConfigs);
                connection.createStatement().execute(createGmaItems);


                try{
                    ps.setString(1, gma.getName());
                    ps.setString(2, gson.toJson(gma));
                    ps.executeUpdate();
                }catch (Exception e){
                    System.out.println("\u001B[92m gma_configs failed \u001B[0m "+e);
                }

                try {
                    List<String> columnFilterValues = new ArrayList<>();

                    if(gma.getMa()==null){
                        System.out.println("\u001B[93m No MA found in GMA config, skipping gma_items insert. \u001B[0m");
                        return;
                    }
                    for (MAJson ma : gma.getMa()) {
                        String maName = ma.getName();
                        for (TableJson table : ma.getTables()) {
                            String tableName = table.getName();
                            for (ColumnJson column : table.getColumns()) {
                                String columnName = column.getName();
                                ItemJson itemJson = new ItemJson(columnName, column.getTags(), column.getDescription(), tableName, table.getTags(), table.getDescription(), maName, ma.getTags(), ma.getDescription());
                                String value = String.format("('%s','%s','%s','%s')", maName, tableName, columnName, gson.toJson(itemJson));
                                columnFilterValues.add(value);

                            }
                        }
                    }

                    String q = String.format("""
                    INSERT INTO gma_resources.gma_items (db_ma,db_table,db_column,column_json)
                    VALUES 
                        %s
                    ON DUPLICATE KEY UPDATE column_json  = VALUES(column_json)
                    """, String.join(",\n", columnFilterValues));
                    System.out.println(q);


                    String delete ="delete from gma_resources.gma_items";
                    connection.createStatement().execute(delete);
                    connection.createStatement().execute(q);

                }catch (Exception e){
                    System.out.println("\u001B[93m gma_items failed \u001B[0m "+e);
                    e.printStackTrace();
                }

            }

            catch (Exception e){
                System.out.println("\u001B[35mFailed to save GMA config to database.\u001B[0m"+e);

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
        GMAJson gma = kdbContext.getGmaByName(getGmaName());
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

        gma.setName(getGmaName()+"_db");
        kdbContext.addGMA(gma);

    }

    public void analyzeJson() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader("db.json"));
        BufferedReader readerF = new BufferedReader(new FileReader("db_from_db.json"));
        GMAJson vytaGma = gson.fromJson(reader, GMAJson.class);
        GMAJson vytaDbGma = gson.fromJson(readerF, GMAJson.class);



        GmaChecker checker = new GmaChecker(vytaGma, vytaDbGma);
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

