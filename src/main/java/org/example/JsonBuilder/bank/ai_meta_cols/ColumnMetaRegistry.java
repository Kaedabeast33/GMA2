package org.example.JsonBuilder.bank.ai_meta_cols;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ColumnMetaRegistry {
    public static final Map<String, String> COLUMN_TO_TYPE =
            Arrays.stream(ColumnMeta.values())
                    .collect(Collectors.toMap(
                            ColumnMeta::getColumn,
                            ColumnMeta::getJavaType
                    ));

    public static final Map<String,ColumnMeta> BY_COLUMN = Arrays.stream(ColumnMeta.values())
            .collect(Collectors.toMap(
                    ColumnMeta::getColumn,
                    cm -> cm
            ));

    public static final Map<String, String> INPUTS_SCHEMA =
            List.of(
                    ColumnMeta.DB_EMBEDDING,
                    ColumnMeta.DB_UPDATE_DATE,
                    ColumnMeta.DB_INSERT_DATE,
                    ColumnMeta.UPLOAD_GROUP,
                    ColumnMeta.INPUT_GROUP,
                    ColumnMeta.INPUT_TYPE,
                    ColumnMeta.INPUT_NAME,
                    ColumnMeta.INPUT_VALUE,
                    ColumnMeta.VALUE,
                    ColumnMeta.INPUT_JSON,
                    ColumnMeta.RAW_ID
            ).stream().collect(Collectors.toMap(
                    ColumnMeta::getColumn,
                    ColumnMeta::getJavaType
            ));

    public static final Map<String, String> RAW_INPUTS_SCHEMA =
            List.of(
                    ColumnMeta.DB_EMBEDDING,
                    ColumnMeta.DB_UPDATE_DATE,
                    ColumnMeta.DB_INSERT_DATE,
                    ColumnMeta.SOURCE_SYSTEM,
                    ColumnMeta.EXTENSION_TYPE,
                    ColumnMeta.PROCESSED_AT,
                    ColumnMeta.PROCESSING_STATUS,
                    ColumnMeta.CHECKSUM,
                    ColumnMeta.MIME_TYPE,
                    ColumnMeta.FILE_SIZE,
                    ColumnMeta.FILE_PATH,
                    ColumnMeta.STORED_NAME,
                    ColumnMeta.ORIGINAL_NAME,
                    ColumnMeta.UPLOAD_GROUP,
                    ColumnMeta.RAW_ID
            ).stream().collect(Collectors.toMap(
                    ColumnMeta::getColumn,
                    ColumnMeta::getJavaType
            ));

    public static final Map<String, String> RAW_SCHEMA =
            List.of(
                    ColumnMeta.DB_EMBEDDING,
                    ColumnMeta.DB_UPDATE_DATE,
                    ColumnMeta.DB_INSERT_DATE,
                    ColumnMeta.SOURCE_SYSTEM,
                    ColumnMeta.EXTENSION_TYPE,
                    ColumnMeta.PROCESSED_AT,
                    ColumnMeta.PROCESSING_STATUS,
                    ColumnMeta.CHECKSUM,
                    ColumnMeta.MIME_TYPE,
                    ColumnMeta.FILE_SIZE,
                    ColumnMeta.FILE_PATH,
                    ColumnMeta.STORED_NAME,
                    ColumnMeta.ORIGINAL_NAME,
                    ColumnMeta.UPLOAD_GROUP,
                    ColumnMeta.RAW_INPUT_JSON,
                    ColumnMeta.RAW_ID
            ).stream().collect(Collectors.toMap(
                    ColumnMeta::getColumn,
                    ColumnMeta::getJavaType
            ));

}
