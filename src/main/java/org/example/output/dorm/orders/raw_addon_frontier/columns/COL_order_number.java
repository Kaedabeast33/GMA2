package org.example.output.dorm.orders.raw_addon_frontier.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_order_number extends ColumnTemplate {

    public COL_order_number() {
        super(
            "order_number"
,  // name
            "col6fb109b4-0397-4530-9b8f-cc64356c9d89"
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
