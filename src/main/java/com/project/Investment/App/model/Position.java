package com.project.Investment.App.model;

import com.project.Investment.App.model.embeddedId.PositionId;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@javax.persistence.Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "position")
@NoArgsConstructor
@AllArgsConstructor

@Builder
@Data
public class Position {

    @EmbeddedId
    PositionId positionId;
    @Column(name = "FREQUENCY")
    char frequency;
    @Column(name = "WEIGHT")
    Double weight;
    @Column(name = "GROSS_RETURN")
    Double grossReturn;
    @Column(name = "BMV_GROSS")
    Double bmvGross;
    @Column(name = "EMV_GROSS")
    Double emvGross;
    @Column(name = "GAIN_LOSS_GROSS")
    Double gainLossGross;


    public String toStringSql() {
        return "(" +
                '\'' + positionId.getEntityId() + '\'' + ',' +
                '\'' + positionId.getEffectiveDate() + '\'' + ',' +
                positionId.getAggregateId() + ',' +
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
                positionId.getAggregateId() + ',' +
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
