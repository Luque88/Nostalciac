/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nostalciac.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author tss
 */
@Entity
@Table(name = "t_tags")
public class Tag implements Serializable{
    
    // @Id per indicare la proprietà che è la chiave primaria sul db
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Messo mappatura ai field
    // Per la prima colonna abbiamo inserito due proprietà non necessarie
    // visto che il DB già esiste...sono:
    // nullable: indica che è un campo NON NULL:
    // length: indica che è uno String di 50 caratteri
    @Column(name = "id_tag", nullable = false,length = 50)
    private int id;
 
   
    @Column(name = "tag")
    private String tag;
    
    @Column(name = "tipo")
    private String tipo;
    
    public Tag() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash =53 * hash + this.id;
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
        final Tag other = (Tag) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tag{" + "id=" + id + ", tag=" + tag + ", tipo=" + tipo + '}';
    }
}

