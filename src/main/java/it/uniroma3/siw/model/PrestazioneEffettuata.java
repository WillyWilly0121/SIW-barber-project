package it.uniroma3.siw.model;

import java.time.LocalDate;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

@Entity
public class PrestazioneEffettuata {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(nullable = false, name = "barbiere")
    @NotNull
    private Utente barbiere;

    @ManyToOne
    @JoinColumn(nullable = false, name = "utente")
    @NotNull
    private Utente utente;
    
    @Column(nullable = false,name = "data_prestazione")
    @NotNull
    @FutureOrPresent
    private LocalDate dataPrestazione;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private TipoPrestazione prestazione;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Utente getBarbiere() {
		return barbiere;
	}

	public void setBarbiere(Utente barbiere) {
		this.barbiere = barbiere;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public LocalDate getDataPrestazione() {
		return dataPrestazione;
	}

	public void setDataPrestazione(LocalDate dataPrestazione) {
		this.dataPrestazione = dataPrestazione;
	}

	public TipoPrestazione getPrestazione() {
		return prestazione;
	}

	public void setPrestazione(TipoPrestazione prestazione) {
		this.prestazione = prestazione;
	}

	@Override
	public int hashCode() {
		return Objects.hash(barbiere, dataPrestazione, utente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrestazioneEffettuata other = (PrestazioneEffettuata) obj;
		return Objects.equals(barbiere, other.barbiere) && Objects.equals(dataPrestazione, other.dataPrestazione)
				&& Objects.equals(utente, other.utente);
	}
}
