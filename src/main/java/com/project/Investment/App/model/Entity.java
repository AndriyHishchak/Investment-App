package com.project.Investment.App.model;

import com.project.Investment.App.model.embeddedId.EntityId;
import lombok.*;
import lombok.experimental.FieldDefaults;




@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class Entity {

    EntityId entityId;

    String entityName;

    String entityType;

    String defaultBenchmarkId;


}
