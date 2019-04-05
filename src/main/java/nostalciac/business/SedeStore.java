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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import nostalciac.entity.Sede;

/**
 *
 * @author tss
 */
// Indicazione che si tratta di un EJB
@Stateless
public class SedeStore {
    
    @PersistenceContext
    EntityManager em;
    
    /**
     * *
     * Restituisce tutti i tag
     *
     * @return
     */
    public List<Sede> all() {
        // Dammi tutti 
        return em.createQuery("select e FROM Sede e ORDER BY e.nome ", Sede.class)
                .getResultList();
    }

    // per salvare nuovo record su DB
    public Sede create(Sede sede) {
        return em.merge(sede);
    }

    /**
     * *
     * Insert o Update su DB
     *
     * @param sede
     * @return
     */
    public Sede save(Sede sede) {
        return em.merge(sede);
    }

    /**
     * Ritorna il tag con ID passato
     *
     * @param id
     * @return
     */
    public Sede find(int id) {
        return em.find(Sede.class, id);
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
    
}
