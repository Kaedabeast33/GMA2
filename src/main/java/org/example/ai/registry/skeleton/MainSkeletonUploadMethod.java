package org.example.ai.registry.skeleton;

import org.example.ai.PythonService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class MainSkeletonUploadMethod extends SkeletonUploadMethod{
    protected MainSkeletonUploadMethod() {
        super((dto) -> {
                    try {
                        return PythonService.dbSkeletonUpload(dto.getFiles(), dto.getUploadGroup(),dto.getMimeType(),dto.getFullName());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                },
                () -> {
                    try {
                        return PythonService.checkConnection();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
        );
    }


}
