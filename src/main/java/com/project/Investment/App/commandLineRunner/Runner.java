package com.project.Investment.App.commandLineRunner;

import com.project.Investment.App.model.Entity;
import com.project.Investment.App.model.EntityId;
import com.project.Investment.App.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Runner implements CommandLineRunner {

    @Autowired
    private EntityRepository repository;

    @Override
    public void run(String... args) throws Exception {
        List<Entity> entities = new ArrayList<>();

        entities.add(Entity.builder()
                .entityId((new EntityId("B001",LocalDate.parse("2020-02-09"))))
                .entityName("Benchmark 1")
                .entityType("Benchmark")
                .defaultBenchmarkId(null).build());

        entities.add(Entity.builder()
                .entityId(new EntityId("B002",LocalDate.parse("2020-03-10")))
                .entityName("Benchmark 2")
                .entityType("Benchmark")
                .defaultBenchmarkId(null).build());

        entities.add(Entity.builder()
                .entityId(new EntityId("B003",LocalDate.parse("2020-04-11")))
                .entityName("Benchmark 3")
                .entityType("Benchmark")
                .defaultBenchmarkId(null).build());

        entities.add(Entity.builder()
                .entityId(new EntityId("B004",LocalDate.parse("2020-05-12")))
                .entityName("Benchmark 4")
                .entityType("Benchmark")
                .defaultBenchmarkId(null).build());

        entities.add(Entity.builder()
                .entityId(new EntityId("B005",LocalDate.parse("2020-02-09")))
                .entityName("Benchmark 5")
                .entityType("Benchmark")
                .defaultBenchmarkId(null).build());

        repository.saveAll(entities);
    }


}
