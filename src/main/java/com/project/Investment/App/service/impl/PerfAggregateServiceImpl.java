package com.project.Investment.App.service.impl;

import com.project.Investment.App.dto.PerfAggregateRequest;
import com.project.Investment.App.dto.mapperJdbc.PerfAggregateMapper;
import com.project.Investment.App.exception.ResourceNotFoundException;
import com.project.Investment.App.model.PerfAggregate;
import com.project.Investment.App.service.PerfAggregateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PerfAggregateServiceImpl implements PerfAggregateService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PerfAggregateServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PerfAggregate findById(Integer id) {
        PerfAggregate perfAggregate = jdbcTemplate.query("Select * from perf_aggregate where perf_aggregate_id=?",
                new BeanPropertyRowMapper<>(PerfAggregate.class),
                new Object[]{id})
                .stream().findAny().orElseThrow(() -> new ResourceNotFoundException("PerfAggregate not found : " + id));
        log.info("Method: findById - PerfAggregate: {} find by id: {}",perfAggregate,id);
        return perfAggregate;
    }

    @Override
    public List<PerfAggregate> findAll() {
        List<PerfAggregate> perfAggregates = jdbcTemplate.query("Select * from perf_aggregate",new PerfAggregateMapper());
        log.info("Method: getAll - {} PerfAggregate found" , perfAggregates.size());
        return new ArrayList<>(perfAggregates);
    }

    @Override
    public Integer getCountPerfAggregate() {
        Integer count = jdbcTemplate.queryForObject("Select Count(*) from perf_aggregate", Integer.class);
        log.info("Method: getCountPerfAggregate - {} PerfAggregate found" , count);
        return count;
    }
    @Override
    public boolean isPerfAggregateIdExists(Integer id) {
        String sql = "Select 1 from perf_aggregate where perf_aggregate_id=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        log.info("Method: isPerfAggregateIdExists - {} PerfAggregate founds" , count);
        return count > 0;
    }

    @Override
    public List<PerfAggregate> getPerfAggregateDateRange(LocalDate startDate, LocalDate effectiveDate) {
        String sql = "select * from perf_aggregate where effective_date > ? and effective_date < ?'";

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(PerfAggregate.class),
                startDate, effectiveDate);
    }

    @Override
    public void save (PerfAggregateRequest perfAggregateRequest) {
          jdbcTemplate.update(
                "Insert into perf_aggregate (effective_date,perf_aggregate_id,l1,l2,l3,weight,return) values (?,?,?,?,?,?,?)",
                perfAggregateRequest.getEffectiveDate(),
                perfAggregateRequest.getPerfAggregateId(),
                perfAggregateRequest.getL1(),
                perfAggregateRequest.getL2(),
                perfAggregateRequest.getL3(),
                perfAggregateRequest.getWeight(),
                perfAggregateRequest.getReturn());
    }


}
