package org.example.output.dorm.orders.raw_frontier.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_port_request extends ColumnTemplate {

    public COL_port_request() {
        super(
            "port_request"
,  // name
            "col68a01791-0273-4c31-a8f2-158164469922"
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
