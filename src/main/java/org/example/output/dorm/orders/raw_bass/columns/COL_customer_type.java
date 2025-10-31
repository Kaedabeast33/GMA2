package org.example.output.dorm.orders.raw_bass.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_customer_type extends ColumnTemplate {

    public COL_customer_type() {
        super(
            "customer_type"
,  // name
            "col6f92debe-076a-4f59-bfc7-86ae92eecafc"
,  // columnId
            "",  // description
            new String[]{""},  // tags
            true,  // isNullable
            true,  // isEditable
            false,  // isUnique
            false,  // isRequired
            "TEXT"
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
