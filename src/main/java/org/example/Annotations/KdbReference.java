package org.example.Annotations;



import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface KdbReference {
    String[] referenceColumns();

    boolean isForeignKey() default true;

    String cascadeRule();

}
