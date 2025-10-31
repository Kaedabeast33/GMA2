package org.example.output.dorm.orders.raw_addon_metronet.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_carrier_system extends ColumnTemplate {

    public COL_carrier_system() {
        super(
            "carrier_system"
,  // name
            "col37c4cd7e-1b2d-46d2-8f58-7c07094719be"
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
