package com.example.FilmReview.mapper;

import com.example.FilmReview.dto.FilmDto;
import com.example.FilmReview.models.Film;

/**
 * Die Klasse FilmMapper enthält Methoden zum Konvertieren zwischen Film-Objekten und FilmDto-Objekten.
 */
public class FilmMapper {
	
    /**
     * Konvertiert ein Film-Objekt in ein FilmDto-Objekt.
     * 
     * @param film Das Film-Objekt, das konvertiert werden soll.
     * @return Ein FilmDto-Objekt, das aus dem Film-Objekt erstellt wurde.
     */
	public static FilmDto mapToFilmDto(Film film) {
		FilmDto filmDto = FilmDto.builder()
				.id(film.getId())
				.titel(film.getTitel())
				.fsk(film.getFsk())
				.beschreibung(film.getBeschreibung())
				.genre(film.getGenre())
				.bild(film.getBild())
				.laengeInMinuten(film.getLaengeInMinuten())
				.build();
		return filmDto;
	}
	
    /**
     * Konvertiert ein FilmDto-Objekt in ein Film-Objekt.
     * 
     * @param filmdto Das FilmDto-Objekt, das konvertiert werden soll.
     * @return Ein Film-Objekt, das aus dem FilmDto-Objekt erstellt wurde.
     */
	public static Film mapToFilm(FilmDto filmdto) {
		Film film = Film.builder()
				.id(filmdto.getId())
				.titel(filmdto.getTitel())
				.fsk(filmdto.getFsk())
				.beschreibung(filmdto.getBeschreibung())
				.genre(filmdto.getGenre())
				.bild(filmdto.getBild())
				.laengeInMinuten(filmdto.getLaengeInMinuten())
				.build();
		return film;
	}
			
 }
