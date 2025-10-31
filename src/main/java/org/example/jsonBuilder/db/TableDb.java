package org.example.jsonBuilder.db;

import org.example.jsonBuilder.gma.ma.tables.columns.ColumnJson;

import java.util.List;

public class TableDb {
    String name;
    String description;
    String tableId;
    String tableType;
    List<String> tags;
    List<ColumnJson> columns;
    List<Object> indexes;
    List<Object> triggers;
    List<Object> customConstraints;
    List<Object> procedures;
}
