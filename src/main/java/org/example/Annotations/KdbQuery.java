package org.example.Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;

@Retention(RetentionPolicy.RUNTIME)
public @interface KdbQuery {
    String name();

    String description();

    String[] tags() default {};
    
    String queryType();

    String[] queryGroups() default {};

    int[] queryGroupsOrder() default {1};



    String[] querySet() default {};


}
