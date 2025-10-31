package org.example.CommonValues;

import com.google.gson.Gson;

public class ReferenceColumnBuilder {






    public String name;
    public String columnId;
    ReferenceTable referenceTable;

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

    public ReferenceTable getReferenceTable() {
        return referenceTable;
    }

    public void setReferenceTable(ReferenceTable referenceTable) {
        this.referenceTable = referenceTable;
    }

//  field to hold the JSON string

//    {
//        "name": "REFERENCEEXAMPLE",
//            "columnId": "col1233567",
//            "referenceTable": {
//        "tableName": "raw_asap",
//                "tableId": "tab1234321",
//                "referenceMa": {
//            "maName": "orders",
//                    "maId": "ma77787878"
//        }
//    }
//    }



}

