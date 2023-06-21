package com.example.FilmReview.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Die Klasse User repraesentiert einen Benutzer im System.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "benutzername", length = 45, nullable = false)
	private String benutzername;
	
	@Column(name = "passwort", length = 64, nullable = false)
	private String passwort;
	
	@Column(name = "gender", length = 10, nullable = false)
	private String gender;
	
	@Column(name = "arbeitsfeld", length = 30, nullable = false)
	private String arbeitsfeld;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles",
				joinColumns = {@JoinColumn(name = "userref", referencedColumnName = "id")},
				inverseJoinColumns = {@JoinColumn(name = "roleref", referencedColumnName = "id")})
	private List<Role> roles = new ArrayList<>();
	
    /**
     * Gibt eine String-Repraesentation des User-Objekts zurück.
     * 
     * @return Eine String-Repraesentation des User-Objekts.
     */
	@Override
	public String toString() {
		return "User [id=" + id + ", benutzername=" + benutzername + ", passwort=" + passwort + ", gender=" + gender
				+ ", arbeitsfeld=" + arbeitsfeld + "]";
	}
}
