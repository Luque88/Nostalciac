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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 *
 * @author tss
 */
@Entity
@Table (name= "t_anagrafiche")
public class Anagrafica implements Serializable {

    public void setCorsi(Set<Corso> tosave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public static enum Ruolo {
        A, U
    }
    
    
    @Id
    @Column(name="id_anagrafica")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "cognome")
    private String cognome;

    @Column(name = "nome")
    private String nome;

    @Column(name = "usr")
    private String usr;

    @Column(name = "pwd")
    private String pwd;
    
    @Column(name = "mail")
    private String mail;
    
    @Column(name = "ruolo")
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo = Ruolo.U;
    
    @Column(name = "citta")
    private String citta;
    
    @Column(name = "indirizzo")
    private String indirizzo;
    
    @Column(name = "tel")
    private String tel;
     
    @Column(name = "note")
    private String note;
      
    @Column(name = "filefoto")
    private String foto;
    
    @Column(name = "data_nascita")
    @JsonbDateFormat("dd/MM/yyyy")
    private LocalDate nascita;

    
    @ManyToMany
    @OrderBy("nome ASC")
    @JoinTable(
            name = "t_anagtafiche_corsi",
            joinColumns
            = @JoinColumn(name = "id_anagrafica",
                    referencedColumnName = "id_anagrafica"),
            inverseJoinColumns
            = @JoinColumn(name = "id_corso",
                    referencedColumnName = "id_corso")
    )
            
    private Set<Corso> corsi = new TreeSet<>();
    
    
    
    
   //Costruttore vuoto
    public Anagrafica (){
        
    }
    
    //Setter e Getter delle proprietà

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

   public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFilefoto() {
        return foto;
    }

    public void setFilefoto(String filefoto) {
        this.foto = foto;
    }

    public LocalDate getNascita() {
        return nascita;
    }

    public void setNascita(LocalDate nascita) {
        this.nascita = nascita;
    }
   
    // Override dei metodi hashCode & equals, solo chiave primaria

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + this.id;
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
        final Anagrafica other = (Anagrafica) obj;
        return this.id == other.id;
    }
    
     // Override del metodo ToString

    @Override
    public String toString() {
     return "Anagrafica{" + "id=" + id + ", cognome=" + cognome + ", nome="
                + nome + ", nascita=" + nascita + ", usr=" + usr + ", pwd="
                + pwd + ", mail=" + mail + ", ruolo=" + ruolo + ", citta="
                + citta + ", indirizzo=" + indirizzo + ", tel=" + tel + ", note="
                + note + ", foto=" + foto + '}';
    }
}   


    

