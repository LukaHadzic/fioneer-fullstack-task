package com.fioneer.backend.repository;

import com.fioneer.backend.entity.Status;
import com.fioneer.backend.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StatusRepository extends JpaRepository<Status,Long> {

    Optional<Status> findByStatus(String name);

    @Query("UPDATE Status s SET s.status = :newStatus WHERE s.status = :oldStatus")
    Optional<Status> updateStatus(@Param("oldStatus") String oldStatus, @Param("newStatus") String newStatus);

    Integer deleteByStatus(String status);
}
