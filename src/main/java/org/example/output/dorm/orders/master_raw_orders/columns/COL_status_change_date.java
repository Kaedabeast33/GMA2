package org.example.output.dorm.orders.master_raw_orders.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_status_change_date extends ColumnTemplate {

    public COL_status_change_date() {
        super(
            "status_change_date"
,  // name
            "colc3e8a72e-c56b-48ec-a603-5d9c80ba7b97"
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
