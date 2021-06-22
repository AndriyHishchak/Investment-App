package com.project.Investment.App.rest;

import com.project.Investment.App.dao.PerfAggregateDao;
import com.project.Investment.App.service.PerfAggregateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/Perf-Aggregate/")
public class RestControllerPerfAggregate {

    private PerfAggregateService service;

    public RestControllerPerfAggregate(PerfAggregateService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public PerfAggregateDao getById(@PathVariable(name = "id") Integer id) {
        return service.findById(id);
    }
    @GetMapping()
    public List<PerfAggregateDao> getAll() {
        return service.findAll();
    }

    @GetMapping("count")
    public Integer getCountStudent() {
        return service.getCountPerfAggregate();
    }

}
