package org.example.output.dorm.orders.raw_frontier.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_deposit_value extends ColumnTemplate {

    public COL_deposit_value() {
        super(
            "deposit_value"
,  // name
            "col45d90eb2-e7cb-4a07-905e-8e10465acfed"
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
