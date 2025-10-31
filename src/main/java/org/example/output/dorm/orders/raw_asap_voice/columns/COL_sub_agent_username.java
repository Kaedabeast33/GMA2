package org.example.output.dorm.orders.raw_asap_voice.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_sub_agent_username extends ColumnTemplate {

    public COL_sub_agent_username() {
        super(
            "sub_agent_username"
,  // name
            "col1dc67c05-f834-427f-9488-cffd0234ec72"
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
