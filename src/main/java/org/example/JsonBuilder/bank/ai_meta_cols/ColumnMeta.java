package org.example.JsonBuilder.bank.ai_meta_cols;



public enum ColumnMeta {


    DB_EMBEDDING("db_embedding", "java.lang.Double[]"),
    DB_UPDATE_DATE("db_update_date", "java.sql.Timestamp"),
    DB_INSERT_DATE("db_insert_date", "java.sql.Timestamp"),

    DB_ID("db_id", "java.lang.String"),

    UPLOAD_GROUP("upload_group", "java.lang.String"),
    INPUT_GROUP("input_group", "java.lang.String"),
    INPUT_TYPE("input_type", "java.lang.String"),
    INPUT_NAME("input_name", "java.lang.String"),
    INPUT_VALUE("input_value", "java.lang.String"),
    VALUE("value", "java.lang.String"),
    INPUT_JSON("input_json", "java.lang.String"),

    SOURCE_SYSTEM("source_system", "java.lang.String"),
    EXTENSION_TYPE("extension_type", "java.lang.String"),
    PROCESSED_AT("processed_at", "java.sql.Timestamp"),
    PROCESSING_STATUS("processing_status", "java.lang.String"),
    CHECKSUM("checksum", "java.lang.String"),
    MIME_TYPE("mime_type", "java.lang.String"),
    FILE_SIZE("file_size", "java.lang.Long"),
    FILE_PATH("file_path", "java.lang.String"),
    STORED_NAME("stored_name", "java.lang.String"),
    ORIGINAL_NAME("original_name", "java.lang.String"),

    RAW_INPUT_JSON("raw_input_json", "java.lang.String"),

    RAW_ID("raw_id", "java.lang.String"),

    PARSE_GROUP_ID("parse_group_id", "java.lang.String"),
    INPUT_GROUP_ID("input_group_id", "java.lang.String"),
    INPUT_TYPE_ID("input_type_id", "java.lang.String"),
    INPUT_NAME_ID("input_name_id", "java.lang.String"),
    INPUT_VALUE_ID("value_id", "java.lang.String");





    private final String column;
    private final String javaType;


    ColumnMeta(String column, String javaType) {
        this.column = column;
        this.javaType = javaType;
    }

    public String getColumn() {
        return column;
    }

    public String getJavaType() {
        return javaType;
    }
}

