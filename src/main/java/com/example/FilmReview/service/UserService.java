package com.example.FilmReview.service;

import com.example.FilmReview.dto.RegistrationDto;
import com.example.FilmReview.models.User;

/**
 * Das UserService-Interface definiert die Methoden zur Verwaltung von Benutzern.
 */
public interface UserService {

    /**
     * Speichert einen neuen Benutzer.
     * 
     * @param registrationDto Das RegistrationDto-Objekt, das die Benutzerdaten enthaelt.
     */
	void saveUser(RegistrationDto registrationDto);

    /**
     * Sucht einen Benutzer anhand des Benutzernamens.
     * 
     * @param benutzername Der Benutzername des Benutzers.
     * @return Der gefundene Benutzer.
     */
	User findByBenutzername(String benutzername);
	
    /**
     * Ueberprueft, ob der angemeldete Benutzer ein Administrator ist.
     * 
     * @return true, wenn der Benutzer ein Administrator ist, false sonst.
     */
	boolean isUserAdmin();

	
    /**
     * Speichert einen neuen Administrator.
     * 
     * @param registrationDto Das RegistrationDto-Objekt, das die Administrator-Daten enthaelt.
     */
	void saveAdmin(RegistrationDto registrationDto);

}
