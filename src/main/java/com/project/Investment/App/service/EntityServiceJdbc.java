package com.project.Investment.App.service;

import com.project.Investment.App.dto.EntityDtoRequest;
import com.project.Investment.App.model.Entity;

import java.util.List;
import java.util.Optional;

public interface EntityServiceJdbc {
    Entity findById (String id);
    Entity create (EntityDtoRequest entity);
    List<Entity> getAll();
    Entity update (String id, EntityDtoRequest entity);
    Entity deleteEntity (String id);
    List<Entity> findByDefaultBenchmarkId(String id);
}
