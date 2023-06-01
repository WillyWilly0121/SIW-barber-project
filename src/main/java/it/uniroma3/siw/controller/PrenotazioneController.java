package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.PrenotazioneService;
import it.uniroma3.siw.service.UtenteService;
import jakarta.validation.Valid;

@Controller
public class PrenotazioneController {
	
	@Autowired 
	private CredentialsService credentialsService;
	@Autowired 
	PrenotazioneService prenotazioneService;
	@Autowired 
	UtenteService utenteService;
	
	@GetMapping("/user/formNewPrenotazione")
	public String formNuovaPrenotazione(Model model) {
		model.addAttribute("prenotazione", new Prenotazione());
		return "formNewPrenotazione.html";
	}
	
	@GetMapping("/user/resocontoPrenotazione/{id}")
	public String resocontoPrenotazione(@PathVariable("id") Long id, Model model) {
		try {
			Prenotazione p = this.prenotazioneService.getPrenotazione(id);
			model.addAttribute("prenotazione", p);
			return "confermaPrenotazione.html";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "index.html";
		}
	}
	
	@PostMapping("/user/nuovaPrenotazione")
	public String newPrenotazione(@Valid @ModelAttribute("prenotazione") Prenotazione prenotazione, BindingResult bindingResult, Model model) {
		if(!bindingResult.hasErrors()) {
			prenotazioneService.salvaPrenotazione(prenotazione);
			return "redirect:/user/resocontoPrenotazione/" + prenotazione.getId();
		} else {
			return "formNewPrenotazione";
		}
	}
	
	@GetMapping("/Prenotazioni/{id}")
	public String prenotazioni(@PathVariable("id") Long id, Model model) {
		try {
			Credentials c = this.credentialsService.getCredentials(id);
			if(c.getRole().equals(Credentials.DEFAULT_ROLE)) {
				return "redirect:/user/PrenotazioniUtente/" + c.getUser().getId();
			} else {
				return "redirect:/barber/PrenotazioniBarbiere/" + c.getUser().getId();
			}
		} catch(Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "index.html";
		}
	}
	
	@GetMapping("/user/PrenotazioniUtente/{id}")
	public String prenotazioniUtente(@PathVariable("id") Long id, Model model) {
		try {
			Utente u = this.utenteService.getUser(id);
			model.addAttribute("prenotazioni", this.prenotazioneService.findAllByUtente(u));
			return "";
		} catch(Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "index.html";
		}
	}
	
	@GetMapping("/barber/PrenotazioniBarbiere/{id}")
	public String prenotazioniBarbiere(@PathVariable("id") Long id, Model model) {
		try {
			Utente u = this.utenteService.getUser(id);
			model.addAttribute("prenotazioni", this.prenotazioneService.findAllByBarbiere(u));
			return "";
		} catch(Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "index.html";
		}
	}
	
}
