package org.example.CommonValues;

import org.example.jsonBuilder.gma.ma.tables.columns.ColumnJson;
import org.example.jsonBuilder.gma.ma.tables.columns.GroupDTO;

public class IndexMapObject {
    Integer order;
    ColumnJson column;

    public IndexMapObject(Integer order, ColumnJson column) {
        this.order = order;
        this.column = column;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public ColumnJson getColumn() {
        return column;
    }

    public void addIndex(GroupDTO group) {

    }
}
