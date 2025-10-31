package org.example.output.dorm.orders.raw_sp_video.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_phone extends ColumnTemplate {

    public COL_phone() {
        super(
            "phone"
,  // name
            "col14e4037b-6b26-40e1-b578-fe157c59918a"
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
