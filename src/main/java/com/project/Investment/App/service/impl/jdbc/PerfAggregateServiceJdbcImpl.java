package com.project.Investment.App.service.impl.jdbc;

import com.project.Investment.App.dto.EntityDtoRequest;
import com.project.Investment.App.dto.PerfAggregateRequest;
import com.project.Investment.App.exception.ResourceNotFoundException;
import com.project.Investment.App.model.Entity;
import com.project.Investment.App.model.EntityId;
import com.project.Investment.App.model.PerfAggregate;
import com.project.Investment.App.service.PerfAggregateService;
import com.project.Investment.App.service.impl.QuerySQL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service("perfAggregateServiceJdbc")
@Slf4j
public class PerfAggregateServiceJdbcImpl implements PerfAggregateService {

    private static final String URL = "jdbc:h2:mem:investment";
    private static final String USERNAME ="sa";
    private static final String PASSWORD = "";

    private static Connection connection;

    static {
        try {
            Class.forName("org.h2.Driver");
        }catch (ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    public PerfAggregate findById(Integer id) {
        PerfAggregate perfAggregate = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QuerySQL.FIND_PERF_AGGREGATE_BY_PERF_AGGREGATE_ID_SQL);
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            perfAggregate = PerfAggregate.builder()
                    .entityId(resultSet.getString("entity_id"))
                    .effectiveDate(resultSet.getDate("effective_date").toLocalDate())
                    .perfAggregateId(resultSet.getInt("perf_aggregate_id"))
                    .l1(resultSet.getString("l1"))
                    .l2(resultSet.getString("l2"))
                    .l3(resultSet.getString("l3"))
                    .weight(resultSet.getDouble("weight"))
                    .Return(resultSet.getDouble("return")).build();
        }  catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        if (perfAggregate == null) throw new ResourceNotFoundException();
        log.info("Method: findById - PerfAggregate: {} find by id: {}",perfAggregate,id);
        return perfAggregate;
    }

    @Override
    public List<PerfAggregate> findAll() {
        List<PerfAggregate> perfAggregates = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QuerySQL.FIND_ALL_PERF_AGGREGATE_SQL);

            while (resultSet.next()) {
                perfAggregates.add(
                        PerfAggregate.builder()
                                .entityId(resultSet.getString("entity_id"))
                                .effectiveDate(resultSet.getDate("effective_date").toLocalDate())
                                .perfAggregateId(resultSet.getInt("perf_aggregate_id"))
                                .l1(resultSet.getString("l1"))
                                .l2(resultSet.getString("l2"))
                                .l3(resultSet.getString("l3"))
                                .weight(resultSet.getDouble("weight"))
                                .Return(resultSet.getDouble("return")).build());
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        log.info("Method: getAll - {} PerfAggregate found", perfAggregates.size());
        return perfAggregates;
    }

    @Override
    public PerfAggregate update(String id, PerfAggregateRequest updateAggregateRequest) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QuerySQL.UPDATE_PERF_AGGREGATE_BY_PERF_AGGREGATE_ID_SQL);

            preparedStatement.setString(1,updateAggregateRequest.getEntityId());
            preparedStatement.setDate(2,Date.valueOf(updateAggregateRequest.getEffectiveDate()));
            preparedStatement.setInt(3,updateAggregateRequest.getPerfAggregateId());
            preparedStatement.setString(4,updateAggregateRequest.getL1());
            preparedStatement.setString(5,updateAggregateRequest.getL2());
            preparedStatement.setString(6,updateAggregateRequest.getL3());
            preparedStatement.setDouble(7,updateAggregateRequest.getWeight());
            preparedStatement.setDouble(8,updateAggregateRequest.getReturn());

            preparedStatement.setString(9,id);

            preparedStatement.executeUpdate();
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
       log.info("Method: update - PerfAggregate with id : {} ", id);
        return PerfAggregateRequest.fromPerfAggregateRequest(updateAggregateRequest);
    }


        @Override
    public List<PerfAggregate> findPerfAggregateDateRange(LocalDate startDate, LocalDate effectiveDate) {
        List<PerfAggregate> perfAggregates = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QuerySQL.FIND_PERF_AGGREGATE_BY_DATE_RANGE);

            while (resultSet.next()) {
                perfAggregates.add(
                        PerfAggregate.builder()
                                .entityId(resultSet.getString("entity_id"))
                                .effectiveDate(resultSet.getDate("effective_date").toLocalDate())
                                .perfAggregateId(resultSet.getInt("perf_aggregate_id"))
                                .l1(resultSet.getString("l1"))
                                .l2(resultSet.getString("l2"))
                                .l3(resultSet.getString("l3"))
                                .weight(resultSet.getDouble("weight"))
                                .Return(resultSet.getDouble("return")).build());
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        log.info("Method: findPerfAggregateDateRange - {} PerfAggregate found", perfAggregates.size());
        return perfAggregates;
    }

    @Override
    public List<PerfAggregate> findPerfAggregateDateRangeAndEntityId(LocalDate startDate, LocalDate effectiveDate, String entityId) {
        List<PerfAggregate> perfAggregates = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QuerySQL.FIND_PERF_AGGREGATE_BY_DATE_RANGE_AND_ENTITY_ID);

            while (resultSet.next()) {
                perfAggregates.add(
                        PerfAggregate.builder()
                                .entityId(resultSet.getString("entity_id"))
                                .effectiveDate(resultSet.getDate("effective_date").toLocalDate())
                                .perfAggregateId(resultSet.getInt("perf_aggregate_id"))
                                .l1(resultSet.getString("l1"))
                                .l2(resultSet.getString("l2"))
                                .l3(resultSet.getString("l3"))
                                .weight(resultSet.getDouble("weight"))
                                .Return(resultSet.getDouble("return")).build());
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        log.info("Method: findPerfAggregateDateRangeAndEntityId - {} PerfAggregate found", perfAggregates.size());
        return perfAggregates;
    }

    @Override
    public PerfAggregate save(PerfAggregateRequest perfAggregateRequest) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QuerySQL.ADD_PERF_AGGREGATE_SQL);

            preparedStatement.setString(1,perfAggregateRequest.getEntityId());
            preparedStatement.setDate(2,Date.valueOf(perfAggregateRequest.getEffectiveDate()));
            preparedStatement.setInt(3,perfAggregateRequest.getPerfAggregateId());
            preparedStatement.setString(4,perfAggregateRequest.getL1());
            preparedStatement.setString(5,perfAggregateRequest.getL2());
            preparedStatement.setString(6,perfAggregateRequest.getL3());
            preparedStatement.setDouble(7,perfAggregateRequest.getWeight());
            preparedStatement.setDouble(8,perfAggregateRequest.getReturn());


            preparedStatement.executeUpdate();

        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        log.info("Method: save - created PerfAggregate: {} successfully created : ", perfAggregateRequest);
        return PerfAggregateRequest.fromPerfAggregateRequest(perfAggregateRequest);
    }

    @Override
    public PerfAggregate deletePerfAggregate(String id) {
        try{
            PreparedStatement preparedStatement =
                    connection.prepareStatement(QuerySQL.DELETE_PERF_AGGREGATE_BY_PERF_AGGREGATE_ID_SQL);
            preparedStatement.setString(1,id);

            preparedStatement.executeUpdate();
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        log.info("Method: deletePerfAggregate - delete PerfAggregate with id : {} ", id);
        return null;
    }

    @Override
    public Integer findCountPerfAggregate() {
        return null;
    }

    @Override
    public boolean existsEntityByPerfAggregateId(Integer id) {
        return false;
    }
}
