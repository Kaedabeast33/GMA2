package org.example.output.dorm.orders.master_raw_orders.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_mobile_number extends ColumnTemplate {

    public COL_mobile_number() {
        super(
            "mobile_number"
,  // name
            "col2dc46538-fc5c-4d1b-a29e-c480e3ab4a45"
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
