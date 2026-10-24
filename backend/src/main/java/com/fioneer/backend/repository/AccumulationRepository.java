package com.fioneer.backend.repository;

import com.fioneer.backend.entity.Accumulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccumulationRepository extends JpaRepository<Accumulation, Long> {
}
