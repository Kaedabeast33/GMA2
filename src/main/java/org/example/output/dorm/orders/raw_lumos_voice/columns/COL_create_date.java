package org.example.output.dorm.orders.raw_lumos_voice.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_create_date extends ColumnTemplate {

    public COL_create_date() {
        super(
            "create_date"
,  // name
            "col52d69d57-204e-4d98-96ee-54a3c0304aa0"
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
