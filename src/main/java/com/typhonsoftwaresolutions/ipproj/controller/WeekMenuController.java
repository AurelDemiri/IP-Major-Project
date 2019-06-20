package com.typhonsoftwaresolutions.ipproj.controller;

import com.typhonsoftwaresolutions.ipproj.model.DagMenu;
import com.typhonsoftwaresolutions.ipproj.model.service.WeekMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class WeekMenuController {

    private final WeekMenuService weekMenuService;

    @Autowired
    public WeekMenuController(WeekMenuService weekMenuService) {
        this.weekMenuService = weekMenuService;
    }

    @GetMapping("/weekmenu/{jaar}/{weekNr}")
    public List<DagMenu> getWeekMenu(@PathVariable("jaar") Integer jaar, @PathVariable("weekNr") Integer weekNr) {
        return weekMenuService.getWeekMenu(jaar, weekNr);
    }
}
