package com.fioneer.backend.repository;

import com.fioneer.backend.entity.Accumulation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccumulationRepository extends JpaRepository<Accumulation, Long> {

    Optional<Accumulation> findByName(String name);
}
