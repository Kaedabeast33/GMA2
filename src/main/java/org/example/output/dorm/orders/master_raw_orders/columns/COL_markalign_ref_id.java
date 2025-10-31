package org.example.output.dorm.orders.master_raw_orders.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_markalign_ref_id extends ColumnTemplate {

    public COL_markalign_ref_id() {
        super(
            "markalign_ref_id"
,  // name
            "colbe8041a9-baf4-45df-84de-fdc4c1eefe9a"
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
