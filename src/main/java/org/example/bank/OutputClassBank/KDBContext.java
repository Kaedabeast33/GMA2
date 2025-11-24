package org.example.bank.OutputClassBank;

import jakarta.persistence.EntityManager;
import org.example.ClassOutputCreator.templates.ColumnTemplate;
import org.example.ClassOutputCreator.templates.KdbGma;
import org.example.JsonBuilder.json.GMAJson;
import org.example.JsonBuilder.json.ma.MAJson;
import org.example.JsonBuilder.json.ma.tables.TableJson;
import org.example.JsonBuilder.json.ma.tables.columns.ColumnJson;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public String getQueryByCol(String gmaName, String maName, String tableName, List<ColumnTemplate> byCols) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);
        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
                List<String> whereClause = new ArrayList<>();
                for(ColumnTemplate byCol: byCols){
                    StringBuilder sb = new StringBuilder();
                    sb.append(byCol.getName()).append(" ").append(byCol.getQueryMatchString());
                    whereClause.add(sb.toString());
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
                return query;
            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }
        } else {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
        }

        return "";
    }

    public String getQueryByCol(String gmaName, String maName, String tableName, List<ColumnTemplate> byCols,List<ColumnTemplate> getCols) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);
        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
                String columsSb = getCols.stream().map(ColumnTemplate::getName).collect(Collectors.joining(",\n"));


                List<String> whereClause = new ArrayList<>();
                for(ColumnTemplate byCol: byCols){
                    whereClause.add(byCol.getName() + " " + byCol.getQueryMatchString());
                }
                String query = String.format("""
                        SELECT
                            %s
                        FROM
                            %s.%s
                        WHERE
                            %s
                        """,columsSb,maName,tableName,String.join(" AND ",whereClause));
                System.out.println(query);
                return query;
            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }
        } else {
            System.out.println("MA " + maName + " not found in GMA " + gmaName);
        }

        return "";
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
                List<ColumnJson> matchingColumns = Arrays.stream(table.getColumns())
                        .filter(col -> Arrays.stream(col.getColumnGroups())
                                .anyMatch(group -> Objects.equals(group.getName(), groupName)))
                        .toList();
                return matchingColumns;

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


    public void saveAll(EntityInterface table, List<EntityInterface> entities,  EntityManager entityManager,List<String> upsertStrings) throws ParseException {

        String tableName = table.getTableName();
        String insertTableName = tableName + "_insert";
        String columns = String.join(",", table.getColumnsString());

        String init = "INSERT INTO " + insertTableName + " (" + columns + ") VALUES\n";
        StringBuilder sb = new StringBuilder(init);

        String drop = "DROP TABLE IF EXISTS " + insertTableName + ";\n";
        String create = "CREATE TABLE IF NOT EXISTS " + insertTableName + " LIKE " + tableName + ";\n";
        entityManager.createNativeQuery(drop).executeUpdate();
        entityManager.createNativeQuery(create).executeUpdate();

        int i = 0;
        for (EntityInterface entity : entities) {
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
            System.out.println("Executing upsert query: " + upsertQuery);
            try {
                entityManager.createNativeQuery(upsertQuery).executeUpdate();
            }catch (Exception e){
                System.out.println("error executing upsert query: " + upsertQuery);
                throw new RuntimeException(e);
            }
        }
    }

//    public void saveAll(EntityInterface table, List<EntityInterface> entities,  EntityManager entityManager) throws ParseException {
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
//        for (EntityInterface entity : entities) {
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
        String modifiedString = sb.toString().replace("'null'", "NULL");
//        System.out.println(modifiedString);
        try {
//            System.out.println(modifiedString.substring(0, Math.min(modifiedString.length(), 10000)));
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
                                  List<ColumnTemplate> toDeleteBy, Boolean includeNullValues) {

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
        """, tableName, tableName + "_insert", joinCondition, toDeleteBy.get(0).getName());

        return deleteQuery;
    }


    public String getUploadUpdate(String gmaName, String maName, String tableName,
                                  List<ColumnTemplate> toUpdateBy, Boolean includeNullValues,
                                  List<ColumnJson> updateColumns) {

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

        String tableNameInsert = tableName + "_insert";

        // Build join condition from toUpdateBy
        String joinCondition = toUpdateBy.stream()
                .map(col -> "t1." + col.getName() + (includeNullValues ? "<=>" : "=") + "t2." + col.getName())
                .collect(Collectors.joining(" AND "));

        // Filter columns to update: exclude primary keys, db_id, and columns used in join
        Set<String> toUpdateByNames = toUpdateBy.stream().map(ColumnTemplate::getName).collect(Collectors.toSet());
        List<ColumnJson> columnsToUpdate = updateColumns.stream()
                .filter(c -> !c.getPrimaryKey() && !Objects.equals(c.getName(), "db_id") && !toUpdateByNames.contains(c.getName()))
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


    public String getUploadInsert(String gmaName, String maName, String tableName, List<ColumnTemplate> toInsertBy,Boolean includeNullValues,List<ColumnJson> insertColumns,Boolean includePrimaryKey) {
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

        String tableNameInsert = tableName + "_insert";

        // Build join condition
        String joinCondition = toInsertBy.stream()
                .map(col -> "t1." + col.getName() + (includeNullValues ? "<=>" : "=") + "t2." + col.getName())
                .collect(Collectors.joining(" AND "));

        // Columns for insert
        List<ColumnJson> colsToInsert = insertColumns.stream()
                .filter(c -> includePrimaryKey || !c.getPrimaryKey())
                .toList();

        String columns = colsToInsert.stream()
                .map(ColumnJson::getName)
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
            """, tableName, columns, values, tableNameInsert, tableName, joinCondition, toInsertBy.get(0).getName());

        return insertQuery;



    }

    public String getUploadInsert(String gmaName, String maName, String tableName) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);

        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
//                 table.getColumns();

                String tableNameInsert = tableName + "_insert";



                String insertQuery = String.format("""
                        INSERT INTO %s 
                        SELECT 
                            *
                        FROM %s t2
                        
                        """, tableName,tableNameInsert);

                return insertQuery;


            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
                return "";
            }
        }
        System.out.println("MA " + maName + " not found in GMA " + gmaName);
        return "";

    }

    public String getUploadInsert(String gmaName, String maName, String tableName, List<ColumnTemplate> toInsertBy,Boolean includeNullValues) {
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

        String tableNameInsert = tableName + "_insert";

        // Build join condition
        String joinCondition = toInsertBy.stream()
                .map(col -> "t1." + col.getName() + (includeNullValues ? "<=>" : "=") + "t2." + col.getName())
                .collect(Collectors.joining(" AND "));

        // Build insert query using LEFT JOIN
        String insertQuery = String.format("""
            INSERT INTO %s
            SELECT t2.*
            FROM %s t2
            LEFT JOIN %s t1
                ON %s
            WHERE t1.%s IS NULL;
            """,
                tableName,
                tableNameInsert,
                tableName,
                joinCondition,
                toInsertBy.get(0).getName() // first column for IS NULL check
        );

        return insertQuery;

    }




}

