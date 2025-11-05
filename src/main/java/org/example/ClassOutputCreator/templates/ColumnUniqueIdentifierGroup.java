package org.example.ClassOutputCreator.templates;

import org.example.JsonBuilder.json.ma.tables.columns.ColumnJson;

public class ColumnUniqueIdentifierGroup {
    String name;
    String description;
    String[] tags;
    String uniqueColumnIdentifierGroupId = "colug" + java.util.UUID.randomUUID();

    ColumnJson[] columns;
}
