package org.example.output.dorm.orders.overwrite.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_my_row_id extends ColumnTemplate {

    public COL_my_row_id() {
        super(
            "my_row_id"
,  // name
            "cold0e63215-8d39-41cc-a6d3-4f3c58929191"
,  // columnId
            "",  // description
            new String[]{""},  // tags
            false,  // isNullable
            true,  // isEditable
            true,  // isUnique
            true,  // isRequired
            "BIGINT"
,  // type
            "",  // defaultValue
            new String[]{"default"},  // columnGroups
            false,  // isUniqueIdentifier
            new String[]{""},  // uniqueIdentifierGroups
            true,  // isIndex
            new String[]{"PRIMARY_my_row_id"},  // indexGroups
            null,  // referenceColumns
            java.lang.Long.class,  // fieldType
            org.example.CommonValues.DefaultKdbConverter.class  // kdbConverter


        );
    }

}
