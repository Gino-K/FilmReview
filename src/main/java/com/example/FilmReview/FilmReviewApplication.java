package com.example.FilmReview;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.FilmReview.models.Role;
import com.example.FilmReview.models.User;
import com.example.FilmReview.repository.RoleRepository;
import com.example.FilmReview.repository.UserRepository;

/**
 * Webapplikation, die es ermoeglicht Filme zu bewerten
 * Applikation verfuegt ueber verschiedene Rollen: Admin, Guest
 * 
 * Admin: kann Filme hinzufuegen, ansehen, bearbeiten, loeschen (CRUD)
 * 		  Bewertungen abrufen und loeschen
 * 		  Weitere Admins hinzufuegen		
 * 		  User Ansicht wechseln
 * 
 * Guest: Filme aufrufen und bewerten 
 * 		  eigene Bewertungen ansehen und bearbeiten und loeschen (CRUD)
 * 
 * keine Rolle: kann sich registrieren 
 * 				zugriff nur landingpage "/", login "/login", registrieren"/registrieren"   
 * 
 * @author Gino Kettler
 * @version 1.0.0
 */
@SpringBootApplication
public class FilmReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmReviewApplication.class, args);
	}

	/**
	 *Führt die anfängliche Erstellung von Benutzern, Rollen und deren Zuordnungen durch und erstellt einen Admin.
	 *Benutzername: admin
	 *Passwort: admin
	 *@param userRepository Das Repository für Benutzer.
	 *
	 *@param roleRepository Das Repository für Rollen.
	 *
	 *@param passwordEncoder Der Passwort-Encoder für die Verschlüsselung der Passwörter.
	 *
	 *@return Ein CommandLineRunner-Objekt, das die Initialisierung ausführt.
	 */
		@Bean
		public CommandLineRunner initialCreate(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		    return (args) -> {
		        boolean adminUserExists = userRepository.existsUserByRolesName("ROLE_ADMIN");
		        boolean adminRoleExists = roleRepository.findById(1).isPresent();
		        boolean guestRoleExists = roleRepository.findById(2).isPresent();

		        if (!adminRoleExists) {
		            Role adminRole = new Role();
		            adminRole.setId(1);
		            adminRole.setName("ROLE_ADMIN");
		            roleRepository.saveAndFlush(adminRole);
		        }

		        if (!guestRoleExists) {
		            Role guestRole = new Role();
		            guestRole.setId(2);
		            guestRole.setName("ROLE_GUEST");
		            roleRepository.saveAndFlush(guestRole);
		        }
		        if (!adminUserExists) {
		    		User user = new User();
		    		user.setId(1);
		    		user.setBenutzername("admin");
		    		user.setPasswort(passwordEncoder.encode("admin"));
		    		user.setGender("admin");
		    		user.setArbeitsfeld("admin");
		    		Role role = roleRepository.findByName("ROLE_ADMIN");
		    		user.setRoles(Arrays.asList(role));
		    		userRepository.save(user);
		        }
		    };
		}
}
