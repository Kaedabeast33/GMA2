package org.example.output.dorm.orders.raw_sp_video.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_zip extends ColumnTemplate {

    public COL_zip() {
        super(
            "zip"
,  // name
            "col4cfb8631-8b6f-41b8-8c66-2ce2a512c4c3"
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
