package org.example.output.dorm.orders.raw_metronet.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_sub_id extends ColumnTemplate {

    public COL_sub_id() {
        super(
            "sub_id"
,  // name
            "col391601ef-5d21-41fc-9e78-7ce5c41edfa0"
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
            new String[]{"sub_id_order_date_idx"},  // indexGroups
            null,  // referenceColumns
            java.lang.String.class,  // fieldType
            org.example.CommonValues.DefaultKdbConverter.class  // kdbConverter


        );
    }

}
