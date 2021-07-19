package com.project.Investment.App.dto;

import com.project.Investment.App.dto.validator.DateRange;
import com.project.Investment.App.dto.validator.DateRangeValidator;
import com.project.Investment.App.dto.validator.UniqueDefaultBenchmarkId;
import com.project.Investment.App.dto.validator.groupSequence.OrderedChecks;
import com.project.Investment.App.dto.validator.groupSequence.Second;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.groups.Default;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@DateRange
@GroupSequence({FundCalculationRequest.class, OrderedChecks.class})
public class FundCalculationRequest {
    @NotBlank(message = "{entityId.notEmpty}",groups = Second.class)
    String entityId;
    @NotNull(message = "{date.notEmpty}")
    @PastOrPresent(message = "{date.PastOrPresent}")
    LocalDate startDate;
    @NotNull(message = "{date.notEmpty}")
    LocalDate asOfDate;


}
