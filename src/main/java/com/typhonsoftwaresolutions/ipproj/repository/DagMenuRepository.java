package com.typhonsoftwaresolutions.ipproj.repository;

import com.typhonsoftwaresolutions.ipproj.model.DagMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DagMenuRepository extends JpaRepository<DagMenu, LocalDate> {
}