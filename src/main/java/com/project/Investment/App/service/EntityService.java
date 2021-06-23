package com.project.Investment.App.service;

import com.project.Investment.App.dto.EntityDtoRequest;
import com.project.Investment.App.model.Entity;

import java.util.List;
import java.util.Optional;

public interface EntityService {

    Entity findById (String id);
    EntityDtoRequest create (EntityDtoRequest entity);
    List<Entity> getAll(Optional<String> name);
    Entity updateParametersEntity(String id,
                                            Optional<String> entityType,
                                            Optional <String> entityName,
                                            Optional<String> defaultBenchmarkId );
    Entity update (String id, EntityDtoRequest entity);
    Entity deleteEntity (String id);
    void deleteAllEntity ();
    List<Entity> findByDefaultBenchmarkId(String id);
}
