package org.example.output.dorm.orders.raw_vexus.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_cancel_date extends ColumnTemplate {

    public COL_cancel_date() {
        super(
            "cancel_date"
,  // name
            "col9a4c988a-46d7-4ff4-9a65-90b1618efed7"
,  // columnId
            "",  // description
            new String[]{""},  // tags
            true,  // isNullable
            true,  // isEditable
            false,  // isUnique
            false,  // isRequired
            "DATETIME"
,  // type
            "",  // defaultValue
            new String[]{"default"},  // columnGroups
            false,  // isUniqueIdentifier
            new String[]{""},  // uniqueIdentifierGroups
            false,  // isIndex
            new String[]{""},  // indexGroups
            null,  // referenceColumns
            java.time.LocalDateTime.class,  // fieldType
            org.example.CommonValues.DefaultKdbConverter.class  // kdbConverter


        );
    }

}
