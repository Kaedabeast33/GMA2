package org.example.output.dorm.orders.raw_bass.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_order_status extends ColumnTemplate {

    public COL_order_status() {
        super(
            "order_status"
,  // name
            "colfafa10a6-f56f-4d77-b3e8-281158b659bf"
,  // columnId
            "",  // description
            new String[]{""},  // tags
            true,  // isNullable
            true,  // isEditable
            false,  // isUnique
            false,  // isRequired
            "VARCHAR(255)"
,  // type
            "",  // defaultValue
            new String[]{"default"},  // columnGroups
            false,  // isUniqueIdentifier
            new String[]{""},  // uniqueIdentifierGroups
            true,  // isIndex
            new String[]{"order_status_idx"},  // indexGroups
            null,  // referenceColumns
            java.lang.String.class,  // fieldType
            org.example.CommonValues.DefaultKdbConverter.class  // kdbConverter


        );
    }

}
