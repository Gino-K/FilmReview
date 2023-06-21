package com.example.FilmReview.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.FilmReview.dto.RegistrationDto;
import com.example.FilmReview.models.User;
import com.example.FilmReview.service.UserService;

import jakarta.validation.Valid;


@Controller
public class AuthController {

	@Autowired
	private UserService userService;
	
    /**
     * Verarbeitet die Anfrage zur Anmeldeseite fuer Benutzer.
     *
     * @return Die View fuer die Anmeldeseite.
     */
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
    /**
     * Verarbeitet die Anfrage zur Registrierungsseite fuer Benutzer.
     *
     * @param model Das Model-Objekt zur Uebermittlung von Daten an die View.
     * @return Die View fuer die Registrierungsseite.
     */
	@GetMapping("/registrieren")
	public String registrationPage(Model model) {
		RegistrationDto user = new RegistrationDto();
		model.addAttribute("user", user);
		
		List<String> listArbeitsfeld = Arrays.asList("Informatik", "Finanzen", "Soziales", "Marketing", "Rechtswesen", "Kunst", "Schüler/Student");
		model.addAttribute("listArbeitsfeld", listArbeitsfeld);
		return "registrieren-form";
	}
	
    /**
     * Verarbeitet die Anfrage zum Absenden des Registrierungsformulars.
     *
     * @param user   Das RegistrationDto-Objekt mit den Benutzerdaten.
     * @param result Das BindingResult-Objekt zur Ueberpruefung der Validierungsergebnisse.
     * @param model  Das Model-Objekt zur Uebermittlung von Daten an die View.
     * @return Die Weiterleitungs-URL nach erfolgreicher Registrierung.
     */
	@PostMapping("/registrieren/s")
	public String submitForm(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result, Model model) {

	    User existingUser = userService.findByBenutzername(user.getBenutzername());

	    if (existingUser != null && existingUser.getBenutzername() != null && !existingUser.getBenutzername().isEmpty()) {
	        result.rejectValue("benutzername", null, "Benutzername existiert bereits");
	    }

	    if (result.hasErrors()) {
	        model.addAttribute("user", user);
	        List<String> listArbeitsfeld = Arrays.asList("Informatik", "Finanzen", "Soziales", "Marketing", "Rechtswesen", "Kunst", "Schueler/Student");
	        model.addAttribute("listArbeitsfeld", listArbeitsfeld);
	        return "registrieren-form";
	    }

	    userService.saveUser(user);
	    return "redirect:/registrieren?success";
	}
	
	
    /**
     * Verarbeitet die Anfrage zur Registrierungsseite fuer Administratoren.
     *
     * @param model Das Model-Objekt zur Uebermittlung von Daten an die View.
     * @return Die View fuer die Registrierungsseite fuer Administratoren.
     */
	@GetMapping("/admin/registrieren")
	public String registrationAdminPage(Model model) {
		RegistrationDto user = new RegistrationDto();
		model.addAttribute("user", user);
		return "admin/registrieren-admin";
	}	
	
    /**
     * Verarbeitet die Anfrage zum Absenden des Registrierungsformulars fuer Administratoren.
     *
     * @param user   Das RegistrationDto-Objekt mit den Benutzerdaten.
     * @param result Das BindingResult-Objekt zur Ueberpruefung der Validierungsergebnisse.
     * @param model  Das Model-Objekt zur Uebermittlung von Daten an die View.
     * @return Die Weiterleitungs-URL nach erfolgreicher Registrierung.
     */
	@PostMapping("/admin/registrieren/save")
	public String submitAdminForm(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result, Model model) {

	    User existingUser = userService.findByBenutzername(user.getBenutzername());

	    if (existingUser != null && existingUser.getBenutzername() != null && !existingUser.getBenutzername().isEmpty()) {
	        result.rejectValue("benutzername", null, "Benutzername existiert bereits");
	    }

	    if (result.hasErrors()) {
	        model.addAttribute("user", user);
	        return "admin/registrieren-admin";
	    }

	    user.setArbeitsfeld("Admin");
	    user.setGender("Admin");
	    userService.saveAdmin(user);
	    return "redirect:/admin/registrieren?success";
	}
}
