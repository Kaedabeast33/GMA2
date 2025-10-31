package org.example.output.dorm.orders.raw_lumos.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_wtn extends ColumnTemplate {

    public COL_wtn() {
        super(
            "wtn"
,  // name
            "col910bc0aa-d06d-4959-8d5c-8374e8b3e1ae"
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
