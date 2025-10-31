package org.example.output.dorm.orders.fun_hub_queue.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_sync_status extends ColumnTemplate {

    public COL_sync_status() {
        super(
            "sync_status"
,  // name
            "col225f9799-9d41-457b-ab1e-6c39c91a8977"
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
