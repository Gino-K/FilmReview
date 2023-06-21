package com.example.FilmReview.dto;



import org.hibernate.validator.constraints.Length;

import com.example.FilmReview.util.annotation.ValidPassword;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
/**
 * Transfer der Daten zwischen Controller und View
 *
 */
public class RegistrationDto {
	
	private int id;
	@NotEmpty
	@Length(max = 20, message = "Der Benutzername darf nicht länger als 20 Zeichen sein.")
	private String benutzername;
	
	@NotEmpty
	@Length(min= 8, max = 64, message = "Das Passwort muss mindestens 8 Zeichen lang und nicht größer als 64 Zeichen sein.")
	@ValidPassword
	private String passwort;
	@NotEmpty
	private String gender;
	private String arbeitsfeld;


}
