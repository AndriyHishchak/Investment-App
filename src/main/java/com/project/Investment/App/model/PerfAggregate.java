package com.project.Investment.App.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class PerfAggregate {

    String entityId;

    LocalDate effectiveDate;

    Integer perfAggregateId;

    String l1;

    String l2;

    String l3;

    Double weight;

    Double Return;


}
