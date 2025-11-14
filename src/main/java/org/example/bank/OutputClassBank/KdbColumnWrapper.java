package org.example.bank.OutputClassBank;

import org.example.bank.JsonBuilderRef.EntityValue;

public class KdbColumnWrapper {
    public static String safeGetValue(EntityValue<?> col) {
        if (col == null || col.getValue() == null || col.getType() == null) {
            return "null";
        }
        return col.getValue().toString();
    }

}
