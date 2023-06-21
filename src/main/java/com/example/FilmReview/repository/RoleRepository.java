package com.example.FilmReview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.FilmReview.models.Role;

/**
 * Das RoleRepository-Interface ist eine Schnittstelle für den Zugriff auf die Datenbank für Benutzerrollen.
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
    /**
     * Sucht eine Benutzerrolle anhand des Namens.
     * 
     * @param name Der Name der Benutzerrolle.
     * @return Die gefundenen Benutzerrolle.
     */
	Role findByName(String name);

}
