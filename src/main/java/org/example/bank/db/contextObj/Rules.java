// java
package org.example.bank.db.contextObj;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.Array;
import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rules {
    private Map<String, List<Object>> rules;

    public Rules() {
        this.rules = new HashMap<>();
    }

    // Used by Jackson when the JSON object is mapped directly to Rules
    @JsonCreator
    public Rules(Map<String, Object> initial) {
        this();
        if (initial == null) return;
        for (Map.Entry<String, Object> e : initial.entrySet()) {
            this.rules.put(e.getKey(), toListForStorage(e.getValue()));
        }
    }

    public Map<String, List<Object>> getMap() {
        return rules;
    }

    /**
     * Called for JSON properties not matched to a regular setter.
     * Example JSON: "column_rules": {"extension_type": ["csv"], "db_id": ["1234"]}
     */
    @JsonAnySetter
    public void setRule(String name, Object value) {
        rules.put(name, toListForStorage(value));
    }

    @JsonAnyGetter
    public Map<String, List<Object>> any() {
        return rules;
    }

    public void put(String key, Object value) {
        rules.put(key, toListForStorage(value));
    }

    public void addAll(Rules other) {
        if (other == null || other.rules == null) return;
        other.rules.forEach((k, v) -> {
            List<Object> existing = this.rules.get(k);
            List<Object> incoming = toListForStorage(v);
            if (existing == null) {
                this.rules.put(k, new ArrayList<>(incoming));
            } else {
                existing.addAll(incoming);
            }
        });
    }

    public boolean matchesColumnRules(String columnName, Rules other) {
        if (columnName == null) return false;
        List<Object> a = this.rules == null ? null : this.rules.get(columnName);
        List<Object> b = other == null || other.rules == null ? null : other.rules.get(columnName);
        return multisetEquals(a, b);
    }

    private static List<Object> toListForStorage(Object v) {
        if (v == null) return new ArrayList<>();
        List<Object> result = new ArrayList<>();
        if (v instanceof List<?>) {
            for (Object e : (List<?>) v) result.add(normalizeElement(e));
            return result;
        }
        if (v instanceof Collection<?>) {
            for (Object e : (Collection<?>) v) result.add(normalizeElement(e));
            return result;
        }
        if (v.getClass().isArray()) {
            int len = Array.getLength(v);
            for (int i = 0; i < len; i++) result.add(normalizeElement(Array.get(v, i)));
            return result;
        }
        result.add(normalizeElement(v));
        return result;
    }

    private static Object normalizeElement(Object o) {
        if (o == null) return null;
        if (o instanceof Number) {
            Number n = (Number) o;
            double dv = n.doubleValue();
            long lv = n.longValue();
            if (Double.compare(dv, (double) lv) == 0) {
                return Long.valueOf(lv);
            } else {
                return Double.valueOf(dv);
            }
        }
        if (o instanceof String) {
            return ((String) o).trim();
        }
        if (o instanceof Collection<?>) {
            List<Object> list = new ArrayList<>();
            for (Object e : (Collection<?>) o) list.add(normalizeElement(e));
            return Collections.unmodifiableList(list);
        }
        if (o.getClass().isArray()) {
            int len = Array.getLength(o);
            List<Object> list = new ArrayList<>(len);
            for (int i = 0; i < len; i++) list.add(normalizeElement(Array.get(o, i)));
            return Collections.unmodifiableList(list);
        }
        if (o instanceof Map<?, ?>) {
            Map<Object, Object> sorted = new TreeMap<>();
            for (Map.Entry<?, ?> e : ((Map<?, ?>) o).entrySet()) {
                Object key = e.getKey() == null ? null : e.getKey().toString().trim();
                sorted.put(key, normalizeElement(e.getValue()));
            }
            return Collections.unmodifiableMap(sorted);
        }
        return o;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rules)) return false;
        Rules other = (Rules) o;

        Map<String, List<Object>> a = this.rules == null ? Collections.emptyMap() : this.rules;
        Map<String, List<Object>> b = other.rules == null ? Collections.emptyMap() : other.rules;

        if (!a.keySet().equals(b.keySet())) return false;

        for (String key : a.keySet()) {
            List<Object> va = a.get(key);
            List<Object> vb = b.get(key);
            if (!multisetEquals(va, vb)) return false;
        }
        return true;
    }

    private static boolean multisetEquals(List<Object> a, List<Object> b) {
        if (a == null) a = Collections.emptyList();
        if (b == null) b = Collections.emptyList();
        if (a.size() != b.size()) return false;

        Map<Object, Integer> freq = new HashMap<>();
        for (Object item : a) {
            freq.merge(normalizeForKey(item), 1, Integer::sum);
        }
        for (Object item : b) {
            Object key = normalizeForKey(item);
            Integer count = freq.get(key);
            if (count == null || count == 0) return false;
            freq.put(key, count - 1);
        }
        return true;
    }

    private static Object normalizeForKey(Object o) {
        if (o == null) return new NullKey();
        if (o instanceof Collection<?> || o.getClass().isArray() || o instanceof Map<?, ?>) {
            return o;
        }
        return o;
    }

    private static final class NullKey {
        @Override public boolean equals(Object obj) { return obj instanceof NullKey; }
        @Override public int hashCode() { return 0; }
    }

    @Override
    public int hashCode() {
        if (rules == null || rules.isEmpty()) return 0;
        List<String> keys = new ArrayList<>(rules.keySet());
        Collections.sort(keys);
        int result = 1;
        for (String k : keys) {
            List<Object> v = rules.get(k);
            int vh = normalizedHash(v);
            result = 31 * result + (k == null ? 0 : k.hashCode());
            result = 31 * result + vh;
        }
        return result;
    }

    private static int normalizedHash(Object v) {
        if (v == null) return 0;
        if (v instanceof Collection<?> || v.getClass().isArray()) {
            List<Object> list = v instanceof List<?> ? new ArrayList<>((Collection<?>) v)
                    : toListIfArray(v);
            int h = 1;
            for (Object e : list) h = 31 * h + (e == null ? 0 : e.hashCode());
            return h;
        }
        if (v instanceof Map<?, ?>) {
            return v.hashCode();
        }
        return v.hashCode();
    }

    private static List<Object> toListIfArray(Object v) {
        if (v == null) return Collections.emptyList();
        if (v instanceof Collection<?>) return new ArrayList<>((Collection<?>) v);
        if (v.getClass().isArray()) {
            int len = Array.getLength(v);
            List<Object> list = new ArrayList<>(len);
            for (int i = 0; i < len; i++) list.add(Array.get(v, i));
            return list;
        }
        return Collections.singletonList(v);
    }

    @Override
    public String toString() {
        return "Rules{" +
                "rules=" + rules +
                '}';
    }
}
