package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.entity.TestEntity;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Integer> {

}
