package com.fioneer.backend.repository;

import com.fioneer.backend.entity.Accumulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccumulationRepository extends JpaRepository<Accumulation, Long> {

    @Query("SELECT a FROM Accumulation a LEFT JOIN FETCH a.coverages WHERE a.name = :name")
    Optional<Accumulation> findByName(String name);

    Integer deleteByName(String name);
}
