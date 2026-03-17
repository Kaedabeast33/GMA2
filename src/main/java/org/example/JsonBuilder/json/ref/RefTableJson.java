package org.example.JsonBuilder.json.ref;

public class RefTableJson {
    String tableName;
    String tableId;
    RefMaJson referenceMa;

    public RefTableJson(String tableName, String tableId, RefMaJson referenceMa) {
        this.tableName = tableName;
        this.tableId = tableId;
        this.referenceMa = referenceMa;
    }

    public RefTableJson(){

    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public RefMaJson getReferenceMa() {
        return referenceMa;
    }

    public void setReferenceMa(RefMaJson referenceMa) {
        this.referenceMa = referenceMa;
    }
}
