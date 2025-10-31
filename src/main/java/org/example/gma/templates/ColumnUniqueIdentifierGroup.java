package org.example.gma.templates;

import org.example.jsonBuilder.gma.ma.tables.columns.ColumnJson;

public class ColumnUniqueIdentifierGroup {
    String name;
    String description;
    String[] tags;
    String uniqueColumnIdentifierGroupId = "colug" + java.util.UUID.randomUUID();

    ColumnJson[] columns;
}
