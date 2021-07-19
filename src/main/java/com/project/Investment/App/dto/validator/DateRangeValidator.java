package com.project.Investment.App.dto.validator;

import com.project.Investment.App.dto.EntityDtoRequest;
import com.project.Investment.App.dto.FundCalculationRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<DateRange, FundCalculationRequest> {

    @Override
    public boolean isValid(FundCalculationRequest request, ConstraintValidatorContext context) {
        return request.getStartDate().isBefore(request.getAsOfDate());
    }
}
