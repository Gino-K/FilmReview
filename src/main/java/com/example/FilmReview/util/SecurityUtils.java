package com.example.FilmReview.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

/**
 * Die SecurityUtils-Klasse enthaelt Hilfsmethoden im Zusammenhang mit der Sicherheit und Authentifizierung.
 */
public class SecurityUtils {
	
    /**
     * Gibt den aktuellen Benutzer zurueck, der in der Sicherheitskontext-Halterung gespeichert ist.
     *
     * @return Der aktuelle Benutzer oder null, wenn kein Benutzer authentifiziert ist oder der Benutzer keine Instanz der Klasse User ist.
     */
	public static User getCurrentUser() {
		Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principle instanceof User) {
			return (User) principle;
		}
		return null;
	}
	
    /**
     * Ueberprueft, ob der aktuelle Benutzer die Rolle "ROLE_ADMIN" hat.
     *
     * @return true, wenn der Benutzer die Rolle "ROLE_ADMIN" hat, andernfalls false.
     */
    public static boolean isUserAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getAuthorities() != null) {
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    return true;
                }
            }
        }
        return false;
    }
}

