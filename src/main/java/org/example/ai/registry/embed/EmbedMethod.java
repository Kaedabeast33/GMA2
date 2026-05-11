package org.example.ai.registry.embed;

import java.io.File;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class EmbedMethod {

    protected Function<String, Double[]> embedMethod;
    protected Function<EmbedFileObject, Double[]> embedFileMethod;
    protected Supplier<Boolean> checkMethod;

    private final ConcurrentHashMap<String, Future<?>> taskQueue;
    private final ExecutorService executorService;

    protected EmbedMethod(
            Function<String, Double[]> embedMethod,
            Function<EmbedFileObject, Double[]> embedFileMethod,
            Supplier<Boolean> checkMethod
    ) {
        this.taskQueue = new ConcurrentHashMap<>();
        this.executorService = Executors.newFixedThreadPool(1);

        this.embedMethod = embedMethod;
        this.embedFileMethod = embedFileMethod;
        this.checkMethod = checkMethod;
    }

    public Future<Double[]> runEmbed(String input) throws ExecutionException, InterruptedException {
        if(!runCheck().get()) throw new RuntimeException("Check failed");
        String uuid  = java.util.UUID.randomUUID().toString();
        Future<Double[]> future = executorService.submit(() -> embedMethod.apply(input));
        taskQueue.put("embed_"+uuid, future);
        return future;
    }

    public Future<Double[]> runEmbedFile(File file, String fileType) throws ExecutionException, InterruptedException {
        if(!runCheck().get()) throw new RuntimeException("Check failed");
        EmbedFileObject obj = new EmbedFileObject(file, fileType);
        String uuid  = java.util.UUID.randomUUID().toString();
        Future<Double[]> future = executorService.submit(() -> embedFileMethod.apply(obj));
        taskQueue.put("embed_file_"+uuid, future);
        return future;
    }

    public Future<Boolean> runCheck() {
        String uuid  = java.util.UUID.randomUUID().toString();
        Future<Boolean> future = executorService.submit(checkMethod::get);
        taskQueue.put("check_"+uuid, future);
        return future;
    }
}
