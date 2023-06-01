package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.UtenteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtenteService {

    @Autowired
    protected UtenteRepository userRepository;
    
    @Autowired
    protected CredentialsRepository credentialsRepository;

    @Transactional
    public Utente getUser(Long id) throws Exception{
        Utente result = this.userRepository.findById(id).orElse(null);
        if(result==null) {
        	throw new Exception("utente.notFound"); 
        } else {
        	return result;
        }
    }

    @Transactional
    public Utente saveUser(Utente user) {
        return this.userRepository.save(user);
    }

    @Transactional
    public List<Utente> getAllUsers() {
        List<Utente> result = new ArrayList<>();
        Iterable<Utente> iterable = this.userRepository.findAll();
        for(Utente user : iterable)
            result.add(user);
        return result;
    }
    
    public List<Utente> getAllBarbieri() {
    	Iterable<Credentials> credenziali = this.credentialsRepository.findAllByRole(Credentials.BARBER_ROLE);
    	ArrayList<Utente> utenti = new ArrayList<>();
    	for(Credentials credenziale : credenziali) {
    		utenti.add(credenziale.getUser());
    	}
    	return utenti;
    }
}