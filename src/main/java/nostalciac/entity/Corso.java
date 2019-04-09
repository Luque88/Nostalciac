/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nostalciac.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author tss
 */
@Entity
@Table(name = "t_corsi")
public class Corso implements Serializable {

    @Id
    @Column(name = "id_corso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome_corso")
    private String nome;

    @Column(name = "edizione")
    private String edizione;

    @Column(name = "data_inizio")
    @JsonbDateFormat("dd/MM/yyyy")
    private LocalDate inizio;

    @Column(name = "data_fine")
    @JsonbDateFormat("dd/MM/yyyy")
    private LocalDate fine;

    @Column(name = "note_corso")
    private String note;

    @Transient
    private Integer idSede;
 
    //@JsonTransient
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sede", referencedColumnName = "id_sede")
    private Sede sede;
    
    
    //@JsonTransient
    @ManyToMany()
    @OrderBy("tag ASC")
    @JoinTable(
            name = "t_tags_corsi",
            joinColumns =
                    @JoinColumn(name = "id_corso", referencedColumnName = "id_corso"),
            inverseJoinColumns = 
                    @JoinColumn(name = "id_tag", referencedColumnName = "id_tag")
    )
    private Set<Tag> tags = new TreeSet<>();

    public Corso() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEdizione() {
        return edizione;
    }

    public void setEdizione(String edizione) {
        this.edizione = edizione;
    }

    public LocalDate getInizio() {
        return inizio;
    }

    public void setInizio(LocalDate inizio) {
        this.inizio = inizio;
    }

    public LocalDate getFine() {
        return fine;
    }

    public void setFine(LocalDate fine) {
        this.fine = fine;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Corso other = (Corso) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Corso{" + "id=" + id + ", nome=" + nome + ", edizione=" + edizione
                + ", inizio=" + inizio + ", fine=" + fine + ", note=" + note
                + ", sede=" + sede.getNome() + '}';
    }
}
