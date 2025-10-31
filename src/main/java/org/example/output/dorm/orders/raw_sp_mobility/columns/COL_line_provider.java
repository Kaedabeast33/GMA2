package org.example.output.dorm.orders.raw_sp_mobility.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_line_provider extends ColumnTemplate {

    public COL_line_provider() {
        super(
            "line_provider"
,  // name
            "colbb9793eb-3b56-4631-b82a-148e07c6b541"
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
