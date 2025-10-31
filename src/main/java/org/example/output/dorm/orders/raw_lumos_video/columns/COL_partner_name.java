package org.example.output.dorm.orders.raw_lumos_video.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_partner_name extends ColumnTemplate {

    public COL_partner_name() {
        super(
            "partner_name"
,  // name
            "col95f02eb9-0397-487f-ba6c-6c29360fb3e1"
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
