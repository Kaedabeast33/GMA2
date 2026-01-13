package org.example.bank.db.contextObj;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import java.util.*;

public class Rules {
    private final Map<String, List<Object>> values = new HashMap<>();

    public Rules() {}

    @JsonAnySetter
    public void set(String name, Object value) {
        if (name == null) return;
        if (value == null) {
            values.put(name, new ArrayList<>());
            return;
        }
        if (value instanceof Collection) {
            values.put(name, new ArrayList<>((Collection<?>) value));
        } else if (value.getClass().isArray()) {
            Object[] arr = (Object[]) value;
            values.put(name, new ArrayList<>(Arrays.asList(arr)));
        } else {
            values.put(name, new ArrayList<>(Collections.singletonList(value)));
        }
    }

    public Map<String, List<Object>> getValues() {
        return values;
    }

    public List<Object> get(String name) {
        return values.get(name);
    }

    public void addAll(Rules other) {
        if (other == null) return;
        for (Map.Entry<String, List<Object>> e : other.values.entrySet()) {
            values.computeIfAbsent(e.getKey(), k -> new ArrayList<>()).addAll(e.getValue());
        }
    }

    @Override
    public String toString() {
        return "Rules" + values;
    }
}
