package it.uniroma3.siw.service;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.PrestazioneEffettuata;
import it.uniroma3.siw.repository.PrenotazioneRepository;
import it.uniroma3.siw.repository.PrestazioneEffettuataRepository;
import jakarta.transaction.Transactional;

@Service
public class PrenotazioneService {
	
	@Autowired
	private PrenotazioneRepository prenotazioneRepository;
	
	@Autowired
	private PrestazioneEffettuataRepository prestazioneEffettuataResository;
	

    Map<Date,Integer> ottieniNumeroPrenotazioniPerData(Long barberId) {
        HashMap<Date,Integer> result = new HashMap<>();
        for (Object[] tuple: prenotazioneRepository.ottieniNumeroPrenotazioniPerData(barberId)){
            Date data = (Date) tuple[0];
            Integer count= (Integer) tuple[1];
            result.put(data,count);
        }
        return result;
    }
	
	@Transactional
	public Prenotazione salvaPrenotazione(Prenotazione prenotazione) {
		return this.prenotazioneRepository.save(prenotazione);
	}
	
	public Iterable<Prenotazione> getAllPrenotazioni(){
		return this.prenotazioneRepository.findAll();
	}
	
	@Transactional
	public void eliminaPrenotazione(Long id) {
		this.prenotazioneRepository.deleteById(id);
	}
	
	@Transactional
	public void effettuaPrenotazione(Long id) throws Exception{
		PrestazioneEffettuata p = new PrestazioneEffettuata();
		Prenotazione pr = this.prenotazioneRepository.findById(id).orElse(null);
		if(pr==null) {
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
}
