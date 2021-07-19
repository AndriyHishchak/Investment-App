package com.project.Investment.App.model;

import com.project.Investment.App.model.embeddedId.PositionId;
import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Position {

    PositionId positionId;
    char frequency;
    Double weight;
    Double grossReturn;
    Double bmvGross;
    Double emvGross;
    Double gainLossGross;


    public String toStringSql() {
        return "(" +
                '\'' + positionId.getEntityId() + '\'' + ',' +
                '\'' + positionId.getEffectiveDate() + '\'' + ',' +
                '\'' + frequency + '\'' + ',' +
                positionId.getSecurityId() + ',' +
                weight + ',' +
                grossReturn + ',' +
                bmvGross + ',' +
                emvGross + ',' +
                gainLossGross +
                "),\n";
    }

    public String toStringCsv() {
        return positionId.getEntityId() + ',' +
                positionId.getEffectiveDate() + ',' +
                frequency + ',' +
                positionId.getSecurityId() + ',' +
                weight + ',' +
                grossReturn + ',' +
                bmvGross + ',' +
                emvGross + ',' +
                gainLossGross +
                "\n";
    }
}
