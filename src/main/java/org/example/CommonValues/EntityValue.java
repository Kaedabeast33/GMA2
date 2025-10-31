package org.example.CommonValues;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EntityValue<T> {
    private T value;
    private Class<T> type;

    public EntityValue(T value, Class<T> type) {
        this.value = value;
        this.type = type;
    }

    public <T> T getValue() {
        if (value == null) {
            if (type == String.class) return (T) "";
            if (type == Integer.class) return (T) Integer.valueOf(0);
            if (type == Long.class) return (T) Long.valueOf(0);
            if (type == Float.class) return (T) Float.valueOf(0);
            if (type == Double.class) return (T) Double.valueOf(0);
            if (type == Boolean.class) return (T) Boolean.FALSE;
            if (type == LocalDate.class) return (T) LocalDate.now();
            if (type == LocalDateTime.class) return (T) LocalDateTime.now();
            // fallback: return null for unknown types
            return null;
        }
        return (T) value;
    }


    public void setValue(T value) {
        this.value = value;
    }

    public Class<T> getType() {
        return type;
    }

    public void setType(Class<T> type) {
        this.type = type;
    }
}
