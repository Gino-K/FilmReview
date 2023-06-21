// Java Program to Illustrate Spring Security

package com.example.FilmReview.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * Die SecurityConfiguration-Klasse konfiguriert die Sicherheit und Authentifizierung fuer die Anwendung.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
    /**
     * Erstellt einen Bean fuer den PasswordEncoder, der verwendet wird, um Passwoerter zu codieren und zu ueberpruefen.
     *
     * @return Der PasswordEncoder-Bean.
     */
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
    /**
     * Konfiguriert die Sicherheitsfilterkette fuer HTTP-Anfragen.
     *
     * @param http Die HttpSecurity-Instanz zum Konfigurieren der Filterkette.
     * @return Die konfigurierte SecurityFilterChain.
     * @throws Exception Wenn ein Fehler bei der Konfiguration auftritt.
     */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf((csrf) -> csrf.disable())
			.authorizeHttpRequests((authorize) -> authorize.requestMatchers(new AntPathRequestMatcher("/resources/**")).permitAll()
															.requestMatchers(new AntPathRequestMatcher("/registrieren/**")).permitAll()
															.requestMatchers(new AntPathRequestMatcher("/image/**")).permitAll()
															.requestMatchers(new AntPathRequestMatcher("/filmCom.jpg")).permitAll()
															.requestMatchers(new AntPathRequestMatcher("/")).permitAll()
															.requestMatchers(new AntPathRequestMatcher("/about")).permitAll()
															.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAnyRole("ADMIN")
															.requestMatchers(new AntPathRequestMatcher("/user/**")).hasAnyRole("ADMIN", "GUEST"))
			.formLogin( form -> form.loginPage("/login")
						.defaultSuccessUrl("/user/filme")
						.loginProcessingUrl("/login").permitAll())
						.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
								.permitAll());
		return http.build();
	}
	
    /**
     * Konfiguriert den AuthenticationManagerBuilder fuer die Benutzerauthentifizierung.
     *
     * @param builder Der AuthenticationManagerBuilder zum Konfigurieren des Benutzerauthentifizierungssystems.
     * @throws Exception Wenn ein Fehler bei der Konfiguration auftritt.
     */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
	}
}
