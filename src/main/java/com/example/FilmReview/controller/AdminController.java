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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.FilmReview.dto.FilmDto;
import com.example.FilmReview.models.Film;
import com.example.FilmReview.service.FilmService;
import com.example.FilmReview.util.FileUpload;

import jakarta.validation.Valid;

/**
 * Der AdminController ist fuer die Verwaltung von Filmen im Administrationsbereich zustaendig.
 */
@Controller
public class AdminController {

	@Autowired
	private FilmService filmService;
	
    /**
     * Zeigt alle Filme an.
     *
     * @param model Das Model-Objekt zur Uebermittlung von Daten an die View.
     * @return Die View fuer die Liste der Filme.
     */
	@GetMapping("/admin/filme")
	public String findAllFilms(Model model) {
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
		return "admin/filme";
	}
	
	@GetMapping("/admin/images/{id}")
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
     * Zeigt die Details eines Films an.
     *
     * @param id    Die ID des Films.
     * @param model Das Model-Objekt zur Uebermittlung von Daten an die View.
     * @return Die View fuer die Film-Detailansicht.
     */
	@GetMapping("/admin/film/{id}")
	public String findFilm(@PathVariable int id, Model model) {
		Film film = filmService.findFilmById(id);
		model.addAttribute("film", film);
		return "admin/list-film";
	}
	
    /**
     * Liefert das Bild eines Films als Antwort.
     *
     * @param id Die ID des Films.
     * @return Die ResponseEntity, die das Bild enthaelt.
     * @throws IOException Falls ein Fehler beim Lesen des Bildes auftritt.
     */
	@GetMapping("/admin/film/image/{id}")
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
    
    /**
     * Zeigt das Formular zum Hinzufuegen eines Films an.
     *
     * @param model Das Model-Objekt zur Uebermittlung von Daten an die View.
     * @return Die View fuer das Hinzufuegen eines Films.
     */
    @GetMapping("admin/filme/addfilm")
    public String newFilmForm(Model model) {
    	FilmDto filmDto = new FilmDto();
    	model.addAttribute("film", filmDto);
    	return "admin/add-film";
    }
    
    /**
     * Verarbeitet das Hinzufuegen eines Films.
     *
     * @param filmDto       Das FilmDto-Objekt mit den Daten des Films.
     * @param bindingResult Das BindingResult-Objekt zur Ueberpruefung der Validierungsergebnisse.
     * @param multipartFile Das MultipartFile-Objekt, das das Bild des Films enthaelt.
     * @param model         Das Model-Objekt zur Uebermittlung von Daten an die View.
     * @return Die Weiterleitungs-URL nach erfolgreichem Hinzufuegen des Films.
     * @throws IOException Falls ein Fehler beim Speichern des Bildes auftritt.
     */
    @PostMapping("/admin/save-film")
    public String createFilm(@ModelAttribute("film") @Valid FilmDto filmDto, BindingResult bindingResult, 
    		@RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("film", filmDto);
            return "admin/add-film";
        }
        
