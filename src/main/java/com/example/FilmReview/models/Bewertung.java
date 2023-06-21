package com.example.FilmReview.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Die Klasse Bewertung repraesentiert eine Bewertung eines Films durch einen Benutzer.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bewertungen")
public class Bewertung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "filmref", nullable = false)
    private Film film;

    @ManyToOne
    @JoinColumn(name = "userref", nullable = false)
    private User createdBy;

    @Lob
    @Column(nullable = false)
    private String kritik;

    @Column(nullable = false)
    private int punkte;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    /**
     * Konstruktor der Bewertung-Klasse.
     * 
     * @param kritik Die Kritik oder Bewertung des Films.
     * @param punkte Die Punktzahl, die dem Film gegeben wurde.
     */
    public Bewertung(String kritik, int punkte) {
        this.kritik = kritik;
        this.punkte = punkte;
    }
}

