package org.example.service;

import org.example.JsonBuilder.json.GMAJson;
import org.example.JsonBuilder.json.ma.MAJson;
import org.example.JsonBuilder.json.ma.tables.TableJson;
import org.example.JsonBuilder.json.ma.tables.TriggerJson;
import org.example.JsonBuilder.json.ma.tables.UniqueKeyJson;
import org.example.JsonBuilder.json.ma.tables.columns.ColumnDTO;
import org.example.JsonBuilder.json.ma.tables.columns.ColumnJson;
import org.example.JsonBuilder.json.ma.tables.columns.IndexJson;
import org.example.bank.commonValues.TableTypes;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

import static org.example.bank.commonValues.AppConfig.*;


public class GmaChecker {

    String name;
    List<MAChecker> maCheckers;

    public void runUpdate(String query) {
        // Remove MySQL CLI-only syntax (DELIMITER)
        query = query
                .replace("DELIMITER $$", "")
                .replace("DELIMITER ;", "")
                .replace("$$", "")
                .replace("\u001B[3m", "").replace("\u001B[0m", ""); // Remove ANSI codes (ITALIC


        try (Connection connection = DriverManager.getConnection(
                getJdbcUrl(), getJdbcUser(), getJdbcPassword())) {
//            System.out.println(getJdbcPassword()+" "+getJdbcUser()+" "+getJdbcUrl());

            Statement stmt = connection.createStatement();

            // Split on semicolon to allow running multiple statements
            query = query.trim().replaceAll(";$", "");  // Remove trailing semicolon if present
            stmt.execute(query);
            System.out.println("Update executed successfully");


        } catch (SQLException e) {

            System.out.println("\u001B[31mSQL Exception: " + e.getMessage() + "\u001B[0m");
        }
    }




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GmaChecker(GMAJson dormGma, GMAJson dormDbGma) {


        System.out.println("Checking MA...");

        this.maCheckers = checkMA(dormGma.getMa().toArray(new MAJson[0]), dormDbGma.getMa().toArray(new MAJson[0]));
        this.name = dormGma.getName();


    }

    final String ITALIC = "\u001B[3m";
    final String RESET = "\u001B[0m";
    final String BOLD = "\u001B[1m";

    void reviewDifferencesWithPrompt() {
        Scanner scanner = new Scanner(System.in);
        String divider = "\u001B[36m" + "──────────────────────────────────────────────────────────────" + "\u001B[0m";

        for (MAChecker maChecker : maCheckers) {
            System.out.println("\n\u001B[1;34m🔍 Reviewing MA:\u001B[0m " + maChecker.getName());
            System.out.println(divider);

            List<TableChecker> tables = maChecker.getTableCheckers();
            if (tables == null || tables.isEmpty()) continue;

            for (TableChecker tableChecker : tables) {
                System.out.println("\n-\n-\n-");

                System.out.printf("\n\u001B[1;33m📘 Table:\u001B[0m %s.%s%n", maChecker.getName(), tableChecker.getName());
                String tableName = maChecker.getName() + "." + tableChecker.getName();
                String schemaName = maChecker.getName();

                // 🔹 Column Differences
                if(tableChecker.getTabCheckList()!=null){
                    for (TabCheck tabCheck : tableChecker.getTabCheckList()) {
                        System.out.printf("   • TYPE: %-15s | NAME: %-20s | DIFF: %s%n",
                                tabCheck.getType(), tabCheck.getName(), tabCheck.getDifference());
                        askContinueTab(scanner, tabCheck.getTable().getColumns(),tabCheck.getTable().getIndexes(),tabCheck.getTable().getUniqueKeys(),tableName);
                    }
                }
                if (tableChecker.getColCheckMap() != null) {
                    System.out.println("\n\u001B[1;32m🧩 Column Differences:\u001B[0m");
                    for (List<ColCheck> colChecks : tableChecker.getColCheckMap().values()) {
                        for (ColCheck colCheck : colChecks) {
                            System.out.printf("   • TYPE: %-15s | NAME: %-20s | DIFF: %s%n",
                                    colCheck.getType(), colCheck.getName(), colCheck.getDifference());
                            askContinueCol(scanner, colCheck.getName(), colCheck.getType(), colCheck.getChangeType(), tableName, colCheck.getColumn());
                        }
                    }
                }

                // 🔹 Trigger Differences
                if (tableChecker.getTriggerCheckMap() != null) {
                    System.out.println("\n\u001B[1;35m⚡ Trigger Differences:\u001B[0m");
                    for (List<TriggerCheck> triggerChecks : tableChecker.getTriggerCheckMap().values()) {
                        for (TriggerCheck trigCheck : triggerChecks) {
                            System.out.printf("   • TYPE: %-15s | NAME: %-20s | DIFF: %s%n",
                                    trigCheck.getType(), trigCheck.name, trigCheck.getDifference());
                            askContinueTrigger(scanner, trigCheck.getType(), trigCheck.getName(),
                                    trigCheck.getTriggerContent(), trigCheck.getTriggerType(), tableName, schemaName);
                        }
                    }
                }

                // 🔹 Index Differences
                if (tableChecker.getIndexesCheckMap() != null) {
                    System.out.println("\n\u001B[1;36m📊 Index Differences:\u001B[0m");
                    for (List<IndexesCheck> indexChecks : tableChecker.getIndexesCheckMap().values()) {
                        for (IndexesCheck idxCheck : indexChecks) {
                            System.out.printf("   • TYPE: %-15s | NAME: %-20s | DIFF: %s%n",
                                    idxCheck.getType(), idxCheck.name, idxCheck.getDifference());
                            askContinueIndex(scanner, idxCheck.getType(), tableName, idxCheck.getName(), idxCheck.getColumns());
                        }
                    }
                }

                // 🔹 Unique Key Differences
                if (tableChecker.getUniqueKeyCheckMap() != null) {
                    System.out.println("\n\u001B[1;31m🔑 Unique Key Differences:\u001B[0m");
                    for (List<UniqueKeyCheck> uniqueKeyChecks : tableChecker.getUniqueKeyCheckMap().values()) {
                        for (UniqueKeyCheck ukCheck : uniqueKeyChecks) {
                            System.out.printf("   • TYPE: %-15s | NAME: %-20s | DIFF: %s%n",
                                    ukCheck.getType(), ukCheck.name, ukCheck.getDifference());
                            askContinueUniqueKey(scanner, ukCheck.getType(), tableName, ukCheck.getName(), ukCheck.getColumns());
                        }
                    }
                }
            }
        }

        System.out.println("\n\u001B[1;32m✅ All differences reviewed successfully.\u001B[0m\n");
    }




