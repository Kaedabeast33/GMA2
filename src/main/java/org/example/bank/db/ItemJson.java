package org.example.bank.db;

public class ItemJson {
    String column_name;
    String[] column_tags;
    String column_description;

    String table_name;
    String[] table_tags;
    String table_description;

    String ma_name;
    String[] ma_tags;
    String ma_description;

    public ItemJson(String column_name, String[] column_tags, String column_description, String table_name, String[] table_tags, String table_description, String ma_name, String[] ma_tags, String ma_description) {
        this.column_name = column_name;
        this.column_tags = column_tags;
        this.column_description = column_description;
        this.table_name = table_name;
        this.table_tags = table_tags;
        this.table_description = table_description;
        this.ma_name = ma_name;
        this.ma_tags = ma_tags;
        this.ma_description = ma_description;
    }
}
