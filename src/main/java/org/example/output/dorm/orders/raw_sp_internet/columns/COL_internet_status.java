package org.example.output.dorm.orders.raw_sp_internet.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_internet_status extends ColumnTemplate {

    public COL_internet_status() {
        super(
            "internet_status"
,  // name
            "col9f0908de-eca2-4e85-8c0d-2838b7bb5e70"
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
