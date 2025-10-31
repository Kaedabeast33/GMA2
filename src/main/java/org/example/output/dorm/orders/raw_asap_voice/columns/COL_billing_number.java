package org.example.output.dorm.orders.raw_asap_voice.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_billing_number extends ColumnTemplate {

    public COL_billing_number() {
        super(
            "billing_number"
,  // name
            "col1f543509-65f2-41a6-98ae-a69b5f5d5525"
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
