package org.example.Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface  KdbCustomContraint {
    String name();
    String description() default "";
    String[] tags() default {};
    String constraintType() default "";
    String constraint();


}
