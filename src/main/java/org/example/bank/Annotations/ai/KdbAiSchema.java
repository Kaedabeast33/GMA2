package org.example.bank.Annotations.ai;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface KdbAiSchema {



    String description() default "";

    String[] tags() default {};


    String [] uploadTypes() default {"inputs","raw_inputs","raw"};



}
