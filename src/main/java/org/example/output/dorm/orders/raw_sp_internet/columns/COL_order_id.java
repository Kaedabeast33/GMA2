package org.example.output.dorm.orders.raw_sp_internet.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_order_id extends ColumnTemplate {

    public COL_order_id() {
        super(
            "order_id"
,  // name
            "col24664db3-526c-4cf7-9f28-3a4c3f8ba63b"
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
            false,  // isIndex
            new String[]{""},  // indexGroups
            null,  // referenceColumns
            java.lang.String.class,  // fieldType
            org.example.CommonValues.DefaultKdbConverter.class  // kdbConverter


        );
    }

}
