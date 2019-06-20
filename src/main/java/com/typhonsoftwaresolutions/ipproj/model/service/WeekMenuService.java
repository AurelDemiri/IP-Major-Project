package com.typhonsoftwaresolutions.ipproj.model.service;

import com.typhonsoftwaresolutions.ipproj.model.DagMenu;
import com.typhonsoftwaresolutions.ipproj.repository.DagMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeekMenuService {
    private final DagMenuRepository dagMenuRepository;

    @Autowired
    public WeekMenuService(DagMenuRepository dagMenuRepository) {
        this.dagMenuRepository = dagMenuRepository;
    }

    public List<DagMenu> getWeekMenu(Integer jaar, Integer weekNr) {
        if (jaar < 0)
            throw new IllegalArgumentException("jaar is invalid");

        if (weekNr < 1 || weekNr > 52)
            throw new IllegalArgumentException("weekNr is invalid");

        LocalDate startDatum = LocalDate.of(jaar, 7, 1)
                .with(WeekFields.ISO.weekOfWeekBasedYear(), weekNr)
                .with(WeekFields.ISO.dayOfWeek(), 1);

        List<DagMenu> dagMenus = new ArrayList<>(7);
        for (int i = 0; i < 7; i++) {
            dagMenuRepository.findById(startDatum.plusDays(i)).ifPresent(dagMenus::add);
        }

        return dagMenus;
    }
}
