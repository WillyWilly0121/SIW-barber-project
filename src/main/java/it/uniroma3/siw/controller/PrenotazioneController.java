package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.TipoPrestazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.PrenotazioneService;
import it.uniroma3.siw.service.UtenteService;

import java.time.LocalDate;
import java.util.*;

@Controller
public class PrenotazioneController {

    @Autowired
    private CredentialsService credentialsService;
    @Autowired
    PrenotazioneService prenotazioneService;
    @Autowired
    UtenteService utenteService;
    @Autowired
    TipoPrestazioneService tipoPrestazioneService;

    @GetMapping("/user/resocontoPrenotazione/{id}")
    public String resocontoPrenotazione(@PathVariable("id") Long id, Model model) {
        try {
            Prenotazione p = this.prenotazioneService.getPrenotazione(id);
            model.addAttribute("prenotazione", p);
            return "/user/resocontoPrenotazione.html";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "index.html";
        }
    }

    @GetMapping("/Prenotazioni")
    public String prenotazioni(Model model) {
        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Credentials c = credentialsService.getCredentials(user.getUsername());
            if (c.getRole().equals(Credentials.DEFAULT_ROLE)) {
                return "redirect:/user/Prenotazioni";
            } else {
                return "redirect:/barber/Prenotazioni";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "index.html";
        }
    }

    @GetMapping("/user/Prenotazioni")
    public String prenotazioniUtente(Model model) {
        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Credentials c = credentialsService.getCredentials(user.getUsername());
            model.addAttribute("prenotazioni", this.prenotazioneService.findAllByUtente(c.getUser()));
            return "";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "index.html";
        }
    }

    @GetMapping("/barber/Prenotazioni")
    public String prenotazioniBarbiere(Model model) {
        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Credentials c = credentialsService.getCredentials(user.getUsername());
            model.addAttribute("prenotazioni", this.prenotazioneService.findAllByBarbiere(c.getUser()));
            return "";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "index.html";
        }
    }

    @GetMapping("/admin/PrenotazioniUtente/{id}")
    public String prenotazioniUtente(@PathVariable("id") Long userId, Model model) {
        try {
            model.addAttribute("prenotazioni", this.prenotazioneService.findAllByUtente(utenteService.getUser(userId)));
            return "";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "index.html";
        }
    }

    @GetMapping("/user/prenota")
    public String scegliTipoPrestazione(Model model) {
        model.addAttribute("tipiPrestazione", tipoPrestazioneService.findAllTipiPrestazioni());
        return "user/scegliTipoPrestazione";
    }

    @GetMapping("/user/prenota/{tp}")
    public String scegliBarbiere(@PathVariable("tp") Long tp, Model model) {
        model.addAttribute("idTP", tp);
        model.addAttribute("barbieri", utenteService.getAllBarbieri());
        return "user/scegliBarbiere";
    }

    @GetMapping("/user/prenota/{tp}/{bar}")
    public String scegliData(@PathVariable("tp") Long tp, @PathVariable("bar") Long barberId, Model model) {
        model.addAttribute("idTP", tp);
        model.addAttribute("idBar", barberId);

        Map<LocalDate, Integer> numeroPrenotazioniPerData = prenotazioneService.ottieniNumeroPrenotazioniPerData(barberId);
        Map<LocalDate, Boolean> opzioniDate = new HashMap<>();

        int count = 1;
        for (LocalDate date = LocalDate.now(); count <= 7; count++) {
            LocalDate data = date.plusDays(count);
            Integer amount = numeroPrenotazioniPerData.get(data);

            opzioniDate.put(data,amount==null || amount < Utente.maxPrenotazioniGiornaliere);
        }

        model.addAttribute("opzioniDate", opzioniDate);
        return "user/scegliData";
    }

    @GetMapping("/user/prenota/{tp}/{bar}/{data}")
    public String confermaPrenotazione(@PathVariable("tp") Long tp, @PathVariable("bar") Long barberId, @PathVariable("data") String data, Model model) {
        try {
            UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Credentials c = credentialsService.getCredentials(user.getUsername());
            LocalDate giorno = LocalDate.parse(data);

            Prenotazione p = new Prenotazione();
            p.setBarbiere(utenteService.getUser(barberId));
            p.setPrestazione(tipoPrestazioneService.findById(tp));
            p.setUtente(c.getUser());
            p.setDataPrestazione(giorno);
            p.setDataPrenotazione(LocalDate.now());

            prenotazioneService.salvaPrenotazione(p);

            return "redirect:/user/resocontoPrenotazione/" + p.getId();
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "/index";
        }
    }

    @GetMapping("/admin/PrenotazioniBarbiere/{id}")
    public String prenotazioniBarbiere(@PathVariable("id") Long barberId, Model model) {
        try {
            model.addAttribute("prenotazioni",
                    this.prenotazioneService.findAllByBarbiere(utenteService.getUser(barberId)));
            return "";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "index.html";
        }
    }

    @GetMapping("/admin/Prenotazioni")
    public String getAllPrenotazioni(@PathVariable("id") Long barberId, Model model) {
        model.addAttribute("prenotazioni", this.prenotazioneService.getAllPrenotazioni());
        return "";
    }

}
