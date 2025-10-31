package org.example.Annotations;

import org.example.CommonValues.DefaultKdbConverter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.UUID;

@Retention(RetentionPolicy.RUNTIME)
public @interface KdbColumn {
    String name();

    String description() default "";

    String[] tags() default {};

    String type() default "default";

    boolean isNullable() default true;

    boolean isEditable() default true;

    String[] columnGroupNames() default {};

    boolean unique() default false;

    boolean uniqueIdentifier() default false;

    String[] uniqueIdenftifierGroupNames() default {};

    boolean isRequired() default false;

    String defaultValue() default "";

    int[] uniqueIdentifierGroupNames() default {};

    String id ="col"+ UUID.randomUUID();

    Class<?> converter() default DefaultKdbConverter.class;



}
