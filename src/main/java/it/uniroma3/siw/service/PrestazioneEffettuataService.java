package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.PrestazioneEffettuata;
import it.uniroma3.siw.repository.PrestazioneEffettuataRepository;

@Service
public class PrestazioneEffettuataService {
	
	@Autowired
	private PrestazioneEffettuataRepository prestazioneEffettuataRepository;
	
	public Iterable<PrestazioneEffettuata> getAllPrestazioni(){
		return this.prestazioneEffettuataRepository.findAll();
	}

	public Iterable<PrestazioneEffettuata> findAllByUtente(Utente u){
		return this.prestazioneEffettuataRepository.findAllByUtente(u);
	}

	public Iterable<PrestazioneEffettuata> findAllByBarbiere(Utente u){
		return this.prestazioneEffettuataRepository.findAllByBarbiere(u);
	}

}
