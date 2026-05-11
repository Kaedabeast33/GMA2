
package org.example.bank.ai;


import org.example.APP.schemas.vyta.client_med.inputs.TAB_inputs;
import org.example.bank.OutputClassBank.QueryResult;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.example.bank.AppConfig.*;


public class VectorQueryResultWrapper {
    List<VectorQueryResult> vectorQueryResults;


    public VectorQueryResultWrapper(List<VectorQueryResult> vectorQueryResults) {
        this.vectorQueryResults = vectorQueryResults;
    }

    public List<String> extractDbIds(double confidenceThreshold) {
        List<String> dbIds = new ArrayList<>();
        for (VectorQueryResult result : this.vectorQueryResults) {
            if (result.getConfidence() >= confidenceThreshold) {
                dbIds.add(result.getDb_id());
            }
        }
        System.out.println("dbids" + dbIds);
        return dbIds;
    }



    public  List<String> getInputValuesDbIds(double confidenceThreshold,String table) throws SQLException {
        Connection connection = DriverManager.getConnection(getJdbcUrl(),getJdbcUser(),getJdbcPassword());
        List<String> values = new ArrayList<>();
        String selectInputs = String.format("""
                SELECT
                    %s
                from %s
                where %s IN ('%s')


                """, new TAB_inputs().getCOL_value().getName(),table, new TAB_inputs().getCOL_db_id().getName(), String.join("','", extractDbIds(confidenceThreshold))
        );
        QueryResult queryResult = QueryResult.getQueryResultObj(selectInputs,connection);
        for(int i = 0; i < queryResult.getResultSize(); i++) {
            System.out.println(queryResult.safeGetRow("col1", i)+" is the value for col1 at index "+i);
            values.add(String.valueOf(queryResult.safeGetRow("col1", i)));
        }



        return values;
    }


    public List<VectorQueryResult> getList() {
        return vectorQueryResults;
    }

    public VectorQueryResultWrapper getListInOrder() {
        if (vectorQueryResults == null || vectorQueryResults.isEmpty()) {
            return new VectorQueryResultWrapper(List.of());
        }
        return new VectorQueryResultWrapper(vectorQueryResults.stream()
                .filter(Objects::nonNull)
                .sorted((a, b) -> {
                    Double ca = a.getConfidence();
                    Double cb = b.getConfidence();
                    if (ca == null && cb == null) return 0;
                    if (ca == null) return 1;   // treat null as least confident
                    if (cb == null) return -1;
                    return Double.compare(cb, ca); // descending
                })
                .collect(Collectors.toList()));
    }

    public VectorQueryResultWrapper getListInOrder(Integer in) {
        if (vectorQueryResults == null || vectorQueryResults.isEmpty()) {
            return new VectorQueryResultWrapper(List.of()) ;
        }
        return new VectorQueryResultWrapper(vectorQueryResults.stream()
                .filter(Objects::nonNull)
                .sorted((a, b) -> {
                    Double ca = a.getConfidence();
                    Double cb = b.getConfidence();
                    if (ca == null && cb == null) return 0;
                    if (ca == null) return 1;   // treat null as least confident
                    if (cb == null) return -1;
                    return Double.compare(cb, ca); // descending
                })
                .collect(Collectors.toList()).subList(0, Math.min(in, vectorQueryResults.size()))) ;
    }

    public void setVectorQueryResults(List<VectorQueryResult> vectorQueryResults) {
        this.vectorQueryResults = vectorQueryResults;
    }

    @Override
    public String toString() {
        return vectorQueryResults.stream().map(VectorQueryResult::toString).collect(Collectors.joining("\n"));
    }
}
