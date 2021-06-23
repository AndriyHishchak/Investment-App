package com.project.Investment.App.dto.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDefaultBenchmarkIdValidator.class)
public @interface UniqueDefaultBenchmarkId {
    String message() default "Creating a record with unique Default Benchmark Id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