    private String indexQueryCreator(IndexJson[] indexes) {
        if (indexes == null || indexes.length == 0) return "";

        List<String> q = new ArrayList<>();

        for (IndexJson idx : indexes) {
            if (idx.getIndexGroupName().contains("_fk") ||
                    idx.getIndexGroupName().contains("_key") ||
                    idx.getIndexGroupName().contains("PRIMARY")) {
                continue;
            }

            q.add(String.format("    KEY %s (%s)",
                    idx.getIndexGroupName(),
                    idx.getColumns().stream()
                            .map(ColumnDTO::getName)
                            .collect(Collectors.joining(", "))
            ));
        }

        return String.join(",\n", q);
    }
    private String uniqueCreator(UniqueKeyJson[] keys) {
        StringBuilder query = new StringBuilder();
        List<String> queries = new ArrayList<>();

        // Check if keys array is null or empty
        if (keys == null || keys.length == 0) {
            return query.toString();
        }

        for (UniqueKeyJson uk : keys) {
            // Skip if the unique key name contains "PRIMARY" or if columns are null/less than 2

            if (uk == null || uk.getName() == null || uk.getColumns() == null || uk.getColumns().length < 2) {
                continue;
            }
            query = new StringBuilder();

            query.append(String.format("    CONSTRAINT `%s` UNIQUE (%s)\n",
                    uk.getName(),
                    Arrays.stream(uk.getColumns())
                            .map(ColumnDTO::getName)
                            .collect(Collectors.joining(", "))));

            queries.add(query.toString());
        }



        return String.join(",",queries);
    }

    private String columnQueryCreator(ColumnJson[] columns) {
        if (columns == null || columns.length == 0) return "";

        List<String> q = new ArrayList<>();
        List<String> primaryKeys = new ArrayList<>();

        for (ColumnJson col : columns) {
            String defaultValue = "";
            String nullable = "";

            nullable = col.isNullable()? "NULL": "NOT NULL";
            defaultValue = (col.getDefaultValue() != null && !col.getDefaultValue().trim().isEmpty())
                    ? "DEFAULT " + col.getDefaultValue()
                    : "";

            if (col.isPrimaryKey()) {
                primaryKeys.add(col.getName());
                nullable ="";
                if(Objects.equals(col.getDefaultValue(), "AUTO_INCREMENT")){
                    defaultValue = col.getDefaultValue();
                }
            }

            q.add(String.format("    %s %s %s %s",
                    col.getName(),
                    col.getType(),
                    nullable,
                    defaultValue

//                    col.getPrimaryKey() ? " PRIMARY KEY" : ""
            ));
        }
        String p = "PRIMARY KEY (" + String.join(", ", primaryKeys) + ")";
        q.add(p);

        return String.join(",\n", q);
    }

    // ────────────────────────────── COLOR CONSTANTS ──────────────────────────────
    private static final String RED    = "\u001B[31m";
    private static final String GREEN  = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String CYAN   = "\u001B[36m";


    // ────────────────────────────── REUSABLE PROMPT ──────────────────────────────
    private String askYesNo(Scanner scanner) {
        System.out.print(ITALIC + "Do you want to update the DB? (Y/N): " + RESET);
        String input = scanner.nextLine().trim().toUpperCase();
        while (!input.equals("Y") && !input.equals("N")) {
            System.out.print("\t\tPlease enter Y or N: ");
            input = scanner.nextLine().trim().toUpperCase();
        }
        return input;
    }

    private void pauseForEnter(Scanner scanner) {
        System.out.println(YELLOW + "Press Enter to continue to next difference..." + RESET);
        scanner.nextLine();
    }

    // ────────────────────────────── 1. askContinueTab ──────────────────────────────
    private void askContinueTab(Scanner scanner, ColumnJson[] columns, IndexJson[] indexes,
                                UniqueKeyJson[] keys, String tableName) {
        String colGen = columnQueryCreator(columns);
        String idxGen = indexQueryCreator(indexes);
        String keyGen = uniqueCreator(keys);

        List<String> parts = new ArrayList<>();
        if (!colGen.isEmpty()) parts.add(colGen);
        if (!idxGen.isEmpty()) parts.add(idxGen);
        if (!keyGen.isEmpty()) parts.add(keyGen);

        if (parts.isEmpty()) {
            System.out.println(YELLOW + "No changes to apply for this table." + RESET);
            pauseForEnter(scanner);
            return;
        }

        String createSQL = String.format("CREATE TABLE %s (\n%s\n);", tableName, String.join(",\n", parts));
        System.out.println(CYAN + createSQL + RESET);

        if (askYesNo(scanner).equals("Y")) {
            System.out.println(GREEN + "\"Inserting table creation...\"" + RESET);
            try {
                runUpdate(createSQL);
                System.out.println(GREEN + "Table created successfully!" + RESET);
            } catch (Exception e) {
                System.out.println(RED + "Failed to create table (continuing): " + e.getMessage() + RESET);
            }
        } else {
            System.out.println(YELLOW + "Skipped table creation." + RESET);
        }
        pauseForEnter(scanner);
    }

    // ────────────────────────────── 2. askContinueUniqueKey ──────────────────────────────
    private void askContinueUniqueKey(Scanner scanner, String type, String tableName,
                                      String keyName, ColumnDTO[] columns) {
        List<String> replace = new ArrayList<>();
        String insert = null;

        switch (type) {
            case "key IDE column", "key IDE" -> {
                replace.add(String.format(ITALIC + "ALTER TABLE %s DROP CONSTRAINT  `%s`;" + RESET, tableName, keyName)) ;
                for(ColumnDTO c :columns){
                    replace.add(String.format(ITALIC + "ALTER TABLE %s DROP CONSTRAINT `%s`;" + RESET, tableName, c.getName()));
                }
                insert  = String.format(ITALIC + "ALTER TABLE %s ADD CONSTRAINT `%s` UNIQUE (%s);" + RESET,
                        tableName, keyName, Arrays.stream(columns).map(ColumnDTO::getName).collect(Collectors.joining(", ")));
            }
            case "key DB" -> {
                replace.add(String.format(ITALIC + "ALTER TABLE %s DROP CONSTRAINT  `%s`;" + RESET, tableName, keyName)) ;
                for(ColumnDTO c :columns){
                    replace.add(String.format(ITALIC + "ALTER TABLE %s DROP CONSTRAINT `%s`;" + RESET, tableName, c.getName()));
                }
            }
            default -> {
                System.out.println(YELLOW + "\"No action for this unique key type.\"" + RESET);
                pauseForEnter(scanner);
                return;
            }
        }

        if (replace != null) System.out.println(replace);
        if (insert != null)  System.out.println(insert);

        if (askYesNo(scanner).equals("Y")) {
            System.out.println(GREEN + "\"Applying unique key change...\"" + RESET);
            try {
                if (replace .size()>0){
                    for(String r : replace){
                        runUpdate(r);
                    }

                }
                if (insert != null)  runUpdate(insert);
                System.out.println(GREEN + "Unique key updated successfully!" + RESET);
            } catch (Exception e) {
                System.out.println(RED + "Unique key update failed (continuing): " + e.getMessage() + RESET);
            }
        } else {
            System.out.println(YELLOW + "Skipped unique key change." + RESET);
        }
        pauseForEnter(scanner);
    }

