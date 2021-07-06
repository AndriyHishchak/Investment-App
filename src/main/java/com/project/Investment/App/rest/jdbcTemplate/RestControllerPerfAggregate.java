package com.project.Investment.App.rest.jdbcTemplate;

import com.project.Investment.App.dto.EntityDtoRequest;
import com.project.Investment.App.dto.PerfAggregateRequest;
import com.project.Investment.App.model.Entity;
import com.project.Investment.App.model.PerfAggregate;
import com.project.Investment.App.service.PerfAggregateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/perf-aggregate/")
public class RestControllerPerfAggregate {

    private final PerfAggregateService service;

    @Autowired
    public RestControllerPerfAggregate(@Qualifier("perfAggregateServiceJdbcTemplate") PerfAggregateService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public PerfAggregate getById(@PathVariable(name = "id") Integer id) {
        return service.findById(id);
    }
    @GetMapping()
    public List<PerfAggregate> getAll() {
        return service.findAll();
    }

    @GetMapping("count")
    public Integer getCountStudent() {
        return service.findCountPerfAggregate();
    }

    @GetMapping("DateRange")
    public List<PerfAggregate> getPerfAggregateDateRange(@RequestParam (value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                         @RequestParam (value = "effectiveDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate effectiveDate) {
        return service.findPerfAggregateDateRange(startDate,effectiveDate);
    }
    @GetMapping("DateRange/{id}")
    public List<PerfAggregate> getPerfAggregateDateRangeAndEntityId( @PathVariable("id") String entityId,
                                                         @RequestParam (value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                         @RequestParam (value = "effectiveDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate effectiveDate) {
        return service.findPerfAggregateDateRangeAndEntityId(startDate,effectiveDate,entityId);
    }
    @GetMapping("isExist/{id}")
    public boolean isPerfAggregateIdExists(@PathVariable(name = "id") Integer id) {
        return service.existsEntityByPerfAggregateId(id);
    }
    @PostMapping()
    public void save (@Valid @RequestBody PerfAggregateRequest perfAggregateRequest){
       service.save(perfAggregateRequest);
    }

}
