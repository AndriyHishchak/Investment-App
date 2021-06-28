package com.project.Investment.App.dto;


import com.project.Investment.App.dto.validator.UniquePerfAggregateId;
import com.project.Investment.App.model.EntityId;
import com.project.Investment.App.model.PerfAggregate;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PerfAggregateRequest {

    EntityId entityId;
    //@NotNull(message = "Effective Date should not be empty")
    LocalDate effectiveDate;
    @UniquePerfAggregateId
    Integer perfAggregateId;
    @NotBlank(message = "Level 1 should not be empty")
    String l1;
    String l2;
    String l3;
    @NotNull(message = "Weight should not be empty")
    Double weight;
    @NotNull(message = "Return should not be empty")
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
