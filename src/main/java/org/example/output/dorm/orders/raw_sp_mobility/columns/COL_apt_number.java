package org.example.output.dorm.orders.raw_sp_mobility.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_apt_number extends ColumnTemplate {

    public COL_apt_number() {
        super(
            "apt_number"
,  // name
            "col21682250-725f-4500-a0e5-d863e673027f"
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
