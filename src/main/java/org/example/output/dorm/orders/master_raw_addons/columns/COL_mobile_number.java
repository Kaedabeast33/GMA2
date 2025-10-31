package org.example.output.dorm.orders.master_raw_addons.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_mobile_number extends ColumnTemplate {

    public COL_mobile_number() {
        super(
            "mobile_number"
,  // name
            "col9f0750cf-4d4f-4376-997c-d1a305b5d186"
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
