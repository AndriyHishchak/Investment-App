package com.project.Investment.App.service.impl;

import com.project.Investment.App.DTO.EntityDto;
import com.project.Investment.App.exception.NotFountException;
import com.project.Investment.App.model.Entity;
import com.project.Investment.App.repository.EntityRepository;
import com.project.Investment.App.service.EntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EntityServiceImpl implements EntityService {

    private final EntityRepository repository;

    public EntityServiceImpl(EntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public EntityDto findById(String id) {
        Entity entity = repository.findById(id)
                .orElseThrow(() -> new NotFountException("Entity not found"));
        log.info("In findById - entity: {} find by id: {}",entity,id);
        return EntityDto.fromEntity(entity);
    }
}
