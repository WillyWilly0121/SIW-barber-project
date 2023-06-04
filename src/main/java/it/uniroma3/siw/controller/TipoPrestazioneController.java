package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.TipoPrestazione;
import it.uniroma3.siw.service.TipoPrestazioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String addTipoPrestazione(@Valid @ModelAttribute("tipoPrestazione") TipoPrestazione tipoPrestazione) {
		tipoPrestazioneService.save(tipoPrestazione);
		return "redirect:/admin/tipiPrestazione";
	}

	@GetMapping("/admin/tipiPrestazione")
	public String getTipiPrestazione(Model model) {
		model.addAttribute("tipiPrestazione", this.tipoPrestazioneService.findAllTipiPrestazioni());
		return "admin/modificaTipoPrestazione.html";
	}

	@PostMapping("/admin/editTipoPrestazione/{id}")
	public String addTipoPrestazione(@PathVariable("id") Long id, @RequestParam("money") Integer money, Model model) {
		try {
			this.tipoPrestazioneService.updateTipoP(id, money);
			return "redirect:/admin/tipiPrestazione";
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "index";
		}
	}
}
