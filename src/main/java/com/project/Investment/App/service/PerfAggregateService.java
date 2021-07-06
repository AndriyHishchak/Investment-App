package com.project.Investment.App.service;

import com.project.Investment.App.dto.PerfAggregateRequest;
import com.project.Investment.App.model.Entity;
import com.project.Investment.App.model.PerfAggregate;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface PerfAggregateService {
    PerfAggregate findById (Integer id);
    List<PerfAggregate> findAll();
    Integer findCountPerfAggregate();
    boolean existsEntityByPerfAggregateId(Integer id);
    PerfAggregate update(String id, PerfAggregateRequest updateAggregateRequest);
    List<PerfAggregate> findPerfAggregateDateRange(LocalDate startDate, LocalDate effectiveDate);
    List<PerfAggregate> findPerfAggregateDateRangeAndEntityId(LocalDate startDate, LocalDate effectiveDate,String entityId);
    PerfAggregate save (PerfAggregateRequest perfAggregateRequest);
    PerfAggregate deletePerfAggregate (String id);
}
