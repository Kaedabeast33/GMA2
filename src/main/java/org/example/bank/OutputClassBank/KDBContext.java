package org.example.bank.OutputClassBank;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import okhttp3.*;
import org.example.ClassOutputCreator.templates.airtable.AirColumnTemplate;
import org.example.ClassOutputCreator.templates.ColumnTemplate;
import org.example.ClassOutputCreator.templates.KdbGma;
import org.example.JsonBuilder.json.GMAJson;
import org.example.JsonBuilder.json.ma.MAJson;
import org.example.JsonBuilder.json.ma.tables.TableJson;
import org.example.JsonBuilder.json.ma.tables.columns.ColumnJson;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static org.example.bank.AppConfig.*;
import static org.example.bank.OutputClassBank.KdbColumnWrapper.safeGetValue;
import static org.example.bank.OutputClassBank.QueryResult.getQueryResultObj;

import static org.example.bank.commonValues.ColumnConverter.toPersonaJson;

public enum KDBContext {
    KDB_CONTEXT;

    final Map<String, GMAJson> gmaJsonMap = new HashMap<>();

    final List<KdbGma> gmaConfigList = new ArrayList<>();

    public void addGmaConfig(KdbGma kdbGma) {
        gmaConfigList.add(kdbGma);
    }
    public List<KdbGma> getGmaConfigList() {
        return gmaConfigList;
    }


    public void addGMA(GMAJson gmaJson) {
        gmaJsonMap.put(gmaJson.getName(), gmaJson);
    }

    public GMAJson getGmaByName(String gmaName) {

        return gmaJsonMap.get(gmaName);

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getJdbcUrl(), getJdbcUser(), getJdbcPassword());
    }



    public QueryResult getQuery(String gmaName, String maName, String tableName,
                                List<KdbColumnPersona> getCols,
                                EntityManager entityManager) throws SQLException {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream()
                .filter(maJson -> Objects.equals(maJson.getName(), maName))
                .findFirst().orElse(null);

        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables())
                    .filter(tableJson -> Objects.equals(tableJson.getName(), tableName))
                    .findFirst().orElse(null);

            if (table != null) {
                List<String> selectCols = getCols.stream()
                        .map(KdbColumnPersona::getName)
                        .toList();

                String query = String.format("""
                    SELECT
                        %s
                    FROM
                        %s.%s
                    """, String.join(",\n", selectCols), maName, tableName);

                System.out.println(query);

                return getQueryResultObj(getCols, query, entityManager);
            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }
        } else {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
        }

        return null;
    }


    public QueryResult getQueryByColumns(String gmaName, String maName, String tableName,
                                         List<KdbColumnPersona> byCols,
                                         EntityManager entityManager) throws SQLException {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream()
                .filter(maJson -> Objects.equals(maJson.getName(), maName))
                .findFirst().orElse(null);

        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables())
                    .filter(tableJson -> Objects.equals(tableJson.getName(), tableName))
                    .findFirst().orElse(null);

            if (table != null) {
                List<String> whereClause = new ArrayList<>();
                for (KdbColumnPersona byCol : byCols) {
                    if (byCol.getQueryMatchStrings().size() > 1) {
                        List<String> inClause = byCol.getQueryMatchStrings().stream()
                                .map(s -> "'" + s + "'")
                                .toList();
                        whereClause.add(byCol.getName() + " IN (" + String.join(",", inClause) + ")");
                    } else {
                        whereClause.add(byCol.getName() + " = '" + byCol.getQueryMatchStrings().get(0) + "'");
                    }
                }

                String query = String.format("""
                    SELECT
                        *
                    FROM
                        %s.%s
                    WHERE
                        %s
                    """, maName, tableName, String.join(" AND ", whereClause));

                System.out.println(query);

                List<KdbColumnPersona> tableCols = toPersonaJson(Arrays.asList(table.getColumns()));
                return getQueryResultObj(tableCols, query, entityManager);
            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }
        } else {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
        }

        return null;
    }


    public QueryResult getQueryByColumns(String gmaName, String maName, String tableName,
                                         List<KdbColumnPersona> byCols,
                                         List<KdbColumnPersona> getCols,
                                         EntityManager entityManager) throws SQLException {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream()
                .filter(maJson -> Objects.equals(maJson.getName(), maName))
                .findFirst().orElse(null);

        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables())
                    .filter(tableJson -> Objects.equals(tableJson.getName(), tableName))
                    .findFirst().orElse(null);

            if (table != null) {
                List<String> selectCols = getCols.stream()
                        .map(KdbColumnPersona::getName)
                        .toList();

                List<String> whereClause = new ArrayList<>();
                for (KdbColumnPersona byCol : byCols) {
                    if (byCol.getQueryMatchStrings().size() > 1) {
                        List<String> inClause = byCol.getQueryMatchStrings().stream()
                                .map(s -> "'" + s + "'")
                                .toList();
                        whereClause.add(byCol.getName() + " IN (" + String.join(",", inClause) + ")");
                    } else {
                        whereClause.add(byCol.getName() + " = '" + byCol.getQueryMatchStrings().get(0) + "'");
                    }
                }

                String query = String.format("""
                    SELECT
                        %s
                    FROM
                        %s.%s
                    WHERE
                        %s
                    """, String.join(",\n", selectCols), maName, tableName, String.join(" AND ", whereClause));

                System.out.println(query);

                return getQueryResultObj(getCols, query, entityManager);
            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }
        } else {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
        }

        return null;
    }

    public QueryResult getQuery(String gmaName,String maName, String tableName,List<KdbColumnPersona> getCols) throws SQLException {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(),maName)).findFirst().orElse(null);
        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
                List<String> whereClause = new ArrayList<>();
                for(KdbColumnPersona   getCol: getCols){
                    whereClause.add(getCol.getName());
                }

                String query = String.format("""
                        SELECT
                            %s
                        FROM
                            %s.%s
                        
                            
                        """,String.join(",\n ",whereClause),maName,tableName);
                System.out.println(query);
                return getQueryResultObj(getCols,query,getConnection());
            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }
        } else {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
        }

        return null;
    }

    public QueryResult getQueryByColumns(String gmaName, String maName, String tableName, List<KdbColumnPersona> byCols) throws SQLException {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);
        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
                List<String> whereClause = new ArrayList<>();
                for(KdbColumnPersona byCol: byCols){

                    if(byCol.getQueryMatchStrings().size()>1){
                        List<String> inClause = byCol.getQueryMatchStrings().stream().map(s-> "'"+s+"'").toList();
                        whereClause.add(byCol.getName() + " IN ( " + String.join(",",inClause) + " )");

                    }else {
                        whereClause.add(byCol.getName() + " = " + "'" + byCol.getQueryMatchStrings().get(0) + "'");
                    }
                }
                String query = String.format("""
                        SELECT
                            *
                        FROM
                            %s.%s
                        WHERE
                            %s
                        """,maName,tableName,String.join(" AND ",whereClause));
                System.out.println(query);

                return  getQueryResultObj( toPersonaJson(Arrays.stream(table.getColumns()).toList()),query,getConnection());
            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }
        } else {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
        }

        return null;
    }








    // java
    public QueryResult getQueryByColumns(String gmaName, String maName, String tableName, List<KdbColumnPersona> byCols, List<KdbColumnPersona> getCols) throws SQLException {
        if (byCols == null || byCols.isEmpty()) {
            throw new IllegalArgumentException("byCols must not be null or empty for getQueryByColumns");
        }
        if (getCols == null || getCols.isEmpty()) {
            throw new IllegalArgumentException("getCols must not be null or empty for getQueryByColumns");
        }

        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream()
                .filter(maJson -> Objects.equals(maJson.getName(), maName))
                .findFirst().orElse(null);

        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables())
                    .filter(tableJson -> Objects.equals(tableJson.getName(), tableName))
                    .findFirst().orElse(null);

            if (table != null) {
                String columsSb = getCols.stream()
                        .map(KdbColumnPersona::getName)
                        .collect(Collectors.joining(",\n"));

                List<String> whereClause = new ArrayList<>();
                for (KdbColumnPersona byCol : byCols) {
                    if (byCol == null) {
                        throw new IllegalStateException("Encountered null byCol in byCols list");
                    }
                    List<String> q = byCol.getQueryMatchStrings();
                    if (q == null || q.isEmpty()) {
                        throw new IllegalStateException("Missing queryMatchStrings for column: " + byCol.getName());
                    }

                    if (q.size() > 1) {
                        List<String> inClause = q.stream().map(s -> "'" + s.replace("'", "''") + "'").toList();
                        whereClause.add(byCol.getName() + " IN ( " + String.join(",", inClause) + " )");
                    } else {
                        whereClause.add(byCol.getName() + " = '" + q.get(0).replace("'", "''") + "'");
                    }
                }

                String query = String.format("""
                    SELECT
                        %s
                    FROM
                        %s.%s
                    WHERE
                        %s
                    """, columsSb, maName, tableName, String.join(" AND ", whereClause));

                System.out.println(query);
                return getQueryResultObj(getCols, query, getConnection());
            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }
        } else {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
        }

        return null;
    }







    public List<ColumnJson> getColumns(String gmaName, String maName, String tableName) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
