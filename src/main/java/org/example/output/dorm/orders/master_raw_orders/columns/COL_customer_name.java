package org.example.output.dorm.orders.master_raw_orders.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_customer_name extends ColumnTemplate {

    public COL_customer_name() {
        super(
            "customer_name"
,  // name
            "col106376e6-4eca-48aa-b259-59b9c2d6fd63"
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
