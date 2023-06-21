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

import com.example.FilmReview.dto.FilmDto;
import com.example.FilmReview.models.Film;
import com.example.FilmReview.service.FilmService;

/**
 * Der BlogController ist fuer die Verarbeitung der Anfragen im Blog-Bereich zustaendig.
 */
@Controller
public class BlogController {
	
	@Autowired
	private FilmService filmService;
	
	
    /**
     * Verarbeitet die Anfrage zur Startseite des Blogs.
     *
     * @param model Das Model-Objekt zur Uebermittlung von Daten an die View.
     * @return Die View fuer die Anzeige der Filme im Blog.
     */
	@GetMapping("/")
	public String viewBlogFilme(Model model) {
		List<FilmDto> filmResponse = filmService.findAllFilme();
		
		for (FilmDto film : filmResponse) {
	        String titel = film.getTitel();
	        if (titel.length() > 30) {
	            film.setTitel(titel.substring(0, 30) + "...");
	        }
	        String beschreibung = film.getBeschreibung();
	        if (beschreibung.length() > 120) {
	            film.setBeschreibung(beschreibung.substring(0, 120) + "...");
	        }
	    }
		
		model.addAttribute("filmResponse", filmResponse);
		return "blog/view-filme";
	}
	
    /**
     * Verarbeitet die Anfrage zum Abrufen des Film-Bildes.
     *
     * @param id Die ID des Films.
     * @return Die ResponseEntity mit dem Film-Bild oder einem Fehlerstatus, falls das Bild nicht gefunden wird.
     * @throws IOException Wenn ein Fehler beim Lesen des Bildes auftritt.
     */
	@GetMapping("/image/{id}")
	public ResponseEntity<Resource> getFilmImage(@PathVariable int id) throws IOException {
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
}
