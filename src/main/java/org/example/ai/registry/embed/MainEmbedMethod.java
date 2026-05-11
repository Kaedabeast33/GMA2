package org.example.ai.registry.embed;

import org.example.ai.PythonService;

import java.io.IOException;
import java.util.function.Function;
import java.util.function.Supplier;

public class MainEmbedMethod extends EmbedMethod {

    public MainEmbedMethod() {
        super(
                PythonService::makeEmbedding,
                fileObj -> {
                    try {
                        return PythonService.makeEmbeddingFile(
                                fileObj.getFile(),
                                fileObj.getFileType()
                        );
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
