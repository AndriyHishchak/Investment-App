package com.project.Investment.App.service;

import com.project.Investment.App.DTO.EntityDto;
import com.project.Investment.App.DTO.PerfAggregateDto;
import com.project.Investment.App.model.PerfAggregate;

import java.util.List;

public interface PerfAggregateService {
    PerfAggregateDto findById (Integer id);
    List<PerfAggregateDto> findAll();
    Integer getCountPerfAggregate();
}
