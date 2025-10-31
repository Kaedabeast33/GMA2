package org.example.output.dorm.orders.raw_sp_mobility.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_phone extends ColumnTemplate {

    public COL_phone() {
        super(
            "phone"
,  // name
            "col51e2cdf3-1ab1-493a-b8a1-20b7193d4616"
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
