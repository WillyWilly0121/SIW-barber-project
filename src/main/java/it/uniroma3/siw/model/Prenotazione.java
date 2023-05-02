package it.uniroma3.siw.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;
import java.util.List;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"barbiere", "data_prestazione"}), @UniqueConstraint(columnNames = {"utente", "data_prestazione"})})
public class Prenotazione {

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

    @ManyToMany
    @Column(nullable = false)
    @NotEmpty
    private List<TipoPrestazione> prestazioni;

    @Column(nullable = false,name = "data_prestazione")
    @NotNull
    @FutureOrPresent
    private Date dataPrestazione;

    @Column(nullable = false,name = "data_prenotazione")
    @PastOrPresent
    private Date dataPrenotazione;

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

    public List<TipoPrestazione> getPrestazioni() {
        return prestazioni;
    }

    public void setPrestazioni(List<TipoPrestazione> prestazioni) {
        this.prestazioni = prestazioni;
    }

    public Date getDataPrestazione() {
        return dataPrestazione;
    }

    public void setDataPrestazione(Date data) {
        this.dataPrestazione = data;
    }

    public Date getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(Date dataPrenotazione) {
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
