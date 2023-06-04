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
		return this.tipoPrestazioneRepository.findAllByOrderById();
	}

	public TipoPrestazione findById(Long id) throws Exception{
		TipoPrestazione tp= tipoPrestazioneRepository.findById(id).orElse(null);
		if (tp==null){
			throw new Exception("tipoPrestazione.notFound");
		}
		return tp;
	}

	public void save(TipoPrestazione tipoPrestazione) {
		tipoPrestazioneRepository.save(tipoPrestazione);
	}
	
	public void updateTipoP(Long id, Integer prezzo) throws Exception{
		TipoPrestazione tp = this.findById(id);
		tp.setPrezzo(prezzo);
		this.save(tp);
	}
}
