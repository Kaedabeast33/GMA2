package org.example.bank.Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface KdbTable {
    String description();

    int autoIncrementStart() default 1;

    String name();

    String[] tags() default {};

    String type();

    String uploadType() default "default";

    String airtableId() default "";
}
