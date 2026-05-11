package org.example.ai.registry.skeleton;

import org.example.ai.AiRagSchemaJson;
import org.example.ai.registry.AiMethod;
import org.example.ai.registry.embed.EmbedFileObject;

import java.io.File;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class SkeletonUploadMethod implements AiMethod {
    protected Function<DbSkeletonDto, AiRagSchemaJson> skeletonMethod;
    protected Supplier<Boolean> checkMethod;

    private final ConcurrentHashMap<String, Future<?>> taskQueue;
    private final ExecutorService executorService;

    protected SkeletonUploadMethod(
            Function<DbSkeletonDto, AiRagSchemaJson> skeletonMethod,
            Supplier<Boolean> checkMethod

    ) {
        this.taskQueue = new ConcurrentHashMap<>();
        this.executorService = Executors.newFixedThreadPool(1);

        this.skeletonMethod = skeletonMethod;
        this.checkMethod = checkMethod;
    }

    public Future<AiRagSchemaJson> runGetSkeleton(DbSkeletonDto input) throws ExecutionException, InterruptedException {
        if(!runCheck().get()) throw new RuntimeException("Check failed");
        String uuid  = java.util.UUID.randomUUID().toString();
        Future<AiRagSchemaJson> future = executorService.submit(() -> skeletonMethod.apply(input));
        taskQueue.put("embed_"+uuid, future);
        return future;
    }
    public Future<Boolean> runCheck() {
        String uuid  = java.util.UUID.randomUUID().toString();
        Future<Boolean> future = executorService.submit(checkMethod::get);
        taskQueue.put("check_"+uuid, future);
        return future;
    }

    @Override
    public void removeOldTasks(){

    }



    public static class DbSkeletonDto{
        List<File> files;
        String uploadGroup;
        String mimeType;
        String fullName;

         public DbSkeletonDto(List<File> files, String uploadGroup, String mimeType, String fullName) {
             this.files = files;
             this.uploadGroup = uploadGroup;
             this.mimeType = mimeType;
             this.fullName = fullName;
         }

        public String getMimeType() {
            return mimeType;
        }

        public void setMimeType(String mimeType) {
            this.mimeType = mimeType;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public List<File> getFiles() {
            return files;
        }

        public void setFiles(List<File> files) {
            this.files = files;
        }

        public String getUploadGroup() {
            return uploadGroup;
        }

        public void setUploadGroup(String uploadGroup) {
            this.uploadGroup = uploadGroup;
        }
    }
}
