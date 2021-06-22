package com.project.Investment.App.rest;

import com.project.Investment.App.dao.EntityDao;
import com.project.Investment.App.exception.ErrorDetails;
import com.project.Investment.App.exception.ResourceNotFoundException;
import com.project.Investment.App.model.Entity;
import com.project.Investment.App.service.EntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/entity/")
public class RestControllerEntity {

    private final EntityService service;

    public RestControllerEntity(EntityService service) {
        this.service = service;
    }


    @GetMapping("{id}")
    public EntityDao getById(@PathVariable(name = "id") String id) {
        return service.findById(id);
    }
    @GetMapping()
    public List<EntityDao> getAll(@RequestParam(value = "name",required = false) Optional<String> name ) {
        return service.getAll( name );
    }
    @PostMapping()
    public ResponseEntity<?> save (@RequestBody Entity airCompany){
        EntityDao entityDao = service.create(airCompany);
        return ResponseEntity.created( URI.create("/air-entity/" + entityDao.getEntityId())).build();
    }
    @PatchMapping("{id}/update/parameters")
    public ResponseEntity<EntityDao> updateParameters(@PathVariable("id")String id,
                                                      @RequestParam(value = "type",required = false) Optional<String> entityType,
                                                      @RequestParam(value = "name",required = false) Optional <String> entityName ,
                                                      @RequestParam(value = "defaultBenchmarkId",required = false) Optional<String> defaultBenchmarkId) {
        return new ResponseEntity<>(service.updateParametersEntity(id, entityType,entityName,defaultBenchmarkId), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<EntityDao> update(@PathVariable("id")String id, @RequestBody Entity entity) {
        return new ResponseEntity<>(service.update(id,entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") String id){
        service.deleteEntity(id);
    }
    @DeleteMapping("/all")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAll(){
        service.deleteAllEntity();
    }

//    @ExceptionHandler(value = ResourceNotFoundException.class)
//    public ResponseEntity<?> handlerNotFoundException (ResourceNotFoundException exception) {
//        return (ResponseEntity<?>) ResponseEntity.notFound();
//    }

}
