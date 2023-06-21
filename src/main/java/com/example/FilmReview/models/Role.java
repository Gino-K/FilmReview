package com.example.FilmReview.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Die Klasse Role repraesentiert eine Benutzerrolle im System.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, unique = false)
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private List<User> users = new ArrayList<>();

    /**
     * Gibt eine String-Repraesentation des Role-Objekts zurück.
     * 
     * @return Eine String-Repraesentation des Role-Objekts.
     */
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
}
