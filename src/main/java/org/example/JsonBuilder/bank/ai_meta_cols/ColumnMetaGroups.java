package org.example.JsonBuilder.bank.ai_meta_cols;

import java.util.List;

public enum ColumnMetaGroups {

    PARSE_GROUPS_IDX("primary_idx", new int[]{0}, "primary_key", List.of(ColumnMeta.UPLOAD_GROUP)),
    PARSE_GROUPS(null,null, null, List.of(ColumnMeta.DESCRIPTION,ColumnMeta.IS_ACTIVE)),

    INPUT_GROUPS_IDX("primary_idx", new int[]{0}, "primary_key", List.of(ColumnMeta.INPUT_GROUP)),
    INPUT_GROUPS(null,null, null, List.of(ColumnMeta.DESCRIPTION,ColumnMeta.IS_ACTIVE)),

    INPUT_TYPES_IDX("primary_idx", new int[]{0}, "primary_key", List.of(ColumnMeta.INPUT_TYPE)),
    INPUT_TYPES(null,null, null, List.of(ColumnMeta.DESCRIPTION,ColumnMeta.IS_ACTIVE)),

    INPUT_NAMES_IDX("primary_idx", new int[]{0}, "primary_key", List.of(ColumnMeta.INPUT_NAME)),
    INPUT_NAMES(null,null, null, List.of(ColumnMeta.DESCRIPTION,ColumnMeta.IS_ACTIVE)),

    INPUT_VALUES_IDX("primary_idx", new int[]{0}, "primary_key", List.of(ColumnMeta.INPUT_VALUE)),
    INPUT_VALUES(null,null, null, List.of(ColumnMeta.DESCRIPTION,ColumnMeta.IS_ACTIVE)),

    MTM_PGIG("parse_index", null, "pgig", List.of(ColumnMeta.PARSE_GROUP_ID, ColumnMeta.INPUT_GROUP_ID)),
    MTM_IGIT("parse_index", null, "igit", List.of(ColumnMeta.INPUT_GROUP_ID, ColumnMeta.INPUT_TYPE_ID)),
    MTM_ITIN("parse_index", null, "itin", List.of(ColumnMeta.INPUT_TYPE_ID, ColumnMeta.INPUT_NAME_ID)),
    MTM_INIV("parse_index", null, "iniv", List.of(ColumnMeta.INPUT_NAME_ID, ColumnMeta.INPUT_VALUE_ID)),

    INPUTS_INDEX("parse_groups", new int[]{0,1,2,3,4}, null, List.of(ColumnMeta.UPLOAD_GROUP, ColumnMeta.INPUT_GROUP,ColumnMeta.INPUT_TYPE,ColumnMeta.INPUT_NAME,ColumnMeta.INPUT_VALUE)),
    INPUTS(null,null, null, List.of(
            ColumnMeta.DB_EMBEDDING,
            ColumnMeta.DB_UPDATE_DATE,
            ColumnMeta.DB_INSERT_DATE,
            ColumnMeta.VALUE,
            ColumnMeta.INPUT_JSON
            )),

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




            ColumnMeta.RAW_INPUT_JSON)),


    RAW_INDEX("parse_index", new int[]{0,1}, "iniv", List.of(



            ColumnMeta.UPLOAD_GROUP,

            ColumnMeta.STORED_NAME

            )),

    RAW_INPUTS_INDEX("parse_index", null, "inputs", List.of(



            ColumnMeta.UPLOAD_GROUP,
            ColumnMeta.STORED_NAME
    )),

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


    )),

    RAW_INPUTS_RAW_ID("raw_id", null, "inputs", List.of(
                       ColumnMeta.RAW_ID
               ));







    private final String indexName;
    private final int[] indexOrder;
    private final String keyGroupName;
    private final List<ColumnMeta> columns;
    private final ColumnMeta primaryKey;

    public ColumnMeta getPrimaryKey() {
        return primaryKey;
    }

    ColumnMetaGroups(String indexName, int[] indexOrder, String keyGroupName, List<ColumnMeta> columns) {
        this.indexName = indexName;
        this.indexOrder = indexOrder;
        this.keyGroupName = keyGroupName;
        this.columns = columns;
        this.primaryKey = ColumnMeta.DB_ID; //

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
}
