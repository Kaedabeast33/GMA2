package org.example.output.dorm.orders.raw_sp_video.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_video_account_number extends ColumnTemplate {

    public COL_video_account_number() {
        super(
            "video_account_number"
,  // name
            "col69c0b53b-c088-4cb2-a592-048fcd6f9752"
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
