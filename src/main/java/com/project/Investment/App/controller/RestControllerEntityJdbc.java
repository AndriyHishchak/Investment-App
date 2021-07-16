package com.project.Investment.App.controller;

import com.project.Investment.App.dto.EntityDtoRequest;
import com.project.Investment.App.model.Entity;
import com.project.Investment.App.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entities")
public class RestControllerEntityJdbc {

    private final EntityService service;

    
    @Autowired
    public RestControllerEntityJdbc(EntityService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Entity>> getById(
            @PathVariable(name = "id") String id,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(value = "effectiveDate", required = false) LocalDate effectiveDate,
            @RequestParam(value = "limit") Integer limit
    ) {
        return ResponseEntity.ok(service.findById(id, effectiveDate, limit));
    }

    @GetMapping("/benchmarkId/{id}")
    public ResponseEntity<List<Entity>> getByDefaultBenchmarkId(@PathVariable(name = "id") String id) {
        return ResponseEntity.ok(service.findByDefaultBenchmarkId(id));
    }

    @GetMapping()
    public ResponseEntity<List<Entity>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid @RequestBody EntityDtoRequest entity) {

        Entity entityDtoRequest = service.create(entity);
        return ResponseEntity.created(URI.create("/entities/" + entityDtoRequest.getEntityId().getEntityId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entity> update(@PathVariable("id") String id, @Valid @RequestBody EntityDtoRequest entity) {
        return ResponseEntity.ok(service.update(id, entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Entity> deleteById(@PathVariable("id") String id) {
        return ResponseEntity.accepted().body(service.deleteEntity(id));
    }

}
