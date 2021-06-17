
create table entity (
    entity_id varchar(255) not null,
    default_benchmark_id varchar(255),
    effective_date date,
    entity_name varchar(255),
    entity_type varchar(255),
    primary key (entity_id));
