package org.example.Annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
public @interface KdbTrigger {

    String name();

    String description();

    String[] tags() default {};

    String triggerType() ;



    String[] triggerSet();

}
