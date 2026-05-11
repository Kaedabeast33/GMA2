package org.example.ai.registry.simple_request;

import java.util.HashMap;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AiRequestMethod {
    ConcurrentHashMap<String, Future<?>> taskQueue;
    ExecutorService executorService;

    protected Function<SimpleRequestObj,String> simpleRequestMethod;
    protected Supplier<Boolean> checkMethod;

    public AiRequestMethod(
            Function<SimpleRequestObj,String> simpleRequestMethod,
            Supplier<Boolean> checkMethod
    ) {
        this.taskQueue = new ConcurrentHashMap<>();
        this.executorService = Executors.newFixedThreadPool(1);
        this.simpleRequestMethod = simpleRequestMethod;
        this.checkMethod = checkMethod;
    }
    public boolean check(){
        return checkMethod.get();
    }
    public Future<Boolean> runCheck() {
        String uuid  = java.util.UUID.randomUUID().toString();
        Future<Boolean> future = executorService.submit(checkMethod::get);
        taskQueue.put("check_"+uuid, future);
        return future;
    }


    public Future<String> runSimpleRequest(String userPrompt,String systemPrompt,Integer tokens) throws ExecutionException, InterruptedException {
        if(!runCheck().get()) throw new RuntimeException("Check failed");
        SimpleRequestObj simpleRequestObj = new SimpleRequestObj(userPrompt, systemPrompt, tokens);
        String uuid  = java.util.UUID.randomUUID().toString();
        Future<String> future = executorService.submit(() -> simpleRequestMethod.apply(simpleRequestObj));
        taskQueue.put("simpleRequest_"+uuid, future);
        return future;

    }


    public static class SimpleRequestObj {
        public String userPrompt;
        public String systemPrompt;
        public Integer tokens;

        public SimpleRequestObj(String userPrompt, String systemPrompt, Integer tokens) {
            this.userPrompt = userPrompt;
            this.systemPrompt = systemPrompt;
            this.tokens = tokens;
        }

        public String getUserPrompt() {
            return userPrompt;
        }

        public void setUserPrompt(String userPrompt) {
            this.userPrompt = userPrompt;
        }

        public String getSystemPrompt() {
            return systemPrompt;
        }

        public void setSystemPrompt(String systemPrompt) {
            this.systemPrompt = systemPrompt;
        }

        public Integer getTokens() {
            return tokens;
        }

        public void setTokens(Integer tokens) {
            this.tokens = tokens;
        }
    }


}
