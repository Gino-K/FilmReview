package com.example.FilmReview.dto;



import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
/**
 * Transfer der Daten zwischen Controller und View
 *
 */
public class FilmDto {
	
	private int id;
	@NotEmpty
	@Length(max = 100, message = "Titel darf nicht länger als 100 Zeichen")
	private String titel;
	@NotNull
	private int fsk;
	@NotEmpty
	@Length(max = 600, message = "Titel darf nicht länger als 600 Zeichen")
	private String beschreibung;
	@NotEmpty
	@Length(max = 45, message = "Titel darf nicht länger als 45 Zeichen")
	private String genre;
	@NotNull
	private int laengeInMinuten;

	private String bild;
}
