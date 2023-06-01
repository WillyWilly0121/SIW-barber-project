package it.uniroma3.siw.service;

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
}
