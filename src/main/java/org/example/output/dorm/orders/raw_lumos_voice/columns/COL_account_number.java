package org.example.output.dorm.orders.raw_lumos_voice.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_account_number extends ColumnTemplate {

    public COL_account_number() {
        super(
            "account_number"
,  // name
            "colf6dc8543-e117-4481-a588-a85e12983ac1"
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
