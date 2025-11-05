package org.example.bank.Annotations;

public @interface KdbDependency {
    String[] referenceColumns() default {};
}
