package com.project.Investment.App.service;

import com.project.Investment.App.dao.PerfAggregateDao;

import java.util.List;

public interface PerfAggregateService {
    PerfAggregateDao findById (Integer id);
    List<PerfAggregateDao> findAll();
    Integer getCountPerfAggregate();
}
