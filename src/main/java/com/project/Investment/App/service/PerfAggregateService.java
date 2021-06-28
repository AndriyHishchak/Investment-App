package com.project.Investment.App.service;

import com.project.Investment.App.dto.PerfAggregateRequest;
import com.project.Investment.App.model.PerfAggregate;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;


public interface PerfAggregateService {
    PerfAggregate findById (Integer id);
    List<PerfAggregate> findAll();
    Integer getCountPerfAggregate();
    boolean isPerfAggregateIdExists(Integer id);
    List<PerfAggregate> getPerfAggregateDateRange(LocalDate startDate, LocalDate effectiveDate);
    void save (PerfAggregateRequest perfAggregateRequest);
}
