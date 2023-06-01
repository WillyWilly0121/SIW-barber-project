package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.TipoPrestazione;
import it.uniroma3.siw.repository.TipoPrestazioneRepository;

@Service
public class TipoPrestazioneService {
	
	@Autowired
	private TipoPrestazioneRepository tipoPrestazioneRepository;
	
	public Iterable<TipoPrestazione> findAllTipiPrestazioni(){
		return this.tipoPrestazioneRepository.findAll();
	}

}