//        System.out.println(gmaJsonMap.getName()+" gma name");
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);
        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
                return List.of(table.getColumns());
            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }
        } else {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
        }
        return new ArrayList<>();
    }

    public List<ColumnJson> getColumnsByGroupName(String gmaName, String maName, String tableName, String groupName) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);
        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
                return Arrays.stream(table.getColumns())
                        .filter(col -> Arrays.stream(col.getColumnGroups())
                                .anyMatch(group -> Objects.equals(group.getName(), groupName)))
                        .toList();

            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }
        } else {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
        }
        return new ArrayList<>();
    }






    public List<ColumnJson> getUniqueIdentifierColumns(String gmaName, String maName, String tableName) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);
        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
                List<ColumnJson> matchingColumns = Arrays.stream(table.getColumns())
                        .filter(ColumnJson::isUniqueIdentifier)
                        .toList();
                return matchingColumns;

            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }

        }


        return new ArrayList<>();
    }

    public List<ColumnJson> getUniqueIdentifierColumnsByGroupName(String gmaName, String maName, String tableName, String groupName) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);
        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
                List<ColumnJson> matchingColumns = Arrays.stream(table.getColumns())
                        .filter(ColumnJson::isUniqueIdentifier)
                        .toList();
                return matchingColumns.stream().filter(col -> Arrays.stream(col.getColumnGroups()).anyMatch(group -> Objects.equals(group.getName(), groupName))).toList();


            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }

        }


        return new ArrayList<>();
    }


    public List<String> getColumnsString(String gmaName, String maName, String tableName) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
