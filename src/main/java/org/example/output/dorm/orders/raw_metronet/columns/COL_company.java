package org.example.output.dorm.orders.raw_metronet.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_company extends ColumnTemplate {

    public COL_company() {
        super(
            "company"
,  // name
            "col55331491-37c5-4184-9458-aeafb624afa0"
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
