package org.example.output.dorm.orders.raw_bass.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_outside_sales_rep extends ColumnTemplate {

    public COL_outside_sales_rep() {
        super(
            "outside_sales_rep"
,  // name
            "col59692981-d2d2-4ef3-a30c-53c641050c16"
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
