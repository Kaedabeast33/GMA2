package org.example.bank.db.contextObj;

import java.util.Arrays;

public class ContextColumn {
    String column_name;
    String[] column_tags;
    String[] column_description;

    @Override
    public String toString() {
        return "ContextColumn{" +
                "column_name='" + column_name + '\'' +
                ", column_tags=" + Arrays.toString(column_tags) +
                ", column_description='" + Arrays.toString(column_description) + '\'' +
                '}';
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String[] getColumn_tags() {
        return column_tags;
    }

    public void setColumn_tags(String[] column_tags) {
        this.column_tags = column_tags;
    }

    public String[] getColumn_description() {
        return column_description;
    }

    public void setColumn_description(String[] column_description) {
        this.column_description = column_description;
    }
}
