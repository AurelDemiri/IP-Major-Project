package com.typhonsoftwaresolutions.ipproj;

import com.typhonsoftwaresolutions.ipproj.model.DagMenu;
import com.typhonsoftwaresolutions.ipproj.model.Gerecht;
import com.typhonsoftwaresolutions.ipproj.repository.DagMenuRepository;
import com.typhonsoftwaresolutions.ipproj.repository.GerechtenRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner runner(GerechtenRepository gerechtenRepository, DagMenuRepository dagMenuRepository) {
        return args -> {
            gerechtenRepository.save(new Gerecht("Bloemkoolsoep", 1.20d, "SOEP"));
            gerechtenRepository.save(new Gerecht("Tomatensoep", 1.30d, "SOEP"));
            gerechtenRepository.save(new Gerecht("Pompoensoep", 0.90d, "SOEP"));
            gerechtenRepository.save(new Gerecht("Broccolisoep", 1.20d, "SOEP"));
            gerechtenRepository.save(new Gerecht("Champignonsoep", 1.60d, "SOEP"));
            gerechtenRepository.save(new Gerecht("Groentesoep", 1.60d, "SOEP"));
            gerechtenRepository.save(new Gerecht("Kippensoep", 2.00d, "SOEP"));
            gerechtenRepository.save(new Gerecht("Misosoep", 1.30d, "SOEP"));

            gerechtenRepository.save(new Gerecht("Cordon blue", 4.20d, "DAGSCHOTEL"));
            gerechtenRepository.save(new Gerecht("Konijn met pruimen", 6.50d, "DAGSCHOTEL"));
            gerechtenRepository.save(new Gerecht("Rode kool met appeltjes en pensen", 3.10d, "DAGSCHOTEL"));
            gerechtenRepository.save(new Gerecht("Spaghetti Bolognese", 5.30d, "DAGSCHOTEL"));
            gerechtenRepository.save(new Gerecht("Konijn met trappist en spruitjes", 9.20d, "DAGSCHOTEL"));
            gerechtenRepository.save(new Gerecht("Gevulde tomaten en paprika's", 8.40d, "DAGSCHOTEL"));
            gerechtenRepository.save(new Gerecht("Chili con carne", 7.90d, "DAGSCHOTEL"));
            gerechtenRepository.save(new Gerecht("Vispannetje", 4.60d, "DAGSCHOTEL"));

            gerechtenRepository.save(new Gerecht("Groentenlasange", 3.10d, "VEGGIE"));
            gerechtenRepository.save(new Gerecht("Veggie pasta", 4.80d, "VEGGIE"));
            gerechtenRepository.save(new Gerecht("Couscous met venkel en bonen", 5.20d, "VEGGIE"));
            gerechtenRepository.save(new Gerecht("Chili sin carne", 4.80d, "VEGGIE"));
            gerechtenRepository.save(new Gerecht("Gevulde aubergine met ei en kaas", 3.10d, "VEGGIE"));
            gerechtenRepository.save(new Gerecht("Groentemix met aardappelvulling", 6.60d, "VEGGIE"));
            gerechtenRepository.save(new Gerecht("Veggiesatés", 7.30d, "VEGGIE"));
            gerechtenRepository.save(new Gerecht("Pitabroodjes met spinazie en walnoot", 2.20d, "VEGGIE"));


            Random random = new Random(0xC0FFEE);
            List<Gerecht> gerechtenSoep = gerechtenRepository.findAll().stream().filter(x -> x.getType().equals("SOEP")).collect(Collectors.toList());
            List<Gerecht> gerechtenDagschotel = gerechtenRepository.findAll().stream().filter(x -> x.getType().equals("DAGSCHOTEL")).collect(Collectors.toList());
            List<Gerecht> gerechtenVeggie = gerechtenRepository.findAll().stream().filter(x -> x.getType().equals("VEGGIE")).collect(Collectors.toList());

            LocalDate startDatum = LocalDate.of(2018, 12, 31);

            // 31-12-2018 tot 02-06-2019 (154 dagen)
            for (int i = 0; i < 154; i++) {
                dagMenuRepository.save(
                        new DagMenu(
                                startDatum.plusDays(i),
                                gerechtenSoep.get(random.nextInt(gerechtenSoep.size() - 1)),
                                gerechtenDagschotel.get(random.nextInt(gerechtenDagschotel.size() - 1)),
                                gerechtenVeggie.get(random.nextInt(gerechtenVeggie.size() - 1))
                        )
                );
            }
        };
    }
}
