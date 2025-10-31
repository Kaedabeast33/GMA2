package org.example.output.dorm.orders.raw_bass_voice.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_latest_insert_date extends ColumnTemplate {

    public COL_latest_insert_date() {
        super(
            "latest_insert_date"
,  // name
            "col2cf554c6-f708-4ac6-adc7-22cd0dd28475"
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
