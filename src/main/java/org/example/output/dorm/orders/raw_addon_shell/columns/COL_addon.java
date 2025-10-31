package org.example.output.dorm.orders.raw_addon_shell.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_addon extends ColumnTemplate {

    public COL_addon() {
        super(
            "addon"
,  // name
            "colc1a86eb3-bf63-4fbc-9cbe-ee3ba8ae663c"
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
