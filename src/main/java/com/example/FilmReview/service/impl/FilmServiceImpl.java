package com.example.FilmReview.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.FilmReview.dto.FilmDto;
import com.example.FilmReview.mapper.FilmMapper;
import com.example.FilmReview.models.Film;
import com.example.FilmReview.repository.FilmRepository;
import com.example.FilmReview.service.FilmService;

/**
 * Die FilmServiceImpl-Klasse implementiert das FilmService-Interface und stellt Methoden zur Verwaltung von Filmen bereit.
 */
@Service
public class FilmServiceImpl implements FilmService {
	
	@Autowired
	private FilmRepository filmRepository;
	
	 /**
     * {@inheritDoc}
     */
	@Override
	public List<FilmDto> findAllFilme() {
		List<Film> filme = filmRepository.findAll();
		return filme.stream().map(FilmMapper::mapToFilmDto).collect(Collectors.toList());
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public Film findFilmById(int id) {
		Film film = filmRepository.findById(id).get();
		return film;
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public Film createFilm(FilmDto filmDto) {
	    Film film = FilmMapper.mapToFilm(filmDto);
	    return filmRepository.save(film);
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public void deleteFilm(int id) {
		Film film = filmRepository.findById(id).get();
		filmRepository.deleteById(film.getId());
		
	}

	 /**
     * {@inheritDoc}
     */
	@Override
	public Film updateFilm(FilmDto filmDto) {			
	    Film film = FilmMapper.mapToFilm(filmDto);
	    return filmRepository.save(film);	    
	}
	
	 /**
     * {@inheritDoc}
     */
	@Override
	public List<FilmDto> searchFilme(String query){
		List<Film> filme = filmRepository.searchFilme(query);
		return filme.stream().map(FilmMapper::mapToFilmDto).collect(Collectors.toList());
	}
}
