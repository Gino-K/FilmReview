package com.example.FilmReview.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.FilmReview.dto.RegistrationDto;
import com.example.FilmReview.models.Role;
import com.example.FilmReview.models.User;
import com.example.FilmReview.repository.RoleRepository;
import com.example.FilmReview.repository.UserRepository;
import com.example.FilmReview.service.UserService;
import com.example.FilmReview.util.SecurityUtils;

/**
 * Die UserServiceImpl-Klasse implementiert das UserService-Interface und stellt Methoden zur Verwaltung von Benutzern bereit.
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 /**
     * {@inheritDoc}
     */
	@Override
	public void saveUser(RegistrationDto registrationDto) {
		User user = new User();
		user.setBenutzername(registrationDto.getBenutzername());
		user.setPasswort(passwordEncoder.encode(registrationDto.getPasswort()));
		user.setGender(registrationDto.getGender());
		user.setArbeitsfeld(registrationDto.getArbeitsfeld());
		Role role = roleRepository.findByName("ROLE_GUEST");
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public User findByBenutzername(String benutzername) {
		return userRepository.findByBenutzername(benutzername);
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public boolean isUserAdmin() {
     return SecurityUtils.isUserAdmin();
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public void saveAdmin(RegistrationDto registrationDto) {
		User user = new User();
		user.setBenutzername(registrationDto.getBenutzername());
		user.setPasswort(passwordEncoder.encode(registrationDto.getPasswort()));
		user.setGender(registrationDto.getGender());
		user.setArbeitsfeld(registrationDto.getArbeitsfeld());
		Role role = roleRepository.findByName("ROLE_ADMIN");
		user.setRoles(Arrays.asList(role));
		userRepository.save(user);
		
	}

}
