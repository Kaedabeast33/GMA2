package org.example.output.dorm.orders.raw_lumos_video.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_partner_reference_id extends ColumnTemplate {

    public COL_partner_reference_id() {
        super(
            "partner_reference_id"
,  // name
            "colfddd7882-7bd1-4bc8-ae76-995ada734881"
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
