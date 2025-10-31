package org.example.output.dorm.orders.overwrite.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_carrier_speed extends ColumnTemplate {

    public COL_carrier_speed() {
        super(
            "carrier_speed"
,  // name
            "col67d3593e-e051-4a5c-949d-77e2aee59548"
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
