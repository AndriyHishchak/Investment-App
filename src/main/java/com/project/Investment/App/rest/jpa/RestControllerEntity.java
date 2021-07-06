package com.project.Investment.App.rest.jpa;

import com.project.Investment.App.dto.EntityDtoRequest;
import com.project.Investment.App.model.Entity;

import com.project.Investment.App.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/entity/")
public class RestControllerEntity {

    private final EntityService service;
    @Autowired
    public RestControllerEntity(@Qualifier("entityServiceJpa") EntityService service) {
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
        return service.getAll( name );
    }
    @PostMapping()
    public ResponseEntity<?> save (@Valid @RequestBody EntityDtoRequest entity){

        Entity entityDtoRequest = service.create(entity);
        return ResponseEntity.created( URI.create("/entity/" + entityDtoRequest.getEntityId().getEntityId())).build();
    }
    @PatchMapping("{id}/update/parameters")
    public ResponseEntity<Entity> updateParameters(@PathVariable("id")String id,
                                                             @RequestParam(value = "type",required = false) Optional<String> entityType,
                                                             @RequestParam(value = "name",required = false) Optional <String> entityName ,
                                                             @RequestParam(value = "defaultBenchmarkId",required = false) Optional<String> defaultBenchmarkId) {
        return new ResponseEntity<>(service.updateParametersEntity(id, entityType,entityName,defaultBenchmarkId), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Entity> update(@PathVariable("id")String id, @Valid @RequestBody EntityDtoRequest entity) {
        return new ResponseEntity<>(service.update(id,entity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Entity> deleteById(@PathVariable("id") String id){
      return  new ResponseEntity<>(service.deleteEntity(id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/all")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAll(){
        service.deleteAllEntity();
    }

}
