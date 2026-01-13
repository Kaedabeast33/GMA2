package org.example.bank.db.whereObj;

import org.example.JsonBuilder.json.ma.tables.TableJson;
import org.example.bank.db.contextObj.Rules;
import org.example.bank.db.contextObj.match.JsonTableContextMatch;

import java.util.*;
import java.util.stream.Collectors;

public class TabWhereList implements WhereObjInterface {

    private Map<TableJson, Rules> tableJsonList;

    public TabWhereList(List<JsonTableContextMatch> tableFilter) {
        this.tableJsonList = new HashMap<>();
        if (tableFilter == null) return;

        for (JsonTableContextMatch tableContextMatch : tableFilter) {
            if (tableContextMatch == null) continue;
            TableJson tableJson = tableContextMatch.getJson();
            if (tableJson == null) continue;

            String tableName = tableJson.getName();
            if (tableName == null) continue;

            Rules contextRules = tableContextMatch.getContext() == null ? null : tableContextMatch.getContext().getRules();

            if (tableJsonList.containsKey(tableJson)) {
                Rules existing = tableJsonList.get(tableJson);
                if (existing == null) {
                    existing = new Rules();
                    tableJsonList.put(tableJson, existing);
                }
                if (contextRules != null) existing.addAll(contextRules);
            } else {
                Rules toPut = new Rules();
                if (contextRules != null) toPut.addAll(contextRules);
                tableJsonList.put(tableJson, toPut);
            }
        }
    }

    public TabWhereList(Collection<TableJson> tables) {
        System.out.println("initializing TabWhereList with null Rules for provided tables");
        this.tableJsonList = new HashMap<>();
        if (tables == null) return;

        for (TableJson table : tables) {
            if (table == null) continue;
            String name = table.getName();
            if (name == null) continue;
            tableJsonList.putIfAbsent(table, null);
        }
    }

    public TabWhereList(MaWhereList mas) {
        System.out.println("initializing TabWhereList from MaWhereList");
        this.tableJsonList = new HashMap<>();
        if (mas == null || mas.getMaJsonList() == null) return;

        for (Map.Entry<org.example.JsonBuilder.json.ma.MAJson, Rules> maEntry : mas.getMaJsonList().entrySet()) {
            org.example.JsonBuilder.json.ma.MAJson maJson = maEntry.getKey();
            Rules maRules = maEntry.getValue();
            if (maJson == null || maJson.getTables() == null) continue;

            for (TableJson table : maJson.getTables()) {
                if (table == null) continue;

                if (tableJsonList.containsKey(table)) {
                    Rules existing = tableJsonList.get(table);
                    if (existing == null) {
                        existing = new Rules();
                        tableJsonList.put(table, existing);
                    }
                    if (maRules != null) existing.addAll(maRules);
                } else {
                    Rules toPut = new Rules();
                    if (maRules != null) toPut.addAll(maRules);
                    tableJsonList.put(table, toPut);
                }
            }
        }
    }



    // java
    // java
    public TabWhereList(List<JsonTableContextMatch> tableFilter, TabWhereList tableJsons) {
        this(tableFilter);
        if (tableJsons == null || tableJsons.getTableJsonList() == null) return;
        if (this.tableJsonList == null) this.tableJsonList = new HashMap<>();

        for (Map.Entry<TableJson, Rules> entry : tableJsons.getTableJsonList().entrySet()) {
            TableJson table = entry.getKey();
            Rules rules = entry.getValue();
            if (table == null) continue;

            // Only update keys that are already present from the tableFilter.
            if (!this.tableJsonList.containsKey(table)) continue;

            Rules existing = this.tableJsonList.get(table);
            if (existing == null) {
                if (rules != null) {
                    Rules toPut = new Rules();
                    toPut.addAll(rules);
                    this.tableJsonList.put(table, toPut);
                }
                // if both existing and incoming are null, leave as null
            } else {
                if (rules != null) existing.addAll(rules);
            }
        }
    }



    public Map<TableJson, Rules> getTableJsonList() {
        return tableJsonList;
    }

    public void setTableJsonList(Map<TableJson, Rules> tableJsonList) {
        this.tableJsonList = tableJsonList;
    }

    public Rules addRule(TableJson table, Rules incoming) {
        if (table == null) throw new IllegalArgumentException("table cannot be null");
        String tableName = table.getName();
        if (tableName == null) throw new IllegalArgumentException("table name cannot be null");

        if (tableJsonList == null) tableJsonList = new HashMap<>();

        if (!tableJsonList.containsKey(table)) {
            Rules toPut = new Rules();
            if (incoming != null) toPut.addAll(incoming);
            tableJsonList.put(table, toPut);
            return toPut;
        } else {
            Rules existing = tableJsonList.get(table);
            if (existing == null) {
                existing = new Rules();
                tableJsonList.put(table, existing);
            }
            if (incoming != null) existing.addAll(incoming);
            return existing;
        }
    }

    @Override
    public Rules addRule(Rules incoming) {
        throw new UnsupportedOperationException("Use addRule(TableJson, Rules) with a table name");
    }

    public List<TableJson> getList() {
        if (tableJsonList == null) return Collections.emptyList();
        return tableJsonList.keySet().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "TabWhereList{" +
                "tableJsonList=" + tableJsonList +
                '}';
    }
}
