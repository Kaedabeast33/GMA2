package org.example.output.dorm.orders.master_orders.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_product_type extends ColumnTemplate {

    public COL_product_type() {
        super(
            "product_type"
,  // name
            "coled809a9b-13f1-42e2-907f-8fa8dbdcdc16"
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
