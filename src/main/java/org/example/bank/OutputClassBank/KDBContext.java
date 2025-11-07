package org.example.bank.OutputClassBank;

import jakarta.persistence.EntityManager;
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

    public List<String> getColumns(String gmaName, String maName, String tableName) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
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


    public void saveAll(EntityInterface table, List<EntityInterface> entities,  EntityManager entityManager,List<String> upsertStrings) throws ParseException {

        String tableName = table.getTableName();
        String insertTableName = tableName + "_insert";
        String columns = String.join(",", table.getColumns());

        String init = "INSERT INTO " + insertTableName + " (" + columns + ") VALUES\n";
        StringBuilder sb = new StringBuilder(init);

        String drop = "DROP TABLE IF EXISTS " + insertTableName + ";\n";
        String create = "CREATE TABLE IF NOT EXISTS " + insertTableName + " LIKE " + tableName + ";\n";
        entityManager.createNativeQuery(drop).executeUpdate();
        entityManager.createNativeQuery(create).executeUpdate();

        int i = 0;
        for (EntityInterface entity : entities) {
            sb.append(entity.getValues());
            System.out.println(entity.getValues());
            if (++i % 1000 == 0 || i == entities.size()) {
                System.out.println(sb);
                saveEntities(sb, entityManager);
                sb = new StringBuilder(init);
            }
        }

        // Ensure the insertFunction runs in the same transaction
        for(String upsertQuery : upsertStrings) {
            System.out.println("Executing upsert query: " + upsertQuery);
            entityManager.createNativeQuery(upsertQuery).executeUpdate();
        }
    }

    public void saveAll(EntityInterface table, List<EntityInterface> entities,  EntityManager entityManager) throws ParseException {

        String tableName = table.getTableName();
        String insertTableName = tableName + "_insert";
        String columns = String.join(",", table.getColumns());

        String init = "INSERT INTO " + insertTableName + " (" + columns + ") VALUES\n";
        StringBuilder sb = new StringBuilder(init);

        String drop = "DROP TABLE IF EXISTS " + insertTableName + ";\n";
        String create = "CREATE TABLE IF NOT EXISTS " + insertTableName + " LIKE " + tableName + ";\n";
        entityManager.createNativeQuery(drop).executeUpdate();
        entityManager.createNativeQuery(create).executeUpdate();

        int i = 0;
        for (EntityInterface entity : entities) {
            sb.append(entity.getValues());
            System.out.println(entity.getValues());
            if (++i % 1000 == 0 || i == entities.size()) {
                System.out.println(sb);
                saveEntities(sb, entityManager);
                sb = new StringBuilder(init);
            }
        }

        // Ensure the insertFunction runs in the same transaction
        for(String upsertQuery : table.getUpsert().getUpsert()) {
            System.out.println("Executing upsert query: " + upsertQuery);
            entityManager.createNativeQuery(upsertQuery).executeUpdate();
        }
    }

    public void saveEntities(StringBuilder sb, EntityManager entityManager) {
        sb.setLength(sb.length() - 1);
        String modifiedString = sb.toString().replace("'null'", "NULL");
//        System.out.println(modifiedString);
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



    public List<String> getColumnsByGroupName(String gmaName, String maName, String tableName, String groupName) {
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






    public List<String> getUniqueIdentifierColumns(String gmaName, String maName, String tableName) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);
        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
                List<ColumnJson> matchingColumns = Arrays.stream(table.getColumns())
                        .filter(ColumnJson::isUniqueIdentifier)
                        .toList();
                return matchingColumns.stream().map(ColumnJson::getName).toList();

            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }

        }


        return new ArrayList<>();
    }

    public List<String> getUniqueIdentifierColumnsByGroupName(String gmaName, String maName, String tableName, String groupName) {
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


}

