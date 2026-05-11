package org.example.ai.bank;

import com.google.gson.Gson;
import okhttp3.*;
import org.example.bank.OutputClassBank.QueryResult;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class ParseValue {

    String db_id;
    String name;
    String description;

    public ParseValue(String db_id, String name, String description) {
        this.db_id = db_id;
        this.name = name;
        this.description = description;
    }

    public ParseValue(QueryResult queryResult,String name) {
        if(queryResult.getResultSize()==1){
            System.out.println("QueryResult contains exactly one row, extracting values."+name);
            this.db_id = (String) queryResult.safeGetRow("db_id", 0);
            this.name = (String) queryResult.safeGetRow(name, 0);

            this.description = (String) queryResult.safeGetRow("description", 0);
        }else if(queryResult.getResultSize()==0){
            System.out.println("QueryResult contains no rows, creating new ParseValue with generated db_id and provided name."+name);
            this.db_id = UUID.randomUUID().toString();
            this.name = name;
            this.description = null;
        }
        else{
            throw new IllegalArgumentException("QueryResult contains multiple rows, expected only one.");
        }


    }

    public String rewriteDescription(List<String> descriptions,String name){
        String url = "http://localhost:5000/rewrite";
        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();
        String userPrompt = "Combine the two definitions of "+name+" into a single description: " + String.join("; ", descriptions);
        String systemPrompt = "You are a helpful assistant that combines multiple descriptions of a database column into one concise description. Focus on the most important details and avoid redundancy.";

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Map<String, Object> payload = new HashMap<>();
        payload.put("system_prompt", systemPrompt);
        payload.put("user_prompt", userPrompt);
        String jsonPayload = gson.toJson(payload);

        RequestBody body = RequestBody.create(JSON, jsonPayload);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body() != null ? response.body().string() : null;
        } catch (IOException e) {
            throw new RuntimeException("Failed to rewrite description", e);
        }

    }

    public String getDb_id() {
        return db_id;
    }

    public void setDb_id(String db_id) {
        this.db_id = db_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
