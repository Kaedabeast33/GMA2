//
//package org.example.controller;
//
//import com.google.gson.Gson;
//import org.example.controller.body.DbJsonBody;
//import org.example.service.RAG.Parser.AiUploadGroupInferenceService;
//import org.example.service.RAG.Parser.ParserGroupJson.UploadGroupJson;
//
//import org.example.service.RAG.upload.UploadService;
//import org.springframework.http.RequestEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.io.File;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.StandardCopyOption;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@RestController
//@RequestMapping("/gma/v1/upload")
//public class UploadController {
//
//    private final UploadService service;
//
//    @Autowired
//    AiUploadGroupInferenceService aiService;
//
//    @Autowired
//    public UploadController(UploadService service) {
//        this.service = service;
//    }
//
//
//
//
//    @PostMapping("/serverFiles")
//    public String uploadFileToServer(
//            @RequestParam List<MultipartFile> files,
//            @RequestParam Integer clientId,
//            @RequestParam String uploadGroup,
//            @RequestParam Double[] vector,
//            @RequestParam String fileId
//
//            ) throws Exception {
//
//
//
//        List<File> tempFiles = new ArrayList<>();
//        try {
//            // create a temp file for the vector and write CSV (or empty) into it
//            File vectorFile = File.createTempFile("vector", ".txt");
//            String csv = "";
//            if (vector != null && vector.length > 0) {
//                csv = Arrays.stream(vector)
//                        .map(String::valueOf)
//                        .collect(java.util.stream.Collectors.joining(","));
//            }
//            Files.writeString(vectorFile.toPath(), csv);
//            tempFiles.add(vectorFile);
//
//            if (files != null) {
//                for (MultipartFile mf : files) {
//                    if (mf == null || mf.isEmpty()) continue;
//                    String original = mf.getOriginalFilename()!= null? mf.getOriginalFilename() : "uploaded_file";
//                    String suffix = (original == null) ? null : original.replaceAll("[^A-Za-z0-9\\.\\-]", "_");
//
//                    File temp = File.createTempFile(original, (suffix == null ? null : "-" + suffix));
//                    try (InputStream is = mf.getInputStream()) {
//                        Files.copy(is, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
//                    }
//                    tempFiles.add(temp);
//                }
//            }
//
//            // upload all temp files (including the vector file)
//            service.writeReportsClients(tempFiles, clientId, uploadGroup, "reports", "clients",fileId);
//
//        } finally {
//            // cleanup temp files
//            for (File f : tempFiles) {
//                try {
//                    Files.deleteIfExists(f.toPath());
//                } catch (Exception ignored) {}
//            }
//        }
//
//        return "File uploaded successfully!";
//    }
//
//
//    @PostMapping("/dbJson")
//    public ResponseEntity<String> uploadFileToServer(
//            @RequestParam String json,
//            @RequestParam Integer clientId,
//            @RequestParam String uploadGroup,
//            @RequestParam Double[] vector,
//            @RequestParam String fileId,
//            @RequestParam String fileName
//            ) throws Exception {
//
//        service.reloadRawInputs(clientId, json, uploadGroup,vector,fileId,fileName);
//        return ResponseEntity.ok ("File uploaded successfully!");
//
//    }
//
//    @PostMapping("/dbSkeleton")
//    public ResponseEntity<String> postUploadGroup(@RequestBody String json,@RequestParam String uploadName) throws Exception {
//        Gson gson = new Gson();
//        UploadGroupJson uploadGroupJson = gson.fromJson(json, UploadGroupJson.class);
//        aiService.postUploadGroup(uploadGroupJson,uploadName);
//
//        return ResponseEntity.ok("Post Upload Group Inference processed successfully!");
//    }
//
//
//}
