package org.example.output.dorm.orders.raw_lumos.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_subcategory_username extends ColumnTemplate {

    public COL_subcategory_username() {
        super(
            "subcategory_username"
,  // name
            "colf5d6f3bb-88ff-4d81-99cb-4efbdc800ba0"
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
