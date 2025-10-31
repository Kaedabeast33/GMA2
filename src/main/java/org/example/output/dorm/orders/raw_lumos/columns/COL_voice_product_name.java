package org.example.output.dorm.orders.raw_lumos.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_voice_product_name extends ColumnTemplate {

    public COL_voice_product_name() {
        super(
            "voice_product_name"
,  // name
            "col9624031a-2147-4dbb-b4cd-36bdc8a8f4d1"
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
