package com.typhonsoftwaresolutions.ipproj;

import com.typhonsoftwaresolutions.ipproj.model.Gerecht;
import com.typhonsoftwaresolutions.ipproj.model.service.GerechtenService;
import com.typhonsoftwaresolutions.ipproj.repository.GerechtenRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GerechtenServiceUnitTest {

    // Prevents CommandLineRunner in Application.class to run
    @MockBean
    private CommandLineRunner runner;

    @Autowired
    private GerechtenService gerechtenService;

    @MockBean
    private GerechtenRepository gerechtenRepository;

    private Gerecht gerechtKebab, gerechtSalade, gerechtSpaghetti;
    private List<Gerecht> gerechten;

    @Before
    public void setUp() {
        gerechtKebab = new Gerecht.GerechtBuilder("Kebab")
                .withPrijs(5d)
                .withType("DAGSCHOTEL")
                .build();

        gerechtSalade = new Gerecht.GerechtBuilder("Caesar Salade")
                .withPrijs(2d)
                .withType("VEGGIE")
                .build();

        gerechtSpaghetti = new Gerecht.GerechtBuilder("Spaghetti")
                .withPrijs(1d)
                .withType("DAGSCHOTEL")
                .build();

        gerechten = new ArrayList<>();
        gerechten.add(gerechtKebab);
        gerechten.add(gerechtSalade);
    }

    @Test
    public void should_find_gerecht_by_given_beschrijving() {
        Mockito.when(gerechtenRepository.findByBeschrijving(gerechtKebab.getBeschrijving())).thenReturn(gerechtKebab);

        Gerecht found = gerechtenService.findGerechtByBeschrijving(gerechtKebab.getBeschrijving());

        assertThat(found).isEqualTo(gerechtKebab);
    }

    @Test
    public void should_get_all_gerechten() {
        Mockito.when(gerechtenRepository.findAll()).thenReturn(gerechten);

        List<Gerecht> foundGerechten = gerechtenService.getAllGerechten();

        assertThat(foundGerechten.size()).isEqualTo(2);
        assertThat(foundGerechten).contains(gerechtKebab);
        assertThat(foundGerechten).contains(gerechtSalade);
    }

    @Test
    public void should_add_gerecht() {
        Mockito.when(gerechtenRepository.save(gerechtSpaghetti)).thenReturn(gerechtSpaghetti);

        Gerecht added = gerechtenService.addGerecht(gerechtSpaghetti);

        Mockito.verify(gerechtenRepository).save(added);
        assertThat(added).isEqualTo(gerechtSpaghetti);
    }

    @Test
    public void should_delete_gerecht() {
        Mockito.when(gerechtenRepository.findByBeschrijving(gerechtSpaghetti.getBeschrijving())).thenReturn(gerechtSpaghetti);

        gerechtenService.deleteGerecht(gerechtSpaghetti);

        Mockito.verify(gerechtenRepository).delete(gerechtSpaghetti);
    }

    @Test
    public void should_update_gerecht() {
        Mockito.when(gerechtenRepository.findById(gerechtSpaghetti.getId())).thenReturn(Optional.of(gerechtSpaghetti));

        Gerecht gerechtOld = gerechtenService.findGerechtById(gerechtSpaghetti.getId());

        Gerecht gerechtNew = new Gerecht.GerechtBuilder("Nieuwe bescrijving")
                .withPrijs(2d)
                .withType("SOEP")
                .build();

        gerechtenService.updateGerecht(gerechtOld.getId(), gerechtNew);

        Mockito.verify(gerechtenRepository).save(gerechtOld);

        assertThat(gerechtOld.getBeschrijving()).isEqualTo(gerechtNew.getBeschrijving());
        assertThat(gerechtOld.getPrijs()).isEqualTo(gerechtNew.getPrijs());
        assertThat(gerechtOld.getType()).isEqualTo(gerechtNew.getType());
    }
}
