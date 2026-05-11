package org.example.ai.registry.parse;

import org.example.ai.AiRagSchemaJson;
import org.example.ai.PythonService;

import java.util.function.Function;

public class MainParseFileMethod extends ParseFileMethod{


    protected MainParseFileMethod() {
        super(
                (inputs)->{
                    try{
                        return    PythonService.parseFile(inputs.getFiles(),inputs.getUploadGroup(),inputs.getPrompt(),inputs.getMimeType());
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }

                });

    }
}
