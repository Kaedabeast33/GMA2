package org.example.output.dorm.orders.raw_sp_mobility.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_account_number extends ColumnTemplate {

    public COL_account_number() {
        super(
            "account_number"
,  // name
            "colb478f2cc-38de-4925-b2ea-0590791d3537"
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
