package com.project.Investment.App.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@javax.persistence.Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "perf_aggregate")
@Builder
@Data
public class PerfAggregate {

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



    @Override
    public String toString() {
        return "PerfAggregate{" +
                "effectiveDate=" + effectiveDate +
                ", perfAggregateId=" + perfAggregateId +
                ", l1='" + l1 + '\'' +
                ", l2='" + l2 + '\'' +
                ", l3='" + l3 + '\'' +
                ", weight=" + weight +
                ", Return=" + Return +
                '}';
    }
}
