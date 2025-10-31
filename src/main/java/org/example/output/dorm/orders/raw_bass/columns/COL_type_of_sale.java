package org.example.output.dorm.orders.raw_bass.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_type_of_sale extends ColumnTemplate {

    public COL_type_of_sale() {
        super(
            "type_of_sale"
,  // name
            "colae01a229-167b-4c59-b51f-d329dffb1951"
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
