package com.typhonsoftwaresolutions.ipproj.repository;

import com.typhonsoftwaresolutions.ipproj.model.Gerecht;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerechtenRepository extends JpaRepository<Gerecht, Integer> {
    Gerecht findByBeschrijving(String beschrijving);
}