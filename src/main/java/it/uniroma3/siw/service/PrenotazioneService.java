package it.uniroma3.siw.service;

import java.time.LocalDate;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.PrestazioneEffettuata;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.PrenotazioneRepository;
import it.uniroma3.siw.repository.PrestazioneEffettuataRepository;
import jakarta.transaction.Transactional;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private PrestazioneEffettuataRepository prestazioneEffettuataResository;

    public Prenotazione getPrenotazione(Long id) throws Exception {
        Prenotazione pr = this.prenotazioneRepository.findById(id).orElse(null);
        if (pr == null) {
            throw new Exception("prenotazione.notFound");
        } else {
            return pr;
        }
    }

    public Map<LocalDate, Integer> ottieniNumeroPrenotazioniPerData(Long barberId) {
        HashMap<LocalDate, Integer> result = new HashMap<>();
        for (Object[] tuple : prenotazioneRepository.ottieniNumeroPrenotazioniPerData(barberId)) {
            LocalDate data = LocalDate.parse(tuple[0].toString());
            Integer count = Integer.parseInt(tuple[1].toString());
            result.put(data, count);
        }
        return result;
    }

    @Transactional
    public Prenotazione salvaPrenotazione(Prenotazione prenotazione) throws Exception {
        if (prenotazioneRepository.countPrenotazioneByBarbiere_IdAndDataPrestazione(prenotazione.getBarbiere().getId(), prenotazione.getDataPrestazione()) >= Utente.maxPrenotazioniGiornaliere) {
            throw new Exception("prenotazione.MaxAmountReached");
        }
        return this.prenotazioneRepository.save(prenotazione);
    }

    public Iterable<Prenotazione> getAllPrenotazioni() {
        return this.prenotazioneRepository.findAll();
    }

    @Transactional
    public void eliminaPrenotazione(Long id) throws Exception{
    	Prenotazione pr = this.prenotazioneRepository.findById(id).orElse(null);
        if (pr == null) {
            throw new Exception("prenotazione.notFound");
        } else {
            this.prenotazioneRepository.delete(pr);
        }
    }

    @Transactional
    public void effettuaPrenotazione(Long id) throws Exception {
        PrestazioneEffettuata p = new PrestazioneEffettuata();
        Prenotazione pr = this.prenotazioneRepository.findById(id).orElse(null);
        if (pr == null) {
            throw new Exception("prenotazione.notFound");
        } else {
            p.setBarbiere(pr.getBarbiere());
            p.setDataPrestazione(pr.getDataPrestazione());
            p.setPrestazione(pr.getPrestazione());
            p.setBarbiere(pr.getBarbiere());
            p.setUtente(pr.getUtente());
            this.prestazioneEffettuataResository.save(p);
            this.eliminaPrenotazione(id);
        }
    }

    public Integer countPrenotazioneByBarbiere_IdAndDataPrestazione(Long barberId, LocalDate data) {
        return prenotazioneRepository.countPrenotazioneByBarbiere_IdAndDataPrestazione(barberId, data);
    }

    public List<Prenotazione> findAllByUtente(Utente u) {
        return this.prenotazioneRepository.findAllByUtente(u);
    }

    public List<Prenotazione> findAllByBarbiere(Utente u) {
        return this.prenotazioneRepository.findAllByBarbiere(u);
    }
}
