package com.example.FilmReview.mapper;

import com.example.FilmReview.dto.BewertungDto;
import com.example.FilmReview.models.Bewertung;


/**
 * Verbindung zwischen Model und Dto
 *
 */
public class BewertungMapper {
	
    /**
     * Konvertiert ein Bewertung-Objekt in ein BewertungDto-Objekt.
     * 
     * @param bewertung Die Bewertung, die konvertiert werden soll.
     * @return Ein BewertungDto-Objekt, das aus der Bewertung erstellt wurde.
     */
	public static BewertungDto mapToBewertungDto(Bewertung bewertung) {
		BewertungDto bewertungDto = BewertungDto.builder()
				.id(bewertung.getId())
				.kritik(bewertung.getKritik())
				.punkte(bewertung.getPunkte())
				.createdOn(bewertung.getCreatedOn())
				.updatedOn(bewertung.getUpdatedOn())
				.build();
		return bewertungDto;
	}
	
    /**
     * Konvertiert ein BewertungDto-Objekt in ein Bewertung-Objekt.
     * 
     * @param bewertungDto Das BewertungDto-Objekt, das konvertiert werden soll.
     * @return Eine Bewertung, die aus dem BewertungDto-Objekt erstellt wurde.
     */
	public static Bewertung mapToBewertung(BewertungDto bewertungDto) {
		Bewertung bewertung = Bewertung.builder()				
				.id(bewertungDto.getId())
				.kritik(bewertungDto.getKritik())
				.punkte(bewertungDto.getPunkte())
				.createdOn(bewertungDto.getCreatedOn())
				.updatedOn(bewertungDto.getUpdatedOn())
				.build();
		return bewertung;
	}
}
