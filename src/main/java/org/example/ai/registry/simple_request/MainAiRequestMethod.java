package org.example.ai.registry.simple_request;

import org.example.ai.PythonService;

import java.io.IOException;
import java.util.function.Function;
import java.util.function.Supplier;

public class MainAiRequestMethod extends AiRequestMethod {

    public MainAiRequestMethod() {
        super((input)->{
            try {
                return PythonService.simpleLLMRequest(input.userPrompt, input.systemPrompt, input.tokens);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, ()->{
            try{
                return PythonService.checkConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
