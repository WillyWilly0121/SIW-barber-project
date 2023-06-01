package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Prenotazione;
import it.uniroma3.siw.model.TipoPrestazione;
import it.uniroma3.siw.service.TipoPrestazioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TipoPrestazioneController {
    @Autowired
    TipoPrestazioneService tipoPrestazioneService;

    @GetMapping("/admin/formNewTipoPrestazione")
    public String formNuovoTipoPrestazione(Model model) {
        model.addAttribute("tipoPrestazione", new TipoPrestazione());
        return "/admin/formNewTipoPrestazione.html";
    }
    @PostMapping("/admin/addTipoPrestazione")
    public String addTipoPrestazione(@Valid @ModelAttribute("tipoPrestazione")TipoPrestazione tipoPrestazione){
        tipoPrestazioneService.save(tipoPrestazione);
        return "redirect:/index.html";
    }
}
