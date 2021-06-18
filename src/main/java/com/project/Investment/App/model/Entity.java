package com.project.Investment.App.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@javax.persistence.Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "entity")
@Data
public class Entity {

    @Id
    @Column(name = "entity_id")
    String entityId;
    @Column(name = "entity_name")
    String entityName;
    @Column(name = "entity_type")
    String entityType;
    @Column(name = "effective_date")
    LocalDate effectiveDate;
    @Column(name = "default_benchmark_id")
    String defaultBenchmarkId;

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<PerfAggregate> perfAggregates;
}
