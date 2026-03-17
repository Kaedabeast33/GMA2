package org.example.bank.db.contextObj;

import java.util.Arrays;

public class ContextObj {
    ContextMa[] mas;
    ContextTable[] tables;
    ContextColumn[] columns;

    public ContextMa[] getMas() {
        return mas;
    }

    public void setMas(ContextMa[] mas) {
        this.mas = mas;
    }

    public ContextTable[] getTables() {
        return tables;
    }

    public void setTables(ContextTable[] tables) {
        this.tables = tables;
    }

    public ContextColumn[] getColumns() {
        return columns;
    }

    public void setColumns(ContextColumn[] columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "ContextObj{" +
                "mas=" + Arrays.toString(mas) +
                ", tables=" + Arrays.toString(tables) +
                ", columns=" + Arrays.toString(columns) +
                '}';
    }

    //{"ma_name": "client", "ma_tags": ["raw", "json", "parsed", "client", "health", "records"], "table_name": "clients", "table_tags": [], "column_name": "db_id", "column_tags": [],
// "ma_description": "this is the schema where all client health records data is kept",
// "table_description": "This contains Client information which includes the Client Id",
// "column_description": ""}

}
