package it.uniroma3.siw.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.Utente;

import org.springframework.data.repository.query.Param;

public interface PrenotazioneRepository extends CrudRepository<Prenotazione, Long>{

    @Query(nativeQuery = true, value = "select data_prestazione, count(*) from prenotazione where barbiere= :barberId group by data_prestazione")
    Iterable<Object[]> ottieniNumeroPrenotazioniPerData(@Param("barberId") Long barberId);

    Integer countPrenotazioneByBarbiere_IdAndDataPrestazione(Long barberId, LocalDate data);
    
    List<Prenotazione> findAllByUtente(Utente u);
    
    List<Prenotazione> findAllByBarbiere(Utente u);
}
