package org.example.output.dorm.orders.raw_vexus.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_order_sales_type_full extends ColumnTemplate {

    public COL_order_sales_type_full() {
        super(
            "order_sales_type_full"
,  // name
            "col0ebaa470-4717-4579-8d55-92a69dac6bfb"
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
