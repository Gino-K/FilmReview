package com.example.FilmReview.service;

import java.util.List;

import com.example.FilmReview.dto.BewertungDto;
import com.example.FilmReview.models.Bewertung;

/**
 * Das BewertungService-Interface definiert die Methoden zur Verwaltung von Bewertungen.
 */
public interface BewertungService {
	
    /**
     * Ruft alle Bewertungen als BewertungDto-Objekte ab.
     *
     * @return Eine Liste von BewertungDto-Objekten.
     */
	List<BewertungDto> findAllBewertungenDTO();
	
    /**
     * Ruft alle Bewertungen eines Benutzers ab.
     *
     * @return Eine Liste von Bewertungsobjekten.
     */
	List<Bewertung> findAllBewertungenByUser();
	
    /**
     * Ruft alle Bewertungen ab.
     *
     * @return Eine Liste von Bewertungsobjekten.
     */
	List<Bewertung> findAllBewertungen();
	
    /**
     * Ruft ein BewertungDto anhand der Bewertungs-ID ab.
     *
     * @param bewertungId Die ID der Bewertung.
     * @return Das BewertungDto-Objekt.
     */
	BewertungDto findBewertungDtoById(int bewertungId);
	
    /**
     * Ruft ein BewertungDto anhand der Film-ID ab.
     *
     * @param filmId Die ID des Films.
     * @return Das BewertungDto-Objekt.
     */
	BewertungDto findBewertungDtoByFilm(int filmId);

    /**
     * Ruft eine Bewertung anhand der Bewertungs-ID ab.
     *
     * @param bewertungId Die ID der Bewertung.
     * @return Das Bewertungsobjekt.
     */
	Bewertung findBewertungById(int bewertungId);
	
    /**
     * Sucht nach Bewertungen anhand eines Suchbegriffs.
     *
     * @param query Der Suchbegriff.
     * @return Eine Liste von Bewertungsobjekten, die dem Suchergebnis entsprechen.
     */
	List<Bewertung> searchBewertungen(String query);
	
    /**
     * Sucht nach Bewertungen eines Benutzers anhand eines Suchbegriffs.
     *
     * @param query Der Suchbegriff.
     * @return Eine Liste von Bewertungsobjekten, die dem Suchergebnis entsprechen.
     */
	List<Bewertung> searchBewertungenUser(String query);

    /**
     * Erstellt eine neue Bewertung fuer einen Film.
     *
     * @param filmId      Die ID des Films.
     * @param bewertungDto Das BewertungDto-Objekt mit den Bewertungsdaten.
     */
    void createBewertung(int filmId, BewertungDto BewertungDto);
	
    /**
     * Aktualisiert eine Bewertung.
     *
     * @param bewertungDto Das BewertungDto-Objekt mit den aktualisierten Bewertungsdaten.
     */
	void updateBewertung(BewertungDto bewertungDto);

    /**
     * Loescht eine Bewertung anhand der Bewertungs-ID.
     *
     * @param bewertungId Die ID der Bewertung.
     */
	void deleteBewertung(int bewertungId);	
	
    /**
     * Berechnet den durchschnittlichen Punktestand aller Bewertungen.
     *
     * @return Der durchschnittliche Punktestand.
     */
	double avgPunkte();
	
    /**
     * Ruft den durchschnittlichen Punktestand fuer einen bestimmten Film ab.
     *
     * @param filmRef Die ID des Films.
     * @return Der durchschnittliche Punktestand.
     */
	double getAveragePunkteByFilmRef(int filmRef);
	
    /**
     * Ueberprueft, ob ein Benutzer bereits eine Bewertung fuer einen bestimmten Film abgegeben hat.
     *
     * @param filmId Die ID des Films.
     * @return true, wenn der Benutzer bereits eine Bewertung abgegeben hat, andernfalls false.
     */
	boolean hasUserRated(int filmid);
}
