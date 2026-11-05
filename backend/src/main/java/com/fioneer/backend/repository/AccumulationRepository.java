package com.fioneer.backend.repository;

import com.fioneer.backend.dto.AccumulationsCreatedPerDateDto;
import com.fioneer.backend.dto.AverageSumInsuredPerStatusDto;
import com.fioneer.backend.entity.Accumulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccumulationRepository extends JpaRepository<Accumulation, Long> {

    @Query("SELECT a FROM Accumulation a LEFT JOIN FETCH a.coverages WHERE a.name = :name")
    Optional<Accumulation> findByName(String name);

    @Query("SELECT new com.fioneer.backend.dto.AverageSumInsuredPerStatusDto(s.status, COALESCE(AVG(c.sumInsured), 0.0)) " +
            "FROM Accumulation a " +
            "LEFT JOIN a.status s " +
            "LEFT JOIN a.coverages c " +
            "GROUP BY s.status")
    List<AverageSumInsuredPerStatusDto> getAverageSumInsuredPerStatus();

    @Query("SELECT new com.fioneer.backend.dto.AccumulationsCreatedPerDateDto(a.dateCreated, COUNT(*)) " +
            "FROM Accumulation a " +
            "GROUP BY a.dateCreated " +
            "ORDER BY a.dateCreated ASC")
    List<AccumulationsCreatedPerDateDto> getAccumulationsCreatedPerDate();

    Integer deleteByName(String name);
}
