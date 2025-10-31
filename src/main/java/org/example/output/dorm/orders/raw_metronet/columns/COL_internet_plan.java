package org.example.output.dorm.orders.raw_metronet.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_internet_plan extends ColumnTemplate {

    public COL_internet_plan() {
        super(
            "internet_plan"
,  // name
            "colaaeda1e2-a45f-4369-aa8a-7b3409366dea"
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
