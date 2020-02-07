package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epam.entity.TrainerEntity;

@Repository
public interface RegisterRepository extends JpaRepository<TrainerEntity, Integer> {	

}
