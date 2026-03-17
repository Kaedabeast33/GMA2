package org.example.bank.db.whereObj;

import org.example.JsonBuilder.json.ma.tables.TableJson;
import org.example.JsonBuilder.json.ma.tables.columns.ColumnJson;
import org.example.bank.db.contextObj.Rules;
import org.example.bank.db.contextObj.match.JsonColumnContextMatch;

import java.util.*;
import java.util.stream.Collectors;

public class ColWhereList implements WhereObjInterface {

    private Map<ColumnJson, Rules> columnJsonList;

    public ColWhereList(List<JsonColumnContextMatch> columnFilter) {
        this.columnJsonList = new HashMap<>();
        if (columnFilter == null) return;

        for (JsonColumnContextMatch colContextMatch : columnFilter) {
            if (colContextMatch == null) continue;
            ColumnJson columnJson = colContextMatch.getJson();
            if (columnJson == null) continue;

            String columnName = columnJson.getName();
            if (columnName == null) continue;

            Rules contextRules = colContextMatch.getContext() == null ? null : colContextMatch.getContext().getRules();

            if (columnJsonList.containsKey(columnJson)) {
                Rules existing = columnJsonList.get(columnJson);
                if (existing == null) {
                    existing = new Rules();
                    columnJsonList.put(columnJson, existing);
                }
                if (contextRules != null) existing.addAll(contextRules);
            } else {
                Rules toPut = new Rules();
                if (contextRules != null) toPut.addAll(contextRules);
                columnJsonList.put(columnJson, toPut);
            }
        }
    }

    public ColWhereList(Collection<ColumnJson> columns) {
        System.out.println("initializing ColWhereList with null Rules for provided columns");
        this.columnJsonList = new HashMap<>();
        if (columns == null) return;

        for (ColumnJson col : columns) {
            if (col == null) continue;
            String name = col.getName();
            if (name == null) continue;
            columnJsonList.putIfAbsent(col, null);
        }
    }

    public ColWhereList(TabWhereList tabs) {
        System.out.println("initializing ColWhereList from TabWhereList");
        this.columnJsonList = new HashMap<>();
        if (tabs == null || tabs.getTableJsonList() == null) return;

        for (Map.Entry<TableJson, Rules> tableEntry : tabs.getTableJsonList().entrySet()) {
            TableJson tableJson = tableEntry.getKey();
            Rules tableRules = tableEntry.getValue();
            if (tableJson == null || tableJson.getColumns() == null) continue;

            for (ColumnJson column : tableJson.getColumns()) {
                if (column == null) continue;

                if (columnJsonList.containsKey(column)) {
                    Rules existing = columnJsonList.get(column);
                    if (existing == null) {
                        existing = new Rules();
                        columnJsonList.put(column, existing);
                    }
                    if (tableRules != null) existing.addAll(tableRules);
                } else {
                    Rules toPut = new Rules();
                    if (tableRules != null) toPut.addAll(tableRules);
                    columnJsonList.put(column, toPut);
                }
            }
        }
    }

    // java
    public ColWhereList(List<JsonColumnContextMatch> columnFilter, ColWhereList columnJsons) {
        this(columnFilter);
        if (columnJsons == null || columnJsons.getColumnJsonList() == null) return;
        if (this.columnJsonList == null) this.columnJsonList = new HashMap<>();

        for (Map.Entry<ColumnJson, Rules> entry : columnJsons.getColumnJsonList().entrySet()) {
            ColumnJson column = entry.getKey();
            Rules rules = entry.getValue();
            if (column == null) continue;

            // Only update keys that are already present from the columnFilter.
            if (!this.columnJsonList.containsKey(column)) continue;

            Rules existing = this.columnJsonList.get(column);
            if (existing == null) {
                if (rules != null) {
                    Rules toPut = new Rules();
                    toPut.addAll(rules);
                    this.columnJsonList.put(column, toPut);
                }
                // if both existing and incoming rules are null, leave as null
            } else {
                if (rules != null) existing.addAll(rules);
            }
        }
    }


    public Map<ColumnJson, Rules> getColumnJsonList() {
        return columnJsonList;
    }

    public void setColumnJsonList(Map<ColumnJson, Rules> columnJsonList) {
        this.columnJsonList = columnJsonList;
    }

    public Rules addRule(ColumnJson column, Rules incoming) {
        if (column == null) throw new IllegalArgumentException("column cannot be null");
        String columnName = column.getName();
        if (columnName == null) throw new IllegalArgumentException("column name cannot be null");

        if (columnJsonList == null) columnJsonList = new HashMap<>();

        if (!columnJsonList.containsKey(column)) {
            Rules toPut = new Rules();
            if (incoming != null) toPut.addAll(incoming);
            columnJsonList.put(column, toPut);
            return toPut;
        } else {
            Rules existing = columnJsonList.get(column);
            if (existing == null) {
                existing = new Rules();
                columnJsonList.put(column, existing);
            }
            if (incoming != null) existing.addAll(incoming);
            return existing;
        }
    }

    @Override
    public Rules addRule(Rules incoming) {
        throw new UnsupportedOperationException("Use addRule(ColumnJson, Rules) with a column name");
    }

    public List<ColumnJson> getList() {
        if (columnJsonList == null) return Collections.emptyList();
        return columnJsonList.keySet().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "ColWhereList{" +
                "columnJsonList=" + columnJsonList +
                '}';
    }
}
