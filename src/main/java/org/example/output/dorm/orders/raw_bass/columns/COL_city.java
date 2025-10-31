package org.example.output.dorm.orders.raw_bass.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_city extends ColumnTemplate {

    public COL_city() {
        super(
            "city"
,  // name
            "col4745bece-b7f6-4d79-a131-47879a89abc2"
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
