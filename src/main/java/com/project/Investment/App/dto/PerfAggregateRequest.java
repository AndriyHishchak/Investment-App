package com.project.Investment.App.dto;


import com.project.Investment.App.model.PerfAggregate;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PerfAggregateRequest {

    LocalDate effectiveDate;
    Integer perfAggregateId;
    String l1;
    String l2;
    String l3;
    Double weight;
    Double Return;

    public static PerfAggregateRequest fromPerfAggregate (PerfAggregate perfAggregate) {
        return PerfAggregateRequest.builder()
                .effectiveDate(perfAggregate.getEffectiveDate())
                .perfAggregateId(perfAggregate.getPerfAggregateId())
                .l1(perfAggregate.getL1())
                .l2(perfAggregate.getL2())
                .l3(perfAggregate.getL3())
                .weight(perfAggregate.getWeight())
                .Return(perfAggregate.getReturn())
                .build();
    }
}
