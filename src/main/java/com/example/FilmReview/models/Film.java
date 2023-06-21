package com.example.FilmReview.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

/**
 * Die Klasse Film repraesentiert einen Film mit verschiedenen Eigenschaften wie Titel, FSK-Einstufung, Beschreibung, Genre usw.
 */
@Entity
@Table(name = "filme")
public class Film {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "titel", length = 100, nullable = false)
	private String titel;
	
	@Column(name = "fsk", columnDefinition = "INT(2) NOT NULL")
	private int fsk;
	
	@Column(name = "beschreibung", length = 600, nullable = false)
	private String beschreibung;
	
	@Column(name = "genre", length = 45, nullable = false)
	private String genre;
	
	@Column(name = "bild", length = 64)
	private String bild;
	
	@Column(name = "laenge_in_minuten", columnDefinition = "INT(3) NOT NULL")
	private int laengeInMinuten;
	
	@OneToMany(mappedBy = "film", cascade = CascadeType.REMOVE)
	private Set<Bewertung> bewertungen = new HashSet<>();
	
    /**
     * Konstruktor der Film-Klasse.
     * 
     * @param titel             Der Titel des Films.
     * @param fsk               Die FSK-Einstufung des Films.
     * @param beschreibung      Die Beschreibung des Films.
     * @param genre             Das Genre des Films.
     * @param bild              Der Dateiname des Film-Bildes.
     * @param laengeInMinuten   Die Laenge des Films in Minuten.
     */
	public Film(String titel, int fsk, String beschreibung, String genre, String bild, int laengeInMinuten) {
		this.titel = titel;
		this.fsk = fsk;
		this.beschreibung = beschreibung;
		this.genre = genre;
		this.bild = bild;
		this.laengeInMinuten = laengeInMinuten;
	}
	
    /**
     * Gibt eine String-Repraesentation des Film-Objekts zurück.
     * 
     * @return Eine String-Repraesentation des Film-Objekts.
     */
	@Override
	public String toString() {
		return "Film [id=" + id + ", titel=" + titel + ", fsk=" + fsk + ", beschreibung=" + beschreibung + ", genre="
				+ genre + ", bild=" + bild + ", laengeInMinuten=" + laengeInMinuten + ", bewertungen=" + bewertungen
				+ "]";
	}
}
	
