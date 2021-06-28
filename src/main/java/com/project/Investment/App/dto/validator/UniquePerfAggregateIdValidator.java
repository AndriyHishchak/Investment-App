package com.project.Investment.App.dto.validator;

import com.project.Investment.App.service.PerfAggregateService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePerfAggregateIdValidator implements ConstraintValidator<UniquePerfAggregateId,Integer> {

    private final PerfAggregateService perfAggregateService;

    public UniquePerfAggregateIdValidator(PerfAggregateService perfAggregateService) {
        this.perfAggregateService = perfAggregateService;
    }


    @Override
    public boolean isValid(Integer perfAggregateId, ConstraintValidatorContext context) {
        return perfAggregateService.isPerfAggregateIdExists(perfAggregateId);
    }
}
