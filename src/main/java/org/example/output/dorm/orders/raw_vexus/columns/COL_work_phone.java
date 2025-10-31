package org.example.output.dorm.orders.raw_vexus.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_work_phone extends ColumnTemplate {

    public COL_work_phone() {
        super(
            "work_phone"
,  // name
            "colf4accc35-4b6b-4279-90df-b5541e6d0d9c"
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
