package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.PrestazioneEffettuataService;
import it.uniroma3.siw.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PrestazioneController {
	@Autowired
	PrestazioneEffettuataService prestazioneService;
	@Autowired
	CredentialsService credentialsService;
	@Autowired
	UtenteService utenteService;

	@GetMapping("/Prestazioni")
	public String prestazioni(Model model) {
		try {
			UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Credentials c = credentialsService.getCredentials(user.getUsername());
			if (c.getRole().equals(Credentials.DEFAULT_ROLE)) {
				return "redirect:/user/Prestazioni";
			} else {
				return "redirect:/barber/Prestazioni";
			}
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "index.html";
		}
	}

    @GetMapping("/user/Prestazioni")
    public String prestazioniUtente(Model model) {
        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Credentials c = credentialsService.getCredentials(user.getUsername());
            model.addAttribute("prestazioni", this.prestazioneService.findAllByUtente(c.getUser()));
            return "user/prestazioni";
        } catch(Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "index.html";
        }
    }

    @GetMapping("/barber/Prestazioni")
    public String prestazioniBarbiere(Model model) {
        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Credentials c = credentialsService.getCredentials(user.getUsername());
            model.addAttribute("prestazioni", this.prestazioneService.findAllByBarbiere(c.getUser()));
            return "barber/prestazioni";
        } catch(Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "index.html";
        }
    }

    @GetMapping("/admin/PrestazioniUtente/{id}")
    public String prestazioniUtente(@PathVariable("id") Long userId,Model model){
        try {
            model.addAttribute("prestazioni", this.prestazioneService.findAllByUtente(utenteService.getUser(userId)));
            return "admin/prestazioniUtente";
        } catch(Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "index.html";
        }
    }

    @GetMapping("/admin/PrestazioniBarbiere/{id}")
    public String prestazioniBarbiere(@PathVariable("id") Long barberId,Model model){
        try {
            model.addAttribute("prestazioni", this.prestazioneService.findAllByBarbiere(utenteService.getUser(barberId)));
            return "admin/prestazioniBarbiere";
        } catch(Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "index.html";
        }
    }

	@GetMapping("/admin/Prestazioni")
	public String getAllPrestazioni(@PathVariable("id") Long barberId, Model model) {
		model.addAttribute("prestazioni", this.prestazioneService.getAllPrestazioni());
		return "";
	}

}
