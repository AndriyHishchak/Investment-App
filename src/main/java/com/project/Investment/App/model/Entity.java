package com.project.Investment.App.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@javax.persistence.Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "entity")
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Data
public class Entity {

    @EmbeddedId
    @Column(name = "entity_id")
    EntityId entityId;
    @Column(name = "entity_name")
    String entityName;
    @Column(name = "entity_type")
    String entityType;
    @Column(name = "default_benchmark_id")
    String defaultBenchmarkId;

    @Override
    public String toString() {
        return "Entity{" +
                "entityId='" + entityId + '\'' +
                ", entityName='" + entityName + '\'' +
                ", entityType='" + entityType + '\'' +
                ", defaultBenchmarkId='" + defaultBenchmarkId + '\'' +

                '}';
    }
}
