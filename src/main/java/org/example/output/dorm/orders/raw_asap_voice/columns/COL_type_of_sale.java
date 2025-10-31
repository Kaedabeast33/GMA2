package org.example.output.dorm.orders.raw_asap_voice.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_type_of_sale extends ColumnTemplate {

    public COL_type_of_sale() {
        super(
            "type_of_sale"
,  // name
            "col62fdec09-c02d-4590-ab0f-dfba0b134090"
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