    // ────────────────────────────── 3. askContinueCol ──────────────────────────────
    private void askContinueCol(Scanner scanner, String columnName, String type, String changeType,
                                String tableName, ColumnJson col) {
        String replace = null, insert = null;
        String defaultValue  = "";
        if(col.getDefaultValue()!=null && !Objects.equals(col.getDefaultValue(), "")){
            if(col.isPrimaryKey()&&col.getDefaultValue().equalsIgnoreCase("AUTO_INCREMENT")){
//                replace = String.format("Alter table %s DROP index uq_primary_key",tableName);

                defaultValue = " AUTO_INCREMENT ";
            }else {
                defaultValue = " DEFAULT " + col.getDefaultValue();
            }
        }
        String def = String.format("%s %s %s %s %s",
                columnName,
                col.getType(),
                col.isNullable() ? "NULL" : "NOT NULL",
                col.isUnique() && !col.isPrimaryKey() ? " UNIQUE" : "",                           // note the leading space
                defaultValue
        );

        switch (type) {
            case "type", "isNullable", "isUnique DB","defaultValue" -> {

                insert = String.format(ITALIC + "ALTER TABLE %s MODIFY COLUMN %s;" + RESET, tableName, def.trim());
            }
            case "column DB" -> replace = String.format(ITALIC + "ALTER TABLE %s DROP COLUMN %s;" + RESET, tableName, columnName);
            case "column IDE" -> {

                replace = String.format(ITALIC + "ALTER TABLE %s ADD COLUMN %s;" + RESET, tableName, def.trim());
            }
            case "isUnique IDE" -> insert = String.format(ITALIC + "ALTER TABLE %s ADD CONSTRAINT %s UNIQUE (%s);" + RESET,
                    tableName, columnName + "_unique", columnName);

            default -> {
                System.out.println(YELLOW + "\"No action for this column type.\"" + RESET);
                pauseForEnter(scanner);
                return;
            }
        }

        if (replace != null) System.out.println("\n" + replace);
        if (insert != null)  System.out.println("\n" + insert);

        if (askYesNo(scanner).equals("Y")) {
            System.out.println(GREEN + "\"Applying column change...\"" + RESET);
            try {
                if (replace != null) runUpdate(replace);
                if (insert != null)  runUpdate(insert);
                System.out.println(GREEN + "Column updated successfully!" + RESET);
            } catch (Exception e) {
                System.out.println(RED + "Column update failed (continuing): " + e.getMessage() + RESET);
            }
        } else {
            System.out.println(YELLOW + "Skipped column change." + RESET);
        }
        pauseForEnter(scanner);
    }

    // ────────────────────────────── 4. askContinueTrigger ──────────────────────────────
    private void askContinueTrigger(Scanner scanner, String type, String triggerName,
                                    String triggerContent, String triggerType, String tableName, String schemaName) {
        String replace = null, insert = null;

        switch (type) {
            case "trigger IDE" -> insert = ITALIC + """
                DELIMITER $$
                CREATE TRIGGER %s.%s %s ON %s FOR EACH ROW
                %s$$
                DELIMITER ;""".formatted(schemaName, triggerName, triggerType, tableName, triggerContent) + RESET;

            case "trigger DB" -> replace = String.format(ITALIC + "DROP TRIGGER IF EXISTS %s.%s;" + RESET, schemaName, triggerName);

            case "trigger content", "trigger type" -> {
                replace = String.format(ITALIC + "DROP TRIGGER IF EXISTS %s.%s;" + RESET, schemaName, triggerName);
                insert  = ITALIC + """
                    DELIMITER $$
                    CREATE TRIGGER %s.%s %s ON %s FOR EACH ROW
                    %s$$
                    DELIMITER ;""".formatted(schemaName, triggerName, triggerType, tableName, triggerContent) + RESET;
            }
            default -> {
                System.out.println(YELLOW + "\"No action for this trigger type.\"" + RESET);
                pauseForEnter(scanner);
                return;
            }
        }

        System.out.println();
        if (replace != null) System.out.println(replace);
        if (insert != null)  System.out.println(insert);

        if (askYesNo(scanner).equals("Y")) {
            System.out.println(GREEN + "\"Applying trigger change...\"" + RESET);
            try {
                if (replace != null) runUpdate(replace);
                if (insert != null)  runUpdate(insert);
                System.out.println(GREEN + "Trigger updated successfully!" + RESET);
            } catch (Exception e) {
                System.out.println(RED + "Trigger update failed (continuing): " + e.getMessage() + RESET);
            }
        } else {
            System.out.println(YELLOW + "Skipped trigger change." + RESET);
        }
        pauseForEnter(scanner);
    }

