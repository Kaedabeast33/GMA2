package org.example.output.dorm.orders.raw_sp_internet.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_email extends ColumnTemplate {

    public COL_email() {
        super(
            "email"
,  // name
            "col6c65c192-1c14-46c2-8833-63894fdbb20a"
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
