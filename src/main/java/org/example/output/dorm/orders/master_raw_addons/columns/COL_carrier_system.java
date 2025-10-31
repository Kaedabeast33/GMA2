package org.example.output.dorm.orders.master_raw_addons.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_carrier_system extends ColumnTemplate {

    public COL_carrier_system() {
        super(
            "carrier_system"
,  // name
            "colc4a4b058-6b31-4ce3-aa84-16e927ff71b0"
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
