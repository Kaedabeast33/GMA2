package org.example.JsonBuilder.IDE.json.ma.tables.columns;

public class ColumnIndexJson {
    String[] groups;
    int[] order;
    boolean isIndex;


    public ColumnIndexJson(boolean b, String[] strings, int[] order) {
        this.isIndex = b;
        this.groups = strings;
        this.order = order;
    }
}
