package org.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/gma/v1/upload")
public class UploadController {
    @PostMapping("/fake")
    public String uploadFileFake() {
        String location = "/path/to/fake/file.txt";
        String client_id = "0";
        String system_source = "file_upload";
        String raw_input_name = "";


        return "File uploaded successfully!";
    }
}
