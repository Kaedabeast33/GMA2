package org.example.output.dorm.orders.master_raw_addons.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_addon extends ColumnTemplate {

    public COL_addon() {
        super(
            "addon"
,  // name
            "colb1ca47db-1adb-4fae-a8b3-ba179e137226"
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
