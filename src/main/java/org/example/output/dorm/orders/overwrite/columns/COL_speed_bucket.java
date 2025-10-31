package org.example.output.dorm.orders.overwrite.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_speed_bucket extends ColumnTemplate {

    public COL_speed_bucket() {
        super(
            "speed_bucket"
,  // name
            "col4e459be4-1cf8-4180-aef8-eb238bfa9c43"
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
