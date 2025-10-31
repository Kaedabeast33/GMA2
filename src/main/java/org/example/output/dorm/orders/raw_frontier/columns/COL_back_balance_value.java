package org.example.output.dorm.orders.raw_frontier.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_back_balance_value extends ColumnTemplate {

    public COL_back_balance_value() {
        super(
            "back_balance_value"
,  // name
            "colc7fa8e24-48b9-46db-82ec-65397c01bfd9"
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
