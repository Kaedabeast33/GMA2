package org.example.jsonBuilder.db;

import org.example.jsonBuilder.gma.ma.tables.*;
import org.example.jsonBuilder.gma.ma.tables.columns.ColumnDTO;
import org.example.jsonBuilder.gma.ma.tables.columns.ColumnJson;
import org.example.jsonBuilder.gma.ma.tables.columns.IndexJson;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DbToJsonExtractor {

    /**
     * Extracts tables and columns from the given database.
     *
     * @param conn         JDBC connection
     * @param databaseName database/catalog to extract
     * @return list of TableJson objects
     * @throws SQLException if DB access fails
     */
    public static List<TableJson> extractTables(Connection conn, String databaseName) throws SQLException, InvocationTargetException, IllegalAccessException {
//        System.out.println("Starting table extraction for database: " + databaseName);
        DatabaseMetaData metaData = conn.getMetaData();
        List<TableJson> tables = new ArrayList<>();

        // Use database name as catalog, null as schema for MySQL
        ResultSet tableRs = metaData.getTables(databaseName, null, "%", new String[]{"TABLE"});
        while (tableRs.next()) {
            String tableName = tableRs.getString("TABLE_NAME");
//            System.out.println("Found table: " + tableName);

            TableJson table = new TableJson();
            table.setName(tableName);
            table.setDescription("");
            table.setTableId("tab" + UUID.randomUUID());
            table.setTags(new String[]{"auto-generated"});


            table.setTriggers(getTriggers(conn,databaseName,table.getName()));
//            table.setCustomConstraints(getCustomConstraints(conn,databaseName,table.getName()));
            table.setTableProcedures(getProcedures(conn,databaseName,table.getName()));


//            System.out.println("  Columns found: " + Arrays.stream(columns).map(ColumnJson::getName).toList());
            table.setUniqueKeys(getCustomKeys(conn,databaseName,tableName));
            table.setColumns( getColumns(metaData, databaseName, tableName));
            table.setIndexes(getIndexes(metaData, databaseName, table.getName(),table.getColumns()));
            tables.add(table);
        }

//        System.out.println("Extraction completed. Total tables: " + tables.size());
        return tables;
    }

    private static UniqueKeyJson[] getCustomKeys(Connection conn, String databaseName, String tableName) throws SQLException {
        // Map of unique key name -> list of ColumnJson
        Map<String, List<ColumnJson>> groupKeyMap = new HashMap<>();

        // Query information_schema for unique constraints (excluding PRIMARY KEYs)
        String sql = """
        SELECT CONSTRAINT_NAME, COLUMN_NAME
        FROM information_schema.KEY_COLUMN_USAGE
        WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ? AND CONSTRAINT_NAME != 'PRIMARY'
    """;

        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, databaseName);
            stmt.setString(2, tableName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String keyName = rs.getString("CONSTRAINT_NAME");
                String columnName = rs.getString("COLUMN_NAME");

                if (keyName == null || columnName == null) continue;

                // Add to map
                List<ColumnJson> value = groupKeyMap.computeIfAbsent(keyName, k -> new ArrayList<>());
                ColumnJson col = new ColumnJson();
                col.setName(columnName);
                value.add(col);
            }
        }

        // Convert map entries into UniqueKeyJson[]
        UniqueKeyJson[] uniqueKeys = new UniqueKeyJson[groupKeyMap.size()];
        int k = 0;
        for (Map.Entry<String, List<ColumnJson>> entry : groupKeyMap.entrySet()) {
            uniqueKeys[k++] = new UniqueKeyJson(entry);
        }

        return uniqueKeys;
    }



    private static ColumnJson[] getColumns(DatabaseMetaData metaData, String databaseName, String tableName) throws SQLException {
//        System.out.println("  Extracting columns for table: " + tableName);
        ResultSet columnsRs = metaData.getColumns(databaseName, null, tableName, "%");
        Set<String> primaryKeys = getPrimaryKeys(metaData, databaseName, tableName);
        Set<String> uniqueColumns = getUniqueColumns(metaData, databaseName, tableName); // Get unique columns
//        System.out.println("    Primary keys: " + primaryKeys);

        List<ColumnJson> cols = new ArrayList<>();
        while (columnsRs.next()) {
            String colName = columnsRs.getString("COLUMN_NAME");
//            System.out.println("    Processing column: " + colName);

            ColumnJson col = new ColumnJson();
            col.setName(colName);
            col.setColumnId("col" + UUID.randomUUID());

            // Get column type and size
            String typeName = columnsRs.getString("TYPE_NAME");
            int columnSize = columnsRs.getInt("COLUMN_SIZE");
            col.setType(typeName);

            col.setNullable(columnsRs.getInt("NULLABLE") == DatabaseMetaData.columnNullable);
            col.setPrimaryKey(primaryKeys.contains(colName));
            col.setRequired(!col.isNullable());
            col.setUnique(uniqueColumns.contains(colName)); // Set unique property
            col.setUniqueIdentifier(false);
            col.setDefaultValue(Optional.ofNullable(columnsRs.getString("COLUMN_DEF")).orElse(""));
            col.setIndex(false);

            cols.add(col);
        }

//        System.out.println("    Total columns extracted for " + tableName + ": " + cols.size());
        return cols.toArray(new ColumnJson[0]);
    }

    private static Set<String> getUniqueColumns(DatabaseMetaData metaData, String databaseName, String tableName) throws SQLException {
        Set<String> uniqueColumns = new HashSet<>();
        ResultSet indexRs = metaData.getIndexInfo(databaseName, null, tableName, false, false);
        while (indexRs.next()) {
            boolean nonUnique = indexRs.getBoolean("NON_UNIQUE");
            String columnName = indexRs.getString("COLUMN_NAME");
            if (!nonUnique && columnName != null) {
                uniqueColumns.add(columnName);
            }
        }
        return uniqueColumns;
    }


    private static Set<String> getPrimaryKeys(DatabaseMetaData metaData, String databaseName, String tableName) throws SQLException {
        Set<String> keys = new HashSet<>();
        ResultSet pkRs = metaData.getPrimaryKeys(databaseName, null, tableName);
        while (pkRs.next()) {
            keys.add(pkRs.getString("COLUMN_NAME"));
        }
        return keys;
    }
    private static IndexJson[] getIndexes(DatabaseMetaData metaData, String databaseName, String tableName, ColumnJson[] tableColumns) throws SQLException {
//        System.out.println("  Extracting indexes for table: " + tableName);
        Map<String, IndexJson> indexMap = new HashMap<>();
        Set<String> indexedColumnNames = new HashSet<>(); // track all columns that are part of any index

        // First, handle primary key columns: add them to a "PRIMARY" index
//        IndexJson primaryIndex = new IndexJson();
//        primaryIndex.setIndexGroupName("PRIMARY");
//        primaryIndex.setColumns(new ArrayList<>());

        for (ColumnJson col : tableColumns) {
            if (col.isPrimaryKey()) {
                col.setIndex(true); // mark as indexed
                indexedColumnNames.add(col.getName());
//                primaryIndex.getColumns().add(new ColumnDTO(col));
            }
        }
//        if (!primaryIndex.getColumns().isEmpty()) {
//            indexMap.put("PRIMARY", primaryIndex);
//        }

        // Then handle all other indexes from metadata
        ResultSet rs = metaData.getIndexInfo(databaseName, null, tableName, false, false);
        while (rs.next()) {
            String indexName = rs.getString("INDEX_NAME");
            String columnName = rs.getString("COLUMN_NAME");
            boolean nonUnique = rs.getBoolean("NON_UNIQUE"); // true = non-unique, false = unique

            if (indexName == null || columnName == null) continue;

            // Skip unique indexes (we're handling primary keys separately)
            if (!nonUnique) continue;

            // Get or create IndexJson object
            IndexJson index = indexMap.computeIfAbsent(indexName, k -> {
                IndexJson idx = new IndexJson();
                idx.setIndexGroupName(indexName);
                idx.setColumns(new ArrayList<>());
                return idx;
            });

            // Find the ColumnJson for this column
            ColumnJson colJson = Arrays.stream(tableColumns)
                    .filter(c -> c.getName().equalsIgnoreCase(columnName))
                    .findFirst()
                    .orElse(null);

            if (colJson != null) {
                colJson.setIndex(true); // mark column as indexed
                indexedColumnNames.add(colJson.getName());
                index.getColumns().add(new ColumnDTO(colJson));
            }

//            System.out.println("    Index: " + indexName + " Column: " + columnName);
        }

        // Ensure all tableColumns are updated if part of any index
        for (ColumnJson col : tableColumns) {
            if (indexedColumnNames.contains(col.getName())) {
                col.setIndex(true);
            }
        }

        return indexMap.values().toArray(new IndexJson[0]);
    }





    private static TriggerJson[] getTriggers(Connection conn, String databaseName, String tableName) throws SQLException, InvocationTargetException, IllegalAccessException {
//        System.out.println("  Extracting triggers for table: " + tableName);
        List<TriggerJson> triggers = new ArrayList<>();
        String sql = "SELECT TRIGGER_NAME, ACTION_TIMING, EVENT_MANIPULATION, ACTION_STATEMENT " +
                "FROM information_schema.TRIGGERS " +
                "WHERE TRIGGER_SCHEMA = ? AND EVENT_OBJECT_TABLE = ?";

        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, databaseName);
            stmt.setString(2, tableName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String triggerName = rs.getString("TRIGGER_NAME");
                String timing = rs.getString("ACTION_TIMING");       // BEFORE, AFTER
                String event = rs.getString("EVENT_MANIPULATION");   // INSERT, UPDATE, DELETE
                String statement = rs.getString("ACTION_STATEMENT"); // trigger bodysout
//                System.out.println();
                TriggerJson trigger = new TriggerJson();
                trigger.setName(triggerName);
                trigger.setTriggerType(timing + " " + event);
                trigger.setTriggerString(statement);
//                trigger.setDescription("");      // optional
//                trigger.setTags(new String[0]);  // optional

                triggers.add(trigger);
//                System.out.println("    Trigger found: " + triggerName + " (" + timing + " " + event + ")");
            }
        }

        return triggers.toArray(new TriggerJson[0]);
    }


