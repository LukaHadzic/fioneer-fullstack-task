package com.fioneer.backend.repository;

import com.fioneer.backend.entity.Coverage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoverageRepository extends JpaRepository<Coverage, Long> {


    Optional<Coverage> findByName (String name);

}
