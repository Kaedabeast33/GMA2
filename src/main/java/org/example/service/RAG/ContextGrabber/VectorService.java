package org.example.service.RAG.ContextGrabber;//package org.example.service.RAG.ContextGrabber;
//
//import com.google.gson.Gson;
////import okhttp3.*;
////import com.chipr.APP.schemas.vyta.client.inputs.TAB_inputs;
////import com.chipr.APP.schemas.vyta.client.raw_inputs.TAB_raw_inputs;
//import okhttp3.*;
//import com.chipr.APP.schemas.gma.client.inputs.TAB_inputs;
//import com.chipr.APP.schemas.gma.client.raw_inputs.TAB_raw_inputs;
//import org.example.ClassOutputCreator.templates.TableTemplate;
//import org.example.bank.OutputClassBank.QueryResult;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//
//import javax.management.Query;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.*;
//
//import static org.example.bank.commonValues.AppConfig.*;
//
//@Service
//public class VectorService {
//
//
//
//    private final OkHttpClient client = new OkHttpClient();
//    private final Gson gson = new Gson();
//    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//    private static final String EMBED_URL = "http://127.0.0.1:5000/embed/find_match";
//
//
//
//    public VectorQueryResultWrapper getConfidentDbIds(
//            Map<String, float[]> dbidVectorMap,
//            String query) {
//
//        if (dbidVectorMap == null || dbidVectorMap.isEmpty() || query == null) {
//            return new VectorQueryResultWrapper(Collections.emptyList()) ;
//        }
//
//        // 🔥 DO NOT stringify dbidVectorMap
//        Map<String, Object> payload = new HashMap<>();
//        payload.put("dbid_dict", dbidVectorMap); // ✅ real object
//        payload.put("query", query);
//
//        String requestJson = gson.toJson(payload);
//        RequestBody body = RequestBody.create(requestJson, JSON);
//
//        Request request = new Request.Builder()
//                .url(EMBED_URL)
//                .post(body)
//                .build();
//
//        System.out.println("Sending request to embedding service with payload:");
//
//        System.out.println(query);
//        try (Response response = client.newCall(request).execute()) {
//
//            if (!response.isSuccessful()) {
//                System.out.println("Failed to get response. HTTP " + response.code());
//                return new VectorQueryResultWrapper(Collections.emptyList()) ;
//            }
//
//            String respBody = response.body() != null ? response.body().string() : null;
//            if (respBody == null || respBody.isEmpty()) {
//                return new VectorQueryResultWrapper(Collections.emptyList()) ;
//            }
//
//            VectorQueryResult[] results =
//                    gson.fromJson(respBody, VectorQueryResult[].class);
//
//            VectorQueryResultWrapper v =  results == null
//                    ? new VectorQueryResultWrapper(Collections.emptyList())
//                    : new VectorQueryResultWrapper(Arrays.asList(results)) ;
//            System.out.println(v.toString());
//            return  v;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new VectorQueryResultWrapper(Collections.emptyList()) ;
//        }
//    }
//
//
//    public Map<String,float[]> makeDbidVectorMap(String query) throws SQLException {
//        System.out.println(query);
//        Connection connection = DriverManager.getConnection(getJdbcUrl(), getJdbcUser(), getJdbcPassword());
//        QueryResult qr = QueryResult.getQueryResultObj(query,connection);
//
//
//        Map<String, float[]> dbidVectorMap = new HashMap<>();
////        System.out.println("QueryResult data: " + qr.getData());
//        List<Object> dbids = qr.getData().get("col1");
//        List<Object> vectors = qr.getData().get("col2");
////        System.out.println("dbids: " + dbids);
////        System.out.println("vectors: " + vectors);
//
//        if (dbids == null || vectors == null || dbids.size() != vectors.size()) {
//            System.out.println("Invalid query result: dbids or vectors are null, or their sizes do not match");
//            return Collections.emptyMap();
//        }
//        for(int i = 0; i < qr.getResultSize(); i++) {
//            String dbid = String.valueOf(dbids.get(i));
//            float[] vector = vectors.get(i) instanceof float[] ? (float[]) vectors.get(i) : null;
//
//
//            dbidVectorMap.put(dbid, vector);
//        }
////        System.out.println(dbidVectorMap);
//        return dbidVectorMap;
//
//
//
//    }
//
//    public void queryVectorSearch(String query, int clientId) throws SQLException {
//        VectorQueryResultWrapper rawInputDb =  getRawInputsVectorQueryResults(query,clientId);
//        VectorQueryResultWrapper inputDb = getInputsVectorQueryResults(query,clientId);
//
////        String selectRawInputs = String.format("""
////                SELECT
////                    raw_input_json
////                from %s
////                where %s IN ('%s')
////
////
////                """, new TAB_raw_inputs().getMaName() + "." + new TAB_raw_inputs().getName(), new TAB_raw_inputs().getCOL_db_id().getName(), String.join("','", extractDbIds(rawInputDb,0.66))
////        );
//
//        String selectInputs = String.format("""
//                SELECT
//                    value
//                from %s
//                where %s IN ('%s')
//
//
//                """, new TAB_inputs().getMaName() + "." + new TAB_inputs().getName(), new TAB_inputs().getCOL_db_id().getName(), String.join("','", extractDbIds(inputDb,0.66))
//        );
//
//        Connection connection = DriverManager.getConnection(getJdbcUrl(), getJdbcUser(), getJdbcPassword());
////        QueryResult rawInputsResult = QueryResult.getQueryResultObj(selectRawInputs, connection);
////        System.out.println(rawInputsResult.getData());
//
//        QueryResult inputsResult = QueryResult.getQueryResultObj(selectInputs, connection);
//        System.out.println(inputsResult.getData());
//    }
//
//    private List<String> extractDbIds(VectorQueryResultWrapper rawInputDb,Double confidenceThreshold) {
//        List<String> dbIds = new ArrayList<>();
//        for (VectorQueryResult result : rawInputDb.getListInOrder().getList()) {
//            if(result.getConfidence()>= confidenceThreshold)
//            dbIds.add(result.getDb_id());
//        }
//        return dbIds;
//    }
//
//    public VectorQueryResultWrapper getRawInputsVectorQueryResults(String query, int clientId) throws SQLException {
//
//        TAB_raw_inputs ri = new TAB_raw_inputs();
//        String raw_inputs = String.format("""
//                SELECT
//                    %s
//                from %s
//                where %s = '%s'
//
//
//                """, String.join(",", List.of(ri.getCOL_db_id().getName(), ri.getCOL_db_embedding().getName())), ri.getMaName() + "." + ri.getName(), ri.getCOL_client_id().getName(),clientId
//
//        );
//
//
//        Map<String, float[]> vectorMap = makeDbidVectorMap(raw_inputs);
//        VectorQueryResultWrapper confidenceResult = getConfidentDbIds(vectorMap, query);
//        System.out.println(confidenceResult);
//        return confidenceResult;
//    }
//
//
//
//    public VectorQueryResultWrapper getInputsVectorQueryResults(String query, int clientId) throws SQLException {
//
//        TAB_inputs ri = new TAB_inputs();
//        String inputs = String.format("""
//                SELECT
//                    %s
//                from %s
//                where %s = '%s'
//
//
//                """, String.join(",", List.of(ri.getCOL_db_id().getName(), ri.getCOL_db_embedding().getName())), ri.getMaName() + "." + ri.getName(), ri.getCOL_client_id().getName(),clientId
//
//        );
//
//
//        Map<String, float[]> vectorMap = makeDbidVectorMap(inputs);
//        VectorQueryResultWrapper confidenceResult = getConfidentDbIds(vectorMap, query);
////        System.out.println(confidenceResult);
//        return confidenceResult;
//    }
//}
