package org.example.bank.Annotations.ai;

import org.example.bank.KdbConverter.DefaultKdbConverter;
import org.example.bank.commonValues.UploadTypes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.UUID;

@Retention(RetentionPolicy.RUNTIME)
public @interface KdbAiColumn {

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

    String id = "col" + UUID.randomUUID();

    String[] uploadTypes() default {UploadTypes.RAW, UploadTypes.INPUTS, UploadTypes.RAW_INPUTS};

    Class<?> converter() default DefaultKdbConverter .class;
}
