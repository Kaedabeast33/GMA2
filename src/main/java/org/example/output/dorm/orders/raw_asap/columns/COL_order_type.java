package org.example.output.dorm.orders.raw_asap.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_order_type extends ColumnTemplate {

    public COL_order_type() {
        super(
            "order_type"
,  // name
            "col24be1bb0-389f-4295-9a1b-3ca95c3fa37c"
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
