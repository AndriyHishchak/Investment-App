
create table perf_aggregate
(
    perf_aggregate_id integer not null,
    return            double,
    effective_date    date,
    l1                varchar(255),
    l2                varchar(255),
    l3                varchar(255),
    weight            double,
    primary key (perf_aggregate_id)
);
