create table position (
    effective_date date not null,
    entity_id varchar(255) not null,
    security_id integer,
    frequency char(255),
    weight double,
    bmv_gross double,
    emv_gross double,
    gross_return double,
    gain_loss_gross double,
    primary key (effective_date, entity_id,security_id))