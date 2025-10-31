package org.example.output.dorm.orders.raw_lumos_video.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_sales_person_name extends ColumnTemplate {

    public COL_sales_person_name() {
        super(
            "sales_person_name"
,  // name
            "colb849e3b2-87ea-43c1-8b39-11dae149b4dd"
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
