package com.project.Investment.App.dto.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniquePerfAggregateIdValidator.class)
public @interface UniquePerfAggregateId {
    String message() default "Value is present in DB";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
