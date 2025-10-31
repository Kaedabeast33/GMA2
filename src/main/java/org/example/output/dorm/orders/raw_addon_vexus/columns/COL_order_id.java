package org.example.output.dorm.orders.raw_addon_vexus.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_order_id extends ColumnTemplate {

    public COL_order_id() {
        super(
            "order_id"
,  // name
            "colfd42a2df-ac8f-4acb-bad3-e8441917ad9b"
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
