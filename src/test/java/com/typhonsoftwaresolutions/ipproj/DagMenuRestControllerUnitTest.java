package com.typhonsoftwaresolutions.ipproj;

import com.typhonsoftwaresolutions.ipproj.controller.DagMenuController;
import com.typhonsoftwaresolutions.ipproj.model.DagMenu;
import com.typhonsoftwaresolutions.ipproj.model.Gerecht;
import com.typhonsoftwaresolutions.ipproj.model.service.DagMenuService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DagMenuController.class)
public class DagMenuRestControllerUnitTest {

    // Prevents CommandLineRunner in Application.class to run
    @MockBean
    private CommandLineRunner runner;

    @Autowired
    private MockMvc dagMenuController;

    @MockBean
    private DagMenuService service;

    private Gerecht gerechtPompoenSoep, gerechtSalade, gerechtSpaghetti, gerechtTomatenSoep, gerechtVeggieburger, gerechtKebab;
    private DagMenu vrijdag, zaterdag;

    @Before
    public void setUp() {
        gerechtPompoenSoep = new Gerecht.GerechtBuilder("Pompoensoep")
                .withPrijs(5d)
                .withType("SOEP")
                .build();

        gerechtSalade = new Gerecht.GerechtBuilder("Caesar Salade")
                .withPrijs(2d)
                .withType("VEGGIE")
                .build();

        gerechtSpaghetti = new Gerecht.GerechtBuilder("Spaghetti")
                .withPrijs(1d)
                .withType("DAGSCHOTEL")
                .build();

        vrijdag = new DagMenu.DagMenuBuilder(LocalDate.of(2019, 5, 24))
                .withSoep(gerechtPompoenSoep)
                .withDagschotel(gerechtSpaghetti)
                .withVeggie(gerechtSalade)
                .build();

        gerechtTomatenSoep = new Gerecht.GerechtBuilder("Tomatensoep")
                .withPrijs(2d)
                .withType("SOEP")
                .build();

        gerechtVeggieburger = new Gerecht.GerechtBuilder("Veggieburger")
                .withPrijs(4d)
                .withType("VEGGIE")
                .build();

        gerechtKebab = new Gerecht.GerechtBuilder("Kebab Mix")
                .withPrijs(6d)
                .withType("DAGSCHOTEL")
                .build();

        zaterdag = new DagMenu.DagMenuBuilder(LocalDate.of(2019, 5, 25))
                .withSoep(gerechtTomatenSoep)
                .withDagschotel(gerechtKebab)
                .withVeggie(gerechtVeggieburger)
                .build();
    }

    @Test
    public void givenTwoDagMenus_whenGetDagMenus_thenReturnJsonArray() throws Exception {

        Mockito.when(service.getAllDagMenu()).thenReturn(Arrays.asList(vrijdag, zaterdag));

        dagMenuController.perform(get("/dagmenu")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].datum").value("24-05-2019"))
                .andExpect(jsonPath("$[0].dag").value("VRIJDAG"))
                .andExpect(jsonPath("$[0].soep.beschrijving").value(vrijdag.getSoep().getBeschrijving()))
                .andExpect(jsonPath("$[0].soep.prijs").value(vrijdag.getSoep().getPrijs()))
                .andExpect(jsonPath("$[0].soep.type").value(vrijdag.getSoep().getType()))
                .andExpect(jsonPath("$[0].dagschotel.beschrijving").value(vrijdag.getDagschotel().getBeschrijving()))
                .andExpect(jsonPath("$[0].dagschotel.prijs").value(vrijdag.getDagschotel().getPrijs()))
                .andExpect(jsonPath("$[0].dagschotel.type").value(vrijdag.getDagschotel().getType()))
                .andExpect(jsonPath("$[0].veggie.beschrijving").value(vrijdag.getVeggie().getBeschrijving()))
                .andExpect(jsonPath("$[0].veggie.prijs").value(vrijdag.getVeggie().getPrijs()))
                .andExpect(jsonPath("$[0].veggie.type").value(vrijdag.getVeggie().getType()))
                .andExpect(jsonPath("$[1].datum").value("25-05-2019"))
                .andExpect(jsonPath("$[1].dag").value("ZATERDAG"))
                .andExpect(jsonPath("$[1].soep.beschrijving").value(zaterdag.getSoep().getBeschrijving()))
                .andExpect(jsonPath("$[1].soep.prijs").value(zaterdag.getSoep().getPrijs()))
                .andExpect(jsonPath("$[1].soep.type").value(zaterdag.getSoep().getType()))
                .andExpect(jsonPath("$[1].dagschotel.beschrijving").value(zaterdag.getDagschotel().getBeschrijving()))
                .andExpect(jsonPath("$[1].dagschotel.prijs").value(zaterdag.getDagschotel().getPrijs()))
                .andExpect(jsonPath("$[1].dagschotel.type").value(zaterdag.getDagschotel().getType()))
                .andExpect(jsonPath("$[1].veggie.beschrijving").value(zaterdag.getVeggie().getBeschrijving()))
                .andExpect(jsonPath("$[1].veggie.prijs").value(zaterdag.getVeggie().getPrijs()))
                .andExpect(jsonPath("$[1].veggie.type").value(zaterdag.getVeggie().getType()));
    }
}