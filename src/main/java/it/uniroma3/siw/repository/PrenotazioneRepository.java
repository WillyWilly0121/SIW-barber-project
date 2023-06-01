package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.Utente;

import org.springframework.data.repository.query.Param;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long>{

    @Query(nativeQuery = true, value = "select data_prenotazione, count(*) from prenotazione where barbiere= :barberId group by data_prenotazione")
    Iterable<Object[]> ottieniNumeroPrenotazioniPerData(@Param("barberId") Long barberId);
    
    List<Prenotazione> findAllByUtente(Utente u);
    
    List<Prenotazione> findAllByBarbiere(Utente u);
}