//        System.out.println(gmaJsonMap.getName()+" gma name");
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);
        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
                return Arrays.stream(table.getColumns()).map(ColumnJson::getName).toList();
            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }
        } else {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
        }
        return new ArrayList<>();
    }

    public List<String> getColumnsByGroupNameString(String gmaName, String maName, String tableName, String groupName) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);
        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
                List<ColumnJson> matchingColumns = Arrays.stream(table.getColumns())
                        .filter(col -> Arrays.stream(col.getColumnGroups())
                                .anyMatch(group -> Objects.equals(group.getName(), groupName)))
                        .toList();
                return matchingColumns.stream().map(ColumnJson::getName).toList();

            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }
        } else {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
        }
        return new ArrayList<>();
    }






    public List<String> getUniqueIdentifierColumnsString(String gmaName, String maName, String tableName) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);
        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
                List<ColumnJson> matchingColumns = Arrays.stream(table.getColumns())
                        .filter(ColumnJson::isUniqueIdentifier)
                        .toList();
                return matchingColumns.stream().map((ColumnJson::getName)).toList();

            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }

        }


        return new ArrayList<>();
    }

    public List<String> getUniqueIdentifierColumnsByGroupNameString(String gmaName, String maName, String tableName, String groupName) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);
        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
                List<ColumnJson> matchingColumns = Arrays.stream(table.getColumns())
                        .filter(ColumnJson::isUniqueIdentifier)
                        .toList();
                return matchingColumns.stream().filter(col -> Arrays.stream(col.getColumnGroups()).anyMatch(group -> Objects.equals(group.getName(), groupName)))
                        .map(ColumnJson::getName).toList();

            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }

        }


        return new ArrayList<>();
    }


    public void saveAllGma(SaveInterface table, List<SaveInterface> entities,  EntityManager entityManager,List<String> upsertStrings)throws ParseException {


        String batchId = "'" + UUID.randomUUID() + "'";

        String insertTableName = table.getUpsertName();
        String columns = String.join(",", table.getColumnsString()) + ",batch_id";

        String init = "INSERT INTO " + insertTableName + " (" + columns + ") VALUES\n";
        StringBuilder sb = new StringBuilder(init);

//        String drop = "DROP TABLE IF EXISTS " + insertTableName + ";\n";
//        String create = "CREATE TABLE IF NOT EXISTS " + insertTableName + " LIKE " + tableName + ";\n";

        String delete = String.format("delete from " + insertTableName + " where batch_id = %s;\n", batchId);

        try {
            int i = 0;
            for (SaveInterface entity : entities) {
                sb.append(entity.getValues(batchId));
//            System.out.println(entity.getValues());
                if (++i % 1000 == 0 || i == entities.size()) {
//                System.out.println(sb);
                    saveEntities(sb, entityManager);
                    sb = new StringBuilder(init);
                }
            }

            System.out.println("saved insert");

            // Ensure the insertFunction runs in the same transaction
            for (String upsertQuery : upsertStrings) {
                System.out.println("Executing upsert query: " + upsertQuery);
                try {
                    entityManager.createNativeQuery(upsertQuery).executeUpdate();
                } catch (Exception e) {
                    System.out.println("error executing upsert query: " + upsertQuery);
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            System.out.println("something went wrong with saving entities or executing upsert queries");
            throw new RuntimeException(e);
        }finally {

            System.out.println(delete);
            entityManager.createNativeQuery(delete).executeUpdate();
        }
    }

    public void saveAllGma(SaveInterface table, List<SaveInterface> entities,  EntityManager entityManager,List<String> upsertStrings,List<String> checks) throws ParseException, ClassNotFoundException {

        String tableName =  table.getMaName()+"."+ table.getTableName();
        String insertTableName = tableName + "_insert";
        String columns = String.join(",", table.getColumnsString());

        String init = "INSERT INTO " + insertTableName + " (" + columns + ") VALUES\n";
        StringBuilder sb = new StringBuilder(init);

        String drop = "DROP TABLE IF EXISTS " + insertTableName + ";\n";
        String create = "CREATE TABLE IF NOT EXISTS " + insertTableName + " LIKE " + tableName + ";\n";
        entityManager.createNativeQuery(drop).executeUpdate();
        entityManager.createNativeQuery(create).executeUpdate();

        int i = 0;
        for (SaveInterface entity : entities) {
            sb.append(entity.getValues());
//            System.out.println(entity.getValues());
            if (++i % 1000 == 0 || i == entities.size()) {
//                System.out.println(sb);
                saveEntities(sb, entityManager);
                sb = new StringBuilder(init);
            }
        }

        // Ensure the insertFunction runs in the same transaction
        for(String upsertQuery : upsertStrings) {
//            System.out.println("Executing upsert query: " + upsertQuery);
            try {
                entityManager.createNativeQuery(upsertQuery).executeUpdate();
            }catch (Exception e){
//                System.out.println("error executing upsert query: " + upsertQuery);
                throw new RuntimeException(e);
            }
        }

        for (String checkQuery : checks) {
            System.out.println("Executing check query: " + checkQuery);
            try {
                QueryResult queryResult = getQueryResultObj(checkQuery, entityManager);
                if (queryResult.getResultSize()>0) {
                    System.out.println(queryResult.getData().toString());
                    throw new RuntimeException("Check query returned results: " + checkQuery);
                }
            } catch (Exception e) {
                System.out.println("Error executing check query: " + checkQuery);
                throw new RuntimeException(e);
            }
        }


//        entityManager.createNativeQuery(drop).executeUpdate();
    }

    public void saveAll(SaveInterface table, List<SaveInterface> entities,  EntityManager entityManager,List<String> upsertStrings) throws ParseException {


//        String batchId = "'" + UUID.randomUUID() + "'";

//        String insertTableName = table.getUpsertName();
        String tableName =  table.getMaName()+"."+ table.getTableName();
        String insertTableName = table.getTableName() + "_insert";
        String columns = String.join(","
                , table.getColumnsString());
//                + ",batch_id";

        String init = "INSERT INTO " + insertTableName + " (" + columns + ") VALUES\n";
        StringBuilder sb = new StringBuilder(init);

        String drop = "DROP TABLE IF EXISTS " + insertTableName + ";\n";
        String create = "CREATE TABLE IF NOT EXISTS " + insertTableName + " LIKE " + tableName + ";\n";

        entityManager.createNativeQuery(drop).executeUpdate();
        entityManager.createNativeQuery(create).executeUpdate();

//        String delete = String.format("delete from " + insertTableName + " where batch_id = %s;\n", batchId);

        try {
            int i = 0;
            for (SaveInterface entity : entities) {
                sb.append(entity.getValues());
//            System.out.println(entity.getValues());
                if (++i % 1000 == 0 || i == entities.size()) {
//                System.out.println(sb);
                    saveEntities(sb, entityManager);
                    sb = new StringBuilder(init);
                }
            }

            System.out.println("saved insert");

            // Ensure the insertFunction runs in the same transaction
            for (String upsertQuery : upsertStrings) {
                System.out.println("Executing upsert query: " + upsertQuery);
                try {
                    entityManager.createNativeQuery(upsertQuery).executeUpdate();
                } catch (Exception e) {
                    System.out.println("error executing upsert query: " + upsertQuery);
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            System.out.println("something went wrong with saving entities or executing upsert queries");
            throw new RuntimeException(e);
        }finally {

            entityManager.createNativeQuery(drop).executeUpdate();
        }
    }

    public void saveAll(SaveInterface table, List<SaveInterface> entities,  EntityManager entityManager,List<String> upsertStrings,List<String> checks) throws ParseException, ClassNotFoundException {

        String tableName =  table.getMaName()+"."+ table.getTableName();
        String insertTableName = tableName + "_insert";
        String columns = String.join(",", table.getColumnsString());

        String init = "INSERT INTO " + insertTableName + " (" + columns + ") VALUES\n";
        StringBuilder sb = new StringBuilder(init);

        String drop = "DROP TABLE IF EXISTS " + insertTableName + ";\n";
        String create = "CREATE TABLE IF NOT EXISTS " + insertTableName + " LIKE " + tableName + ";\n";
        entityManager.createNativeQuery(drop).executeUpdate();
        entityManager.createNativeQuery(create).executeUpdate();

        int i = 0;
        for (SaveInterface entity : entities) {
            sb.append(entity.getValues());
//            System.out.println(entity.getValues());
            if (++i % 1000 == 0 || i == entities.size()) {
//                System.out.println(sb);
                saveEntities(sb, entityManager);
                sb = new StringBuilder(init);
            }
        }

        // Ensure the insertFunction runs in the same transaction
        for(String upsertQuery : upsertStrings) {
//            System.out.println("Executing upsert query: " + upsertQuery);
            try {
                entityManager.createNativeQuery(upsertQuery).executeUpdate();
            }catch (Exception e){
//                System.out.println("error executing upsert query: " + upsertQuery);
                throw new RuntimeException(e);
            }
        }

        for (String checkQuery : checks) {
            System.out.println("Executing check query: " + checkQuery);
            try {
                QueryResult queryResult = getQueryResultObj(checkQuery, entityManager);
                if (queryResult.getResultSize()>0) {
                    System.out.println(queryResult.getData().toString());
                    throw new RuntimeException("Check query returned results: " + checkQuery);
                }
            } catch (Exception e) {
                System.out.println("Error executing check query: " + checkQuery);
                throw new RuntimeException(e);
            }
        }


//        entityManager.createNativeQuery(drop).executeUpdate();
    }

//    public void saveAll(SaveInterface table, List<SaveInterface> entities,  EntityManager entityManager) throws ParseException {
//
//        String tableName = table.getTableName();
//        String insertTableName = tableName + "_insert";
//        String columns = String.join(",", table.getColumns());
//
//        String init = "INSERT INTO " + insertTableName + " (" + columns + ") VALUES\n";
//        StringBuilder sb = new StringBuilder(init);
//
//        String drop = "DROP TABLE IF EXISTS " + insertTableName + ";\n";
//        String create = "CREATE TABLE IF NOT EXISTS " + insertTableName + " LIKE " + tableName + ";\n";
//        entityManager.createNativeQuery(drop).executeUpdate();
//        entityManager.createNativeQuery(create).executeUpdate();
//
//        int i = 0;
//        for (SaveInterface entity : entities) {
//            sb.append(entity.getValues());
//            System.out.println(entity.getValues());
//            if (++i % 1000 == 0 || i == entities.size()) {
//                System.out.println(sb);
//                saveEntities(sb, entityManager);
//                sb = new StringBuilder(init);
//            }
//        }
//
//        // Ensure the insertFunction runs in the same transaction
//        for(String upsertQuery : table.getUpsert().getUpsert()) {
//            System.out.println("Executing upsert query: " + upsertQuery);
//            entityManager.createNativeQuery(upsertQuery).executeUpdate();
//        }
//    }

    public void saveEntities(StringBuilder sb, EntityManager entityManager) {
        sb.setLength(sb.length() - 1);
        String modifiedString = sb.toString().replace("'null'", "NULL").replace("'DEFAULT'", "DEFAULT");
        System.out.println(modifiedString);
        try {
            System.out.println(modifiedString.substring(0, Math.min(modifiedString.length(), 10000)));
            Files.write(Path.of("query.txt"), modifiedString.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
//            System.out.println(modifiedString);
//            System.out.println("inserted batch 1000 records");
            System.out.println(entityManager.createNativeQuery(modifiedString).executeUpdate());
        } catch (Exception e) {
            System.out.println("something went wrong with saving entity");
            throw new RuntimeException(e);
        }
        System.out.println("inserted batch 1000 records");
    }






    public String getUploadDelete(String gmaName, String maName, String tableName,
                                  List<KdbColumnPersona> toDeleteBy, Boolean includeNullValues) {

        String tableIdentity = maName+"."+tableName;
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        if (gmaJsonMap == null) {
            System.out.println("GMA " + gmaName + " not found");
            return "";
        }

        MAJson ma = gmaJsonMap.getMa().stream()
                .filter(maJson -> Objects.equals(maJson.getName(), maName))
                .findFirst()
                .orElse(null);

        if (ma == null) {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
            return "";
        }

        TableJson table = Arrays.stream(ma.getTables())
                .filter(tableJson -> Objects.equals(tableJson.getName(), tableName))
                .findFirst()
                .orElse(null);

        if (table == null) {
            System.out.println("Table " + tableName + " not found in MA " + maName);
            return "";
        }

        // Build join condition string
        String joinCondition = toDeleteBy.stream()
                .map(col -> "t1." + col.getName() + (includeNullValues ? "<=>" : "=") + "t2." + col.getName())
                .collect(Collectors.joining(" AND "));

        // Build join-based delete query
        String deleteQuery = String.format("""
        DELETE t1
        FROM %s t1
        LEFT JOIN %s t2
        ON %s
        WHERE t2.%s IS NULL;
        """, tableIdentity, tableIdentity+ "_insert", joinCondition, toDeleteBy.get(0).getName());

        return deleteQuery;
    }

    public String getUploadDeleteGma(String gmaName, String maName, String tableName,
                                     List<KdbColumnPersona> toDeleteBy, Boolean includeNullValues) {

        String tableIdentity = maName+"."+tableName;
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        if (gmaJsonMap == null) {
            System.out.println("GMA " + gmaName + " not found");
            return "";
        }

        MAJson ma = gmaJsonMap.getMa().stream()
                .filter(maJson -> Objects.equals(maJson.getName(), maName))
                .findFirst()
                .orElse(null);

        if (ma == null) {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
            return "";
        }

        TableJson table = Arrays.stream(ma.getTables())
                .filter(tableJson -> Objects.equals(tableJson.getName(), tableName))
                .findFirst()
                .orElse(null);

        if (table == null) {
            System.out.println("Table " + tableName + " not found in MA " + maName);
            return "";
        }

        // Build join condition string
        String joinCondition = toDeleteBy.stream()
                .map(col -> "t1." + col.getName() + (includeNullValues ? "<=>" : "=") + "t2." + col.getName())
                .collect(Collectors.joining(" AND "));

        // Build join-based delete query
        String deleteQuery = String.format("""
        DELETE t1
        FROM %s t1
        LEFT JOIN %s t2
        ON %s
        WHERE t2.%s IS NULL;
        """, tableIdentity, "gma_resources."+maName+"."+tableName + "_insert", joinCondition, toDeleteBy.get(0).getName());

        return deleteQuery;
    }


    public String getUploadUpdate(String gmaName, String maName, String tableName,
                                  List<KdbColumnPersona> toUpdateBy, Boolean includeNullValues,
                                  List<KdbColumnPersona> updateColumns) {

        String tableIdentity = maName+"."+tableName;
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        if (gmaJsonMap == null) {
            System.out.println("GMA " + gmaName + " not found");
            return "";
        }

        MAJson ma = gmaJsonMap.getMa().stream()
                .filter(m -> Objects.equals(m.getName(), maName))
                .findFirst()
                .orElse(null);

        if (ma == null) {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
            return "";
        }

        TableJson table = Arrays.stream(ma.getTables())
                .filter(t -> Objects.equals(t.getName(), tableName))
                .findFirst()
                .orElse(null);

        if (table == null) {
            System.out.println("Table " + tableName + " not found in MA " + maName);
            return "";
        }

        String tableNameInsert = maName+"."+tableName + "_insert";

        // Build join condition from toUpdateBy
        String joinCondition = toUpdateBy.stream()
                .map(col -> "t1." + col.getName() + (includeNullValues ? "<=>" : "=") + "t2." + col.getName())
                .collect(Collectors.joining(" AND "));

        // Filter columns to update: exclude primary keys, db_id, and columns used in join
        Set<String> toUpdateByNames = toUpdateBy.stream().map(KdbColumnPersona::getName).collect(Collectors.toSet());
        List<KdbColumnPersona> columnsToUpdate = updateColumns.stream()
                .filter(c -> !c.isPrimaryKey() && !Objects.equals(c.getName(), "db_id") && !toUpdateByNames.contains(c.getName()))
                .toList();

        // Build SET clause
        String setClause = columnsToUpdate.stream()
                .map(c -> "t1." + c.getName() + " = t2." + c.getName())
                .collect(Collectors.joining(", "));

        // Build final UPDATE query
        String updateQuery = String.format("""
            UPDATE %s AS t1
            JOIN %s AS t2
                ON %s
            SET %s;
            """, tableIdentity, tableNameInsert, joinCondition, setClause);

        return updateQuery;
    }

    public String getUploadUpdateGma(String gmaName, String maName, String tableName,
                                     List<KdbColumnPersona> toUpdateBy, Boolean includeNullValues,
                                     List<KdbColumnPersona> updateColumns) {

        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        if (gmaJsonMap == null) {
            System.out.println("GMA " + gmaName + " not found");
            return "";
        }

        MAJson ma = gmaJsonMap.getMa().stream()
                .filter(m -> Objects.equals(m.getName(), maName))
                .findFirst()
                .orElse(null);

        if (ma == null) {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
            return "";
        }

        TableJson table = Arrays.stream(ma.getTables())
                .filter(t -> Objects.equals(t.getName(), tableName))
                .findFirst()
                .orElse(null);

        if (table == null) {
            System.out.println("Table " + tableName + " not found in MA " + maName);
            return "";
        }

        String tableNameInsert = "gma_resources."+maName+"_"+tableName + "_insert";

        // Build join condition from toUpdateBy
        String joinCondition = toUpdateBy.stream()
                .map(col -> "t1." + col.getName() + (includeNullValues ? "<=>" : "=") + "t2." + col.getName())
                .collect(Collectors.joining(" AND "));

        // Filter columns to update: exclude primary keys, db_id, and columns used in join
        Set<String> toUpdateByNames = toUpdateBy.stream().map(KdbColumnPersona::getName).collect(Collectors.toSet());
        List<KdbColumnPersona> columnsToUpdate = updateColumns.stream()
                .filter(c -> !c.isPrimaryKey() && !Objects.equals(c.getName(), "db_id") && !toUpdateByNames.contains(c.getName()))
                .toList();

        // Build SET clause
        String setClause = columnsToUpdate.stream()
                .map(c -> "t1." + c.getName() + " = t2." + c.getName())
                .collect(Collectors.joining(", "));

        // Build final UPDATE query
        String updateQuery = String.format("""
            UPDATE %s AS t1
            JOIN %s AS t2
                ON %s
            SET %s;
            """, tableName, tableNameInsert, joinCondition, setClause);

        return updateQuery;
    }


    public String getUploadInsert(String gmaName, String maName, String tableName, List<KdbColumnPersona> toInsertBy,Boolean includeNullValues,List<KdbColumnPersona> insertColumns,Boolean includePrimaryKey) {
        String tableIdentity = maName+"."+tableName;
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        if (gmaJsonMap == null) {
            System.out.println("GMA " + gmaName + " not found");
            return "";
        }

        MAJson ma = gmaJsonMap.getMa().stream()
                .filter(m -> Objects.equals(m.getName(), maName))
                .findFirst()
                .orElse(null);

        if (ma == null) {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
            return "";
        }

        TableJson table = Arrays.stream(ma.getTables())
                .filter(t -> Objects.equals(t.getName(), tableName))
                .findFirst()
                .orElse(null);

        if (table == null) {
            System.out.println("Table " + tableName + " not found in MA " + maName);
            return "";
        }

        String tableNameInsert = maName+"."+tableName + "_insert";

        // Build join condition
        String joinCondition = toInsertBy.stream()
                .map(col -> "t1." + col.getName() + (includeNullValues ? "<=>" : "=") + "t2." + col.getName())
                .collect(Collectors.joining(" AND "));

        // Columns for insert
        List<KdbColumnPersona> colsToInsert = insertColumns.stream()
                .filter(c -> includePrimaryKey || !c.isPrimaryKey())
                .toList();

        String columns = colsToInsert.stream()
                .map(KdbColumnPersona::getName)
                .collect(Collectors.joining(", "));

        String values = colsToInsert.stream()
                .map(c -> "t2." + c.getName())
                .collect(Collectors.joining(", "));
        String insertQuery = String.format("""
            INSERT INTO %s (%s)
            SELECT %s
            FROM %s t2
            LEFT JOIN %s t1
                ON %s
            WHERE t1.%s IS NULL;
            """, tableIdentity, columns, values, tableNameInsert, tableIdentity, joinCondition, toInsertBy.get(0).getName());

        return insertQuery;



    }

    public String getUploadInsertGma(String gmaName, String maName, String tableName, List<KdbColumnPersona> toInsertBy,Boolean includeNullValues,List<KdbColumnPersona> insertColumns,Boolean includePrimaryKey) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        String tableIdentity = maName+"."+tableName;
        if (gmaJsonMap == null) {
            System.out.println("GMA " + gmaName + " not found");
            return "";
        }

        MAJson ma = gmaJsonMap.getMa().stream()
                .filter(m -> Objects.equals(m.getName(), maName))
                .findFirst()
                .orElse(null);

        if (ma == null) {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
            return "";
        }

        TableJson table = Arrays.stream(ma.getTables())
                .filter(t -> Objects.equals(t.getName(), tableName))
                .findFirst()
                .orElse(null);

        if (table == null) {
            System.out.println("Table " + tableName + " not found in MA " + maName);
            return "";
        }

        String tableNameInsert = "gma_resources."+maName+"_"+tableName + "_insert";

        // Build join condition
        String joinCondition = toInsertBy.stream()
                .map(col -> "t1." + col.getName() + (includeNullValues ? "<=>" : "=") + "t2." + col.getName())
                .collect(Collectors.joining(" AND "));

        // Columns for insert
        List<KdbColumnPersona> colsToInsert = insertColumns.stream()
                .filter(c -> includePrimaryKey || !c.isPrimaryKey())
                .toList();

        String columns = colsToInsert.stream()
                .map(KdbColumnPersona::getName)
                .collect(Collectors.joining(", "));

        String values = colsToInsert.stream()
                .map(c -> "t2." + c.getName())
                .collect(Collectors.joining(", "));
        String insertQuery = String.format("""
            INSERT INTO %s (%s)
            SELECT %s
            FROM %s t2
            LEFT JOIN %s t1
                ON %s
            WHERE t1.%s IS NULL;
            """, tableIdentity, columns, values, tableNameInsert, tableIdentity, joinCondition, toInsertBy.get(0).getName());

        return insertQuery;



    }

    public String getUploadInsert(String gmaName, String maName, String tableName,Boolean includePrimaryKey) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        String tableIdentity = maName+"."+tableName;
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);

        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
//                 table.getColumns();

                String tableNameInsert = maName+"."+tableName + "_insert";


                List<String> columnsToInsert = Arrays.stream(table.getColumns())
                        .filter(c -> {
                            if (includePrimaryKey) {
                                // include all columns, do not filter out anything
                                return true;
                            } else {
                                // exclude primary keys and "db_id"
                                return !c.getPrimaryKey() && !Objects.equals(c.getName(), "db_id");
                            }
                        }).map(ColumnJson::getName)
                        .toList();


                StringBuilder selectColumnsSb = new StringBuilder();
                for (int i = 0; i < columnsToInsert.size(); i++) {
                    selectColumnsSb.append("t2.");
                    selectColumnsSb.append(columnsToInsert.get(i));
                    if (i < columnsToInsert.size() - 1) {
                        selectColumnsSb.append(", ");
                    }
                }




                String insertQuery = String.format("""
                        INSERT INTO %s 
                         (%s)
                        SELECT 
                            %s
                        FROM %s t2
                        
                        """, tableIdentity,String.join(",",columnsToInsert),selectColumnsSb,tableNameInsert);

                return insertQuery;


            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
                return "";
            }
        }
        System.out.println("MA " + maName + " not found in GMA " + gmaName);
        return "";

    }

    public String getUploadInsertGma(String gmaName, String maName, String tableName,Boolean includePrimaryKey) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        String tableIdentity = maName+"."+tableName;
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);

        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
