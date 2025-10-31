package org.example.output.dorm.orders.raw_frontier.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_subscriptions extends ColumnTemplate {

    public COL_subscriptions() {
        super(
            "subscriptions"
,  // name
            "col7e57d146-7350-416c-8b71-476919544df1"
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
