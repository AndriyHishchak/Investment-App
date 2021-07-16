package com.project.Investment.App.dto.validator;

import com.project.Investment.App.dto.EntityDtoRequest;
import com.project.Investment.App.model.enam.EntityType;
import com.project.Investment.App.service.EntityService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class UniqueDefaultBenchmarkIdValidator implements ConstraintValidator<UniqueDefaultBenchmarkId, EntityDtoRequest> {

    private final EntityService entityService;

    public UniqueDefaultBenchmarkIdValidator(EntityService entityService) {
        this.entityService = entityService;
    }

    @Override
    public boolean isValid(EntityDtoRequest value, ConstraintValidatorContext context) {
        if (value.getEntityType() == EntityType.FUND && entityService.findById(value.getDefaultBenchmarkId(), null, null).isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{value.notEmpty}")
                    .addConstraintViolation();
            return false;
        } else if (value.getEntityType() == EntityType.BENCHMARK && value.getDefaultBenchmarkId() != null) {

            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{defaultBenchmarkId.notSupport}")
                    .addConstraintViolation();
            return false;
        } else {
            return true;
        }
    }
}
