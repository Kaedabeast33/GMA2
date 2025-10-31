package org.example.output.dorm.orders.raw_vexus.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_email_address extends ColumnTemplate {

    public COL_email_address() {
        super(
            "email_address"
,  // name
            "col6111a2ca-7fa3-40ab-b395-9b651a70eee5"
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
