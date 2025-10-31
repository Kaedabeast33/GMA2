package org.example.output.dorm.orders.raw_bass_voice.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_local_product_status extends ColumnTemplate {

    public COL_local_product_status() {
        super(
            "local_product_status"
,  // name
            "cole1909cf6-5e62-41a2-bacc-dad4266e8cd1"
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
