package org.example.JsonBuilder.IDE;

import org.example.bank.commonValues.ValueTypes;

public class ColumnData {

    private final String column;
    private final String javaType;
    private final String mysqlType;

    public ColumnData(String column, String javaType, String mysqlType) {
//        System.out.println(mysqlType+" mysqlType");
        if(mysqlType.equalsIgnoreCase(ValueTypes.STRING)) mysqlType = "VARCHAR(125)";
        this.column = column;
        this.javaType = javaType;
        this.mysqlType = mysqlType;
    }

    public String getColumn() {
        return column;
    }

    public String getJavaType() {
        return javaType;
    }

    public String getMysqlType() {
        return mysqlType;
    }

    @Override
    public String toString() {
        return "ColumnData{" +
                "column='" + column + '\'' +
                ", javaType='" + javaType + '\'' +
                ", mysqlType='" + mysqlType + '\'' +
                '}';
    }
}
