package org.example.jsonBuilder.gma.ma.tables.columns;

public class GroupDTO {
    String name;

    String columnGroupId = "colg" + java.util.UUID.randomUUID();

    ColumnJson[] columnGroupColumns;

    public GroupDTO(String name, String columnGroupId, ColumnDTO[] columns) {
        this.name = name;
        this.columnGroupId = columnGroupId;
        for(int i = 0; i < columns.length; i++) {
            if (i == 0) {
                this.columnGroupColumns = new ColumnJson[columns.length];
            }
            this.columnGroupColumns[i] = new ColumnJson(columns[i]);
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumnGroupId() {
        return columnGroupId;
    }

    public void setColumnGroupId(String columnGroupId) {
        this.columnGroupId = columnGroupId;
    }

    public ColumnJson[] getColumnGroupColumns() {
        return columnGroupColumns;
    }

    public void setColumnGroupColumns(ColumnJson[] columnGroupColumns) {
        this.columnGroupColumns = columnGroupColumns;
    }
}
