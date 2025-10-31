package org.example.output.dorm.orders.raw_vexus.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_other_phone extends ColumnTemplate {

    public COL_other_phone() {
        super(
            "other_phone"
,  // name
            "col56b0d2eb-003b-4d24-8ded-4d771b27b18c"
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
