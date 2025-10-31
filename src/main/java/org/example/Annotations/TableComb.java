package org.example.Annotations;

import java.util.ArrayList;
import java.util.List;

public class TableComb {
    private List<MethodsComb> methodsComb;
    private List<FieldsComb> fieldsComb;
    private KdbTable kdbTable;

    public TableComb() {
        this.methodsComb = new ArrayList<>(); // Initialize the list
        this.fieldsComb = new ArrayList<>();  // Initialize the list
    }

    public void addMethodsComb(MethodsComb methodsComb) {
        this.methodsComb.add(methodsComb);
    }

    public void addFieldsComb(FieldsComb fieldsComb) {
        this.fieldsComb.add(fieldsComb);
    }

    public List<MethodsComb> getMethodsComb() {
        return methodsComb;
    }

    public List<FieldsComb> getFieldsComb() {
        return fieldsComb;
    }

    public KdbTable getKdbTable() {
        return kdbTable;
    }

    public void setKdbTable(KdbTable kdbTable) {
        this.kdbTable = kdbTable;
    }
}