
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nostalciac.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author tss
 */
@Entity
@Table(name = "t_sedi")
public class Sede implements Serializable{
    // indicazione della chiave primaria tramite notazione @Id
    @Id
    // indicazione nome dei fields tramite @Column
    @Column(name = "id_sede")
    private int id;
    
    @Column(name = "sede")
    private String nome;
    
    @Column(name = "indirizzo_sede")
    private String indirizzo;
    
    @Column(name = "tel_sede")
    private String tel;
    
    @Column(name = "citta_sede")
    private String citta;
    
    @Column(name = "mail_sede")
    private String mail;
    
    @Column(name = "note_sede")
    private String note;
    
    // Costruttore vuoto
    public Sede() {
    }
    
    public Sede(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    // Setter e Getter delle proprietà

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

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    // Override dei metodi hashCode & equals, solo chiave primaria

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
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
        final Sede other = (Sede) obj;
        return this.id == other.id;
    }
    
    // Override del metodo ToString

    @Override
    public String toString() {
        return "Sede{" + "id=" + id + ", nome=" + nome + ", indirizzo=" + indirizzo + ", tel=" + tel + ", citta=" + citta + ", mail=" + mail + ", note=" + note + '}';
    }
    
    
    
    
    
}
