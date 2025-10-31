package org.example.output.dorm.orders.raw_lumos.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_wifi_product_family extends ColumnTemplate {

    public COL_wifi_product_family() {
        super(
            "wifi_product_family"
,  // name
            "col4c211ee9-045b-456a-a1e7-739e3869655f"
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
