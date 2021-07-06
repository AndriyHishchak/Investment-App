package com.project.Investment.App.dto.validator;

import com.project.Investment.App.repository.EntityRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class UniqueDefaultBenchmarkIdValidator implements ConstraintValidator<UniqueDefaultBenchmarkId,String> {

  private final EntityRepository repository;

    public UniqueDefaultBenchmarkIdValidator(EntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(String defaultBenchmarkId, ConstraintValidatorContext context) {
        return repository.existsEntityByDefaultBenchmarkId(defaultBenchmarkId);
    }
}
