package com.project.Investment.App.model;

import com.project.Investment.App.model.embeddedId.EntityId;
import com.project.Investment.App.model.enam.EntityType;
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

    EntityType entityType;

    String defaultBenchmarkId;


}
