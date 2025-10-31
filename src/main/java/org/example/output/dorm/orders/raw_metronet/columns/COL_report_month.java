package org.example.output.dorm.orders.raw_metronet.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_report_month extends ColumnTemplate {

    public COL_report_month() {
        super(
            "report_month"
,  // name
            "col02c31f9c-b5e0-4b7b-8d05-66a4c2109760"
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
