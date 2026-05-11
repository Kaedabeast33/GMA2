package org.example.ai.registry.skeleton;

import org.example.ai.registry.embed.EmbedMethod;

public enum MetaSkeletonUpload {
    MAIN_SKELETON_UPLOAD("main_skeleton_upload",new MainSkeletonUploadMethod());

    final String methodName;
    final SkeletonUploadMethod skeletonUploadMethod;

    MetaSkeletonUpload(String methodName, SkeletonUploadMethod skeletonUploadMethod) {
        this.methodName = methodName;
        this.skeletonUploadMethod = skeletonUploadMethod;
    }

    public String getMethodName() {
        return methodName;
    }

    public SkeletonUploadMethod getSkeletonUploadMethod() {
        return skeletonUploadMethod;
    }
}
