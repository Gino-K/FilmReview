package com.example.FilmReview.security;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.FilmReview.models.User;
import com.example.FilmReview.repository.UserRepository;

/**
 * Verantwortlich fuer das Laden von Benutzerdetails fuer die Authentifizierung.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Laedt die Benutzerdetails fuer die angegebene Benutzername zur Authentifizierung.
	 * 
	 * @param username Der Benutzername des Benutzers.
	 * @return Ein UserDetails-Objekt, das die Benutzerdetails enthaelt.
	 * @throws UsernameNotFoundException Falls kein Benutzer mit dem angegebenen Benutzernamen gefunden wurde.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByBenutzername(username);
		
		if (user != null) {
		 org.springframework.security.core.userdetails.User authenticatedUser = 
				 new org.springframework.security.core.userdetails.User(
						 user.getBenutzername(),
						 user.getPasswort(),
						 user.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
		 return authenticatedUser;
		}else {
			throw new UsernameNotFoundException("Ungueltiger Benutzername und Passwort");
		}
	}
	
	
}
