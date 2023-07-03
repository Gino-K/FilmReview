package com.example.FilmReview.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.FilmReview.dto.FilmDto;
import com.example.FilmReview.models.Film;
import com.example.FilmReview.service.FilmService;
import com.example.FilmReview.service.UserService;

/**
 * Der UserController ist fuer die Verwaltung von Filmen im Userbereich zustaendig.
 */
@Controller
public class UserController {
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private UserService userService;
	
    /**
     * Zeigt alle Filme an.
     *
     * @param model Das Model-Objekt zur Uebermittlung von Daten an die View.
     * @return Die View fuer die Liste der Filme.
     */
	@GetMapping("/user/filme")
	public String findAllFilms(Model model) {
		
        model.addAttribute("isAdmin", userService.isUserAdmin());
		List<FilmDto> filme = filmService.findAllFilme();
		
		for (FilmDto film : filme) {
	        String titel = film.getTitel();
	        if (titel.length() > 30) {
	            film.setTitel(titel.substring(0, 30) + "...");
	        }
	        String beschreibung = film.getBeschreibung();
	        if (beschreibung.length() > 120) {
	            film.setBeschreibung(beschreibung.substring(0, 120) + "...");
	        }
	    }
		
		model.addAttribute("filme", filme);
		return "user/filme";
	}
	
	/**
	 * Liefert das Bild eines Films.
	 *
	 * @param id Die ID des Films.
	 * @return Die HTTP-Antwort mit dem Bild als Ressource.
	 * @throws IOException Falls ein Fehler beim Lesen des Bilds auftritt.
	 */
	@GetMapping("/user/images/{id}")
	public ResponseEntity<Resource> getFilmeImage(@PathVariable int id) throws IOException {
	    Film film = filmService.findFilmById(id);
	    
	    if (film != null && film.getBild() != null) {
	        String imagePath = "src/main/resources/added/" + id + "/" + film.getBild();
	        Path imageFile = Paths.get(imagePath);
	        Resource resource = new UrlResource(imageFile.toUri());

	        if (resource.exists()) {
	            return ResponseEntity.ok()
	                    .contentType(MediaType.IMAGE_PNG)
	                    .body(resource);
	        }
	    }
	    return ResponseEntity.notFound().build();
	}
	

	
    /**
     * Verarbeitet die Suche nach Filmen.
     *
     * @param query  Der Suchbegriff fuer die Filmsuche.
     * @param model Das Model-Objekt zur Uebermittlung von Daten an die View.
     * @return Die View mit den Suchergebnissen.
     */
    @GetMapping("/user/filme/search")
    public String searchFilme(@RequestParam(value = "query") String query, Model model) {
        model.addAttribute("isAdmin", userService.isUserAdmin());
    	List<FilmDto> filme = filmService.searchFilme(query);
    	model.addAttribute("filme", filme);
    	return "user/filme";
    }
    

}
