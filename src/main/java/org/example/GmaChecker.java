package org.example;

import org.example.jsonBuilder.gma.GMAJson;
import org.example.jsonBuilder.gma.ma.MAJson;
import org.example.jsonBuilder.gma.ma.tables.TableJson;
import org.example.jsonBuilder.gma.ma.tables.TriggerJson;
import org.example.jsonBuilder.gma.ma.tables.UniqueKeyJson;
import org.example.jsonBuilder.gma.ma.tables.columns.ColumnDTO;
import org.example.jsonBuilder.gma.ma.tables.columns.ColumnJson;
import org.example.jsonBuilder.gma.ma.tables.columns.IndexJson;

import java.util.*;
import java.util.stream.Collectors;


public class GmaChecker {
    String name;
    List<MAChecker> maCheckers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  GmaChecker(GMAJson dormGma, GMAJson dormDbGma) {


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
                if (tableChecker.getColCheckMap() != null) {
                    System.out.println("\n\u001B[1;32m🧩 Column Differences:\u001B[0m");
                    for (List<ColCheck> colChecks : tableChecker.getColCheckMap().values()) {
                        for (ColCheck colCheck : colChecks) {
                            System.out.printf("   • TYPE: %-15s | NAME: %-20s | DIFF: %s%n",
                                    colCheck.getType(), colCheck.getName(), colCheck.getDifference());
                            askContinueCol(scanner, colCheck.getName(), colCheck.getType(), colCheck.getChangeType(), tableName,colCheck.getColumn());
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


    private void askContinueUniqueKey(Scanner scanner, String type, String tableName, String keyName, ColumnDTO[] columns) {
        String replace = null;
        String insert = null;

        switch (type) {
            case "key IDE column", "key IDE" -> {
                replace = String.format(ITALIC + """
                    ALTER TABLE `%s` DROP CONSTRAINT IF EXISTS `%s`;
                    """ + RESET, tableName, keyName);

                insert = String.format(ITALIC + """
                    ALTER TABLE `%s` ADD CONSTRAINT `%s` UNIQUE (%s);
                    """ + RESET, tableName, keyName, Arrays.stream(columns).map(ColumnDTO::getName).collect(Collectors.joining(", ")));

                System.out.println();
            }
            default -> {
                System.out.println("\"No action for this type.\"");
                return;
            }
        }

        if (replace != null) System.out.println(replace);
        if (insert != null) System.out.println(insert);

        System.out.print(ITALIC + "Do you want to update the DB? (Y/N): " + RESET);
        String input = scanner.nextLine().trim().toUpperCase();
        while (!input.equals("Y") && !input.equals("N")) {
            System.out.print("\t\tPlease enter Y or N: ");
            input = scanner.nextLine().trim().toUpperCase();
        }

        if (input.equals("Y")) {
            System.out.println("\"inserting change\"");
        } else {
            System.out.println("\"continuing without update\"");
        }
    }

    private void askContinueCol(Scanner scanner, String columnName, String type, String changeType, String tableName,ColumnJson col) {
        String replace=null;
        String insert=null;;
        switch (type) {
            case "type" -> {
                replace = String.format(ITALIC + "ALTER TABLE %s MODIFY COLUMN `%s` %s;" + RESET, tableName, columnName, changeType);

            }
            case "isNullable" -> {
                if(!col.isNullable()) replace = String.format(ITALIC + "ALTER TABLE %s MODIFY COLUMN `%s` %s not null;" + RESET, tableName, columnName, col.getType());
                else replace = String.format(ITALIC + "ALTER TABLE %s MODIFY COLUMN `%s` %s null;" + RESET, tableName, columnName, col.getType());

            }
            case "column DB" -> {
                replace = String.format(ITALIC + "ALTER TABLE %s DROP COLUMN `%s`;" + RESET, tableName, columnName);

            }
            case "column IDE" -> {
                String columnDef = String.format("`%s` %s %s %s", columnName, col.getType(), col.isNullable() ? "NULL" : "NOT NULL",col.isUnique()? "UNIQUE":"");
//                replace = String.format(ITALIC + "ALTER TABLE %s drop column `%s`;" + RESET, tableName, columnName);
                insert = String.format(ITALIC + "ALTER TABLE %s ADD COLUMN %s;" + RESET, tableName, columnDef);

            }
            case "isUnique" -> {

//                replace = String.format(ITALIC + "ALTER TABLE %s drop column `%s`;" + RESET, tableName, columnName);
                insert = String.format(ITALIC + "ALTER TABLE %s ADD CONSTRAINT UNIQUE `%s    ;" + RESET, tableName, col.getName());

            }
                default -> {
                System.out.println("\"No action for this type.\"");
                return;
            }
        }
        if(replace!=null)System.out.println("\n" + replace);
        if(insert!=null)System.out.println("\n" + insert);

        System.out.print(ITALIC + "Do you want to update the DB? (Y/N): " + RESET);
        String input = scanner.nextLine().trim().toUpperCase();
        while (!input.equals("Y") && !input.equals("N")) {
            System.out.print("\t\tPlease enter Y or N: ");
            input = scanner.nextLine().trim().toUpperCase();
        }

        if (input.equals("Y")) System.out.println("\"inserting change\"");
        else System.out.println("\"continuing without update\"");
    }

    private void askContinueTrigger(Scanner scanner, String type, String triggerName, String triggerContent, String triggerType, String tableName, String schemaName) {
        String insert = null;
        String replace = null;

        switch (type) {
            case "trigger IDE" -> insert = String.format(ITALIC + """
                DELIMITER $$\n\t\tCREATE TRIGGER `%s` %s ON `%s` FOR EACH ROW %s$$\nDELIMITER ;
                """ + RESET, triggerName, triggerType, tableName, triggerContent);
            case "trigger DB" -> replace = String.format(ITALIC + "DROP TRIGGER %s.`%s`;" + RESET, schemaName, triggerName);
            default -> {
                System.out.println("\"No action for this type.\"");
                return;
            }
        }

        if (insert != null) System.out.println(insert);
        if (replace != null) System.out.println(replace);

        System.out.print(ITALIC + "Do you want to update the DB? (Y/N): " + RESET);
        String input = scanner.nextLine().trim().toUpperCase();
        while (!input.equals("Y") && !input.equals("N")) {
            System.out.print("\t\tPlease enter Y or N: ");
            input = scanner.nextLine().trim().toUpperCase();
        }

        if (input.equals("Y")) System.out.println("\"inserting change\"");
        else System.out.println("\"continuing without update\"");
    }

    private void askContinueIndex(Scanner scanner, String type, String tableName, String idx_name, ColumnDTO[] columns) {
        String replace = null;
        String insert = null;

        switch (type) {
            case "index IDE columns", "index IDE" -> {
                replace = String.format(ITALIC + "ALTER TABLE %s DROP INDEX `%s`;" + RESET, tableName, idx_name);
                insert = String.format(ITALIC + "CREATE INDEX `%s` ON %s (%s);" + RESET, idx_name, tableName, Arrays.stream(columns).map(ColumnDTO::getName).collect(Collectors.joining(", ")));
            }
            case "index DB" -> replace = String.format(ITALIC + "ALTER TABLE %s DROP INDEX `%s`;" + RESET, tableName, idx_name);
            default -> {
                System.out.println("\"No action for this type.\"");
                return;
            }
        }

        if (replace != null) System.out.println(replace);
        if (insert != null) System.out.println(insert);

        System.out.print(ITALIC + "Do you want to update the DB? (Y/N): " + RESET);
        String input = scanner.nextLine().trim().toUpperCase();
        while (!input.equals("Y") && !input.equals("N")) {
            System.out.print("\t\tPlease enter Y or N: ");
            input = scanner.nextLine().trim().toUpperCase();
        }

        if (input.equals("Y")) System.out.println("\"inserting change\"");
        else System.out.println("\"Not updated\"");
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
        List<MAJson> remainingDbMAs = new ArrayList<>(List.of(maDbs));

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
                    // Table exists in IDE
                    if (tableDb == null) {
                        // Table missing in DB
                        TabCheck tabCheck = new TabCheck(table.getName());
                        tabCheck.setType("table");
                        tabCheck.setDifference("IDE: " + table.getName() + ", DB: null");
                        tabCheckList.add(tabCheck);
                    } else {
                        // Check differences
                        colsCheckMap = checkCols(table.getColumns(), tableDb.getColumns());
                        triggerCheckMap = checkTriggers(table.getTriggers(), tableDb.getTriggers());
                        indexesCheckMap = checkIndexes(table.getIndexes(), tableDb.getIndexes());
                        uniqueKeyCheckMap = checkUnique(table.getUniqueKeys(),tableDb.getUniqueKeys());
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
        for (TableJson t : remaining) {
            List<TabCheck> tabCheckList = new ArrayList<>();
            TabCheck tabCheck = new TabCheck(t.getName());
            tabCheck.setType("table");
            tabCheck.setDifference("IDE: null, DB: " + t.getName());
            tabCheckList.add(tabCheck);

            TableChecker tableChecker = new TableChecker(t.getName());
            tableChecker.setTabCheckList(tabCheckList);
            tableCheckers.add(tableChecker);
        }

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
                    if(!index.getIndexGroupName().contains("_fk")&&!index.getIndexGroupName().contains("_key") && !index.getIndexGroupName().contains("PRIMARY")) {
                        IndexesCheck check = new IndexesCheck(index.getIndexGroupName(),index.getColumns().toArray(new ColumnDTO[0]));
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
//
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
            if(ind.getIndexGroupName().contains("_fk")|| ind.getIndexGroupName().contains("fk")|| ind.getIndexGroupName().contains("foreignkey")|| ind.getIndexGroupName().contains("foreign_key") || ind.getIndexGroupName().contains("PRIMARY")) continue;
            List<IndexesCheck> checkList = new ArrayList<>();
            IndexesCheck check = new IndexesCheck(ind.getIndexGroupName(),ind.getColumns().toArray(new ColumnDTO[0]));
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
                    TriggerCheck check =  new TriggerCheck(trigger.getName(),trigger.getTriggerType(),trigger.getTriggerString());
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
                        TriggerCheck check =  new TriggerCheck(trigger.getName(),trigger.getTriggerType(),trigger.getTriggerString());
                        check.setType("trigger type");
                        check.setDifference("IDE: " + (trigger.getTriggerType() == null ? "null" : trigger.getTriggerType())
                                + ", DB: " + (triggerDb.getTriggerType() == null ? "null" : triggerDb.getTriggerType()));
                        triggerCheckList.add(check);
                    }

                    // Trigger string difference
                    String ideStr = trigger.getTriggerString() == null ? "" : trigger.getTriggerString().replaceAll("\\s+", " ").trim();
                    String dbStr = triggerDb.getTriggerString() == null ? "" : triggerDb.getTriggerString().replaceAll("\\s+", " ").trim();
                    if (!Objects.equals(ideStr, dbStr)) {
                        TriggerCheck check = new TriggerCheck(trigger.getName(),trigger.getTriggerType(),trigger.getTriggerString());
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
            TriggerCheck check = new TriggerCheck(t.getName(),t.getTriggerType(),t.getTriggerString());
            check.setType("trigger DB");
            check.setDifference("IDE: null, DB: " + t.getName());
            dbOnlyCheckList.add(check);
            triggerCheckMap.put(t.getName(), dbOnlyCheckList);
        }

        return triggerCheckMap;
    }




    private Map<String, List<ColCheck>> checkCols(ColumnJson[] cols, ColumnJson[] colDbs) {
        Map<String,List<ColCheck>> columnCheckMap = new HashMap<>();
        List<ColumnJson> remaining = new ArrayList<>(List.of(colDbs));

        for (ColumnJson col : cols) {
            List<ColCheck> colCheckList = new ArrayList<>();
            ColumnJson colDb = Arrays.stream(colDbs)
                    .filter(c -> c.getName().equalsIgnoreCase(col.getName()))
                    .findFirst()
                    .orElse(null);

            if (colDb != null) remaining.remove(colDb);

            if (colDb == null) {
                ColCheck colCheck = new ColCheck(col.getName(),col.getType());
                colCheck.setType("column IDE");
                colCheck.setDifference("IDE: " + col.getName() + ", DB: null");
                colCheckList.add(colCheck);
            } else {
                // Compare types
                if(col.getType()==null){
                    throw new RuntimeException("Column type is null for column: " + col.getName() + " in IDE.");
                }
                if (!Objects.equals(
                        col.getType().replaceAll("\\(.*?\\)",""),
                        colDb.getType().replaceAll("\\(.*?\\)","").replaceAll("\\s.*", "")
                )) {
                    ColCheck colCheck = new ColCheck(col.getName(),col.getType());
                    colCheck.setType("type");
                    colCheck.setDifference("IDE: " + col.getType() + ", DB: " + colDb.getType());

                    colCheckList.add(colCheck);
                }
                // Compare nullable, unique, PK
                if (col.isNullable() != colDb.isNullable()) {
                    ColCheck colCheck = new ColCheck(col.getName(),col.getType());
                    colCheck.setColumn(col);
                    colCheck.setType("isNullable");
                    colCheck.setDifference("IDE: " + col.isNullable() + ", DB: " + colDb.isNullable());
                    colCheckList.add(colCheck);
                }
                if (col.isUnique() != colDb.isUnique()) {
                    ColCheck colCheck = new ColCheck(col.getName(),col.getType());
                    colCheck.setType("isUnique");
                    colCheck.setDifference("IDE: " + col.isUnique() + ", DB: " + colDb.isUnique());
                    colCheck.setColumn(col);
                    colCheckList.add(colCheck);
                }
                if (col.isPrimaryKey() != colDb.isPrimaryKey()) {
                    ColCheck colCheck = new ColCheck(col.getName(),col.getType());
                    colCheck.setType("isPrimaryKey");
                    colCheck.setDifference("IDE: " + col.isPrimaryKey() + ", DB: " + colDb.isPrimaryKey());
                    colCheckList.add(colCheck);
                }
            }

            if (!colCheckList.isEmpty()) {
                columnCheckMap.put(col.getName(), colCheckList);
            }
        }

        // Handle remaining DB-only columns
        for (ColumnJson c : remaining) {
            List<ColCheck> remainingColCheckList = new ArrayList<>();
            ColCheck colCheck = new ColCheck(c.getName(),c.getType());
            colCheck.setType("column DB");
            colCheck.setDifference("IDE: null, DB: " + c.getName());
            remainingColCheckList.add(colCheck);
            columnCheckMap.put(c.getName(), remainingColCheckList);
        }

        return columnCheckMap;
    }



    public static class ColCheck {
        String name;
        String changeType;
        ColumnJson column;

        public ColCheck(String name,String changeType) {
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

        public TabCheck(String name) {
            this.name = name;
        }

        String type;
        String difference;
        Map<String,List<ColCheck>> colCheckList;
        Map<String,List<TriggerCheck>> triggerCheckList;
        Map<String,List<IndexesCheck> >indexesCheckList;

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

        public TriggerCheck(String name,String triggerType, String triggerContent) {
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

        public IndexesCheck(String name,ColumnDTO[] columns) {
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

        public UniqueKeyCheck(String name,ColumnDTO[] columns) {
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
    public static class TableChecker{
        String name;

        public TableChecker(String name) {
            this.name = name;
        }

        List<TabCheck> tabCheckList;
        Map<String,List<ColCheck>> colCheckMap;
        Map<String,List<TriggerCheck>> triggerCheckMap;
        Map<String,List<IndexesCheck>> indexesCheckMap;
        Map<String,List<UniqueKeyCheck>> uniqueKeyCheckMap;



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

    public static class MAChecker{
        String name;



        List<TableChecker> tableCheckers;
        List<MACheck> maCheckList   ;

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
