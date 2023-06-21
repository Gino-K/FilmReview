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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.FilmReview.dto.BewertungDto;
import com.example.FilmReview.models.Bewertung;
import com.example.FilmReview.models.Film;
import com.example.FilmReview.service.BewertungService;
import com.example.FilmReview.service.FilmService;
import com.example.FilmReview.service.UserService;

import jakarta.validation.Valid;

@Controller
public class BewertungController {
	
	@Autowired
	private BewertungService bewertungService;
	
	@Autowired
	private FilmService filmService;
	
	@Autowired
	private UserService userService;
	
    //handle Film ratings button
    @GetMapping("/admin/ratings")
    public String getBewertungen(Model model) {
    	List<Bewertung> bewertungen = bewertungService.findAllBewertungen();
    	model.addAttribute("bewertungen", bewertungen);
    	return "admin/ratings";
    }
	
    //handle delete Film rating button ADMIN
    @GetMapping("/admin/ratings/{bewertungId}")
    public String deleteBewertungAdmin(@PathVariable("bewertungId") int bewertungId) {
    	bewertungService.deleteBewertung(bewertungId);
    	 return "redirect:/admin/ratings";
    }
    
    //handle search Bewertung Request
    @GetMapping("/admin/ratings/search")
    public String searchbewertungen(@RequestParam(value = "query") String query, Model model) {
    	List<Bewertung> bewertungen = bewertungService.searchBewertungen(query);
    	model.addAttribute("bewertungen", bewertungen);
    	return "admin/ratings";
    }
	
	//handle view Click USER
	@GetMapping("/user/rate-film/{id}")
	public String findFilm(@PathVariable int id, Model model) {
		
        model.addAttribute("isAdmin", userService.isUserAdmin());
		Film film = filmService.findFilmById(id); 
		model.addAttribute("film", film);
		
	    Double durchschnittlichePunkte = bewertungService.getAveragePunkteByFilmRef(id);
        int anzahlSterne = convertToStars(durchschnittlichePunkte);
        model.addAttribute("anzahlSterne", anzahlSterne);
        
		BewertungDto bewertungDto = new BewertungDto();
		model.addAttribute("bewertung", bewertungDto);
		return "user/rate";
	}
	
    private int convertToStars(Double punkte) {
        int ganzzahligerPunkte = (int) Math.floor(punkte);
        int dezimalPunkte = (int) Math.round((punkte - ganzzahligerPunkte) * 10);
        
        if (dezimalPunkte >= 5) {
            return ganzzahligerPunkte + 1;
        } else {
            return ganzzahligerPunkte;
        }
    }
	
	@GetMapping("/user/rate-film/image/{id}")
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
	
	//handle Bewertung submit USER
	@PostMapping("/user/rate-film/{id}/save")
	public String createRating(@PathVariable("id") int id, @Valid @ModelAttribute("bewertung") BewertungDto bewertungDto,BindingResult result, Model model) {
		
	    Film film = filmService.findFilmById(id);
	    
        if(result.hasErrors()){
            model.addAttribute("film", film);
            model.addAttribute("bewertung", bewertungDto);
    		return "user/rate";
        }
        
        boolean hasRated = bewertungService.hasUserRated(id);
        if (hasRated) {
        	BewertungDto rated = bewertungService.findBewertungDtoByFilm(id);
			model.addAttribute("bewertung", rated);
		    model.addAttribute("updateMessage", "Sie haben den Film schon einmal bewertet. Sie können hier die Bewertung aktualisieren!");
		    return "user/edit-rating";
		}
        
        bewertungService.createBewertung(id, bewertungDto);
		return"redirect:/user/rate-film/" + id;
	}
	
	
	
    //handle rating Request USER
    @GetMapping("/user/ratings")
    public String getRating(Model model) {
        model.addAttribute("isAdmin", userService.isUserAdmin());
        
        List<Bewertung> bewertungen = bewertungService.findAllBewertungenByUser();
        
        model.addAttribute("bewertungen", bewertungen);
        
    	return "user/ratings";
    }
    
    
    //handle search Bewertung Request USER
    @GetMapping("/user/ratings/search")
    public String searchBewertungenUser(@RequestParam(value = "query") String query, Model model) {
    	List<Bewertung> bewertungen = bewertungService.searchBewertungenUser(query);
    	model.addAttribute("bewertungen", bewertungen);
    	return "user/ratings";
    }
    
	@GetMapping("/user/ratings/{id}")
	public ResponseEntity<Resource> getRatingFilmImage(@PathVariable int id) throws IOException {
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
    
    
    //handle Editieren Button USER
    @GetMapping("/user/ratings/edit/{id}")
    public String editRating(@PathVariable int id, Model model) {
    	
	    BewertungDto bewertung = bewertungService.findBewertungDtoById(id);
	    model.addAttribute("bewertung", bewertung);
	    return "user/edit-rating";
    }
    
    //handle Editieren submit Button USER
    @PostMapping("/user/ratings/edited/{bewertungId}")
    public String editRating(@PathVariable("bewertungId") int bewertungId,@ModelAttribute("bewertung") @Valid BewertungDto bewertungDto, BindingResult bindingResult, Model model){
       
    	if (bindingResult.hasErrors()) {
        	model.addAttribute("bewertung", bewertungDto);
            return "user/edit-rating";
        }
        
        bewertungDto.setId(bewertungId);
        bewertungService.updateBewertung(bewertungDto);    
        
        return "redirect:/user/ratings";
    }
    
    //handle delete Film rating button USER
    @GetMapping("/user/ratings/delete/{id}")
    public String deleteBewertungUser(@PathVariable("id") int bewertungId) {
    	bewertungService.deleteBewertung(bewertungId);
    	 return "redirect:/user/ratings";
    }
}
