package com.project.Investment.App.Dao;

import com.project.Investment.App.model.PerfAggregate;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PerfAggregateDao {

    LocalDate effectiveDate;
    Integer perfAggregateId;
    String l1;
    String l2;
    String l3;
    Double weight;
    Double Return;

    public static List<PerfAggregateDao> fromToPerfAggregate (List<PerfAggregate> perfAggregates) {
        return perfAggregates.stream().map(PerfAggregateDao::fromPerfAggregate).collect(Collectors.toList());
    }

    public static PerfAggregateDao fromPerfAggregate (PerfAggregate perfAggregate) {
        return PerfAggregateDao.builder()
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
