package org.example.output.dorm.orders.raw_lumos_voice.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_db_id extends ColumnTemplate {

    public COL_db_id() {
        super(
            "db_id"
,  // name
            "col93b94f17-2709-416a-bc48-0784e8a304d9"
,  // columnId
            "",  // description
            new String[]{""},  // tags
            false,  // isNullable
            true,  // isEditable
            true,  // isUnique
            true,  // isRequired
            "VARCHAR(255)"
,  // type
            "",  // defaultValue
            new String[]{"default"},  // columnGroups
            false,  // isUniqueIdentifier
            new String[]{""},  // uniqueIdentifierGroups
            true,  // isIndex
            new String[]{"PRIMARY_db_id"},  // indexGroups
            null,  // referenceColumns
            java.lang.String.class,  // fieldType
            org.example.CommonValues.DefaultKdbConverter.class  // kdbConverter


        );
    }

}
