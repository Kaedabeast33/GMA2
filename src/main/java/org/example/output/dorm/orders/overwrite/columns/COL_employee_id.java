package org.example.output.dorm.orders.overwrite.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_employee_id extends ColumnTemplate {

    public COL_employee_id() {
        super(
            "employee_id"
,  // name
            "col8d5a6e71-0326-4894-9839-4ef6c263d771"
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
