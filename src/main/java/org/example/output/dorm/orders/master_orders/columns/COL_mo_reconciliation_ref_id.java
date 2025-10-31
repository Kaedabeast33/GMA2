package org.example.output.dorm.orders.master_orders.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_mo_reconciliation_ref_id extends ColumnTemplate {

    public COL_mo_reconciliation_ref_id() {
        super(
            "mo_reconciliation_ref_id"
,  // name
            "col9af15143-a4db-4cc7-8b2a-eeb70a9c01c5"
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
