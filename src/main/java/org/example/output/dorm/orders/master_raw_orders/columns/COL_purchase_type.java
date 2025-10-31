package org.example.output.dorm.orders.master_raw_orders.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_purchase_type extends ColumnTemplate {

    public COL_purchase_type() {
        super(
            "purchase_type"
,  // name
            "col9378968b-acef-4824-917a-a3db7b944e4a"
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
