package org.example.output.dorm.orders.raw_asap.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_sub_agent_name extends ColumnTemplate {

    public COL_sub_agent_name() {
        super(
            "sub_agent_name"
,  // name
            "col1fd16f94-f37c-4599-83ef-0ae56f3d549f"
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
