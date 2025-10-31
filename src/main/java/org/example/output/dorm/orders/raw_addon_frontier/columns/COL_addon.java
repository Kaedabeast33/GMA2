package org.example.output.dorm.orders.raw_addon_frontier.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_addon extends ColumnTemplate {

    public COL_addon() {
        super(
            "addon"
,  // name
            "col641829b4-9ca6-4d6e-8559-12ad2618580d"
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
