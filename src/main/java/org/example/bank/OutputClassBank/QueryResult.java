package org.example.bank.OutputClassBank;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static QueryResult getQueryResultObj(List<KdbColumnPersona> getColumns, String query, Connection connection) throws SQLException {

        // Add your testing logic here
        Map<String,List<Object>> columnsData = new HashMap<>();

        ResultSet resultSet = connection.prepareStatement(query).executeQuery();
        while (resultSet.next()) {
            for (KdbColumnPersona kdbColumnPersona : getColumns) {
                String columnName = kdbColumnPersona.getName();
                Object value = resultSet.getObject(columnName);
                columnsData.computeIfAbsent(columnName, k -> new ArrayList<>()).add(value);
            }
        }

        return new QueryResult(columnsData);
    }


    public static QueryResult getQueryResultObj(List<KdbColumnPersona> getColumns, String query, EntityManager entityManager) {

        Map<String,List<Object>> columnsData = new HashMap<>();

        List<?> results = entityManager.createQuery(query).getResultList();
        for (Object row : results) {
            if (row == null) continue;

            if (row instanceof Object[]) {
                Object[] arr = (Object[]) row;
                for (int i = 0; i < getColumns.size() && i < arr.length; i++) {
                    String columnName = getColumns.get(i).getName();
                    Object value = arr[i];
                    columnsData.computeIfAbsent(columnName, k -> new ArrayList<>()).add(value);
                }
            } else if (row instanceof Tuple) {
                Tuple tuple = (Tuple) row;
                for (KdbColumnPersona col : getColumns) {
                    Object value = tuple.get(col.getName());
                    columnsData.computeIfAbsent(col.getName(), k -> new ArrayList<>()).add(value);
                }
            } else {
                // single scalar result (one column)
                if (getColumns.size() == 1) {
                    String columnName = getColumns.get(0).getName();
                    columnsData.computeIfAbsent(columnName, k -> new ArrayList<>()).add(row);
                } else {
                    // Try bean getters first, then field access
                    for (KdbColumnPersona col : getColumns) {
                        Object value = null;
                        String prop = col.getName();
                        try {
                            String getter = "get" + Character.toUpperCase(prop.charAt(0)) + prop.substring(1);
                            Method m = row.getClass().getMethod(getter);
                            value = m.invoke(row);
                        } catch (Exception e) {
                            try {
                                Field f = row.getClass().getDeclaredField(prop);
                                f.setAccessible(true);
                                value = f.get(row);
                            } catch (Exception ex) {
                                value = null;
                            }
                        }
                        columnsData.computeIfAbsent(col.getName(), k -> new ArrayList<>()).add(value);
                    }
                }
            }
        }

        return new QueryResult(columnsData);
    }


    @Override
    public String toString() {
        return
                "data=" + data;
    }
}
