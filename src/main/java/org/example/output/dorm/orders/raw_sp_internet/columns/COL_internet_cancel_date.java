package org.example.output.dorm.orders.raw_sp_internet.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_internet_cancel_date extends ColumnTemplate {

    public COL_internet_cancel_date() {
        super(
            "internet_cancel_date"
,  // name
            "col764ec295-158d-4d60-8171-a5a660ee43d7"
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
