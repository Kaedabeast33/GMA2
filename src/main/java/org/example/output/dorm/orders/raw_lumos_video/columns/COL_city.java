package org.example.output.dorm.orders.raw_lumos_video.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_city extends ColumnTemplate {

    public COL_city() {
        super(
            "city"
,  // name
            "col74abeec2-78f3-4366-8cba-df88fbeeab00"
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
