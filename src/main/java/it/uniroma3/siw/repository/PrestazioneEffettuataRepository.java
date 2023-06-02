package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Utente;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.PrestazioneEffettuata;

import java.util.List;


public interface PrestazioneEffettuataRepository extends CrudRepository<PrestazioneEffettuata, Long> {
    List<PrestazioneEffettuata> findAllByUtente(Utente u);

    List<PrestazioneEffettuata> findAllByBarbiere(Utente u);
}
