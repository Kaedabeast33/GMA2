package org.example.JsonBuilder.bank.ai_meta_cols;


import org.example.bank.commonValues.DefaultTypes;

public enum ColumnMeta {


    DESCRIPTION("description", "java.lang.String","text",null),
    IS_ACTIVE("is_active", "java.lang.Boolean",null,null),


    DB_EMBEDDING("db_embedding", "java.lang.Double[]",null,null),
    DB_UPDATE_DATE("db_update_date", "java.sql.Timestamp",null,DefaultTypes.NOW),
    DB_INSERT_DATE("db_insert_date", "java.sql.Timestamp",null,DefaultTypes.NOW),

    DB_ID("db_id", "java.lang.String","varchar(50)",null),

    UPLOAD_GROUP("upload_group", "java.lang.String","varchar(50)",null),
    INPUT_GROUP("input_group", "java.lang.String","varchar(50)",null),
    INPUT_TYPE("input_type", "java.lang.String","varchar(50)",null),
    INPUT_NAME("input_name", "java.lang.String","varchar(50)",null),
    INPUT_VALUE("input_value", "java.lang.String","varchar(50)",null),
    VALUE("value", "java.lang.String","JSON",null),
    INPUT_JSON("input_json", "java.lang.String","JSON",null),

    SOURCE_SYSTEM("source_system", "java.lang.String",null,null),
    EXTENSION_TYPE("extension_type", "java.lang.String",null,null),
    PROCESSED_AT("processed_at", "java.sql.Timestamp",null,null),
    PROCESSING_STATUS("processing_status", "java.lang.String",null,null),
    CHECKSUM("checksum", "java.lang.String",null,null),
    MIME_TYPE("mime_type", "java.lang.String",null,null),
    FILE_SIZE("file_size", "java.lang.Long",null,null),
    FILE_PATH("file_path", "java.lang.String",null,null),
    STORED_NAME("stored_name", "java.lang.String",null,null),
    ORIGINAL_NAME("original_name", "java.lang.String",null,null),

    RAW_INPUT_JSON("raw_input_json", "java.lang.String","JSON",null),

    RAW_ID("raw_id", "java.lang.String",null,null),

    PARSE_GROUP_ID("parse_group_id", "java.lang.String",null,null),
    INPUT_GROUP_ID("input_group_id", "java.lang.String",null,null),
    INPUT_TYPE_ID("input_type_id", "java.lang.String",null,null),
    INPUT_NAME_ID("input_name_id", "java.lang.String",null,null),
    INPUT_VALUE_ID("value_id", "java.lang.String",null,null);





    private final String column;
    private final String javaType;
    private final String mysqlType;
    private final String defaultValue;




    ColumnMeta(String column, String javaType, String mysqlType,String defaultValue) {
        this.column = column;
        this.javaType = javaType;
        this.mysqlType = mysqlType;
        this.defaultValue = defaultValue;
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

    public String getDefaultValue() {
        return defaultValue;
    }
}

