package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Utente;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.PrestazioneEffettuata;


public interface PrestazioneEffettuataRepository extends CrudRepository<PrestazioneEffettuata, Long> {
    Iterable<PrestazioneEffettuata> findAllByUtente(Utente u);

    Iterable<PrestazioneEffettuata> findAllByBarbiere(Utente u);
}
