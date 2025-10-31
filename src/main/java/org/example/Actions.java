package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.ContextInstance.KDBContext;
import org.example.gma.templates.KdbGma;
import org.example.jsonBuilder.builder.JsonBuilder;
import org.example.jsonBuilder.db.DbToJsonExtractor;
import org.example.jsonBuilder.gma.GMAJson;
import org.example.jsonBuilder.gma.ma.MAJson;
import org.example.jsonBuilder.gma.ma.tables.ProcedureJson;
import org.example.jsonBuilder.gma.ma.tables.TableJson;
import org.example.jsonBuilder.gma.ma.tables.columns.ColumnJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import static org.example.classbuilder.builder.ClassCreator.getWorkingDirectory;
import static org.example.classbuilder.builder.ClassCreator.jsonToObjects;
import static org.example.jsonBuilder.builder.JsonBuilder.createGson;

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

    // ------------------Build GMa Context for Dorm ------------------------
    public void buildGmaContext() throws InvocationTargetException, IllegalAccessException {

        GMAJson gma = jsonBuilder.buildJsonOfGma(kdbGma);
        kdbContext.addGMA(gma);
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("json written");
    }


//
//        ----------------Getting Json from DB ----------------


    public void getDbJson() throws SQLException, InvocationTargetException, IllegalAccessException, IOException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://dorm.mysql.database.azure.com:3306/leads", "kaeden", "tririllmIli1234?");
        System.out.println("Connected to the database.");
        GMAJson gma = kdbContext.getGmaByName("dorm");
        for(MAJson ma : gma.getMa()){
            List<TableJson> tables =DbToJsonExtractor.extractTables(connection,ma.getName());
            TableJson[] t = new TableJson[tables.size()];
            tables.toArray(t);

            ma.setTables(t);

            List<ProcedureJson> procedures = new ArrayList<>();
            for(TableJson table: tables){
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

        GmaChecker checker = new GmaChecker(dormGma,dormDbGma);
        Gson gson1 = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson1.toJson(checker));
        checker.reviewDifferencesWithPrompt();


//        System.out.println(checker.toString());



    }


}



