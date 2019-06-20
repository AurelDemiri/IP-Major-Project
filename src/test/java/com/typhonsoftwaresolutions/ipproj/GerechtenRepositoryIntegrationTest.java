package com.typhonsoftwaresolutions.ipproj;

import com.typhonsoftwaresolutions.ipproj.model.Gerecht;
import com.typhonsoftwaresolutions.ipproj.repository.GerechtenRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class GerechtenRepositoryIntegrationTest {

    // Prevents CommandLineRunner in Application.class to run
    @MockBean
    private CommandLineRunner runner;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GerechtenRepository gerechtenRepository;

    private Gerecht ietslekkers, ietsvies;

    @Before
    public void setUp() {
        ietslekkers = new Gerecht.GerechtBuilder("Lekker ding")
                .withPrijs(1d)
                .withType("SOEP")
                .build();

        ietsvies = new Gerecht.GerechtBuilder("Eten in de UCLL")
                .withPrijs(10d)
                .withType("DAGSCHOTEL")
                .build();
    }

    @Test
    public void should_get_all_feedbacks() {
        entityManager.persist(ietslekkers);
        entityManager.persist(ietsvies);
        entityManager.flush();

        List<Gerecht> gerechtenFound = gerechtenRepository.findAll();

        assertThat(gerechtenFound.size()).isEqualTo(2);
        assertThat(gerechtenFound).contains(ietslekkers);
        assertThat(gerechtenFound).contains(ietsvies);
    }

    @Test
    public void should_add_gerecht() {
        Gerecht idk = gerechtenRepository.save(ietslekkers);
        assertThat(gerechtenRepository.findByBeschrijving(ietslekkers.getBeschrijving())).isNotNull();
        assertThat(idk).isEqualTo(ietslekkers);
    }

    @Test
    public void should_delete_gerecht() {
        Gerecht idk = gerechtenRepository.save(ietslekkers);
        assertThat(idk).isEqualTo(ietslekkers);

        gerechtenRepository.delete(ietslekkers);

        assertThat(gerechtenRepository.findByBeschrijving(ietslekkers.getBeschrijving())).isNull();
    }

    @Test
    public void should_find_gerecht_by_given_beschrijving() {
        entityManager.persist(ietslekkers);
        entityManager.flush();
        assertThat(gerechtenRepository.findByBeschrijving(ietslekkers.getBeschrijving())).isNotNull();
    }
}
