package org.example.output.dorm.orders.raw_sp_video.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_video_install_date extends ColumnTemplate {

    public COL_video_install_date() {
        super(
            "video_install_date"
,  // name
            "col2e7da7a6-82c9-4d82-b11f-d01f7875f058"
,  // columnId
            "",  // description
            new String[]{""},  // tags
            true,  // isNullable
            true,  // isEditable
            false,  // isUnique
            false,  // isRequired
            "DATETIME"
,  // type
            "",  // defaultValue
            new String[]{"default"},  // columnGroups
            false,  // isUniqueIdentifier
            new String[]{""},  // uniqueIdentifierGroups
            false,  // isIndex
            new String[]{""},  // indexGroups
            null,  // referenceColumns
            java.time.LocalDateTime.class,  // fieldType
            org.example.CommonValues.DefaultKdbConverter.class  // kdbConverter


        );
    }

}
