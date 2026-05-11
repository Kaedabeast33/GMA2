package org.example.bank.commonValues;

import org.example.JsonBuilder.json.ma.AiMAJson;

public class Identifier {
    String gmaName;
    String maName;
    String tableName;
    String columnName;

    public Identifier(String gmaName, String maName, String tableName) {
        this.gmaName = gmaName;
        this.maName = maName;
        this.tableName = tableName;
    }

    public Identifier(Identifier identifier) {
        this.gmaName = identifier.getGmaname();
        this.maName = identifier.getMaName();
        this.tableName = identifier.getTableName();
        this.columnName = identifier.getColumnName();

    }

    public Identifier(AiMAJson aiMAJson) {
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getGmaname() {
        return gmaName;
    }


    public String getGmaName() {
        return gmaName;
    }

    public void setGmaName(String gmaName) {
        this.gmaName = gmaName;
    }

    public String getMaName() {
        return maName;
    }

    public void setMaName(String maName) {
        this.maName = maName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }


    @Override
    public String toString() {
        return "Identifier{" +
                "gmaName='" + gmaName + '\'' +
                ", maName='" + maName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                '}';
    }
}
