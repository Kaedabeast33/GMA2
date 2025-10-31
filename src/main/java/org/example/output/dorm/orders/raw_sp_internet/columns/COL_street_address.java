package org.example.output.dorm.orders.raw_sp_internet.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_street_address extends ColumnTemplate {

    public COL_street_address() {
        super(
            "street_address"
,  // name
            "col0ccde249-6010-4dad-be3d-2776a2da70da"
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
