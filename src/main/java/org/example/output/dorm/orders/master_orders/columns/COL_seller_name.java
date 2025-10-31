package org.example.output.dorm.orders.master_orders.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_seller_name extends ColumnTemplate {

    public COL_seller_name() {
        super(
            "seller_name"
,  // name
            "col8ab3920f-4a0e-4698-9fe6-1c41c2f1452e"
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
