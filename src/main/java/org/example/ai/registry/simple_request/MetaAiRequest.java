package org.example.ai.registry.simple_request;

public enum MetaAiRequest {

    MAIN_AI_REQUEST("main_ai_request", new MainAiRequestMethod());

    final String methodName;
    final AiRequestMethod method;

        MetaAiRequest(String methodName, AiRequestMethod method) {
            this.methodName = methodName;
            this.method = method;
        }
}
