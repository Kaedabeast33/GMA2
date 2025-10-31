package org.example.output.dorm.orders.raw_lumos.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_street_address extends ColumnTemplate {

    public COL_street_address() {
        super(
            "street_address"
,  // name
            "col57119b6e-d3e8-416d-96c9-fbe65a366b29"
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
