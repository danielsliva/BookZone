package org.bookzone.core.security.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({TYPE,ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch {
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String first();
    String second();

    @Target({TYPE,ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List
    {
        FieldMatch[] value();
    }
}
