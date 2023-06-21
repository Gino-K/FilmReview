package com.example.FilmReview.service;

import java.util.List;


import com.example.FilmReview.dto.FilmDto;
import com.example.FilmReview.models.Film;

/**
 * Das FilmService-Interface definiert die Methoden zur Verwaltung von Filmen.
 */
public interface FilmService {
	
    /**
     * Ruft alle Filme ab.
     * 
     * @return Eine Liste von FilmDto-Objekten, die alle Filme repraesentieren.
     */
	List<FilmDto> findAllFilme();
	
    /**
     * Sucht einen Film anhand der ID.
     * 
     * @param id Die ID des Films.
     * @return Der gefundene Film.
     */
	Film findFilmById(int id);
	
    /**
     * Erstellt einen neuen Film.
     * 
     * @param filmDto Das FilmDto-Objekt, das die Informationen fuer den neuen Film enthaelt.
     * @return Der erstellte Film.
     */
	Film createFilm(FilmDto filmDto);
	
    /**
     * Loescht einen Film anhand der ID.
     * 
     * @param id Die ID des zu loeschenden Films.
     */
	void deleteFilm(int id);
	
    /**
     * Aktualisiert einen Film.
     * 
     * @param filmDto Das FilmDto-Objekt, das die aktualisierten Informationen fuer den Film enthaelt.
     * @return Der aktualisierte Film.
     */
	Film updateFilm(FilmDto filmDto);
	
    /**
     * Sucht nach Filmen basierend auf einer Suchanfrage.
     * 
     * @param query Die Suchanfrage.
     * @return Eine Liste von FilmDto-Objekten, die den Suchkriterien entsprechen.
     */
	List<FilmDto> searchFilme(String query);

}
