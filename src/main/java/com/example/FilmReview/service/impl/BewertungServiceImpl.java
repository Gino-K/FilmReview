package com.example.FilmReview.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FilmReview.dto.BewertungDto;
import com.example.FilmReview.mapper.BewertungMapper;
import com.example.FilmReview.models.Bewertung;
import com.example.FilmReview.models.Film;
import com.example.FilmReview.models.User;
import com.example.FilmReview.repository.BewertungRepository;
import com.example.FilmReview.repository.FilmRepository;
import com.example.FilmReview.repository.UserRepository;
import com.example.FilmReview.service.BewertungService;
import com.example.FilmReview.util.SecurityUtils;

/**
 * Die BewertungServiceImpl-Klasse implementiert das BewertungService-Interface und stellt Methoden zur Verwaltung von Filmen bereit.
 */
@Service
public class BewertungServiceImpl implements BewertungService {
	
	@Autowired
	private BewertungRepository bewertungRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private UserRepository userRepository;
	
    /**
     * {@inheritDoc}
     */
	@Override
	public List<Bewertung> findAllBewertungen() {
        List<Bewertung> bewertungen = bewertungRepository.findAll();
        return bewertungen;
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public BewertungDto findBewertungDtoById(int bewertungId) {
		Bewertung bewertung = bewertungRepository.findById(bewertungId).get();
		return BewertungMapper.mapToBewertungDto(bewertung);
	}
	
	 /**
     * {@inheritDoc}
     */
	@Override
	public Bewertung findBewertungById(int bewertungId) {
		Bewertung bewertung = bewertungRepository.findById(bewertungId).get();
		return bewertung;
	}
	
	 /**
     * {@inheritDoc}
     */
	@Override
	public void createBewertung(int filmId, BewertungDto bewertungDto) {
        Film film = filmRepository.findById(filmId).get();
        String benutzername = SecurityUtils.getCurrentUser().getUsername();
        User user = userRepository.findByBenutzername(benutzername);
        Bewertung bewertung = BewertungMapper.mapToBewertung(bewertungDto);
        bewertung.setCreatedBy(user);
        bewertung.setFilm(film);
        film.getBewertungen().add(bewertung);
        bewertungRepository.save(bewertung);
	}
	
	 /**
     * {@inheritDoc}
     */
	@Override
	public void updateBewertung(BewertungDto bewertungDto) {
		Bewertung filmref = bewertungRepository.findById(bewertungDto.getId()).get();
		String name = SecurityUtils.getCurrentUser().getUsername();
		User createdBy = userRepository.findByBenutzername(name);
		Bewertung bewertung = BewertungMapper.mapToBewertung(bewertungDto);
		bewertung.setCreatedBy(createdBy);
		Film film = filmRepository.findById(filmref.getFilm().getId()).get();
		bewertung.setFilm(film);
		bewertungRepository.save(bewertung);
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public void deleteBewertung(int bewertungId) {
		bewertungRepository.deleteById(bewertungId);
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public List<BewertungDto> findAllBewertungenDTO() {
        List<Bewertung> bewertungen = bewertungRepository.findAll();
        return bewertungen.stream()
                .map(BewertungMapper::mapToBewertungDto)
                .collect(Collectors.toList());
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public List<Bewertung> findAllBewertungenByUser() {
		int userId = getUserId();		
		List<Bewertung> bewertungen = bewertungRepository.findBewertungByUser(userId);
		return bewertungen;
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public double avgPunkte() {
		return bewertungRepository.avgPunkte();
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public double getAveragePunkteByFilmRef(int filmRef) {
		Double averagePunkte = bewertungRepository.avgPunkteByFilmRef(filmRef);
		double averagePunkteValue = averagePunkte != null ? averagePunkte : 0.0;
		return averagePunkteValue;
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public boolean hasUserRated(int filmid) {
		int userId = getUserId();		
        return bewertungRepository.existsByFilmIdAndCreatedById(filmid, userId);
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public BewertungDto findBewertungDtoByFilm(int filmId) {
		int userId = getUserId();		
		Bewertung bewertung = bewertungRepository.findBewertungByFilm(filmId, userId);
		BewertungDto bewertungDto = BewertungMapper.mapToBewertungDto(bewertung);
		return bewertungDto;
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public List<Bewertung> searchBewertungen(String query) {
		List<Bewertung> bewertungen = bewertungRepository.searchBewertungen(query);
		return bewertungen;
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public List<Bewertung> searchBewertungenUser(String query) {
		int id = getUserId();
		List<Bewertung> bewertungen = bewertungRepository.searchBewertungenUser(query, id);
		return bewertungen;
	}

	/**
	 * Gibt die Benutzer-ID des aktuellen Benutzers zurück.
	 * 
	 * @return die Benutzer-ID des aktuellen Benutzers
	 */
	public int getUserId() {
		String name = SecurityUtils.getCurrentUser().getUsername();
		User user = userRepository.findByBenutzername(name);
		int id = user.getId();	
		return id;
	}


}
