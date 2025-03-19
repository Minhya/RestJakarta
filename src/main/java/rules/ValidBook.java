package rules;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = ValidBookValidator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBook {
    String message() default "Invalid book";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

