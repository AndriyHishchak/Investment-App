package com.project.Investment.App.dto.validator;

import com.project.Investment.App.service.PerfAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePerfAggregateIdValidator implements ConstraintValidator<UniquePerfAggregateId,Integer> {


    private final PerfAggregateService perfAggregateService;

    @Autowired
    public UniquePerfAggregateIdValidator( @Qualifier ("perfAggregateServiceJdbcTemplate") PerfAggregateService perfAggregateService) {
        this.perfAggregateService = perfAggregateService;
    }

    @Override
    public boolean isValid(Integer perfAggregateId, ConstraintValidatorContext context) {
        return  !perfAggregateService.existsEntityByPerfAggregateId(perfAggregateId);
    }
}
