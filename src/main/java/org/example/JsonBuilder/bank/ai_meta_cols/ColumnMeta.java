package org.example.JsonBuilder.bank.ai_meta_cols;



public enum ColumnMeta {


    DESCRIPTION("description", "java.lang.String","text"),
    IS_ACTIVE("is_active", "java.lang.Boolean",null),


    DB_EMBEDDING("db_embedding", "java.lang.Double[]",null),
    DB_UPDATE_DATE("db_update_date", "java.sql.Timestamp",null),
    DB_INSERT_DATE("db_insert_date", "java.sql.Timestamp",null),

    DB_ID("db_id", "java.lang.String",null),

    UPLOAD_GROUP("upload_group", "java.lang.String","varchar(120)"),
    INPUT_GROUP("input_group", "java.lang.String","varchar(120)"),
    INPUT_TYPE("input_type", "java.lang.String","varchar(120)"),
    INPUT_NAME("input_name", "java.lang.String","varchar(120)"),
    INPUT_VALUE("input_value", "java.lang.String","varchar(120)"),
    VALUE("value", "java.lang.String",null),
    INPUT_JSON("input_json", "java.lang.String","JSON"),

    SOURCE_SYSTEM("source_system", "java.lang.String",null),
    EXTENSION_TYPE("extension_type", "java.lang.String",null),
    PROCESSED_AT("processed_at", "java.sql.Timestamp",null),
    PROCESSING_STATUS("processing_status", "java.lang.String",null),
    CHECKSUM("checksum", "java.lang.String",null),
    MIME_TYPE("mime_type", "java.lang.String",null),
    FILE_SIZE("file_size", "java.lang.Long",null),
    FILE_PATH("file_path", "java.lang.String",null),
    STORED_NAME("stored_name", "java.lang.String",null),
    ORIGINAL_NAME("original_name", "java.lang.String",null),

    RAW_INPUT_JSON("raw_input_json", "java.lang.String","JSON"),

    RAW_ID("raw_id", "java.lang.String",null),

    PARSE_GROUP_ID("parse_group_id", "java.lang.String",null),
    INPUT_GROUP_ID("input_group_id", "java.lang.String",null),
    INPUT_TYPE_ID("input_type_id", "java.lang.String",null),
    INPUT_NAME_ID("input_name_id", "java.lang.String",null),
    INPUT_VALUE_ID("value_id", "java.lang.String",null);





    private final String column;
    private final String javaType;
    private final String mysqlType;




    ColumnMeta(String column, String javaType, String mysqlType) {
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
}