//                 table.getColumns();

                String tableNameInsert = "gma_resources."+maName+"_"+tableName + "_insert";


                List<String> columnsToInsert = Arrays.stream(table.getColumns())
                        .filter(c -> {
                            if (includePrimaryKey) {
                                // include all columns, do not filter out anything
                                return true;
                            } else {
                                // exclude primary keys and "db_id"
                                return !c.getPrimaryKey() && !Objects.equals(c.getName(), "db_id");
                            }
                        }).map(ColumnJson::getName)
                        .toList();


                StringBuilder selectColumnsSb = new StringBuilder();
                for (int i = 0; i < columnsToInsert.size(); i++) {
                    selectColumnsSb.append("t2.");
                    selectColumnsSb.append(columnsToInsert.get(i));
                    if (i < columnsToInsert.size() - 1) {
                        selectColumnsSb.append(", ");
                    }
                }




                String insertQuery = String.format("""
                        INSERT INTO %s 
                         (%s)
                        SELECT 
                            %s
                        FROM %s t2
                        
                        """, tableIdentity,String.join(",",columnsToInsert),selectColumnsSb,tableNameInsert);

                return insertQuery;


            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
                return "";
            }
        }
        System.out.println("MA " + maName + " not found in GMA " + gmaName);
        return "";

    }

    public String getUploadInsertGma(String gmaName, String maName, String tableName) {
        String tableIdentity = maName+"."+tableName;
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);

        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
