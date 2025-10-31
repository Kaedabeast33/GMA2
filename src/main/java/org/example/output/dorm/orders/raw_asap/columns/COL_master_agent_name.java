package org.example.output.dorm.orders.raw_asap.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_master_agent_name extends ColumnTemplate {

    public COL_master_agent_name() {
        super(
            "master_agent_name"
,  // name
            "col0df3a82f-ca3f-4a16-b41c-880971d87efe"
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
