package com.example.FilmReview.dto;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
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
 *
 */
public class BewertungDto {
	
    private int id;
    
	@NotEmpty
	@Length(min = 10, max = 255, message = "Die Kritik muss zwischen 10 und 255 Zeichen lang sein.")
	private String kritik;
	
	private int punkte;
		
	private LocalDateTime createdOn;
	
	private LocalDateTime updatedOn;
	
	private int filmId;

}
