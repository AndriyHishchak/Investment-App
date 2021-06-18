package com.project.Investment.App.DTO;

import com.project.Investment.App.model.Entity;
import com.project.Investment.App.model.PerfAggregate;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntityDto {
    String entityId;
    String entityName;
    String entityType;
    LocalDate effectiveDate;
    String defaultBenchmarkId;
    List<PerfAggregateDto> perfAggregates;

    public static EntityDto fromEntity(Entity entity) {
        List<PerfAggregateDto> perfAggregateDtos = PerfAggregateDto.fromToPerfAggregate(entity.getPerfAggregates());

        return EntityDto.builder()
                .entityId(entity.getEntityId())
                .entityName(entity.getEntityName())
                .entityType(entity.getEntityType())
                .effectiveDate(entity.getEffectiveDate())
                .defaultBenchmarkId(entity.getDefaultBenchmarkId())
                .perfAggregates(perfAggregateDtos)
                .build();
    }
}
