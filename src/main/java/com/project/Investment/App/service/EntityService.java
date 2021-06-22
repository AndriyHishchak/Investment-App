package com.project.Investment.App.service;

import com.project.Investment.App.dao.EntityDao;
import com.project.Investment.App.model.Entity;
import com.project.Investment.App.model.EntityId;

import java.util.List;
import java.util.Optional;

public interface EntityService {

    EntityDao findById (String id);
    EntityDao create (Entity entity);
    List<EntityDao> getAll(Optional<String> name);
    EntityDao updateParametersEntity(String id,
                                         Optional<String> entityType,
                                         Optional <String> entityName,
                                         Optional<String> defaultBenchmarkId );
    EntityDao update (String id,Entity entity);
    void deleteEntity (String id);
    void deleteAllEntity ();
}
