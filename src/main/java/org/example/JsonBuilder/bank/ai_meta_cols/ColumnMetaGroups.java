package org.example.JsonBuilder.bank.ai_meta_cols;

import java.util.List;

public enum ColumnMetaGroups {

    PARSE_GROUPS_IDX("primary_idx", new int[]{0}, "primary_key", List.of(ColumnMeta.UPLOAD_GROUP),null,false),
    PARSE_GROUPS(null,null, null, List.of(ColumnMeta.DESCRIPTION,ColumnMeta.IS_ACTIVE),null,true),

    INPUT_GROUPS_IDX("primary_idx", new int[]{0}, "primary_key", List.of(ColumnMeta.INPUT_GROUP),null,false),
    INPUT_GROUPS(null,null, null, List.of(ColumnMeta.DESCRIPTION,ColumnMeta.IS_ACTIVE),null,true),

    INPUT_TYPES_IDX("primary_idx", new int[]{0}, "primary_key", List.of(ColumnMeta.INPUT_TYPE),null,false),
    INPUT_TYPES(null,null, null, List.of(ColumnMeta.DESCRIPTION,ColumnMeta.IS_ACTIVE),null,true),

    INPUT_NAMES_IDX("primary_idx", new int[]{0}, "primary_key", List.of(ColumnMeta.INPUT_NAME),null,false),
    INPUT_NAMES(null,null, null, List.of(ColumnMeta.DESCRIPTION,ColumnMeta.IS_ACTIVE),null,true),

    INPUT_VALUES_IDX("primary_idx", new int[]{0}, "primary_key", List.of(ColumnMeta.INPUT_VALUE),null,false),
    INPUT_VALUES(null,null, null, List.of(ColumnMeta.DESCRIPTION,ColumnMeta.IS_ACTIVE),null,true),

    MTM_PGIG("parse_index", null, "primary_key", List.of(ColumnMeta.PARSE_GROUP_ID, ColumnMeta.INPUT_GROUP_ID),null,false),
    MTM_IGIT("parse_index", null, "primary_key", List.of(ColumnMeta.INPUT_GROUP_ID, ColumnMeta.INPUT_TYPE_ID),null,false),
    MTM_ITIN("parse_index", null, "primary_key", List.of(ColumnMeta.INPUT_TYPE_ID, ColumnMeta.INPUT_NAME_ID),null,false),
    MTM_INIV("parse_index", null, "primary_key", List.of(ColumnMeta.INPUT_NAME_ID, ColumnMeta.INPUT_VALUE_ID),null,false),

    INPUTS_INDEX("parse_groups", new int[]{0,1,2,3,4}, "primary_key", List.of(ColumnMeta.UPLOAD_GROUP, ColumnMeta.INPUT_GROUP,ColumnMeta.INPUT_TYPE,ColumnMeta.INPUT_NAME,ColumnMeta.INPUT_VALUE),null,false),
    INPUTS(null,null, null, List.of(
            ColumnMeta.DB_EMBEDDING,
            ColumnMeta.DB_UPDATE_DATE,
            ColumnMeta.DB_INSERT_DATE,
            ColumnMeta.VALUE,
            ColumnMeta.INPUT_JSON
            ),null,true),

    RAW(null, null, null, List.of(ColumnMeta.DB_EMBEDDING,
            ColumnMeta.DB_UPDATE_DATE,
            ColumnMeta.DB_INSERT_DATE,
            ColumnMeta.SOURCE_SYSTEM,
            ColumnMeta.EXTENSION_TYPE,

            ColumnMeta.PROCESSING_STATUS,
            ColumnMeta.CHECKSUM,
            ColumnMeta.MIME_TYPE,
            ColumnMeta.FILE_SIZE,
            ColumnMeta.FILE_PATH,

            ColumnMeta.ORIGINAL_NAME,

            ColumnMeta.PROCESSED_AT,




            ColumnMeta.RAW_INPUT_JSON),null,true),


    RAW_INDEX("parse_index", new int[]{0}, "primary_key", List.of(



            ColumnMeta.UPLOAD_GROUP

            ),null,false),
    RAW_INDEX_2("parse_index",new int[]{1},null, List.of(

            ColumnMeta.STORED_NAME
    ),null,true), // Example of an index with no columns, adjust as needed

    RAW_INPUTS_INDEX("parse_index", null, "primary_key", List.of(



            ColumnMeta.UPLOAD_GROUP
    ),null,false),

    RAW_INPUTS_INDEX_2("parse_index", null, null, List.of(




            ColumnMeta.STORED_NAME
    ),null,true),

    RAW_INPUTS(null, null, null, List.of(
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

            ColumnMeta.ORIGINAL_NAME


    ),null,true),

    RAW_INPUTS_RAW_ID("raw_id", null, "inputs", List.of(
                       ColumnMeta.RAW_ID
               ),null,true);







    private final String indexName;
    private final int[] indexOrder;
    private final String keyGroupName;
    private final List<ColumnMeta> columns;
    private final ColumnMeta primaryKey;
    private final List<String> columnGroupNames;
    private final boolean isNullable;

    public ColumnMeta getPrimaryKey() {
        return primaryKey;
    }

    ColumnMetaGroups(String indexName, int[] indexOrder, String keyGroupName, List<ColumnMeta> columns, List<String> columnGroupNames,boolean isNullable) {
        this.indexName = indexName;
        this.indexOrder = indexOrder;
        this.keyGroupName = keyGroupName;
        this.columns = columns;
        this.primaryKey = ColumnMeta.DB_ID; //
        this.columnGroupNames = columnGroupNames;
        this.isNullable = isNullable;

        // Assuming RAW_ID is the primary key for all groups
    }

    public String getIndexName() {
        return indexName;
    }

    public String getTableType() {
        return name(); // returns the enum constant name, e.g. "RAW"
    }



    public int[] getIndexOrder() {
        return indexOrder;
    }
    public int getIndexByIndex(int i){
        if (indexOrder != null && indexOrder.length > 0) {
            return indexOrder[i]; // Return the first index as the primary index
        }
        return -1; // Return -1 if no index order is defined
    }

    public String getKeyGroupName() {
        return keyGroupName;
    }

    public List<ColumnMeta> getColumns() {
        return columns;
    }

    public List<String> getColumnGroupNames() {
        return columnGroupNames;
    }

    public boolean isNullable() {
        return isNullable;
    }
}
