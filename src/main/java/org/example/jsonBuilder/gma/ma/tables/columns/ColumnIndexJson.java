package org.example.jsonBuilder.gma.ma.tables.columns;

import org.example.Annotations.KdbIndex;

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
