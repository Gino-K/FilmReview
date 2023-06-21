package com.example.FilmReview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Webapplikation, die es ermoeglicht Filme zu bewerten
 * Applikation verfuegt ueber verschiedene Rollen: Admin, Guest
 * 
 * Admin: kann Filme hinzufuegen, ansehen, bearbeiten, loeschen (CRUD)
 * 		  Bewertungen abrufen und loeschen
 * 		  Weitere Admins hinzufuegen		
 * 		  User Ansicht wechseln
 * 
 * Guest: Filme aufrufen und bewerten 
 * 		  eigene Bewertungen ansehen und bearbeiten und loeschen (CRUD)
 * 
 * keine Rolle: kann sich registrieren 
 * 				zugriff nur landingpage "/", login "/login", registrieren"/registrieren"   
 * 
 * @author Gino Kettler
 * @version 1.0.0
 */
@SpringBootApplication
public class FilmReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmReviewApplication.class, args);
	}

}