//                 table.getColumns();

                String tableNameInsert = "gma_resources."+maName+"_"+tableName + "_insert";


                List<String> columnsToInsert = Arrays.stream(table.getColumns())
                        .map(ColumnJson::getName)
                        .toList();


                StringBuilder selectColumnsSb = new StringBuilder();
                for (int i = 0; i < columnsToInsert.size(); i++) {
                    selectColumnsSb.append("t2.");
                    selectColumnsSb.append(columnsToInsert.get(i));
                    if (i < columnsToInsert.size() - 1) {
                        selectColumnsSb.append(", ");
                    }
                }




                String insertQuery = String.format("""
                        INSERT INTO %s 
                         (%s)
                        SELECT 
                            %s
                        FROM %s t2
                        
                        """, tableIdentity,String.join(",",columnsToInsert),selectColumnsSb,tableNameInsert);

                return insertQuery;


            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
                return "";
            }
        }
        System.out.println("MA " + maName + " not found in GMA " + gmaName);
        return "";

    }


    public String getUploadInsert(String gmaName, String maName, String tableName) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        String tableIdentity = maName+"."+tableName;
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);

        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
//                 table.getColumns();

                String tableNameInsert = maName+"."+tableName + "_insert";


                List<String> columnsToInsert = Arrays.stream(table.getColumns())
                        .map(ColumnJson::getName)
                        .toList();


                StringBuilder selectColumnsSb = new StringBuilder();
                for (int i = 0; i < columnsToInsert.size(); i++) {
                    selectColumnsSb.append("t2.");
                    selectColumnsSb.append(columnsToInsert.get(i));
                    if (i < columnsToInsert.size() - 1) {
                        selectColumnsSb.append(", ");
                    }
                }




                String insertQuery = String.format("""
                        INSERT INTO %s 
                         (%s)
                        SELECT 
                            %s
                        FROM %s t2
                        
                        """, tableIdentity,String.join(",",columnsToInsert),selectColumnsSb,tableNameInsert);

                return insertQuery;


            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
                return "";
            }
        }
        System.out.println("MA " + maName + " not found in GMA " + gmaName);
        return "";

    }

    public String getUploadInsert(String gmaName, String maName, String tableName, List<KdbColumnPersona> toInsertBy,Boolean includeNullValues) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        String tableIdentity = maName+"."+tableName;
        if (gmaJsonMap == null) {
            System.out.println("GMA " + gmaName + " not found");
            return "";
        }

        MAJson ma = gmaJsonMap.getMa().stream()
                .filter(m -> Objects.equals(m.getName(), maName))
                .findFirst()
                .orElse(null);

        if (ma == null) {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
            return "";
        }

        TableJson table = Arrays.stream(ma.getTables())
                .filter(t -> Objects.equals(t.getName(), tableName))
                .findFirst()
                .orElse(null);

        if (table == null) {
            System.out.println("Table " + tableName + " not found in MA " + maName);
            return "";
        }

        String tableNameInsert = maName+"."+tableName + "_insert";

        List<ColumnJson> insertColumns = Arrays.stream(table.getColumns())
                .filter(c -> !c.isPrimaryKey() || !Objects.equals(c.getName(), "db_id"))

                .toList();



        // Build join condition
        String joinCondition = toInsertBy.stream()
                .map(col -> "t1." + col.getName() + (includeNullValues ? "<=>" : "=") + "t2." + col.getName())
                .collect(Collectors.joining(" AND "));

        String valuesString = insertColumns.stream().map(ColumnJson::getName).collect(Collectors.joining(", "));

        String insertColumnsString = insertColumns.stream()
                .map(c-> "t2." + c.getName())
                .collect(Collectors.joining(", "));

        // Build insert query using LEFT JOIN
        String insertQuery = String.format("""
            INSERT INTO %s
             (%s)
            SELECT %s
            FROM %s t2
            LEFT JOIN %s t1
                ON %s
            WHERE t1.%s IS NULL;
            """,
                tableIdentity,
                valuesString,
                insertColumnsString,
                tableNameInsert,
                tableIdentity,
                joinCondition,
                toInsertBy.get(0).getName() // first column for IS NULL check
        );

        return insertQuery;

    }

    public String getUploadInsertGma(String gmaName, String maName, String tableName, List<KdbColumnPersona> toInsertBy,Boolean includeNullValues) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        String tableIdentity = maName+"."+tableName;
        if (gmaJsonMap == null) {
            System.out.println("GMA " + gmaName + " not found");
            return "";
        }

        MAJson ma = gmaJsonMap.getMa().stream()
                .filter(m -> Objects.equals(m.getName(), maName))
                .findFirst()
                .orElse(null);

        if (ma == null) {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
            return "";
        }

        TableJson table = Arrays.stream(ma.getTables())
                .filter(t -> Objects.equals(t.getName(), tableName))
                .findFirst()
                .orElse(null);

        if (table == null) {
            System.out.println("Table " + tableName + " not found in MA " + maName);
            return "";
        }

        String tableNameInsert = "gma_resources."+maName+"_"+tableName + "_insert";

        List<ColumnJson> insertColumns = Arrays.stream(table.getColumns())
                .filter(c -> !c.isPrimaryKey() || !Objects.equals(c.getName(), "db_id"))

                .toList();



        // Build join condition
        String joinCondition = toInsertBy.stream()
                .map(col -> "t1." + col.getName() + (includeNullValues ? "<=>" : "=") + "t2." + col.getName())
                .collect(Collectors.joining(" AND "));

        String valuesString = insertColumns.stream().map(ColumnJson::getName).collect(Collectors.joining(", "));

        String insertColumnsString = insertColumns.stream()
                .map(c-> "t2." + c.getName())
                .collect(Collectors.joining(", "));

        // Build insert query using LEFT JOIN
        String insertQuery = String.format("""
            INSERT INTO %s
             (%s)
            SELECT %s
            FROM %s t2
            LEFT JOIN %s t1
                ON %s
            WHERE t1.%s IS NULL;
            """,
                tableIdentity,
                valuesString,
                insertColumnsString,
                tableNameInsert,
                tableIdentity,
                joinCondition,
                toInsertBy.get(0).getName() // first column for IS NULL check
        );

        return insertQuery;

    }


    public QueryResult queryAirtable(AirtableInterface table, List<AirColumnTemplate> byCols ,List<KdbAirColumnPersona>getCols, String bearer) {

        Map<String,List<Object>> results = table.getAllColumns().stream().collect(Collectors.toMap(AirColumnTemplate::getRealName,air->new ArrayList<>())) ;

        OkHttpClient client = new OkHttpClient();
        String url = "https://api.airtable.com/v0/";
        String tableUrl = url + table.getAppId() + "/" + table.getTableId();
        List<String> params = new ArrayList<>();
        String fieldsParam = "";

        if (!getCols.isEmpty()) {
            fieldsParam = getCols.stream()
                    .map(KdbAirColumnPersona::getRealName)
                    .map(name -> "fields[]=" + URLEncoder.encode(name, StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));
            params.add(fieldsParam);
        }
        String filterByFormula = "";

        if (!byCols.isEmpty()) {

            String formula = byCols.stream()
                    .map(col -> "{" + col.getRealName() + "}=\""
                            + col.getQueryMatchStrings().get(0) + "\"")
                    .collect(Collectors.joining(","));

            filterByFormula = "&filterByFormula="
                    + URLEncoder.encode("AND(" + formula + ")", StandardCharsets.UTF_8);
            params.add(filterByFormula);
        }


         String offset = null;
            do {
                if(offset != null) {
                    params.add("offset=" + offset);
                }
                String queryString = String.join("&", params);
                if(!queryString.isEmpty()) {
                    queryString = "?" + queryString;
                }


                Request request = new Request.Builder()
                        .url(tableUrl + queryString)
                        .method("GET", null)
                        .header("Authorization", "Bearer " + bearer)
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    String responseBody = response.body().string();
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode rootNode = mapper.readTree(responseBody);
                    JsonNode recordsNode = rootNode.get("records");
                    for(JsonNode recordNode : recordsNode) {
                        JsonNode fieldsNode = recordNode.get("fields");
                        for (KdbAirColumnPersona col : getCols) {

                            String key = col.getRealName();
                            JsonNode valueNode = fieldsNode.get(key);

                            results.computeIfPresent(key, (k, v) -> {
                                v.add(valueNode != null ? valueNode.asText() : null);
                                return v;
                            });
                        }
                    }



                    // Process recordsNode and populate results as needed
                    params.remove(params.size()-1); // remove old offset

                    if (rootNode.has("offset") && !rootNode.get("offset").isNull()) {
                        offset = "offset="+rootNode.get("offset").asText();
                    } else {
                        offset = null; // No more pages
                    }


                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } while (offset != null);

            return new QueryResult(results);




    }

    public QueryResult queryAirtable(AirtableInterface table,String filterByFormula, List<KdbAirColumnPersona>getCols, String bearer) {

        Map<String,List<Object>> results = table.getAllColumns().stream().collect(Collectors.toMap(AirColumnTemplate::getRealName,air->new ArrayList<>())) ;

        OkHttpClient client = new OkHttpClient();
        String url = "https://api.airtable.com/v0/";
        String tableUrl = url + table.getAppId() + "/" + table.getTableId();
        List<String> params = new ArrayList<>();
        String fieldsParam = "";

        if (!getCols.isEmpty()) {
            fieldsParam = getCols.stream()
                    .map(KdbAirColumnPersona::getRealName)
                    .map(name -> "fields[]=" + URLEncoder.encode(name, StandardCharsets.UTF_8))
                    .collect(Collectors.joining("&"));
            params.add(fieldsParam);
        }

            params.add(filterByFormula);



        String offset = null;
        do {
            if(offset != null) {
                params.add("offset=" + offset);
            }
            String queryString = String.join("&", params);
            if(!queryString.isEmpty()) {
                queryString = "?" + queryString;
            }


            Request request = new Request.Builder()
                    .url(tableUrl + queryString)
                    .method("GET", null)
                    .header("Authorization", "Bearer " + bearer)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                String responseBody = response.body().string();
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseBody);
                JsonNode recordsNode = rootNode.get("records");
                for(JsonNode recordNode : recordsNode) {
                    JsonNode fieldsNode = recordNode.get("fields");
                    for (KdbAirColumnPersona col : getCols) {

                        String key = col.getRealName();
                        JsonNode valueNode = fieldsNode.get(key);

                        results.computeIfPresent(key, (k, v) -> {
                            v.add(valueNode != null ? valueNode.asText() : null);
                            return v;
                        });
                    }
                }



                // Process recordsNode and populate results as needed
                params.remove(params.size()-1); // remove old offset

                if (rootNode.has("offset") && !rootNode.get("offset").isNull()) {
                    offset = "offset="+rootNode.get("offset").asText();
                } else {
                    offset = null; // No more pages
                }


            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } while (offset != null);

        return new QueryResult(results);

    }
