package org.example.ai.registry.embed;

import java.io.File;

public class EmbedFileObject{
    private final File file;
    private final String fileType;

    public EmbedFileObject(File file, String fileType) {
        this.file = file;
        this.fileType = fileType;
    }

    public File getFile() {
        return file;
    }

    public String getFileType() {
        return fileType;
    }
}