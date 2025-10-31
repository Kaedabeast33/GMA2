package org.example.ContextInstance;

import org.example.jsonBuilder.gma.GMAJson;
import org.example.jsonBuilder.gma.ma.MAJson;
import org.example.jsonBuilder.gma.ma.tables.TableJson;
import org.example.jsonBuilder.gma.ma.tables.columns.ColumnJson;

import java.util.*;

public enum KDBContext {
    KDB_CONTEXT;

    final Map<String, GMAJson> gmaJsonMap = new HashMap<>();


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

    public List<String> getUniqueIdentifierColumnsByGroupName(String gmaName, String maName, String tableName,String groupName) {
        GMAJson gmaJsonMap = KDB_CONTEXT.gmaJsonMap.get(gmaName);
        MAJson ma = gmaJsonMap.getMa().stream().filter(maJson -> Objects.equals(maJson.getName(), maName)).findFirst().orElse(null);
        if (ma != null) {
            TableJson table = Arrays.stream(ma.getTables()).filter(tableJson -> Objects.equals(tableJson.getName(), tableName)).findFirst().orElse(null);
            if (table != null) {
                List<ColumnJson> matchingColumns = Arrays.stream(table.getColumns())
                        .filter(ColumnJson::isUniqueIdentifier)
                        .toList();
                return matchingColumns.stream().filter(col-> Arrays.stream(col.getColumnGroups()).anyMatch(group-> Objects.equals(group.getName(), groupName)))
                        .map(ColumnJson::getName).toList();

            } else {
                System.out.println("Table " + tableName + " not found in MA " + maName);
            }

        }


        return new ArrayList<>();
    }




}

