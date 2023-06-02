package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.PrestazioneEffettuata;
import it.uniroma3.siw.repository.PrestazioneEffettuataRepository;

import java.util.List;

@Service
public class PrestazioneEffettuataService {

    @Autowired
    private PrestazioneEffettuataRepository prestazioneEffettuataRepository;

    public Iterable<PrestazioneEffettuata> getAllPrestazioni() {
        return this.prestazioneEffettuataRepository.findAll();
    }

    public List<PrestazioneEffettuata> findAllByUtente(Utente u) {
        return this.prestazioneEffettuataRepository.findAllByUtente(u);
    }

    public List<PrestazioneEffettuata> findAllByBarbiere(Utente u) {
        return this.prestazioneEffettuataRepository.findAllByBarbiere(u);
    }

}
