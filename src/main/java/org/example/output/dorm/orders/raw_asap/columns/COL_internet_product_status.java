package org.example.output.dorm.orders.raw_asap.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_internet_product_status extends ColumnTemplate {

    public COL_internet_product_status() {
        super(
            "internet_product_status"
,  // name
            "cold2e2728d-326a-4625-a2c6-e3f210ec78f7"
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