    // ────────────────────────────── 5. askContinueIndex ──────────────────────────────
    private void askContinueIndex(Scanner scanner, String type, String tableName,
                                  String idx_name, ColumnDTO[] columns) {
        String replace = null, insert = null;

        switch (type) {
            case "index IDE columns", "index IDE" -> {
                replace = String.format(ITALIC + "DROP INDEX %s ON %s;" + RESET, idx_name, tableName);
                insert  = String.format(ITALIC + "CREATE INDEX %s ON %s (%s);" + RESET,
                        idx_name, tableName,
                        Arrays.stream(columns).map(ColumnDTO::getName).collect(Collectors.joining(", ")));
            }
            case "index DB" -> replace = String.format(ITALIC + "DROP INDEX %s ON %s;" + RESET, idx_name, tableName);
            default -> {
                System.out.println(YELLOW + "\"No action for this index type.\"" + RESET);
                pauseForEnter(scanner);
                return;
            }
        }

        if (replace != null) System.out.println(replace);
        if (insert != null)  System.out.println(insert);

        if (askYesNo(scanner).equals("Y")) {
            System.out.println(GREEN + "\"Applying index change...\"" + RESET);
            try {
                if (replace != null) runUpdate(replace);
                if (insert != null)  runUpdate(insert);
                System.out.println(GREEN + "Index updated successfully!" + RESET);
            } catch (Exception e) {
                System.out.println(RED + "Index update failed (continuing): " + e.getMessage() + RESET);
            }
        } else {
            System.out.println(YELLOW + "Skipped index change." + RESET);
        }
        pauseForEnter(scanner);
    }

//    private void askContinue(Scanner scanner,String type,String tableName,ColumnDTO[] columns) {
//        System.out.print("Do you want to continue? (Y/N): ");
//        String input = scanner.nextLine().trim().toUpperCase();
//
//        while (!input.equals("Y") && !input.equals("N")) {
//            System.out.print("Please enter Y or N: ");
//            input = scanner.nextLine().trim().toUpperCase();
//
//
//        }
//
//
//        if (input.equals("N")) {
//            System.out.println("Exiting program as requested.");
//            System.exit(0);
//        }
//
//    }


    public List<MAChecker> checkMA(MAJson[] mas, MAJson[] maDbs) {
        List<MAChecker> maCheckers = new ArrayList<>();
        List<MAJson> remainingDbMAs = new ArrayList<>(
                Arrays.stream(maDbs)
                        .filter(ma -> ma.getUploadType() == null || !ma.getUploadType().equalsIgnoreCase("airtable"))
                        .toList()   // toList() returns an immutable list in Java 16+
        );

        int maxLength = Math.max(mas.length, maDbs.length);

        for (int i = 0; i < maxLength; i++) {
            MAJson ma = i < mas.length ? mas[i] : null;
            MAJson maDb = null;

            if (ma != null) {
                maDb = Arrays.stream(maDbs)
                        .filter(db -> db.getName().equalsIgnoreCase(ma.getName()))
                        .findFirst()
                        .orElse(null);

                if (maDb != null) remainingDbMAs.remove(maDb);
            }

            List<MACheck> maCheckList = new ArrayList<>();
            List<TableChecker> tableCheckers = new ArrayList<>();

            if (ma != null) {
                if (maDb == null) {
                    // MA missing in DB
                    MACheck maCheck = new MACheck(ma.getName());
                    maCheck.setType("ma");
                    maCheck.setDifference("IDE: " + ma.getName() + ", DB: null");
                    maCheckList.add(maCheck);
                } else {
                    // Compare tables
                    tableCheckers = checkTabs(ma.getTables(), maDb.getTables());
                }

                MAChecker maChecker = new MAChecker(ma.getName());
                maChecker.setMaCheckList(maCheckList);
                maChecker.setTableCheckers(tableCheckers);
                maCheckers.add(maChecker);
            }
        }

        // Handle DB-only MAs
        for (MAJson dbMA : remainingDbMAs) {
            List<MACheck> dbOnlyList = new ArrayList<>();
            MACheck maCheck = new MACheck(dbMA.getName());
            maCheck.setType("ma");
            maCheck.setDifference("IDE: null, DB: " + dbMA.getName());
            dbOnlyList.add(maCheck);

            MAChecker maChecker = new MAChecker(dbMA.getName());
            maChecker.setMaCheckList(dbOnlyList);
            maCheckers.add(maChecker);
        }

        return maCheckers;
    }


