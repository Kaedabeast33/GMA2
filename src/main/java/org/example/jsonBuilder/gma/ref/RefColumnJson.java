package org.example.jsonBuilder.gma.ref;

public class RefColumnJson {
    String name;
    String columnId;
    RefTableJson referenceTable;

    public RefColumnJson(String s) {
        this.name = s;
        this.referenceTable = new RefTableJson("n/a","n/a",null);
    }

    public RefTableJson getReferenceTable() {
        return referenceTable;
    }

    public void setReferenceTable(RefTableJson referenceTable) {
        this.referenceTable = referenceTable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }
}
