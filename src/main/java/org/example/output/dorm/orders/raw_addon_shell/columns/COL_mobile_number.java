package org.example.output.dorm.orders.raw_addon_shell.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_mobile_number extends ColumnTemplate {

    public COL_mobile_number() {
        super(
            "mobile_number"
,  // name
            "col823d1069-f028-4802-ae8a-300383942fee"
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
