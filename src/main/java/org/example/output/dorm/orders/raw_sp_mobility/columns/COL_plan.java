package org.example.output.dorm.orders.raw_sp_mobility.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_plan extends ColumnTemplate {

    public COL_plan() {
        super(
            "plan"
,  // name
            "col15b4a60c-de3d-4a04-8ccd-ac7dbe4b09f5"
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
