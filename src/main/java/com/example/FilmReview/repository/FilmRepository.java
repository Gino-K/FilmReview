package com.example.FilmReview.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.FilmReview.models.Film;

/**
 * Das FilmRepository-Interface ist eine Schnittstelle fuer den Zugriff auf die Datenbank fuer Filme.
 */
public interface FilmRepository extends JpaRepository<Film, Integer> {
	
    /**
     * Sucht einen Film anhand des Titels.
     * 
     * @param titel Der Titel des Films.
     * @return Ein Optional-Objekt, das den gefundenen Film enthaelt, falls vorhanden.
     */
	Optional<Film> findByTitel(String titel);
	
	
    /**
     * Durchsucht Filme anhand einer Suchanfrage, die den Titel, die Beschreibung oder das Genre enthaelt.
     * 
     * @param query Die Suchanfrage.
     * @return Eine Liste von Filmen, die der Suchanfrage entsprechen.
     */
	@Query("SELECT f FROM Film f WHERE " +
				" f.titel LIKE concat('%', :query, '%') OR " +
				" f.beschreibung LIKE concat('%', :query, '%') OR " +
				" f.genre LIKE concat('%', :query, '%') ") 
	List<Film> searchFilme(String query);
}
