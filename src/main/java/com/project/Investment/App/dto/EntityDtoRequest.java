package com.project.Investment.App.dto;

import com.project.Investment.App.dto.validator.UniqueDefaultBenchmarkId;
import com.project.Investment.App.model.Entity;
import com.project.Investment.App.model.EntityId;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntityDtoRequest {
    @NotBlank(message = "Entity Id should not be empty")
    String entityId;
    @NotBlank(message = "Name should not be empty")
    String entityName;
    @NotBlank(message = "Type should not be empty")
    String entityType;
    @NotNull(message = "Effective Date should not be empty")
    LocalDate effectiveDate;
    @UniqueDefaultBenchmarkId
    String defaultBenchmarkId;


    public static EntityDtoRequest fromEntity(Entity entity) {

        return EntityDtoRequest.builder()
                .entityId(entity.getEntityId().getEntityId())
                .entityName(entity.getEntityName())
                .entityType(entity.getEntityType())
                .defaultBenchmarkId(entity.getDefaultBenchmarkId())
                .effectiveDate(entity.getEntityId().getEffectiveDate())
                .build();
    }
    public static Entity fromEntityDtoResponse(EntityDtoRequest entity) {
        EntityId entityId = new EntityId(entity.getEntityId(),entity.getEffectiveDate());
        return Entity.builder()
                .entityId(entityId)
                .entityName(entity.getEntityName())
                .entityType(entity.getEntityType())
                .defaultBenchmarkId(entity.getDefaultBenchmarkId())
                .build();
    }


}
