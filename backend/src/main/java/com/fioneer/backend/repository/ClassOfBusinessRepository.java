package com.fioneer.backend.repository;

import com.fioneer.backend.entity.ClassOfBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClassOfBusinessRepository extends JpaRepository<ClassOfBusiness, Long> {

    Optional<ClassOfBusiness> findByName(String name);

    @Query("UPDATE ClassOfBusiness c SET c.name = :newName WHERE c.name = :oldName")
    Optional<ClassOfBusiness> updateClassOfBusiness(@Param("oldName") String oldName, @Param("newName") String newName);

    Integer deleteByName(String name);

}