    private List<TableChecker> checkTabs(TableJson[] tables, TableJson[] tableDbs) {
        List<TableChecker> tableCheckers = new ArrayList<>();
        List<TableJson> remaining = new ArrayList<>(List.of(tableDbs));

        int maxLength = Math.max(tables.length, tableDbs.length);

        for (int i = 0; i < maxLength; i++) {
            TableJson table = null;
            TableJson tableDb = null;

            try {
                if (i < tables.length) {
                    table = tables[i];
                    TableJson finalTable = table;
                    tableDb = Arrays.stream(tableDbs)
                            .filter(tdb -> tdb.getName().equalsIgnoreCase(finalTable.getName()))
                            .findFirst()
                            .orElse(null);

                    if (tableDb != null) remaining.remove(tableDb);
                }

                List<TabCheck> tabCheckList = new ArrayList<>();
                Map<String, List<ColCheck>> colsCheckMap = new HashMap<>();
                Map<String, List<TriggerCheck>> triggerCheckMap = new HashMap<>();
                Map<String, List<IndexesCheck>> indexesCheckMap = new HashMap<>();
                Map<String, List<UniqueKeyCheck>> uniqueKeyCheckMap = new HashMap<>();

                if (table != null) {
                    Boolean isReport;
                    isReport = table.getTableType().equalsIgnoreCase(TableTypes.REPORT);
                    // Table exists in IDE
                    if (tableDb == null) {
                        // Table missing in DB
                        TabCheck tabCheck = new TabCheck(table.getName(),table);
                        tabCheck.setType("table");
                        tabCheck.setDifference("IDE: " + table.getName() + ", DB: null");
                        tabCheckList.add(tabCheck);
                    } else {
                        // Check differences
                        colsCheckMap = checkCols(table.getColumns(), tableDb.getColumns(),isReport);
                        triggerCheckMap = checkTriggers(table.getTriggers(), tableDb.getTriggers());
                        indexesCheckMap = checkIndexes(table.getIndexes(), tableDb.getIndexes());
                        uniqueKeyCheckMap = checkUnique(table.getUniqueKeys(), tableDb.getUniqueKeys());
                    }

                    TableChecker tableChecker = new TableChecker(table.getName());
                    tableChecker.setTabCheckList(tabCheckList);
                    tableChecker.setColCheckMap(colsCheckMap);
                    tableChecker.setTriggerCheckMap(triggerCheckMap);
                    tableChecker.setIndexesCheckMap(indexesCheckMap);
                    tableChecker.setUniqueKeyCheckMap(uniqueKeyCheckMap);
                    tableCheckers.add(tableChecker);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error checking table: " + table + " vs DB: " + tableDb, e);
            }
        }

        // Handle any remaining DB-only tables
//        for (TableJson t : remaining) {
//            List<TabCheck> tabCheckList = new ArrayList<>();
//            TabCheck tabCheck = new TabCheck(t.getName(),t);
//            tabCheck.setType("table");
//            tabCheck.setDifference("IDE: null, DB: " + t.getName());
//            tabCheckList.add(tabCheck);
//
//            TableChecker tableChecker = new TableChecker(t.getName());
//            tableChecker.setTabCheckList(tabCheckList);
//            tableCheckers.add(tableChecker);
//        }

        return tableCheckers;
    }

    private Map<String, List<UniqueKeyCheck>> checkUnique(UniqueKeyJson[] uniqueKeys, UniqueKeyJson[] uniqueKeysDb) {
        Map<String, List<UniqueKeyCheck>> uniqueKeyCheckMap = new HashMap<>();
        List<UniqueKeyJson> remaining = new ArrayList<>(List.of(uniqueKeysDb));

        int maxLength = Math.max(uniqueKeys.length, uniqueKeysDb.length);

        for (int i = 0; i < maxLength; i++) {
            UniqueKeyJson uniqueKey = i < uniqueKeys.length ? uniqueKeys[i] : null;
            UniqueKeyJson uniqueKeyDb = null;

            if (uniqueKey != null) {
                uniqueKeyDb = Arrays.stream(uniqueKeysDb)
                        .filter(ukDb -> ukDb.getName().equalsIgnoreCase(uniqueKey.getName()))
                        .findFirst()
                        .orElse(null);

                if (uniqueKeyDb != null) remaining.remove(uniqueKeyDb);
            }

            List<UniqueKeyCheck> uniqueKeyChecksList = new ArrayList<>();

            if (uniqueKey != null) {
                // Skip primary key group
                if (Objects.equals(uniqueKey.getName(), "PrimaryKeyGroup")) continue;

                // Missing entirely in DB
                if (uniqueKeyDb == null && uniqueKey.getColumns().length > 1) {
                    UniqueKeyCheck check = new UniqueKeyCheck(uniqueKey.getName(), uniqueKey.getColumns());
                    check.setType("key IDE");
                    check.setDifference("IDE: " + uniqueKey.getName() + ", DB: null");
                    uniqueKeyChecksList.add(check);
                }

                // Compare when both exist and have >1 columns
                else if (uniqueKeyDb != null && uniqueKey.getColumns().length > 1) {
                    // Collect IDE and DB column names
                    Set<String> ideCols = Arrays.stream(uniqueKey.getColumns())
                            .map(ColumnDTO::getName)
                            .collect(Collectors.toSet());

                    Set<String> dbCols = Arrays.stream(uniqueKeyDb.getColumns())
                            .map(ColumnDTO::getName)
                            .collect(Collectors.toSet());

                    // Determine differences
                    Set<String> missingInDb = new HashSet<>(ideCols);
                    missingInDb.removeAll(dbCols);

                    Set<String> extraInDb = new HashSet<>(dbCols);
                    extraInDb.removeAll(ideCols);

                    // Only add if there are differences
                    if (!missingInDb.isEmpty() || !extraInDb.isEmpty()) {
                        UniqueKeyCheck check = new UniqueKeyCheck(uniqueKey.getName(), uniqueKey.getColumns());
                        check.setType("key IDE columns");
                        check.setDifference("Missing in DB: " + missingInDb + ", Extra in DB: " + extraInDb);
                        uniqueKeyChecksList.add(check);
                    }
                }

                if (!uniqueKeyChecksList.isEmpty()) {
                    uniqueKeyCheckMap.put(uniqueKey.getName(), uniqueKeyChecksList);
                }
            }
        }

        // Handle DB-only keys (not present in IDE)
        for (UniqueKeyJson ukDb : remaining) {
            if (ukDb.getColumns().length > 1 && !"PrimaryKeyGroup".equalsIgnoreCase(ukDb.getName())) {
                List<UniqueKeyCheck> dbOnlyList = new ArrayList<>();
                UniqueKeyCheck check = new UniqueKeyCheck(ukDb.getName(), ukDb.getColumns());
                check.setType("key DB");
                check.setDifference("IDE: null, DB: " + ukDb.getName());
                dbOnlyList.add(check);
                uniqueKeyCheckMap.put(ukDb.getName(), dbOnlyList);
            }
        }

        return uniqueKeyCheckMap;
    }


    private Map<String, List<IndexesCheck>> checkIndexes(IndexJson[] indexes, IndexJson[] indexesDb) {
        Map<String, List<IndexesCheck>> indexesCheckMap = new HashMap<>();
        List<IndexJson> remaining = new ArrayList<>(List.of(indexesDb));

        int maxLength = Math.max(indexes.length, indexesDb.length);

        for (int i = 0; i < maxLength; i++) {
            IndexJson index = i < indexes.length ? indexes[i] : null;
            IndexJson indexDb = null;

            if (index != null) {
                indexDb = Arrays.stream(indexesDb)
                        .filter(idb -> idb.getIndexGroupName().equalsIgnoreCase(index.getIndexGroupName()))
                        .findFirst().orElse(null);

                if (indexDb != null) remaining.remove(indexDb);
            }

            List<IndexesCheck> indexesChecksList = new ArrayList<>();

            if (index != null) {
                if (indexDb == null) {
                    // Index missing in DB
                    if (!index.getIndexGroupName().contains("_fk") && !index.getIndexGroupName().contains("_key") && !index.getIndexGroupName().contains("PRIMARY")) {
                        IndexesCheck check = new IndexesCheck(index.getIndexGroupName(), index.getColumns().toArray(new ColumnDTO[0]));
                        check.setType("index IDE");
                        check.setDifference("IDE: " + index.getIndexGroupName() + ", DB: null");
                        indexesChecksList.add(check);
                    }
                } else {
                    Set<String> ideColumnNames = index.getColumns().stream()
                            .map(ColumnDTO::getName)
                            .collect(Collectors.toSet());

                    // DB column names
                    Set<String> dbColumnNames = indexDb.getColumns().stream()
                            .map(ColumnDTO::getName)
                            .collect(Collectors.toSet());

                    // Columns in IDE but not in DB
                    Set<String> missingInDb = new HashSet<>(ideColumnNames);
                    missingInDb.removeAll(dbColumnNames);

                    // Columns in DB but not in IDE
                    Set<String> extraInDb = new HashSet<>(dbColumnNames);
                    extraInDb.removeAll(ideColumnNames);

                    if (!missingInDb.isEmpty() || !extraInDb.isEmpty()) {
                        IndexesCheck check = new IndexesCheck(index.getIndexGroupName(),
                                index.getColumns().toArray(new ColumnDTO[0]));
                        check.setType("index IDE columns");
                        check.setDifference("Missing in DB: " + missingInDb + ", Extra in DB: " + extraInDb);

                        indexesChecksList.add(check);
                    }


                    // Check index name
//                    if (!Objects.equals(index.getIndexGroupName(), indexDb.getIndexGroupName())&&!index.getIndexGroupName().contains("_fk")&&!index.getIndexGroupName().contains("_key")) {
//                        IndexesCheck check = new IndexesCheck(index.getIndexGroupName(),index.getColumns().toArray(new ColumnDTO[0]));
//                        check.setType("index group name2");
//                        check.setDifference("IDE: " + index.getIndexGroupName() + ", DB: " + indexDb.getIndexGroupName());
//                        indexesChecksList.add(check);
//                    }

                    // Check index columns
//                    List<ColumnDTO> remainingCols = new ArrayList<>(indexDb.getColumns());

//                    for (ColumnDTO col : index.getColumns()) {
//                        ColumnDTO colDb = indexDb.getColumns().stream()
//                                .filter(c -> c.getName().equalsIgnoreCase(col.getName()))
//                                .findFirst().orElse(null);
//
//                        if (colDb != null) remainingCols.remove(colDb);
//
//                        if (colDb == null) {
//                            IndexesCheck check = new IndexesCheck(index.getIndexGroupName(),index.getColumns().toArray(new ColumnDTO[0]));
//                            check.setType("index IDE Column");
//                            check.setDifference("IDE: " + col.getName() + ", DB: null");
//                            indexesChecksList.add(check);
//                        }
//                    }
//
//                    // Remaining DB-only columns
//                    IndexesCheck check = new IndexesCheck(index.getIndexGroupName(),index.getColumns().toArray(new ColumnDTO[0]));
//                    for (ColumnDTO col : remainingCols) {
//
//ask
//                    }
//                    check.setType("index DB Columns");
//
//                    check.setDifference("IDE: null, DB: " + String.join(",",col.getName());
//                    indexesChecksList.add(check);
//                }
                }
                indexesCheckMap.put(index.getIndexGroupName(), indexesChecksList);
            }
        }

        // Handle DB-only indexes
        for (IndexJson ind : remaining) {
            if (ind.getIndexGroupName().contains("_fk") || ind.getIndexGroupName().contains("fk") || ind.getIndexGroupName().contains("foreignkey") || ind.getIndexGroupName().contains("foreign_key") || ind.getIndexGroupName().contains("PRIMARY"))
                continue;
            List<IndexesCheck> checkList = new ArrayList<>();
            IndexesCheck check = new IndexesCheck(ind.getIndexGroupName(), ind.getColumns().toArray(new ColumnDTO[0]));
            check.setType("index DB");
            check.setDifference("IDE: null, DB: " + ind.getIndexGroupName());
            checkList.add(check);
            indexesCheckMap.put(ind.getIndexGroupName(), checkList);
        }

        return indexesCheckMap;
    }


    private Map<String, List<TriggerCheck>> checkTriggers(TriggerJson[] tableTriggers, TriggerJson[] tableDbTriggers) {
        Map<String, List<TriggerCheck>> triggerCheckMap = new HashMap<>();
        List<TriggerJson> remainingDbTriggers = new ArrayList<>(List.of(tableDbTriggers));

        int maxLength = Math.max(tableTriggers.length, tableDbTriggers.length);

        for (int i = 0; i < maxLength; i++) {
            TriggerJson trigger = i < tableTriggers.length ? tableTriggers[i] : null;
            TriggerJson triggerDb = null;

            if (trigger != null) {
                triggerDb = Arrays.stream(tableDbTriggers)
                        .filter(tdb -> tdb.getName().equalsIgnoreCase(trigger.getName()))
                        .findFirst()
                        .orElse(null);

                if (triggerDb != null) remainingDbTriggers.remove(triggerDb);
            }

            List<TriggerCheck> triggerCheckList = new ArrayList<>();

            if (trigger != null) {
                if (triggerDb == null) {
                    // Trigger missing in DB
                    TriggerCheck check = new TriggerCheck(trigger.getName(), trigger.getTriggerType(), trigger.getTriggerString());
                    check.setType("trigger IDE");
                    check.setDifference("IDE: " + trigger.getName() + ", DB: null");
                    triggerCheckList.add(check);
                } else {
                    // Name mismatch (should rarely happen)
//                    if (!Objects.equals(trigger.getName(), triggerDb.getName())) {
//                        TriggerCheck check = new TriggerCheck(trigger.getName());
//                        check.setType("trigger ");
//                        check.setDifference("IDE: " + trigger.getName() + ", DB: " + triggerDb.getName());
//                        triggerCheckList.add(check);
//                    }

                    // Type difference
                    if (!Objects.equals(trigger.getTriggerType(), triggerDb.getTriggerType())) {
                        TriggerCheck check = new TriggerCheck(trigger.getName(), trigger.getTriggerType(), trigger.getTriggerString());
                        check.setType("trigger type");
                        check.setDifference("IDE: " + (trigger.getTriggerType() == null ? "null" : trigger.getTriggerType())
                                + ", DB: " + (triggerDb.getTriggerType() == null ? "null" : triggerDb.getTriggerType()));
                        triggerCheckList.add(check);
                    }

                    // Trigger string difference
                    String ideStr = trigger.getTriggerString() == null ? "" : trigger.getTriggerString().replaceAll("\\s+", "").toLowerCase().trim();
                    String dbStr = triggerDb.getTriggerString() == null ? "" : triggerDb.getTriggerString().replaceAll("\\s+", "").toLowerCase().trim();

                    if (!Objects.equals(ideStr, dbStr)) {
                        TriggerCheck check = new TriggerCheck(trigger.getName(), trigger.getTriggerType(), trigger.getTriggerString());
                        check.setType("trigger content");
                        check.setDifference("IDE: " + (trigger.getTriggerString() == null ? "null" : trigger.getTriggerString())
                                + ", DB: " + (triggerDb.getTriggerString() == null ? "null" : triggerDb.getTriggerString()));
                        triggerCheckList.add(check);
                    }
                }

                triggerCheckMap.put(trigger.getName(), triggerCheckList);
            }
        }

        // Handle DB-only triggers
        for (TriggerJson t : remainingDbTriggers) {
            List<TriggerCheck> dbOnlyCheckList = new ArrayList<>();
            TriggerCheck check = new TriggerCheck(t.getName(), t.getTriggerType(), t.getTriggerString());
            check.setType("trigger DB");
            check.setDifference("IDE: null, DB: " + t.getName());
            dbOnlyCheckList.add(check);
            triggerCheckMap.put(t.getName(), dbOnlyCheckList);
        }

        return triggerCheckMap;
    }


    private Map<String, List<ColCheck>> checkCols(ColumnJson[] cols, ColumnJson[] colDbs, Boolean isReport) {
        Map<String, List<ColCheck>> columnCheckMap = new HashMap<>();
        List<ColumnJson> remaining = new ArrayList<>(List.of(colDbs));

        for (ColumnJson col : cols) {
            List<ColCheck> colCheckList = new ArrayList<>();
            ColumnJson colDb = Arrays.stream(colDbs)
                    .filter(c -> c.getName().equalsIgnoreCase(col.getName()))
                    .findFirst()
                    .orElse(null);

            if (colDb != null) remaining.remove(colDb);

            if (colDb == null) {
                ColCheck colCheck = new ColCheck(col.getName(), col.getType());
                colCheck.setColumn(col);
                colCheck.setType("column IDE");
                colCheck.setDifference("IDE: " + col.getName() + ", DB: null");
                colCheckList.add(colCheck);
            } else {
                // Compare types
                if (col.getType() == null) {
                    throw new RuntimeException("Column type is null for column: " + col.getName() + " in IDE.");
                }
                if (!Objects.equals(
                        col.getType().replaceAll("\\(.*?\\)", ""),
                        colDb.getType().replaceAll("\\(.*?\\)", "").replaceAll("\\s.*", "")
                )) {
                    ColCheck colCheck = new ColCheck(col.getName(), col.getType());
                    colCheck.setType("type");
                    colCheck.setDifference("IDE: " + col.getType() + ", DB: " + colDb.getType());
                    colCheck.setColumn(col);
                    colCheckList.add(colCheck);
                }
                // Compare nullable, unique, PK
                if(!isReport) {
                    //if it is a report skip these checks
                    if (col.isNullable() != colDb.isNullable()) {
                        ColCheck colCheck = new ColCheck(col.getName(), col.getType());
                        colCheck.setColumn(col);
                        colCheck.setType("isNullable");
                        colCheck.setDifference("IDE: " + col.isNullable() + ", DB: " + colDb.isNullable());
                        colCheckList.add(colCheck);
                    }
                    if (col.isUnique() != colDb.isUnique()) {
                        ColCheck colCheck = new ColCheck(col.getName(), col.getType());
                        String id = "";
                        if (col.isUnique()) {
                            id = "IDE";
                        } else {
                            id = "DB";
                        }
                        colCheck.setType("isUnique " + id);
                        colCheck.setDifference("IDE: " + col.isUnique() + ", DB: " + colDb.isUnique());
                        colCheck.setColumn(col);
                        colCheckList.add(colCheck);
                    }
                    if (col.isPrimaryKey() != colDb.isPrimaryKey()) {
                        ColCheck colCheck = new ColCheck(col.getName(), col.getType());
                        colCheck.setType("isPrimaryKey");
                        colCheck.setDifference("IDE: " + col.isPrimaryKey() + ", DB: " + colDb.isPrimaryKey());
                        colCheckList.add(colCheck);
                        colCheck.setColumn(col);
                    }
                    // Normalize and compare default values (treat numeric equivalents like 0.0000 and 0 as equal)
                    String rawA = col.getDefaultValue();
                    String rawB = colDb.getDefaultValue();
                    String normA = normalizeDefaultValue(rawA);
                    String normB = normalizeDefaultValue(rawB);
                    if (!Objects.equals(normA, normB)) {
                        ColCheck colCheck = new ColCheck(col.getName(), col.getType());
                        colCheck.setType("defaultValue");
                        colCheck.setDifference("IDE: " + col.getDefaultValue() + ", DB: " + colDb.getDefaultValue());
                        colCheck.setColumn(col);
                        colCheckList.add(colCheck);
                    }
                }
            }

            if (!colCheckList.isEmpty()) {
                columnCheckMap.put(col.getName(), colCheckList);
            }
        }

        // Handle remaining DB-only columns
        for (ColumnJson c : remaining) {
            List<ColCheck> remainingColCheckList = new ArrayList<>();
            ColCheck colCheck = new ColCheck(c.getName(), c.getType());
            colCheck.setType("column DB");
            colCheck.setDifference("IDE: null, DB: " + c.getName());
            colCheck.setColumn(c);
            remainingColCheckList.add(colCheck);
            columnCheckMap.put(c.getName(), remainingColCheckList);
        }

        return columnCheckMap;
    }


    private static String normalizeDefaultValue(String raw) {
        if (raw == null) return "";
        String s = raw.replace("'", "").replace("(", "").replace(")", "").trim();
        if (s.isEmpty()) return "";
        // Try numeric canonicalization
        try {
            BigDecimal bd = new BigDecimal(s);
            bd = bd.stripTrailingZeros();
            // toPlainString avoids scientific notation
            return bd.toPlainString();
        } catch (NumberFormatException e) {
            // Not a pure number — normalize whitespace and case for textual comparison
            return s.toUpperCase();
        }
    }

    public static class ColCheck {
        String name;
        String changeType;
        ColumnJson column;

        public ColCheck(String name, String changeType) {
            this.name = name;
            this.changeType = changeType;
        }

        String type;
        String difference;

        public ColumnJson getColumn() {
            return column;
        }

        public void setColumn(ColumnJson column) {
            this.column = column;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public String getChangeType() {
            return changeType;
        }

        public void setChangeType(String changeType) {
            this.changeType = changeType;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDifference() {
            return difference;
        }

        public void setDifference(String difference) {
            this.difference = difference;
        }

        @Override
        public String toString() {
            return "ColCheck{" +
                    "type='" + type + '\'' +
                    ", difference='" + difference + '\'' +
                    '}';
        }
    }

    public static class TabCheck {
        String name;

        public TabCheck(String name,TableJson table) {
            this.table = table;
            this.name = name;
        }

        String type;
        String difference;
        TableJson table;
        Map<String, List<ColCheck>> colCheckList;
        Map<String, List<TriggerCheck>> triggerCheckList;
        Map<String, List<IndexesCheck>> indexesCheckList;

        public TableJson getTable() {
            return table;
        }

        public void setTable(TableJson table) {
            this.table = table;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDifference() {
            return difference;
        }

        public void setDifference(String difference) {
            this.difference = difference;
        }

        public Map<String, List<ColCheck>> getColCheckList() {
            return colCheckList;
        }

        public void setColCheckList(Map<String, List<ColCheck>> colCheckList) {
            this.colCheckList = colCheckList;
        }

        public Map<String, List<TriggerCheck>> getTriggerCheckList() {
            return triggerCheckList;
        }

        public void setTriggerCheckList(Map<String, List<TriggerCheck>> triggerCheckList) {
            this.triggerCheckList = triggerCheckList;
        }

        public Map<String, List<IndexesCheck>> getIndexesCheckList() {
            return indexesCheckList;
        }

        public void setIndexesCheckList(Map<String, List<IndexesCheck>> indexesCheckList) {
            this.indexesCheckList = indexesCheckList;
        }

        @Override
        public String toString() {
            return "TabCheck{" +
                    "type='" + type + '\'' +
                    ", difference='" + difference + '\'' +
                    ", colCheckList=" + colCheckList +
                    ", triggerCheckList=" + triggerCheckList +
                    ", indexesCheckList=" + indexesCheckList +
                    '}';
        }
    }

    public static class TriggerCheck {
        String name;
        String triggerType;
        String triggerContent;

        public TriggerCheck(String name, String triggerType, String triggerContent) {
            this.name = name;
            this.triggerType = triggerType;
            this.triggerContent = triggerContent;
        }

        String type;
        String difference;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTriggerType() {
            return triggerType;
        }

        public void setTriggerType(String triggerType) {
            this.triggerType = triggerType;
        }

        public String getTriggerContent() {
            return triggerContent;
        }

        public void setTriggerContent(String triggerContent) {
            this.triggerContent = triggerContent;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDifference() {
            return difference;
        }

        public void setDifference(String difference) {
            this.difference = difference;
        }


        @Override
        public String toString() {
            return "TriggerCheck{" +
                    "type='" + type + '\'' +
                    ", difference='" + difference + '\'' +

                    '}';
        }
    }

    public static class IndexesCheck {
        String name;

        public IndexesCheck(String name, ColumnDTO[] columns) {
            this.name = name;
            this.columns = columns;
        }

        String type;
        String difference;
        ColumnDTO[] columns;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ColumnDTO[] getColumns() {
            return columns;
        }

        public void setColumns(ColumnDTO[] columns) {
            this.columns = columns;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDifference() {
            return difference;
        }

        public void setDifference(String difference) {
            this.difference = difference;
        }


        @Override
        public String toString() {
            return "IndexesCheck{" +
                    "type='" + type + '\'' +
                    ", difference='" + difference + '\'' +

                    '}';
        }
    }

    public static class MACheck {
        String name;

        public MACheck(String name) {
            this.name = name;
        }

        String type;
        String difference;
        List<TableChecker> tableCheckers;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<TableChecker> getTableCheckers() {
            return tableCheckers;
        }

        public void setTableCheckers(List<TableChecker> tableCheckers) {
            this.tableCheckers = tableCheckers;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDifference() {
            return difference;
        }


        public void setDifference(String difference) {
            this.difference = difference;


        }


        @Override
        public String toString() {
            return "IndexesCheck{" +
                    "type='" + type + '\'' +
                    ", difference='" + difference + '\'' +

                    '}';
        }
    }

    public static class UniqueKeyCheck {
        String name;
        ColumnDTO[] columns;

        public UniqueKeyCheck(String name, ColumnDTO[] columns) {
            this.name = name;
            this.columns = columns;
        }

        String type;
        String difference;

        public String getName() {
            return name;
        }

        public ColumnDTO[] getColumns() {
            return columns;
        }

        public void setColumns(ColumnDTO[] columns) {
            this.columns = columns;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDifference() {
            return difference;
        }

        public void setDifference(String difference) {
            this.difference = difference;
        }
    }

    public static class TableChecker {
        String name;

        public TableChecker(String name) {
            this.name = name;
        }

        List<TabCheck> tabCheckList;
        Map<String, List<ColCheck>> colCheckMap;
        Map<String, List<TriggerCheck>> triggerCheckMap;
        Map<String, List<IndexesCheck>> indexesCheckMap;
        Map<String, List<UniqueKeyCheck>> uniqueKeyCheckMap;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<TabCheck> getTabCheckList() {
            return tabCheckList;
        }

        public Map<String, List<UniqueKeyCheck>> getUniqueKeyCheckMap() {
            return uniqueKeyCheckMap;
        }

        public void setUniqueKeyCheckMap(Map<String, List<UniqueKeyCheck>> uniqueKeyCheckMap) {
            this.uniqueKeyCheckMap = uniqueKeyCheckMap;
        }

        public void setTabCheckList(List<TabCheck> tabCheckList) {
            this.tabCheckList = tabCheckList;
        }

        public Map<String, List<ColCheck>> getColCheckMap() {
            return colCheckMap;
        }

        public void setColCheckMap(Map<String, List<ColCheck>> colCheckMap) {
            this.colCheckMap = colCheckMap;
        }

        public Map<String, List<TriggerCheck>> getTriggerCheckMap() {
            return triggerCheckMap;
        }

        public void setTriggerCheckMap(Map<String, List<TriggerCheck>> triggerCheckMap) {
            this.triggerCheckMap = triggerCheckMap;
        }

        public Map<String, List<IndexesCheck>> getIndexesCheckMap() {
            return indexesCheckMap;
        }

        public void setIndexesCheckMap(Map<String, List<IndexesCheck>> indexesCheckMap) {
            this.indexesCheckMap = indexesCheckMap;
        }

        @Override
        public String toString() {
            return "TableChecker{" +
                    "name='" + name + '\'' +
                    ", tabCheckList=" + tabCheckList +
                    ", colCheckMap=" + colCheckMap +
                    ", triggerCheckMap=" + triggerCheckMap +
                    ", indexesCheckMap=" + indexesCheckMap +
                    '}';
        }
    }

    public static class MAChecker {
        String name;


        List<TableChecker> tableCheckers;
        List<MACheck> maCheckList;

        public MAChecker(String name) {
            this.name = name;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<TableChecker> getTableCheckers() {
            return tableCheckers;
        }

        public void setTableCheckers(List<TableChecker> tableCheckers) {
            this.tableCheckers = tableCheckers;
        }

        public List<MACheck> getMaCheckList() {
            return maCheckList;
        }

        public void setMaCheckList(List<MACheck> maCheckList) {
            this.maCheckList = maCheckList;
        }
    }

    @Override
    public String toString() {
        return "GmaChecker{" +
                "name='" + name + '\'' +
                ", maCheckers=" + maCheckers +
                '}';
    }




}

