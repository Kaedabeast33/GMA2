package org.example.output.dorm.orders.raw_lumos.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_db_id extends ColumnTemplate {

    public COL_db_id() {
        super(
            "db_id"
,  // name
            "col9be695e6-591e-4b3f-abc1-4ae78a55502f"
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
