package com.project.Investment.App.dto;

import com.project.Investment.App.dto.validator.UniqueDefaultBenchmarkId;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FundCalculationResponse {
    @NotBlank(message = "{entityId.notEmpty}")
    String entityId;
    @NotNull(message = "{date.notEmpty}")
    LocalDate startDate;
    @NotNull(message = "{date.notEmpty}")
    LocalDate endDate;
    @NotBlank(message = "{group.notEmpty}")
    String group;
    @NotNull(message = "{fundReturn.notEmpty}")
    Double fundReturn;
}
