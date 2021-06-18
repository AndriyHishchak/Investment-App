package com.project.Investment.App.rest;

import com.project.Investment.App.DTO.EntityDto;
import com.project.Investment.App.service.EntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/entity/")
public class RestControllerEntity {

    private EntityService service;

    public RestControllerEntity(EntityService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityDto> getById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
}
