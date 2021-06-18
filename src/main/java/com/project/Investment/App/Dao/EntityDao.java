package com.project.Investment.App.Dao;

import com.project.Investment.App.model.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EntityDao {
    String entityId;
    String entityName;
    String entityType;
    LocalDate effectiveDate;
    String defaultBenchmarkId;
    List<PerfAggregateDao> perfAggregates;

    public static EntityDao fromEntity(Entity entity) {
        List<PerfAggregateDao> perfAggregateDaos = PerfAggregateDao.fromToPerfAggregate(entity.getPerfAggregates());

        return EntityDao.builder()
                .entityId(entity.getEntityId())
                .entityName(entity.getEntityName())
                .entityType(entity.getEntityType())
                .effectiveDate(entity.getEffectiveDate())
                .defaultBenchmarkId(entity.getDefaultBenchmarkId())
                .perfAggregates(perfAggregateDaos)
                .build();
    }
}
