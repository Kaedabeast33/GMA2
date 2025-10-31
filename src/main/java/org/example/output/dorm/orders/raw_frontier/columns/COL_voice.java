package org.example.output.dorm.orders.raw_frontier.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_voice extends ColumnTemplate {

    public COL_voice() {
        super(
            "voice"
,  // name
            "col96b33f0f-0e8c-4376-91b9-78e219103d05"
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
