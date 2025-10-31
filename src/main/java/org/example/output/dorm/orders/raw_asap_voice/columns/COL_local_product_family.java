package org.example.output.dorm.orders.raw_asap_voice.columns;


import org.example.gma.templates.ColumnTemplate;


public class COL_local_product_family extends ColumnTemplate {

    public COL_local_product_family() {
        super(
            "local_product_family"
,  // name
            "cola725e3a1-c3d6-4ae3-83ce-fa59daeb523b"
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
