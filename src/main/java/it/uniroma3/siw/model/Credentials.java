package it.uniroma3.siw.model;

import jakarta.persistence.OneToOne;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Credentials {
	public static final String DEFAULT_ROLE = "DEFAULT";
	public static final String BARBER_ROLE = "BARBER";
	public static final String ADMIN_ROLE = "ADMIN";
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String username;
	private String password;
	private String role;
	@OneToOne
	private Utente utente;
	
	
	public boolean isAdmin() {
		return this.role.equals(ADMIN_ROLE);
	}
	
	public boolean isBarber() {
		return this.role.equals(BARBER_ROLE);
	}
	
	public boolean isUser() {
		return this.role.equals(DEFAULT_ROLE);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Utente getUser() {
		return utente;
	}
	public void setUser(Utente user) {
		this.utente = user;
	}
	@Override
	public int hashCode() {
		return Objects.hash(password, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credentials other = (Credentials) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}

}
