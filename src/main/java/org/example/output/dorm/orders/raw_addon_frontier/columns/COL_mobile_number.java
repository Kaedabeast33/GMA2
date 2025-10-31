package org.example.output.dorm.orders.raw_addon_frontier.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_mobile_number extends ColumnTemplate {

    public COL_mobile_number() {
        super(
            "mobile_number"
,  // name
            "col18af109b-33c4-4c78-ba98-832274545807"
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
