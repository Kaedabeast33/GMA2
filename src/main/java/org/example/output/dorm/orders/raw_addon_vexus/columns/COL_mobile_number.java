package org.example.output.dorm.orders.raw_addon_vexus.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_mobile_number extends ColumnTemplate {

    public COL_mobile_number() {
        super(
            "mobile_number"
,  // name
            "colcef60112-8512-4a1d-ada4-75bde677e927"
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
