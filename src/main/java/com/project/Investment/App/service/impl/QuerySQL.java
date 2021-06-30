package com.project.Investment.App.service.impl;

public class QuerySQL {

    public static final String ADD_ENTITY_SQL = "INSERT INTO entity (entity_id, effective_date, entity_name, entity_type, default_benchmark_id) VALUES (?,?,?,?,?)";
    public static final String UPDATE_ENTITY_SQL = "UPDATE entity SET entity_id=?, effective_date=?, entity_name=?, entity_type=?, default_benchmark_id=? WHERE entity_id=?";
    public static final String FIND_ALL_ENTITY_SQL = "SELECT * FROM entity";
    public static final String FIND_ENTITY_BY_ENTITY_ID_SQL = "SELECT * FROM entity WHERE entity_id=?";
    public static final String DELETE_ENTITY_BY_ENTITY_ID_SQL = "DELETE FROM entity WHERE entity_id=?";
    public static final String FIND_ENTITY_BY_DEFAULT_BENCHMARK_ID_SQL = "SELECT * FROM entity WHERE default_benchmark_id=?";

    public static final String ADD_PERF_AGGREGATE_SQL = "INSERT INTO perf_aggregate (entity_id,effective_date,perf_aggregate_id,l1,l2,l3,weight,return) VALUES (?,?,?,?,?,?,?,?)";
    public static final String FIND_ALL_PERF_AGGREGATE_SQL = "SELECT * FROM perf_Aggregate";
    public static final String FIND_COUNT_ALL_PERF_AGGREGATE= "Select Count(*) from perf_aggregate";
    public static final String FIND_PERF_AGGREGATE_BY_DATE_RANGE = "select * from perf_aggregate where effective_date between ? and ?";
    public static final String DELETE_PERF_AGGREGATE_BY_PERF_AGGREGATE_ID_SQL = "DELETE FROM perf_aggregate WHERE perf_aggregate_id=?";
    public static final String FIND_PERF_AGGREGATE_BY_PERF_AGGREGATE_ID_SQL = "SELECT * FROM perf_Aggregate WHERE perf_Aggregate_id=?";
    public static final String UPDATE_PERF_AGGREGATE_BY_PERF_AGGREGATE_ID_SQL = "UPDATE perf_Aggregate SET entity_id=?,effective_date=?,perf_aggregate_id=?,l1=?,l2=?,l3=?,weight=?,return=? WHERE perf_aggregate_id=?";
    public static final String FIND_PERF_AGGREGATE_BY_DATE_RANGE_AND_ENTITY_ID = "select * from perf_aggregate where effective_date between ? and ? and entity_id=?";
    public static final String EXISTS_PERF_AGGREGATE_BY_PERF_AGGREGATE_ID_SQL = "Select 1 from perf_aggregate where perf_aggregate_id=?";

}
