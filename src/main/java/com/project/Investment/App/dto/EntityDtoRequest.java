package com.project.Investment.App.dto;

import com.project.Investment.App.dto.validator.UniqueDefaultBenchmarkId;
import com.project.Investment.App.model.Entity;
import com.project.Investment.App.model.embeddedId.EntityId;
import com.project.Investment.App.model.enam.EntityType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@UniqueDefaultBenchmarkId
public class EntityDtoRequest {
    @NotBlank(message = "{entityId.notEmpty}")
    String entityId;
    @NotBlank(message = "{entityName.notEmpty}")
    String entityName;
    @NotNull(message = "{entityType.notEmpty}")
    EntityType entityType;
    @PastOrPresent(message = "{date.PastOrPresent}")
    @NotNull(message = "{date.notEmpty}")
    LocalDate effectiveDate;
    String defaultBenchmarkId;

    public static Entity fromEntityDtoResponse(EntityDtoRequest entity) {
        EntityId entityId = new EntityId(entity.getEntityId(), entity.getEffectiveDate());
        return Entity.builder()
                .entityId(entityId)
                .entityName(entity.getEntityName())
                .entityType(entity.getEntityType())
                .defaultBenchmarkId(entity.getDefaultBenchmarkId())
                .build();
    }
}
