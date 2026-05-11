package org.example.bank.Annotations.ai;

import org.example.bank.commonValues.UploadTypes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface KdbAi {
    String schema();
    String custom() default "";

    String[] tags() default {};

    String description();
    String [] uploadTypes() default {UploadTypes.INPUTS,UploadTypes.RAW_INPUTS,UploadTypes.RAW};


    String path() default "";




}
