package org.example.output.dorm.orders.raw_addon_frontier.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_carrier_system extends ColumnTemplate {

    public COL_carrier_system() {
        super(
            "carrier_system"
,  // name
            "col5bae240c-c7dd-4d3a-8be4-fbce7af2cfb4"
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
