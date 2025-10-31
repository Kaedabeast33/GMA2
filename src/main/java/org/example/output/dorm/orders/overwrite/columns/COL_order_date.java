package org.example.output.dorm.orders.overwrite.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_order_date extends ColumnTemplate {

    public COL_order_date() {
        super(
            "order_date"
,  // name
            "colb5adb4f5-06ab-485b-abaf-c29cb1a08c97"
,  // columnId
            "",  // description
            new String[]{""},  // tags
            true,  // isNullable
            true,  // isEditable
            false,  // isUnique
            false,  // isRequired
            "DATETIME"
,  // type
            "",  // defaultValue
            new String[]{"default"},  // columnGroups
            false,  // isUniqueIdentifier
            new String[]{""},  // uniqueIdentifierGroups
            false,  // isIndex
            new String[]{""},  // indexGroups
            null,  // referenceColumns
            java.time.LocalDateTime.class,  // fieldType
            org.example.CommonValues.DefaultKdbConverter.class  // kdbConverter


        );
    }

}
