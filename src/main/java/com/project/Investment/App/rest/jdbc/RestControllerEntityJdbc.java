package com.project.Investment.App.rest.jdbc;

import com.project.Investment.App.dto.EntityDtoRequest;
import com.project.Investment.App.model.Entity;
import com.project.Investment.App.service.EntityService;
import com.project.Investment.App.service.PerfAggregateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/entity/jdbc/")
public class RestControllerEntityJdbc {

    private final EntityService service;

    @Autowired
    public RestControllerEntityJdbc(@Qualifier("entityServiceJdbc") EntityService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public Entity getById(@PathVariable(name = "id") String id) {
        return service.findById(id);
    }

    @GetMapping("/DefaultBenchmarkId/{DefaultBenchmarkId}")
    public List<Entity> getByDefaultBenchmarkId(@PathVariable(name = "DefaultBenchmarkId") String id) {
        return service.findByDefaultBenchmarkId(id);
    }

    @GetMapping()
    public List<Entity> getAll(@RequestParam(value = "name",required = false) Optional<String> name ) {
        return service.getAll();
    }
    @PostMapping()
    public ResponseEntity<?> save (@Valid @RequestBody EntityDtoRequest entity){

        Entity entityDtoRequest = service.create(entity);
        return ResponseEntity.created( URI.create("/entity/" + entityDtoRequest.getEntityId().getEntityId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entity> update(@PathVariable("id")String id, @Valid @RequestBody EntityDtoRequest entity) {
        return new ResponseEntity<>(service.update(id,entity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Entity> deleteById(@PathVariable("id") String id){
      return  new ResponseEntity<>(service.deleteEntity(id), HttpStatus.ACCEPTED);
    }

}
