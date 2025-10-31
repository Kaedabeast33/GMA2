package org.example.output.dorm.orders.raw_bass_voice.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_partner_reference_id extends ColumnTemplate {

    public COL_partner_reference_id() {
        super(
            "partner_reference_id"
,  // name
            "colc572589b-ae01-41f4-9c43-5662836bad80"
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
