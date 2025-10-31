package org.example.output.dorm.orders.raw_lumos_video.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_subcategory_name extends ColumnTemplate {

    public COL_subcategory_name() {
        super(
            "subcategory_name"
,  // name
            "col02fffbdf-3477-40a7-b9cb-41a3c5e69478"
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
