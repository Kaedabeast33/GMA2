package org.example.bank.db.contextObj;

import java.util.Arrays;

public class ContextTable {
    String table_name;
    String[] table_tags;
    String[] table_description;

    @Override
    public String toString() {
        return "ContextTable{" +
                "table_name='" + table_name + '\'' +
                ", table_tags=" + Arrays.toString(table_tags) +
                ", table_description='" + Arrays.toString(table_description) + '\'' +
                '}';
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String[] getTable_tags() {
        return table_tags;
    }

    public void setTable_tags(String[] table_tags) {
        this.table_tags = table_tags;
    }

    public String[] getTable_description() {
        return table_description;
    }

    public void setTable_description(String[] table_description) {
        this.table_description = table_description;
    }
}
