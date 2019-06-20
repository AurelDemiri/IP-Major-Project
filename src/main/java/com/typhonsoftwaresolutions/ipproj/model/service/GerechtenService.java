package com.typhonsoftwaresolutions.ipproj.model.service;

import com.typhonsoftwaresolutions.ipproj.model.Gerecht;
import com.typhonsoftwaresolutions.ipproj.repository.GerechtenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerechtenService {

    private final GerechtenRepository gerechtenRepository;

    @Autowired
    public GerechtenService(GerechtenRepository gerechtenRepository) {
        this.gerechtenRepository = gerechtenRepository;
    }

    public List<Gerecht> getAllGerechten() {
        return gerechtenRepository.findAll();
    }

    public Gerecht addGerecht(Gerecht gerecht) {
        gerechtenRepository.save(gerecht);
        return gerecht;
    }

    public void deleteGerecht(Gerecht gerecht) {
        gerechtenRepository.delete(gerecht);
    }

    public Gerecht findGerechtById(Integer id) {
        Gerecht x = gerechtenRepository.findById(id).orElse(null);

        if (x != null)
            return x;

        throw new IllegalArgumentException("Gerecht with this id could not be found");
    }

    public Gerecht findGerechtByBeschrijving(String beschrijving) {
        Gerecht x = gerechtenRepository.findByBeschrijving(beschrijving);

        if (x != null)
            return x;

        throw new IllegalArgumentException("Gerecht with this beschrijving could not be found");
    }

    public void updateGerecht(Integer id, Gerecht gerecht) {
        Gerecht foundGerecht = findGerechtById(id);
        foundGerecht.setBeschrijving(gerecht.getBeschrijving());
        foundGerecht.setPrijs(gerecht.getPrijs());
        foundGerecht.setType(gerecht.getType());

        gerechtenRepository.save(foundGerecht);
    }
}
