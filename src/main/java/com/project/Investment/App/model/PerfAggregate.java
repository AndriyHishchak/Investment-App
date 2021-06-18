package com.project.Investment.App.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@javax.persistence.Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "perf_aggregate")
@Data
public class PerfAggregate {

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "entity_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Entity entity;

    @Column(name = "effective_date")
    LocalDate effectiveDate;

    @Id
    Integer perfAggregateId;

    @Column(name = "l1")
    String l1;
    @Column(name = "l2")
    String l2;
    @Column(name = "l3")
    String l3;

    @Column(name = "weight")
    Double weight;

    @Column(name = "return")
    Double Return;
}
