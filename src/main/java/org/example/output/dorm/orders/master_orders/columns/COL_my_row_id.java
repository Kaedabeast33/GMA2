package org.example.output.dorm.orders.master_orders.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_my_row_id extends ColumnTemplate {

    public COL_my_row_id() {
        super(
            "my_row_id"
,  // name
            "col8b43734d-ec26-4112-a254-916470e0b044"
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
