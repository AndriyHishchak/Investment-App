package com.project.Investment.App.repository;

import com.project.Investment.App.model.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityRepository extends JpaRepository<Entity,String> {
}
