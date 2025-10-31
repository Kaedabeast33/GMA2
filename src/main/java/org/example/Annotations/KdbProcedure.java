package org.example.Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface KdbProcedure {
    String name();

    String description();

    String[] tags() default{};



    String[] procedureGroups() default {};

    int[] procedureGroupsOrder() default {};

    String[] procedureSet() default {};
}
