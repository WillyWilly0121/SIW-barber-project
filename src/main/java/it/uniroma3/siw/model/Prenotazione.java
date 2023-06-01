package it.uniroma3.siw.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"barbiere", "data_prestazione"}), @UniqueConstraint(columnNames = {"utente", "data_prestazione"})})
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "barbiere")
    private Utente barbiere;

    @ManyToOne
    @JoinColumn(nullable = false, name = "utente")
    private Utente utente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TipoPrestazione prestazione;

    @Column(nullable = false,name = "data_prestazione")
    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPrestazione;

    @Column(nullable = false,name = "data_prenotazione")
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataPrenotazione;

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

    public TipoPrestazione getPrestazione() {
        return prestazione;
    }

    public void setPrestazione(TipoPrestazione prestazione) {
        this.prestazione = prestazione;
    }

    public LocalDate getDataPrestazione() {
        return dataPrestazione;
    }

    public void setDataPrestazione(LocalDate data) {
        this.dataPrestazione = data;
    }

    public LocalDate getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(LocalDate dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prenotazione that = (Prenotazione) o;

        if (!barbiere.equals(that.barbiere)) return false;
        if (!utente.equals(that.utente)) return false;
        return dataPrestazione.equals(that.dataPrestazione);
    }

    @Override
    public int hashCode() {
        int result = barbiere.hashCode();
        result = 31 * result + utente.hashCode();
        result = 31 * result + dataPrestazione.hashCode();
        return result;
    }
}
