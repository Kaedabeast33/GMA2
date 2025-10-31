package org.example.output.dorm.orders.raw_metronet.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_metronet_status extends ColumnTemplate {

    public COL_metronet_status() {
        super(
            "metronet_status"
,  // name
            "col701f60f7-50f0-4801-a9f2-b33864c8e277"
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
            true,  // isIndex
            new String[]{"order_status_idx"},  // indexGroups
            null,  // referenceColumns
            java.lang.String.class,  // fieldType
            org.example.CommonValues.DefaultKdbConverter.class  // kdbConverter


        );
    }

}
