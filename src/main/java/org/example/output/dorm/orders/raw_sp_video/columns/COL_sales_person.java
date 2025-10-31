package org.example.output.dorm.orders.raw_sp_video.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_sales_person extends ColumnTemplate {

    public COL_sales_person() {
        super(
            "sales_person"
,  // name
            "col336ab7cb-eb2e-4a85-a294-322db0c64f5f"
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
