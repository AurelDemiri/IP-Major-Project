package com.typhonsoftwaresolutions.ipproj.model.service;

import com.typhonsoftwaresolutions.ipproj.model.DagMenu;
import com.typhonsoftwaresolutions.ipproj.repository.DagMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DagMenuService {

    private final DagMenuRepository dagMenuRepository;

    @Autowired
    public DagMenuService(DagMenuRepository dagMenuRepository) {
        this.dagMenuRepository = dagMenuRepository;
    }

    public List<DagMenu> getAllDagMenu() {
        return dagMenuRepository.findAll();
    }

    public DagMenu getDagMenu(LocalDate datum) {
        DagMenu foundDagMenu = dagMenuRepository.findById(datum).orElse(null);

        if (foundDagMenu == null)
            throw new IllegalArgumentException("Could not find dagmenu");

        return foundDagMenu;
    }

    public void addDagMenu(DagMenu dagMenu) {
        if (dagMenuRepository.findById(dagMenu.getDatum()).isPresent())
            throw new IllegalArgumentException("Dagmenu with this datum already exists");

        dagMenuRepository.save(dagMenu);
    }

    public void patchDagMenu(LocalDate datum, DagMenu dagMenu) {
        DagMenu foundDagMenu = getDagMenu(datum);

        if (dagMenu.getSoep() != null)
            foundDagMenu.setSoep(dagMenu.getSoep());

        if (dagMenu.getDagschotel() != null)
            foundDagMenu.setDagschotel(dagMenu.getDagschotel());

        if (dagMenu.getVeggie() != null)
            foundDagMenu.setVeggie(dagMenu.getVeggie());

        dagMenuRepository.save(foundDagMenu);
    }

    public void deleteDagMenu(LocalDate datum) {
        dagMenuRepository.delete(getDagMenu(datum));
    }
}
