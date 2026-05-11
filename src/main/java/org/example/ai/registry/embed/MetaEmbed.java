package org.example.ai.registry.embed;

import org.example.ai.PythonService;

import java.io.IOException;
import java.util.function.Supplier;

public enum MetaEmbed {


//    MAIN_EMBED("main_embed",
    MAIN_EMBED("main_embed",new MainEmbedMethod());


    final String methodName;
    final EmbedMethod embedMethod;




    MetaEmbed(String methodName,EmbedMethod embedMethod) {
        this.methodName = methodName;
        this.embedMethod = embedMethod;


    }

    public String getMethodName() {
        return methodName;
    }

    public EmbedMethod getEmbedMethod() {
        return embedMethod;
    }









}
