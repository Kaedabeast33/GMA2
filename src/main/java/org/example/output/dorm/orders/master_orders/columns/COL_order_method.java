package org.example.output.dorm.orders.master_orders.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_order_method extends ColumnTemplate {

    public COL_order_method() {
        super(
            "order_method"
,  // name
            "colcd303491-dd61-4a4f-9a85-dbcaad033771"
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
