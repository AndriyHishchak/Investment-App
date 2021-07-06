package com.project.Investment.App.service.impl.jpa;

import com.project.Investment.App.dto.EntityDtoRequest;
import com.project.Investment.App.exception.ResourceNotFoundException;
import com.project.Investment.App.model.Entity;
import com.project.Investment.App.repository.EntityRepository;
import com.project.Investment.App.service.EntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("entityServiceJpa")
@Slf4j
public class EntityServiceImpl implements EntityService {

    private final EntityRepository repository;

    public EntityServiceImpl(EntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Entity findById(String id) throws ResourceNotFoundException {
        Entity entity = repository.findByEntityId_EntityId(id).stream().findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found : " + id));
        log.info("Method: findById - entity: {} find by id: {}",entity,id);

        return entity;
    }

    @Override
    public List<Entity> findByDefaultBenchmarkId(String id) throws ResourceNotFoundException {
        List<Entity> entities = repository.findByDefaultBenchmarkId(id);
        log.info("Method: findById - entity: {} find by entity: {}",entities.size(),id);
        return entities;
    }
    @Override
    public Entity create (EntityDtoRequest entity) {

            Entity entitySave = repository.save(EntityDtoRequest.fromEntityDtoResponse(entity));
            EntityDtoRequest result = EntityDtoRequest.fromEntity(entitySave);
            log.info("Method: create - created entity: {} successfully created : ", result);
            return entitySave;
    }

    @Override
    public List<Entity> getAll(Optional<String> name) {

        if (name.isPresent()) {
            List<Entity> entities = repository.findByEntityName(name.get());
            log.info("Method: findByName - entity find by name: {}", name);
            return new ArrayList<>(entities);
        }

        List<Entity> entities = repository.findAll();
        log.info("Method: getAll - {} entity found", entities.size());
        return new ArrayList<>(entities);
    }

    @Override
    public List<Entity> getAll() {
        return null;
    }

    @Override
    public Entity update(String id, EntityDtoRequest entityPath) {

        Entity entityRefresh = repository.findByEntityId_EntityId(id).stream().findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found : " + id));

        entityRefresh.setEntityName(entityPath.getEntityName());
        entityRefresh.setEntityType(entityPath.getEntityType());
        entityRefresh.setDefaultBenchmarkId(entityPath.getDefaultBenchmarkId());

        log.info("Method: update - entity with id : {} ", id);
        return repository.save(entityRefresh);
    }
    @Override
    public Entity updateParametersEntity(String id,
                                                   Optional<String> entityType,
                                                   Optional <String> entityName,
                                                   Optional<String> defaultBenchmarkId ) {
        Entity entity = repository.findByEntityId_EntityId(id).stream().findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found : " + id));
        if (entityName.isPresent()) {
            entity.setEntityName(entityName.get());
            log.info("Method: updateParametersEntity - update name entity - Entity with id : {} ", id); }
        if (entityType.isPresent()) {
            entity.setEntityType(entityType.get());
            log.info("Method: updateParametersEntity - update type entity - Entity with id : {} ", id); }
        if (defaultBenchmarkId.isPresent()) {
            entity.setDefaultBenchmarkId(defaultBenchmarkId.get());
            log.info("Method: updateParametersEntity - update defaultBenchmarkId - Entity with id : {}", id );
        }
        log.info("Method: updateParametersEntity - finish update model Entity - Entity with id : {} ", id);

        return repository.save(entity);
    }
    @Override
    public Entity deleteEntity(String id) {
       Entity entity = repository.findByEntityId_EntityId(id).stream().findAny()
                .orElseThrow(() -> new ResourceNotFoundException("Entity not found : " + id));
        repository.delete(entity);
        log.info("Method: deleteEntity - delete Entity with id : {} ", id);
        return entity;
    }

    @Override
    public void deleteAllEntity() {
        repository.deleteAll();
        log.info("Method: deleteAllEntity - deleted All Entity");
    }


}
