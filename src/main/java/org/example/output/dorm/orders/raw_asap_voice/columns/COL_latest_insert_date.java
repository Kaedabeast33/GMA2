package org.example.output.dorm.orders.raw_asap_voice.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_latest_insert_date extends ColumnTemplate {

    public COL_latest_insert_date() {
        super(
            "latest_insert_date"
,  // name
            "col87693d3c-b1a4-463a-ba4d-3bd46a7125f3"
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
