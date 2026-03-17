package org.example.bank.OutputClassBank;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryResult {
    Map<String, List<Object>> data;


    public List<String> getResultForCol(KdbColumnPersona column){
        return data.getOrDefault(column.getName(), null).stream().map(obj -> (String)obj).toList();
    }

    public  Object safeGetRow(String key, int i) {
        if (this.data == null || key == null) return null;
        List<Object> list = data.get(key);
        if (list == null || list.size() <= i) return null;
        return list.get(i);
    }



    public Integer getResultSize(){
        if(data.isEmpty()){
            return 0;
        } else {
            String firstKey = data.keySet().iterator().next();
            return data.get(firstKey).size();
        }
    }

    public QueryResult(Map<String, List<Object>> data) {
        this.data = data;
    }




    public Map<String, List<Object>> getData() {
        return data;
    }

    public void setData(Map<String, List<Object>> data) {
        this.data = data;
    }

    public static QueryResult getQueryResultObj(
            List<KdbColumnPersona> getColumns,
            String query,
            Connection connection) throws SQLException {

        Map<String, List<Object>> columnsData = new HashMap<>();

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                for (KdbColumnPersona col : getColumns) {
                    String name = col.getName();
                    Object value = readJdbcValue(rs, name);
                    columnsData.computeIfAbsent(name, k -> new ArrayList<>()).add(value);
                }
            }
        }

        return new QueryResult(columnsData);
    }

    public static QueryResult getQueryResultObj(
            String query,
            Connection connection) throws SQLException {

        Map<String, List<Object>> columnsData = new HashMap<>();

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
//                    String columnName = meta.getColumnLabel(i);
                    Object value = readJdbcValue(rs, i);
                    columnsData.computeIfAbsent("col"+i, k -> new ArrayList<>()).add(value);
                }
            }
        }

        return new QueryResult(columnsData);
    }

    public static QueryResult getQueryResultObj(
            String query,
            EntityManager entityManager) {

        Map<String, List<Object>> columnsData = new HashMap<>();

        List<?> results = entityManager.createNativeQuery(query).getResultList();

        for (Object row : results) {
            if (row == null) continue;

            if (row instanceof Object[]) {
                Object[] arr = (Object[]) row;
                for (int i = 0; i < arr.length; i++) {
                    String columnName = "col" + (i + 1);
                    Object value = normalizeJpaValue(arr[i]);
                    columnsData.computeIfAbsent(columnName, k -> new ArrayList<>()).add(value);
                }

            } else {
                columnsData.computeIfAbsent("col1", k -> new ArrayList<>())
                        .add(normalizeJpaValue(row));
            }
        }

        return new QueryResult(columnsData);
    }

    private static Object normalizeJpaValue(Object value) {
        if (value instanceof byte[]) {
            return bytesToFloatArray((byte[]) value);
        }
        return value;
    }

    public static QueryResult getQueryResultObj(
            List<KdbColumnPersona> getColumns,
            String query,
            EntityManager entityManager) {

        Map<String, List<Object>> columnsData = new HashMap<>();

        List<?> results = entityManager.createNativeQuery(query).getResultList();

        for (Object row : results) {
            if (row == null) continue;

            if (row instanceof Object[]) {
                Object[] arr = (Object[]) row;
                for (int i = 0; i < getColumns.size() && i < arr.length; i++) {
                    String name = getColumns.get(i).getName();
                    Object value = normalizeJpaValue(arr[i]);
                    columnsData.computeIfAbsent(name, k -> new ArrayList<>()).add(value);
                }

            } else if (row instanceof Tuple) {
                Tuple tuple = (Tuple) row;
                for (KdbColumnPersona col : getColumns) {
                    Object value = normalizeJpaValue(tuple.get(col.getName()));
                    columnsData.computeIfAbsent(col.getName(), k -> new ArrayList<>()).add(value);
                }

            } else {
                // scalar or entity
                if (getColumns.size() == 1) {
                    columnsData.computeIfAbsent(getColumns.get(0).getName(), k -> new ArrayList<>())
                            .add(normalizeJpaValue(row));
                } else {
                    for (KdbColumnPersona col : getColumns) {
                        Object value = extractBeanValue(row, col.getName());
                        columnsData.computeIfAbsent(col.getName(), k -> new ArrayList<>())
                                .add(normalizeJpaValue(value));
                    }
                }
            }
        }

        return new QueryResult(columnsData);
    }

    private static Object extractBeanValue(Object bean, String prop) {
        try {
            String getter = "get" + Character.toUpperCase(prop.charAt(0)) + prop.substring(1);
            Method m = bean.getClass().getMethod(getter);
            return m.invoke(bean);
        } catch (Exception ignored) {
            try {
                Field f = bean.getClass().getDeclaredField(prop);
                f.setAccessible(true);
                return f.get(bean);
            } catch (Exception e) {
                return null;
            }
        }
    }






    private static Object readJdbcValue(ResultSet rs, int index) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int type = meta.getColumnType(index);