        if (!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			filmDto.setBild(fileName);
			Film createdFilm = filmService.createFilm(filmDto);
			String upload ="src/main/resources/added/" + createdFilm.getId();
			
			FileUpload.saveFile(upload, fileName, multipartFile);
		}else {
			if(filmDto.getBild().isEmpty()) {
				filmDto.setBild(null);
				filmService.createFilm(filmDto);
			}
		}        
        return "redirect:/admin/filme";
    }
    
    
    
    /**
     * Zeigt die Bestaetigungsseite zum Loeschen eines Films an.
     *
     * @param id Die ID des Films.
     * @return Die Weiterleitungs-URL zur Bestaetigungsseite.
     */
	@GetMapping("/admin/delete-film/{id}")
	public String showDeleteFilmConfirmation(@PathVariable int id) {
	    return "redirect:/admin/confirm-delete-film/" + id;
	}
    
    /**
     * Zeigt die Bestaetigungsseite zum Loeschen eines Films an und uebergibt den Film an die View.
     *
     * @param id    Die ID des Films.
     * @param model Das Model-Objekt zur Uebermittlung von Daten an die View.
     * @return Die View fuer die Bestaetigungsseite zum Loeschen des Films.
     */
	@GetMapping("/admin/confirm-delete-film/{id}")
	public String showDeleteFilmConfirmation(@PathVariable int id, Model model) {
	    Film film = filmService.findFilmById(id);
	    model.addAttribute("film", film);
	    return "admin/confirm-delete-film";
	}
    
    /**
     * Verarbeitet das Loeschen eines Films.
     *
     * @param id    Die ID des zu loeschenden Films.
     * @param model Das Model-Objekt zur Uebermittlung von Daten an die View.
     * @return Die Weiterleitungs-URL nach erfolgreichem Loeschen des Films.
     */
    @PostMapping("/admin/remove-film")
    public String deleteFilm(@RequestParam int id, Model model) {
        Film film = filmService.findFilmById(id);
        
        if (film != null && film.getBild() != null) {
            String imagePath = "src/main/resources/added/" + id;
            
            try {
                FileUpload.deleteDirectory(imagePath);
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        filmService.deleteFilm(id);
        model.addAttribute("filme", filmService.findAllFilme());
        return "admin/filme";
    }
    
    /**
     * Zeigt das Formular zum Bearbeiten eines Films an.
     *
     * @param id    Die ID des zu bearbeitenden Films.
     * @param model Das Model-Objekt zur Uebermittlung von Daten an die View.
     * @return Die View fuer das Bearbeiten des Films.
     */
    @GetMapping("/admin/edit-film/{id}")
    public String editFilm(@PathVariable int id, Model model) {
    	
	    Film film = filmService.findFilmById(id);
	    model.addAttribute("film", film);
	    return "admin/edit-film";
    }
    
    /**
     * Verarbeitet das Bearbeiten eines Films nach dem Absenden des Formulars.
     *
     * @param filmId          Die ID des zu bearbeitenden Films.
     * @param filmDto         Das FilmDto-Objekt mit den aktualisierten Daten des Films.
     * @param bindingResult   Das BindingResult-Objekt zur Ueberpruefung der Validierungsergebnisse.
     * @param multipartFile   Die hochgeladene Datei fuer das Film-Bild.
     * @param model           Das Model-Objekt zur Uebermittlung von Daten an die View.
     * @return Die Weiterleitungs-URL nach erfolgreichem Bearbeiten des Films.
     * @throws IOException Wenn ein Fehler beim Speichern der Datei auftritt.
     */
    @PostMapping("/admin/edited-film/{filmId}")
    public String editFilm(@PathVariable("filmId") int filmId,@ModelAttribute("film") @Valid FilmDto filmDto, 
    		BindingResult bindingResult, 
    		@RequestParam("image") MultipartFile multipartFile, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("film", filmDto);
            return "admin/edit-film";
        }
        
        filmDto.setId(filmId);
        filmService.updateFilm(filmDto);
        
        if (!multipartFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			filmDto.setBild(fileName);
			Film createdFilm = filmService.updateFilm(filmDto);
			String upload ="src/main/resources/added/" + createdFilm.getId();
			
			FileUpload.saveFile(upload, fileName, multipartFile);
		}        
        
        return "redirect:/admin/filme";
    }
    
    /**
     * Verarbeitet die Suche nach Filmen.
     *
     * @param query  Der Suchbegriff fuer die Filmsuche.
     * @param model Das Model-Objekt zur Uebermittlung von Daten an die View.
     * @return Die View mit den Suchergebnissen.
     */
    @GetMapping("/admin/filme/search")
    public String searchFilme(@RequestParam(value = "query") String query, Model model) {
    	List<FilmDto> filme = filmService.searchFilme(query);
    	model.addAttribute("filme", filme);
    	return "admin/filme";
    }

}