//    private static CustomContraintJson[] getCustomConstraints(Connection conn, String databaseName, String tableName) throws SQLException {
////        System.out.println("  Extracting constraints for table: " + tableName);
//        List<String> constraints = new ArrayList<>();
//        String sql = "SELECT CONSTRAINT_NAME FROM information_schema.TABLE_CONSTRAINTS " +
//                "WHERE CONSTRAINT_SCHEMA = ? AND TABLE_NAME = ? AND CONSTRAINT_TYPE NOT IN ('PRIMARY KEY','FOREIGN KEY')";
//        try (var stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, databaseName);
//            stmt.setString(2, tableName);
//            ResultSet rs = stmt.executeQuery();
//            while (rs.next()) {
//                String constraintName = rs.getString("CONSTRAINT_NAME");
//                constraints.add(constraintName);
////                System.out.println("    Constraint found: " + constraintName);
//            }
//        }
//        return constraints.toArray(new String[0]);
//    }

    private static ProcedureJson[] getProcedures(Connection conn, String databaseName, String tableName) throws SQLException, InvocationTargetException, IllegalAccessException {
//        System.out.println("  Extracting procedures for table: " + tableName);
        List<ProcedureJson> procedures = new ArrayList<>();
        String sql = "SELECT ROUTINE_NAME, ROUTINE_DEFINITION, ROUTINE_TYPE " +
                "FROM information_schema.ROUTINES " +
                "WHERE ROUTINE_SCHEMA = ? AND ROUTINE_TYPE = 'PROCEDURE'";

        try (var stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, databaseName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String procedureName = rs.getString("ROUTINE_NAME");
                String procedureBody = rs.getString("ROUTINE_DEFINITION");

                ProcedureJson procedure = new ProcedureJson();
                procedure.setName(procedureName);
                procedure.setContentString(procedureBody);
//                procedure.setDescription(""); // optional
//                procedure.setTags(new String[0]); // optional
//                procedure.setId("pro" + UUID.randomUUID());

//                System.out.println("    Procedure found: " + procedureName);
                procedures.add(procedure);
            }
        }

        return procedures.toArray(new ProcedureJson[0]);
    }


}



