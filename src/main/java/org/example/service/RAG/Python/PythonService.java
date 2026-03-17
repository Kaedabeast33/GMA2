package org.example.service.RAG.Python;

import com.google.gson.Gson;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PythonService {

    public boolean checkConnection() throws IOException {
        String url = "http://localhost:5000";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.err.println("[WARN] Embedding service returned non-success: " + response.code());
                throw new RuntimeException("Embedding service is not available");
            }

        }catch (Exception e){
//            System.err.println("[ERROR] Failed to connect to embedding service: " + e.getMessage());
            throw new RuntimeException("Embedding service is not available");
        }
            return true;
    }

    public static Double[] makeEmbedding(String text) {
        // Dummy implementation for embedding generation
        // In a real scenario, this would call a Python service or library to generate embeddings
//        Double[] embedding = new Double[1536]; // Assuming 768-dimensional embeddings
//        for (int i = 0; i < embedding.length; i++) {
//            embedding[i] = Math.random(); // Replace with actual embedding values
//        }
//        return embedding;


        String url = "http://localhost:5000";
//        @Todo change to config file or application.properties
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        ----------------- make embedding string

        Map<String, String> payload1 = new HashMap<>();
        payload1.put(
                "system_prompt",
                "You generate clean, clinical embedding text. " +
                        "Return ONLY the embedding text. " +
                        "Do not include explanations, markdown, JSON, or labels."
        );

        payload1.put(
                "user_prompt",
                "Convert the following structured lab data into a concise, " +
                        "clinically meaningful narrative suitable for semantic embeddings:\n\n" +
                        text
        );

        String jsonPayload1 = gson.toJson(payload1);

        RequestBody body1 = RequestBody.create(JSON, jsonPayload1);
        Request request1 = new Request.Builder()
                .url(url+"/claude/simple_request")
                .post(body1)
                .build();

        String stringEmbedding;
        try(Response response = client.newCall(request1).execute()){
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String respBody = response.body() != null ? response.body().string() : null;
            if (respBody == null || respBody.isEmpty()) {
                throw new IOException("Empty response from embedding service");
            }
            System.out.println("Received embedding payload: " + respBody);
            stringEmbedding = respBody.trim();


        } catch (IOException e) {
            throw new RuntimeException("Failed to get embedding payload from service", e);
        }

//        -----------------


        Map<String, String> payload = new HashMap<>();
        payload.put("data", stringEmbedding);
        payload.put("input_type", "document");
        String jsonPayload = gson.toJson(payload);


        RequestBody body = RequestBody.create(JSON, jsonPayload);

        Request request = new Request.Builder()
                .url(url+"/embed")
                .post(body)
                .build();




        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
//                System.err.println("[WARN] Embedding service returned non-success: " + response.code());
//                return null;
            }
            String respBody = response.body() != null ? response.body().string() : null;
            if (respBody == null || respBody.isEmpty()) return null;

            // Expecting the Python service to return a JSON array of numbers, e.g. [0.1, 0.2, ...]
            return gson.fromJson(respBody, Double[].class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to get embedding from service", e);
        }
    }




}
