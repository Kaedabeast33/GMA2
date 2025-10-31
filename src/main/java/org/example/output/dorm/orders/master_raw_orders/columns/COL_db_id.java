package org.example.output.dorm.orders.master_raw_orders.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_db_id extends ColumnTemplate {

    public COL_db_id() {
        super(
            "db_id"
,  // name
            "col9e308533-3679-4d2a-8b08-9087c819b315"
,  // columnId
            "",  // description
            new String[]{""},  // tags
            false,  // isNullable
            true,  // isEditable
            true,  // isUnique
            true,  // isRequired
            "VARCHAR(255)"
,  // type
            "",  // defaultValue
            new String[]{"default"},  // columnGroups
            false,  // isUniqueIdentifier
            new String[]{""},  // uniqueIdentifierGroups
            true,  // isIndex
            new String[]{"PRIMARY_db_id"},  // indexGroups
            null,  // referenceColumns
            java.lang.String.class,  // fieldType
            org.example.CommonValues.DefaultKdbConverter.class  // kdbConverter


        );
    }

}
