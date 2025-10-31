package org.example.output.dorm.orders.master_raw_addons.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_order_id extends ColumnTemplate {

    public COL_order_id() {
        super(
            "order_id"
,  // name
            "col95e03475-c1ae-43df-8916-9fbc1ddb16f0"
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
