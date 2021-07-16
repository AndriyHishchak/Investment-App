package com.project.Investment.App.service.impl;

import com.project.Investment.App.dto.EntityDtoRequest;
import com.project.Investment.App.model.Entity;
import com.project.Investment.App.model.embeddedId.EntityId;
import com.project.Investment.App.model.enam.EntityType;
import com.project.Investment.App.service.EntityService;
import com.project.Investment.App.service.QuerySQL;
import com.project.Investment.App.service.SqlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("entityServiceJdbc")
@Slf4j
public class EntityServiceJdbcImpl implements EntityService {


    private final Connection connection;

    public EntityServiceJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Entity> findById(String id, LocalDate effectiveDate, Integer limit) {
        List<Entity> entities = new ArrayList<>();

        String FIND_ENTITY_BY_ENTITY_ID_OR_EFFECTIVE_DATE_SQL = MessageFormat.format(
                QuerySQL.FIND_ENTITY_BY_ENTITY_ID_SQL +
                        SqlUtil.IsPresentParameter(effectiveDate, " and effective_date=''{1}'' ") +
                        SqlUtil.IsPresentParameter(limit, " limit {2} "),
                id, effectiveDate, limit);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ENTITY_BY_ENTITY_ID_OR_EFFECTIVE_DATE_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entities.add(Entity.builder()
                        .entityId(new EntityId(
                                resultSet.getString("entity_id"),
                                resultSet.getDate("effective_date").toLocalDate()))
                        .entityName(resultSet.getString("entity_name"))
                        .entityType(EntityType.valueOf(resultSet.getString("entity_type")))
                        .defaultBenchmarkId(resultSet.getString("default_benchmark_id")).build());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        log.info("Method: findById - entity: {} find by id: {}", entities, id);
        return entities;
    }

    @Override
    public Entity create(EntityDtoRequest entity) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QuerySQL.ADD_ENTITY_SQL);

            preparedStatement.setString(1, entity.getEntityId());
            preparedStatement.setDate(2, Date.valueOf(entity.getEffectiveDate()));
            preparedStatement.setString(3, entity.getEntityName());
            preparedStatement.setString(4, entity.getEntityType().toString());
            preparedStatement.setString(5, entity.getDefaultBenchmarkId());

            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        log.info("Method: create - created entity: {} successfully created : ", entity);
        return EntityDtoRequest.fromEntityDtoResponse(entity);
    }


    @Override
    public List<Entity> getAll() {
        List<Entity> entities = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QuerySQL.FIND_ALL_ENTITY_SQL);

            while (resultSet.next()) {
                entities.add(
                        Entity.builder()
                                .entityId(new EntityId(
                                        resultSet.getString("entity_id"),
                                        resultSet.getDate("effective_date").toLocalDate()))
                                .entityName(resultSet.getString("entity_name"))
                                .entityType(EntityType.valueOf(resultSet.getString("entity_type")))
                                .defaultBenchmarkId(resultSet.getString("default_benchmark_id")).build()
                );
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        log.info("Method: getAll - {} entity found", entities.size());
        return entities;
    }


    @Override
    public Entity update(String id, EntityDtoRequest updateEntity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QuerySQL.UPDATE_ENTITY_SQL);

            preparedStatement.setString(1, updateEntity.getEntityId());
            preparedStatement.setDate(2, Date.valueOf(updateEntity.getEffectiveDate()));
            preparedStatement.setString(3, updateEntity.getEntityName());
            preparedStatement.setString(4, updateEntity.getEntityType().toString());
            preparedStatement.setString(5, updateEntity.getDefaultBenchmarkId());

            preparedStatement.setString(6, id);

            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        log.info("Method: update - entity with id : {} ", id);
        return EntityDtoRequest.fromEntityDtoResponse(updateEntity);
    }

    @Override
    public Entity deleteEntity(String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QuerySQL.DELETE_ENTITY_BY_ENTITY_ID_SQL);
            preparedStatement.setString(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        log.info("Method: deleteEntity - delete Entity with id : {} ", id);
        return null;
    }


    @Override
    public List<Entity> findByDefaultBenchmarkId(String id) {
        List<Entity> entities = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QuerySQL.FIND_ENTITY_BY_DEFAULT_BENCHMARK_ID_SQL);

            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entities.add(
                        Entity.builder()
                                .entityId(new EntityId(
                                        resultSet.getString("entity_id"),
                                        resultSet.getDate("effective_date").toLocalDate()))
                                .entityName(resultSet.getString("entity_name"))
                                .entityType(EntityType.valueOf(resultSet.getString("entity_type")))
                                .defaultBenchmarkId(resultSet.getString("default_benchmark_id")).build());
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        log.info("Method: findById - entity: {} find by entity: {}", entities.size(), id);
        return entities;
    }

    @Override
    public List<Entity> getAll(Optional<String> name) {
        return null;
    }

    @Override
    public void deleteAllEntity() {
    }

    @Override
    public Entity updateParametersEntity(String id, Optional<String> entityType, Optional<String> entityName, Optional<String> defaultBenchmarkId) {
        return null;
    }

}
