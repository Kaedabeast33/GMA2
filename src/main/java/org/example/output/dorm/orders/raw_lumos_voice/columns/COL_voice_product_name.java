package org.example.output.dorm.orders.raw_lumos_voice.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_voice_product_name extends ColumnTemplate {

    public COL_voice_product_name() {
        super(
            "voice_product_name"
,  // name
            "colb98b6746-c3b1-45cd-b1eb-3d36ebff0eb9"
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
