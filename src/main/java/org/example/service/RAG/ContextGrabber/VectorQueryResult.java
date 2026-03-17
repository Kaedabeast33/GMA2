package org.example.service.RAG.ContextGrabber;//package org.example.service.RAG.ContextGrabber;
//
////import com.chipr.APP.schemas.vyta.client.inputs.TAB_inputs;
//import com.chipr.APP.schemas.gma.client.inputs.TAB_inputs;
//import org.example.bank.OutputClassBank.QueryResult;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.example.bank.commonValues.AppConfig.*;
//
//
//public class VectorQueryResult {
//    String db_id;
//    Double confidence;
//
//
//
//    public String getDb_id() {
//        return db_id;
//    }
//
//    public void setDb_id(String db_id) {VectorQueryResult.
//        this.db_id = db_id;
//    }
//
//    public Double getConfidence() {
//        return confidence;
//    }
//
//    public void setConfidence(Double confidence) {
//        this.confidence = confidence;
//    }
//
//    public String getValue() throws SQLException {
//        Connection connection = DriverManager.getConnection(getJdbcUrl(),getJdbcUser(),getJdbcPassword());
//        String selectInputs = String.format("""
//                SELECT
//                    %s
//                from %s
//                where %s = '%s'
//
//
//                """, new TAB_inputs().getCOL_value().getName(), new TAB_inputs().getMaName() + "." + new TAB_inputs().getName(), new TAB_inputs().getCOL_db_id().getName(), this.db_id
//        );
//        QueryResult queryResult = QueryResult.getQueryResultObj(selectInputs,connection);
//        return String.valueOf(queryResult.safeGetRow("col1", 0));
//    }
//
//
//
//
//
//
//
//    @Override
//    public String toString() {
//        return "VectorQueryResult{" +
////                    "value= " + getValue() + '\'' +
//                "\n\t\t, confidence=" + confidence +
//                '}';
//    }
//}
