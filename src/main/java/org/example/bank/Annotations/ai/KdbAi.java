package org.example.bank.Annotations.ai;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface KdbAi {
    String name();

    String[] tags() default {};

    String description();
    String [] uploadTypes() default {"inputs","raw_inputs","raw"};
    String path() default "";




}
