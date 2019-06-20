package com.typhonsoftwaresolutions.ipproj.controller;

import com.typhonsoftwaresolutions.ipproj.model.DagMenu;
import com.typhonsoftwaresolutions.ipproj.model.service.DagMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
public class DagMenuController {

    private final DagMenuService dagMenuService;

    @Autowired
    public DagMenuController(DagMenuService dagMenuService) {
        this.dagMenuService = dagMenuService;
    }

    @GetMapping("/dagmenu")
    public List<DagMenu> getAllDagMenu() {
        return dagMenuService.getAllDagMenu();
    }

    @GetMapping("/dagmenu/{datum}")
    public DagMenu getDagMenu(@PathVariable("datum") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate datum) {
        return dagMenuService.getDagMenu(datum);
    }

    @PostMapping("/dagmenu/add")
    public List<DagMenu> addDagMenu(@RequestBody @Valid DagMenu dagMenu) {
        dagMenuService.addDagMenu(dagMenu);
        return dagMenuService.getAllDagMenu();
    }

    @PatchMapping("/dagmenu/change/{datum}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeDagMenu(@PathVariable("datum") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate datum, @RequestBody DagMenu dagMenu) {
        dagMenuService.patchDagMenu(datum, dagMenu);
    }

    @DeleteMapping("/dagmenu/delete/{datum}")
    public void deleteDagMenu(@PathVariable("datum") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate datum) {
        dagMenuService.deleteDagMenu(datum);
    }
}
