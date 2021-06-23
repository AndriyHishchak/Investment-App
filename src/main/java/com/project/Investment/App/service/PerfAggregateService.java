package com.project.Investment.App.service;

import com.project.Investment.App.model.PerfAggregate;

import java.util.List;

public interface PerfAggregateService {
    PerfAggregate findById (Integer id);
    List<PerfAggregate> findAll();
    Integer getCountPerfAggregate();
}
