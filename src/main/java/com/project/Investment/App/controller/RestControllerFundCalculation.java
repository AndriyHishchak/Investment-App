package com.project.Investment.App.controller;

import com.project.Investment.App.dto.FundCalculationRequest;
import com.project.Investment.App.dto.FundCalculationResponse;
import com.project.Investment.App.dto.validator.groupSequence.OrderedChecks;
import com.project.Investment.App.model.Entity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/fund")
public class RestControllerFundCalculation {

    @PostMapping()
    public ResponseEntity<FundCalculationResponse> getFundReturn(@Validated(OrderedChecks.class) @RequestBody FundCalculationRequest request) {

        FundCalculationResponse response =
                new FundCalculationResponse(
                        request.getEntityId(),
                        request.getStartDate(),
                        request.getAsOfDate(),
                        "TOTAL",
                        1.000456);

        return ResponseEntity.created(URI.create("/v1/fund/" + response.getEntityId())).build();
    }
}
