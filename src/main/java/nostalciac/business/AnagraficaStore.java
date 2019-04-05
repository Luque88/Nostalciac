/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nostalciac.business;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import nostalciac.entity.Anagrafica;
import nostalciac.entity.Corso;

/**
 *
 * @author tss
 */
@Stateless
public class AnagraficaStore {
    
    @PersistenceContext
    EntityManager em;
    
    /**
     * *
     * Restituisce tutte le anagrafiche
     *
     * @return
     */
    public List<Anagrafica> findAll() {
        // Dammi tutti 
        return em.createQuery("select e FROM Anagrafica e ORDER BY e.cognome ", Anagrafica.class)
                .getResultList();
    }
    
    /**
     * per salvare nuovo record su DB
     * 
     * @param anagrafica
     * @return 
     */
    
    /**
     * Ritorna il tag con ID passato
     *
     * @param id
     * @return
     */
    
        public Anagrafica find(Integer id){
        return em.find(Anagrafica.class, id);
    }

    /**
     * *
     * Insert o Update su DB
     *
     * @param anagrafica
     * @return
     */
    public Anagrafica save(Anagrafica anagrafica) {
        return em.merge(anagrafica);
    }

    
     /**
     * Cancella il record passando l'ID
     *
     * @param id
     */
    public void remove(int id) {
        // prima si cerca per ID e poi si cancella
         em.remove(find(id));
 
    }

    public List<Corso> corsi(Integer id){
        return em.createQuery("select e.corsi from Anagrafica e where e.id= :id", Corso.class)
                .setParameter("id", id)
                .getResultList();
    }

    public Anagrafica create(Anagrafica anagrafica) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

   

   
