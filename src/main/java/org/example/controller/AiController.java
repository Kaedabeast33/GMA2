package org.example.controller;

import com.google.gson.Gson;
import org.example.service.RAG.Parser.AiUploadGroupInferenceService;
import org.example.service.RAG.Parser.ParserGroupJson.UploadGroupJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/gma/v1/ai")
public class AiController {

    @Autowired
    AiUploadGroupInferenceService service;
    @PostMapping("/uploadGroupInference")
    public ResponseEntity<String> uploadGroupInference() {
        return ResponseEntity.ok("Upload Group Inference processed successfully!");

    }

    @GetMapping("/getUploadGroups")
    public ResponseEntity<String> getUploadGroups() throws SQLException {
        String json  = service.getUploadGroups();
        return ResponseEntity.ok(json);
    }

    @GetMapping("/getPromptSchema")
    public ResponseEntity<String> getPromptSchema(@RequestParam Boolean onlyActive) {
        String schema = service.getPromptSchema(onlyActive);
        return ResponseEntity.ok(schema);
    }

    @PostMapping("/post/upload_group")
    public ResponseEntity<String> postUploadGroup(@RequestBody String json) throws Exception {
        Gson gson = new Gson();
        UploadGroupJson uploadGroupJson = gson.fromJson(json, UploadGroupJson.class);
        service.postUploadGroup(uploadGroupJson);

        return ResponseEntity.ok("Post Upload Group Inference processed successfully!");
    }
}
