package com.example.FilmReview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FilmReview.models.User;

/**
 * Das UserRepository-Interface ist eine Schnittstelle für den Zugriff auf die Datenbank für Benutzer.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	
    /**
     * Sucht einen Benutzer anhand des Benutzernamens.
     * 
     * @param benutzername Der Benutzername des Benutzers.
     * @return Der gefundene Benutzer.
     */
	User findByBenutzername(String benutzername);

    /**
     * Sucht einen Benutzer anhand des Benutzernamens.
     * 
     * @param benutzername Der Benutzername des Benutzers.
     * @return Der gefundene Benutzer.
     */
	boolean existsUserByRolesName(String string);
}
