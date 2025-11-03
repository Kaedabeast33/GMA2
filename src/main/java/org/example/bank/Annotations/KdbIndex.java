package org.example.bank.Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface KdbIndex {

    String[] indexGroups() default {};

    int[] order() default 0;
}