public void saveAllAirtable(
        AirtableInterface table,
        List<AirtableInterface> entities,
        String bearer,
        List<KdbAirColumnPersona> upsertFields,
        List<KdbAirColumnPersona> checkFields) {

    OkHttpClient client = new OkHttpClient();
    String url = "https://api.airtable.com/v0/";
    String tableUrl = url + table.getAppId() + "/" + table.getTableId();

    ObjectMapper mapper = new ObjectMapper();

    List<Map<String, Object>> recordsList = new ArrayList<>();

    for (AirtableInterface airtableInterface : entities) {

        Map<String, Object> fieldsMap = new HashMap<>();

        for (KdbAirColumnPersona field : upsertFields) {

            AirColumnTemplate column = airtableInterface.getAllColumns()
                    .stream()
                    .filter(air -> Objects.equals(air.getRealName(), field.getRealName()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("field " + field.getRealName() + " not found"));

            Object value = safeGetValue(column);

            // ✅ Fix null handling
            if ("null".equals(value)) {
                value = null;
            }

            fieldsMap.put(column.getRealName(), value);
        }

        Map<String, Object> record = new HashMap<>();
        record.put("fields", fieldsMap);

        recordsList.add(record);
    }

    Map<String, Object> body = new HashMap<>();
    body.put("typecast", true);
    body.put("records", recordsList);

    if (!checkFields.isEmpty()) {
        Map<String, Object> upsert = new HashMap<>();
        upsert.put("fieldsToMergeOn",
                checkFields.stream().map(KdbAirColumnPersona::getRealName).toList());

        body.put("performUpsert", upsert);
    }

    try {
        String json = mapper.writeValueAsString(body);

        System.out.println(json + " records to be upserted");

        RequestBody requestBody = RequestBody.create(
                json, MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(tableUrl)
                .method(checkFields.isEmpty() ? "POST" : "PATCH", requestBody)
                .header("Authorization", "Bearer " + bearer)
                .build();

        try (Response response = client.newCall(request).execute()) {
            System.out.println(response.body().string());
        }

    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
    public void saveAllAirtable(AirtableInterface table, List<AirtableInterface> entities, List<KdbAirColumnPersona> upsertFields, List<KdbAirColumnPersona> checkFields, List<String> checks) throws ParseException {
//         Example of how the JSON structure for upsert and checks might look:
//         {
//
//            "performUpsert": {
//            "fieldsToMergeOn": ["Email"]
//        },
//            "records": [
//            {
//                "fields": {
//                "Email": "test@example.com",
//                        "Name": "John Doe",
//                        "Age": 30
//            }
//            }
//  ]
//        }
    }

//    public void saveAllAirtable(AirtableInterface table, List<AirtableInterface> entities,  List<String> upsertStrings) {
//    }
}