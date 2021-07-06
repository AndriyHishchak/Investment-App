package com.project.Investment.App.service.impl.jdbcTemplate;

import com.project.Investment.App.dto.PerfAggregateRequest;
import com.project.Investment.App.dto.mapperJdbc.PerfAggregateMapper;
import com.project.Investment.App.exception.ResourceNotFoundException;
import com.project.Investment.App.model.Entity;
import com.project.Investment.App.model.PerfAggregate;
import com.project.Investment.App.service.PerfAggregateService;
import com.project.Investment.App.service.impl.QuerySQL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service("perfAggregateServiceJdbcTemplate")
@Slf4j
public class PerfAggregateServiceImpl implements PerfAggregateService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PerfAggregateServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PerfAggregate findById(Integer id) {
        PerfAggregate perfAggregate = jdbcTemplate.query(QuerySQL.FIND_PERF_AGGREGATE_BY_PERF_AGGREGATE_ID_SQL,
                new BeanPropertyRowMapper<>(PerfAggregate.class),
                new Object[]{id})
                .stream().findAny().orElseThrow(() -> new ResourceNotFoundException("PerfAggregate not found : " + id));
        log.info("Method: findById - PerfAggregate: {} find by id: {}", perfAggregate, id);
        return perfAggregate;
    }

    @Override
    public List<PerfAggregate> findAll() {
        List<PerfAggregate> perfAggregates = jdbcTemplate.query(QuerySQL.FIND_ALL_PERF_AGGREGATE_SQL, new PerfAggregateMapper());
        log.info("Method: getAll - {} PerfAggregate found", perfAggregates.size());
        return new ArrayList<>(perfAggregates);
    }

    @Override
    public Integer findCountPerfAggregate() {
        Integer count = jdbcTemplate.queryForObject(QuerySQL.FIND_COUNT_ALL_PERF_AGGREGATE, Integer.class);
        log.info("Method: getCountPerfAggregate - {} PerfAggregate found", count);
        return count;
    }

    @Override
    public boolean existsEntityByPerfAggregateId(Integer id) {
        Integer count = null;
        try {
            count = jdbcTemplate.queryForObject(QuerySQL.EXISTS_PERF_AGGREGATE_BY_PERF_AGGREGATE_ID_SQL, Integer.class, id);
            log.info("Method: existsEntityByPerfAggregateId - {} PerfAggregate count", count);
        } catch (Exception e) {
            return false;
        }

        if (count == null) {
            return false;
        }
        return count > 0;
    }


    @Override
    public List<PerfAggregate> findPerfAggregateDateRange(LocalDate startDate, LocalDate effectiveDate) {

        List<PerfAggregate> perfAggregates = jdbcTemplate.query(QuerySQL.FIND_PERF_AGGREGATE_BY_DATE_RANGE,
                new BeanPropertyRowMapper<>(PerfAggregate.class),
                startDate, effectiveDate);
        log.info("Method: getPerfAggregateDateRange - {} PerfAggregate found", perfAggregates.size());
        return perfAggregates;
    }

    @Override
    public List<PerfAggregate> findPerfAggregateDateRangeAndEntityId(LocalDate startDate, LocalDate effectiveDate, String entityId) {

        List<PerfAggregate> perfAggregates = jdbcTemplate.query(QuerySQL.FIND_PERF_AGGREGATE_BY_DATE_RANGE_AND_ENTITY_ID,
                new BeanPropertyRowMapper<>(PerfAggregate.class),
                startDate, effectiveDate, entityId);
        log.info("Method: getPerfAggregateDateRange - {} PerfAggregate found", perfAggregates.size());
        return perfAggregates;
    }

    @Override
    public PerfAggregate save(PerfAggregateRequest perfAggregateRequest) {
        jdbcTemplate.update(QuerySQL.ADD_PERF_AGGREGATE_SQL,
                perfAggregateRequest.getEntityId(),
                perfAggregateRequest.getEffectiveDate(),
                perfAggregateRequest.getPerfAggregateId(),
                perfAggregateRequest.getL1(),
                perfAggregateRequest.getL2(),
                perfAggregateRequest.getL3(),
                perfAggregateRequest.getWeight(),
                perfAggregateRequest.getReturn());

        return PerfAggregateRequest.fromPerfAggregateRequest(perfAggregateRequest);

    }

    @Override
    public PerfAggregate deletePerfAggregate(String id) {
        jdbcTemplate.update(QuerySQL.DELETE_PERF_AGGREGATE_BY_PERF_AGGREGATE_ID_SQL, id);
        return null;
    }

    @Override
    public PerfAggregate update(String id, PerfAggregateRequest updateAggregateRequest) {

        jdbcTemplate.update(QuerySQL.UPDATE_PERF_AGGREGATE_BY_PERF_AGGREGATE_ID_SQL,
                updateAggregateRequest.getEntityId(),
                updateAggregateRequest.getEffectiveDate(),
                updateAggregateRequest.getPerfAggregateId(),
                updateAggregateRequest.getL1(),
                updateAggregateRequest.getL2(),
                updateAggregateRequest.getL3(),
                updateAggregateRequest.getWeight(),
                updateAggregateRequest.getReturn(),
                id);

        log.info("Method: update - PerfAggregate with id : {} ", id);
        return PerfAggregateRequest.fromPerfAggregateRequest(updateAggregateRequest);
    }
}
