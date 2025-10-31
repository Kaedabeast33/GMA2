package org.example.output.dorm.orders.raw_asap.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_sales_code extends ColumnTemplate {

    public COL_sales_code() {
        super(
            "sales_code"
,  // name
            "colcea9d160-5d02-429e-b24b-82d15cb7c0f5"
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
