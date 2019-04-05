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
import javax.persistence.Table;

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

    // Cioè non persistente, non viene salvato su DB
//    @Transient
   

    // per aggiungere un field con foreign keys
    //          siccome abbiamo un oggetto che ha il field mappato
    // possiamo usare l'oggetto Sede (già definito)
    // mettendo l'indicazione FetchType LAZY non carica i dati delle sedi
//   @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    // Non fa comparire la sede nel JSON
//    @JsonbTransient
    @JoinColumn(name = "id_sede", referencedColumnName = "id_sede")
    private Sede sede;

    
    @ManyToMany()
    // serve per spiegare come è fatta la nostra tabella "t_tags_corsi"
    @JoinTable(
            name = "t_tags_corsi",
            joinColumns
            = @JoinColumn(name = "id_corso",
                    referencedColumnName = "id_corso"),
            inverseJoinColumns
            = @JoinColumn(name = "id_tag",
                    referencedColumnName = "id_tag")
    )
    private Set<Tag> tags = new TreeSet<>();
    
    

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
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
        return "Corso{" + "id=" + id + ", nome=" + nome + ", edizione=" + edizione + ", inizio=" + inizio + ", fine=" + fine + ", note=" + note + '}';
    }

    public void setTags(Set<Tag> tosave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}