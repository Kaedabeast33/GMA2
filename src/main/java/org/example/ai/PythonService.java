package org.example.ai;

import com.google.gson.Gson;

import okhttp3.*;

import org.example.bank.AppConfig;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpClient;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;

@Service
public class PythonService {



    private final ExecutorService llmExecutor = Executors.newCachedThreadPool();
    public static boolean checkConnection() throws IOException {
        String url = AppConfig.getPyServerUrl();
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



    public static AiRagSchemaJson dbSkeletonUpload(List<File> files, String uploadGroup, String mimeType,String fullName) throws IOException {
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("no files to upload");
        }

        String url = AppConfig.getPyServerUrl() + "/db_skeleton_upload";
        System.out.println(url);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(600, java.util.concurrent.TimeUnit.SECONDS)
                .writeTimeout(300, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(300, java.util.concurrent.TimeUnit.SECONDS)
                .build();

        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);






        try {
            for (File mf : files) {

                MediaType mediaType = MediaType.parse(mimeType);

                // create RequestBody from bytes (avoid calling transferTo on DTO)
                RequestBody fileBody = RequestBody.create(Files.readAllBytes(mf.toPath()), mediaType != null ? mediaType : MediaType.parse("application/octet-stream"));

                // IMPORTANT: use exact field name expected by FastAPI: 'files'
                multipartBuilder.addFormDataPart("files", mf.getName(), fileBody);

            }

            multipartBuilder.addFormDataPart("uploadGroup", uploadGroup);
            multipartBuilder.addFormDataPart("schema_name", fullName);

            RequestBody requestBody = multipartBuilder.build();

            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .addHeader("Accept", "application/json")
                    .build();

            Gson gson = new Gson();

            try (Response response = client.newCall(request).execute()) {
                String respBody = response.body() != null ? response.body().string() : "";

                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response.code() + " body=" + respBody);
                }

                System.out.println("Response: " + respBody);
                return gson.fromJson(respBody, AiRagSchemaJson.class);
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Failed to upload files to embedding service", e);
        }
    }


    public static AiRagSchemaParse parseFile(List<File> files, String uploadGroup, String promptText, String mimeType) throws IOException {
        Gson gson = new Gson();
        if (files == null || files.isEmpty()) {
            throw new IllegalArgumentException("no files to upload");
        }

        String url = AppConfig.getPyServerUrl() + "/parse_file_group";

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(600, java.util.concurrent.TimeUnit.SECONDS)
                .writeTimeout(700, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(600, java.util.concurrent.TimeUnit.SECONDS)
                .build();

        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        // Ensure mimeType is never null when passed to MediaType.parse
        String effectiveMime = (mimeType == null || mimeType.isBlank()) ? "application/octet-stream" : mimeType;

        for (File file : files) {
            String filename = file.getName();

            MediaType mediaType = MediaType.parse(effectiveMime);
            RequestBody fileBody = RequestBody.create(Files.readAllBytes(file.toPath()), mediaType != null ? mediaType : MediaType.parse("application/octet-stream"));

            multipartBuilder.addFormDataPart("files", filename, fileBody);
        }

        System.out.println(promptText+"promptText");
        System.out.println(uploadGroup+"uploadGroup");
        multipartBuilder.addFormDataPart("promptText", promptText);
        multipartBuilder.addFormDataPart("uploadGroup", uploadGroup == null ? "" : uploadGroup);

        RequestBody requestBody = multipartBuilder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            String respBody = response.body() != null ? response.body().string() : "";
            if (respBody.startsWith("\"") && respBody.endsWith("\"")) {
                try {
                    respBody = gson.fromJson(respBody, String.class);
                    System.out.println("Unquoted inner JSON payload length: " + (respBody == null ? 0 : respBody.length()));
                } catch (Exception e) {
                    System.err.println("Failed to unquote JSON string response: " + e.getMessage());
                    throw new IOException("Invalid JSON string response", e);
                }
            }
            System.out.println("parseFile response code: " + response.code());
            System.out.println("parseFile response body: " + respBody);
            if (!response.isSuccessful()) {
                System.err.println("Vyta upload failed: code=" + response.code() + " body=" + respBody);
                throw new IOException("Unexpected code " + response.code() + " " + response.message() + " body=" + respBody);
            }
            return new Gson().fromJson(respBody, AiRagSchemaParse.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to parse files with embedding service", e);
        }
    }

    // java
    public static Double[] makeEmbeddingFile(File file, String fileType) throws IOException {
        String url = AppConfig.getPyServerUrl();



        System.out.println(file.toPath());
        System.out.println(Files.size(file.toPath()));
        fileType = (fileType == null ? "" : fileType.replace(".", ""));
        if (fileType.isBlank()) fileType = "bin";

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(600, TimeUnit.SECONDS)
                .writeTimeout(600, TimeUnit.SECONDS)
                .callTimeout(900, TimeUnit.SECONDS)
                .build();

        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        String original = Objects.requireNonNull(file.getName(), "filename");
        String guessed = java.net.URLConnection.guessContentTypeFromName(original);
        MediaType mediaType = MediaType.parse(guessed != null ? guessed : "application/octet-stream");
        RequestBody fileBody = RequestBody.create(file, mediaType != null ? mediaType : MediaType.parse("application/octet-stream"));
        multipartBuilder.addFormDataPart("file", original, fileBody);
        multipartBuilder.addFormDataPart("file_type", fileType);



        RequestBody requestBody = multipartBuilder.build();

        Request request = new Request.Builder()
                .url(url + "/embedFile")
                .post(requestBody)
                .header("Accept", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            int code = response.code();
            String respBody = null;
            if (response.body() != null) {
                respBody = response.body().string();
            }
            System.out.println("embedFile response code: " + code);
            System.out.println("embedFile response body: " + respBody);

            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response + (respBody == null ? "" : " body=" + respBody));
            }
            Gson gson = new Gson();
            return gson.fromJson(respBody, Double[].class);
        }
    }



    // java
    public static Double[] makeEmbedding(String text) {
        String url = AppConfig.getPyServerUrl();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(600, TimeUnit.SECONDS)   // increase read timeout for debugging
                .writeTimeout(600, TimeUnit.SECONDS)
                .callTimeout(900, TimeUnit.SECONDS)   // overall call timeout
                .build();
        Gson gson = new Gson();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        // Build first request payload
        Map<String, String> payload1 = new HashMap<>();
        payload1.put("system_prompt",
                "You generate clean, clinical embedding text. Return ONLY the embedding text. Do not include explanations, markdown, JSON, or labels.");
        payload1.put("user_prompt",
                "Convert the following structured lab data into a concise, clinically meaningful narrative suitable for semantic embeddings:\n\n" + text);
        String jsonPayload1 = gson.toJson(payload1);

        RequestBody body1 = RequestBody.create(JSON, jsonPayload1);
        Request request1 = new Request.Builder()
                .url(url + "/claude/simple_request")
                .post(body1)
                .build();

        String stringEmbedding;
        long start = System.nanoTime();
        System.out.println("[" + Instant.now() + "] Sending simple_request to " + url + "/claude/simple_request");
        try (Response response = client.newCall(request1).execute()) {
            long tookMs = (System.nanoTime() - start) / 1_000_000;
            System.out.println("[" + Instant.now() + "] simple_request returned in " + tookMs + " ms, code=" + response.code());
            String respBody = response.body() != null ? response.body().string() : null;
            System.out.println("[" + Instant.now() + "] simple_request body length: " + (respBody == null ? 0 : respBody.length()));
            if (respBody == null || respBody.isEmpty()) {
                System.out.println("[" + Instant.now() + "] simple_request returned empty body, throwing");
                throw new IOException("Empty response from embedding service");
            }
            stringEmbedding = respBody.trim();
            System.out.println("[" + Instant.now() + "] Processed embedding string (first 200 chars): " +
                    (stringEmbedding.length() > 200 ? stringEmbedding.substring(0, 200) + "..." : stringEmbedding));
        } catch (IOException e) {
            long totalMs = (System.nanoTime() - start) / 1_000_000;
            System.out.println("[" + Instant.now() + "] simple_request failed after " + totalMs + " ms");
            e.printStackTrace();
            throw new RuntimeException("Failed to get embedding payload from service", e);
        }

        // Build second request payload
        Map<String, String> payload = new HashMap<>();
        payload.put("data", stringEmbedding);
        payload.put("input_type", "document");
        String jsonPayload = gson.toJson(payload);
        System.out.println("[" + Instant.now() + "] Prepared /embed payload length: " + jsonPayload.length());

        RequestBody body = RequestBody.create(JSON, jsonPayload);
        Request request = new Request.Builder()
                .url(url + "/embed")
                .post(body)
                .build();

        start = System.nanoTime();
        System.out.println("[" + Instant.now() + "] Sending embed request to " + url + "/embed");
        try (Response response = client.newCall(request).execute()) {
            long tookMs = (System.nanoTime() - start) / 1_000_000;
            System.out.println("[" + Instant.now() + "] embed returned in " + tookMs + " ms, code=" + response.code());
            String respBody = response.body() != null ? response.body().string() : null;
            System.out.println("[" + Instant.now() + "] embed body length: " + (respBody == null ? 0 : respBody.length()));
            if (respBody == null || respBody.isEmpty()) {
                System.out.println("[" + Instant.now() + "] embed returned empty body");
                return null;
            }
            // Expecting the Python service to return a JSON array of numbers
            Double[] result = gson.fromJson(respBody, Double[].class);
            System.out.println("[" + Instant.now() + "] Parsed embedding length: " + (result == null ? 0 : result.length));
            return result;
        } catch (IOException e) {
            long totalMs = (System.nanoTime() - start) / 1_000_000;
            System.out.println("[" + Instant.now() + "] embed request failed after " + totalMs + " ms");
            e.printStackTrace();
            throw new RuntimeException("Failed to get embedding from service", e);
        }
    }

    public CompletableFuture<String> callSimpleRequest(String systemPrompt, String userPrompt,Integer tokens) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return simpleLLMRequest(systemPrompt,userPrompt,tokens);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        }, llmExecutor);
    }

    public static String simpleLLMRequest(String systemPrompt, String userPrompt,Integer tokens) throws IOException {
        if (systemPrompt == null ) {
            throw new IllegalArgumentException("System prompt cannot be null or empty");
        }
        String url = AppConfig.getPyServerUrl();
        Map<String, String> payload1 = new HashMap<>();
        Gson gson = new Gson();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");


        payload1.put(
                "system_prompt",
                systemPrompt
        );

        payload1.put(
                "user_prompt",
                userPrompt
        );
        payload1.put(
                "tokens",
                String.valueOf(tokens)
        );

        String jsonPayload1 = gson.toJson(payload1);
        RequestBody body = RequestBody.create(JSON, jsonPayload1);
        Request request = new Request.Builder()
                .url(url+"/claude/simple_request") // your endpoint
                .post(body)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(600, TimeUnit.SECONDS)   // increase read timeout for debugging
                .writeTimeout(600, TimeUnit.SECONDS)
                .callTimeout(900, TimeUnit.SECONDS)   // overall call timeout
                .build();

        try (Response resp = client.newCall(request).execute()) {
            if (!resp.isSuccessful()) throw new IOException("Unexpected code " + resp);
            return resp.body() != null ? resp.body().string() : "";
        }
    }









}
