package com.project.Investment.App.dto.mapperJdbc;

import com.project.Investment.App.model.PerfAggregate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PerfAggregateMapper implements RowMapper<PerfAggregate> {
    @Override
    public PerfAggregate mapRow(ResultSet rs, int rowNum) throws SQLException {
        return PerfAggregate.builder()
                .perfAggregateId(rs.getInt("perf_aggregate_id"))
                .l1(rs.getString("l1"))
                .l2(rs.getString("l2"))
                .l3(rs.getString("l3"))
                .weight(rs.getDouble("weight"))
                .Return(rs.getDouble("return"))
                .build();
    }
}
