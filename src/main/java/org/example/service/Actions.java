package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.ClassOutputCreator.templates.KdbGma;
import org.example.ClassOutputCreator.templates.MAConfigTemplate;
import org.example.JsonBuilder.json.GMAJson;
import org.example.bank.OutputClassBank.KDBContext;
import org.example.JsonBuilder.DB.DbToJsonExtractor;
import org.example.JsonBuilder.IDE.JsonBuilder;
import org.example.JsonBuilder.json.ma.MAJson;
import org.example.JsonBuilder.json.ma.tables.ProcedureJson;
import org.example.JsonBuilder.json.ma.tables.TableJson;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

import static org.example.ClassOutputCreator.ClassCreator.getWorkingDirectory;
import static org.example.ClassOutputCreator.ClassCreator.jsonToObjects;
import static org.example.JsonBuilder.IDE.JsonBuilder.createGson;

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
            for(MAConfigTemplate ma :kdbGma.getMa()){
                System.out.println(ma.getJavaFolderPath());
            }
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


}



