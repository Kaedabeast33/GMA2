package org.example.bank.OutputClassBank;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryResult {
    Map<String, List<Object>> data;

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

    @Override
    public String toString() {
        return
                "data=" + data;
    }
}
