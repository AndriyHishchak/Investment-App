create table entity
(
    effective_date       date         not null,
    entity_id            varchar(255) not null,
    default_benchmark_id varchar(255),
    entity_name          varchar(255),
    entity_type          varchar(255),
    primary key (effective_date, entity_id)
)



