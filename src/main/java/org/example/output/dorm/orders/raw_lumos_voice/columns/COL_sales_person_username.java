package org.example.output.dorm.orders.raw_lumos_voice.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_sales_person_username extends ColumnTemplate {

    public COL_sales_person_username() {
        super(
            "sales_person_username"
,  // name
            "col85e87e3b-6fa9-4c4e-94c7-340ea1379c9a"
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
