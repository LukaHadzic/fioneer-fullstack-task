package com.fioneer.backend.repository;

import com.fioneer.backend.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status,Long> {
}