//        System.out.println("Reading column " + index + " of type " + type);

        switch (type) {
            case Types.BINARY:
            case 1111:

            case Types.VARBINARY:
            case Types.LONGVARBINARY:
            case Types.BLOB:
//                System.out.println("Attempting to read binary data for column " + index);
                byte[] bytes = rs.getBytes(index);
                return bytes != null ? bytesToFloatArray(bytes) : null;

            default:
                return rs.getObject(index);
        }
    }

    private static Object readJdbcValue(ResultSet rs, String column) throws SQLException {
        int index = rs.findColumn(column);
        return readJdbcValue(rs, index);
    }

    private static float[] bytesToFloatArray(byte[] bytes) {
        FloatBuffer fb = ByteBuffer
                .wrap(bytes)
                .order(ByteOrder.LITTLE_ENDIAN)
                .asFloatBuffer();

        float[] vector = new float[fb.remaining()];
        fb.get(vector);
        return vector;
    }



    @Override
    public String toString() {
        return
                "data=" + data;
    }

    // Helper: parse the SELECT list from a simple SQL query and return column names/aliases in order.
    // This is a lightweight parser (handles nested parentheses to avoid splitting function args on commas)
    // Assumption: query contains a top-level SELECT ... FROM and columns are comma-separated at that level.
    private static List<String> parseSelectColumns(String query) {
        if (query == null) return null;
        String lower = query.toLowerCase();
        int sel = lower.indexOf("select");
        int from = lower.indexOf(" from ", sel >= 0 ? sel : 0);
        if (sel == -1 || from == -1 || from <= sel) return null;
        String selectList = query.substring(sel + 6, from); // between SELECT and FROM
        List<String> cols = new ArrayList<>();
        StringBuilder cur = new StringBuilder();
        int depth = 0;
        for (int i = 0; i < selectList.length(); i++) {
            char c = selectList.charAt(i);
            if (c == '(') {
                depth++;
                cur.append(c);
            } else if (c == ')') {
                depth = Math.max(0, depth - 1);
                cur.append(c);
            } else if (c == ',' && depth == 0) {
                String token = cur.toString().trim();
                if (!token.isEmpty()) cols.add(extractAliasOrName(token));
                cur.setLength(0);
            } else {
                cur.append(c);
            }
        }
        String last = cur.toString().trim();
        if (!last.isEmpty()) cols.add(extractAliasOrName(last));
        return cols;
    }

    private static String extractAliasOrName(String token) {
        // Remove surrounding whitespace
        token = token.trim();
        // Remove trailing semicolon if any
        if (token.endsWith(";")) token = token.substring(0, token.length() - 1).trim();
        // If contains AS, prefer alias
        String lower = token.toLowerCase();
        int asIndex = lower.lastIndexOf(" as ");
        if (asIndex != -1) {
            String alias = token.substring(asIndex + 4).trim();
            return stripQuotes(alias);
        }
        // If last word looks like an alias (no parentheses and not a SQL keyword), use it
        // e.g. "table.col alias" or "col alias"
        // Split by whitespace outside parens
        String[] parts = token.split("\\s+");
        if (parts.length > 1) {
            String last = parts[parts.length - 1];
            // If last part doesn't contain parentheses or dot-only, treat as alias
            if (!last.contains("(") && !last.contains(")")) {
                return stripQuotes(last);
            }
        }
        // Otherwise, take the column/expression name (strip table prefix)
        // e.g. "table.col" -> "col"
        String name = token;
        // If it's like table.col or schema.table.col, take last segment
        int dot = name.lastIndexOf('.');
        if (dot != -1) {
            name = name.substring(dot + 1);
        }
        return stripQuotes(name).trim();
    }

    private static String stripQuotes(String s) {
        s = s.trim();
        if ((s.startsWith("\"") && s.endsWith("\"")) || (s.startsWith("`") && s.endsWith("`")) || (s.startsWith("[") && s.endsWith("]"))) {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }
}
