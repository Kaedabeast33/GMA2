package org.example.controller.body;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class DbJsonBody {

    List<MultipartFile> files;
     Integer clientId;
     String uploadGroup;
     Double[] vector;

    public  List<MultipartFile> getFiles() {
        return files;
    }

    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getUploadGroup() {
        return uploadGroup;
    }

    public void setUploadGroup(String uploadGroup) {
        this.uploadGroup = uploadGroup;
    }

    public Double[] getVector() {
        return vector;
    }

    public void setVector(Double[] vector) {
        this.vector = vector;
    }
}
