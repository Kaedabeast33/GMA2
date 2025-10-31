package org.example.output.dorm.orders.raw_addon_shell.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_date_db_changed extends ColumnTemplate {

    public COL_date_db_changed() {
        super(
            "date_db_changed"
,  // name
            "col695db635-93ec-40ea-b053-cfb49fb4afff"
,  // columnId
            "",  // description
            new String[]{""},  // tags
            true,  // isNullable
            true,  // isEditable
            false,  // isUnique
            false,  // isRequired
            "DATETIME"
,  // type
            "",  // defaultValue
            new String[]{"default"},  // columnGroups
            false,  // isUniqueIdentifier
            new String[]{""},  // uniqueIdentifierGroups
            false,  // isIndex
            new String[]{""},  // indexGroups
            null,  // referenceColumns
            java.time.LocalDateTime.class,  // fieldType
            org.example.CommonValues.DefaultKdbConverter.class  // kdbConverter


        );
    }

}
