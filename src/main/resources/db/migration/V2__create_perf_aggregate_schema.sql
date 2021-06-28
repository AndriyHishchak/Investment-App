
create table perf_aggregate
(
    entity_id         varchar(255),
    perf_aggregate_id integer not null,
    return            double,
    effective_date    date,
    l1                varchar(255),
    l2                varchar(255),
    l3                varchar(255),
    weight            double,
    primary key (perf_aggregate_id)
);
