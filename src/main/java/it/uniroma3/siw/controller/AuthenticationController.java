package it.uniroma3.siw.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Utente;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.UtenteService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	private UtenteService userService;
	
	@GetMapping(value = "/register") 
	public String showRegisterForm (Model model) {
		model.addAttribute("user", new Utente());
		model.addAttribute("credentials", new Credentials());
		return "formRegisterUser";
	}
	
	@GetMapping(value = "/login") 
	public String showLoginForm (Model model) {
		return "formLogin";
	}

	@GetMapping(value = "/") 
	public String index(Model model) {
        return "index";
	}
		
    @GetMapping(value = "/success")
    public String defaultAfterLogin(Model model) {
        return "index";
    }

	@PostMapping(value = { "/register" })
    public String registerUser(@Valid @ModelAttribute("user") Utente user,
                 BindingResult userBindingResult, @Valid
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model) {
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
        	userService.saveUser(user);
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            model.addAttribute("user", user);
            return "registrationSuccessful";
        }
        return "formRegisterUser";
    }
}
