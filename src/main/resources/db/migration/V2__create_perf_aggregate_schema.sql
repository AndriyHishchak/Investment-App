create table perf_aggregate (
    perf_aggregate_id integer not null,
    return double,
    effective_date date,
    l1 varchar(255),
    l2 varchar(255),
    l3 varchar(255),
    weight double,
    entity_id varchar(255) not null,
    primary key (perf_aggregate_id));

alter table perf_aggregate add constraint FKk9m9bmb348e0c3f8jyiih9f3j
    foreign key (entity_id) references entity on delete cascade

