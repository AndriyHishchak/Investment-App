package com.project.Investment.App.model.embeddedId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Data
public class EntityId implements Serializable {

    String entityId;
    LocalDate effectiveDate;
}
