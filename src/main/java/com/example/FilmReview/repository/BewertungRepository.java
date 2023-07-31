package com.example.FilmReview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.FilmReview.models.Bewertung;


/**
 *Das Interface "BewertungRepository" definiert Methoden zum Zugriff auf Bewertungsdaten in der Datenbank.
 *Diese Schnittstelle ermoeglicht den Zugriff auf verschiedene Bewertungsdatenbankoperationen.
 */
public interface BewertungRepository extends JpaRepository<Bewertung, Integer> {
	
    /**
     * Sucht Bewertungen anhand der Benutzer-ID.
     * 
     * @param userId Die ID des Benutzers.
     * @return Eine Liste von Bewertungen, die dem angegebenen Benutzer zugeordnet sind.
     */
	@Query(value = "SELECT * FROM bewertungen b WHERE b.userref =:userId", nativeQuery = true)
	List<Bewertung> findBewertungByUser(int userId);
	
    /**
     * Berechnet den durchschnittlichen Punktwert aller Bewertungen.
     * 
     * @return Der Durchschnittswert der Punkte aller Bewertungen.
     */
    @Query("SELECT AVG(b.punkte) FROM Bewertung b")
    Double avgPunkte();
    
    /**
     * Berechnet den durchschnittlichen Punktwert fuer eine bestimmte Film-ID.
     * 
     * @param filmId Die ID des Films.
     * @return Der Durchschnittswert der Punkte fuer den angegebenen Film.
     */
    @Query(value = "SELECT AVG(b.punkte) FROM bewertungen b WHERE b.filmref = :filmId", nativeQuery = true)
    Double avgPunkteByFilmRef(int filmId);
	
    /**
     * Ueberprueft, ob eine Bewertung fuer eine bestimmte Film-ID und Benutzer-ID existiert.
     * 
     * @param filmId      Die ID des Films.
     * @param createdById Die ID des erstellenden Benutzers.
     * @return true, wenn eine Bewertung existiert, andernfalls false.
     */
    boolean existsByFilmIdAndCreatedById(int filmId, int createdById);
    
    /**
     * Sucht eine Bewertung anhand der Film-ID und Benutzer-ID.
     * 
     * @param filmId  Die ID des Films.
     * @param userId  Die ID des Benutzers.
     * @return Eine Bewertung, die dem angegebenen Film und Benutzer zugeordnet ist.
     */
	@Query(value = "SELECT * FROM bewertungen b WHERE b.filmref =:filmId AND b.userref =:userId", nativeQuery = true)
	Bewertung findBewertungByFilm(int filmId, int userId);
	
    /**
     * Sucht Bewertungen anhand eines Suchbegriffs in den Bewertungen, Filmnamen und Benutzernamen.
     * 
     * @param query  Der Suchbegriff.
     * @return Eine Liste von Bewertungen, die den Suchkriterien entsprechen.
     */
	@Query(value = "SELECT b.*, f.id as f_id, u.id as u_id " +
            "FROM bewertungen b " +
            "INNER JOIN filme f ON b.filmref = f.id " +
            "INNER JOIN users u ON b.userref = u.id " +
            "WHERE " +
            "b.kritik LIKE CONCAT('%', :query, '%') OR " +
            "b.punkte LIKE CONCAT('%', :query, '%') OR " +
            "u.benutzername LIKE CONCAT('%', :query, '%') OR " +
            "f.titel LIKE CONCAT('%', :query, '%')", nativeQuery = true)
	List<Bewertung> searchBewertungen(String query);
	
    /**
     * Sucht Bewertungen eines bestimmten Benutzers anhand eines Suchbegriffs in den Bewertungen, Filmnamen und Benutzernamen.
     * 
     * @param query   Der Suchbegriff.
     * @param userId  Die ID des Benutzers.
     * @return Eine Liste von Bewertungen, die den Suchkriterien entsprechen und dem angegebenen Benutzer zugeordnet sind.
     */
	@Query(value = "SELECT b.*, f.id as f_id, u.id as u_id\r\n"
			+ "FROM bewertungen b\r\n"
			+ "INNER JOIN filme f ON b.filmref = f.id\r\n"
			+ "INNER JOIN users u ON b.userref = u.id\r\n"
			+ "WHERE \r\n"
			+ "    b.userref = :userId AND\r\n"
			+ "    (b.kritik LIKE CONCAT('%', :query, '%') OR\r\n"
			+ "    b.punkte LIKE CONCAT('%', :query, '%') OR\r\n"
			+ "    f.titel LIKE CONCAT('%', :query, '%'))", nativeQuery = true)
	List<Bewertung> searchBewertungenUser(String query, int userId);

    
}
