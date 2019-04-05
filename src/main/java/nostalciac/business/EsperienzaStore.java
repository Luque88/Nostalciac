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
import nostalciac.entity.Esperienza;
import nostalciac.entity.Tag;

/**
 *
 * @author tss
 */
@Stateless
public class EsperienzaStore {
    
    @PersistenceContext
    EntityManager em;
    
     public List<Esperienza> findAll() {
        // Dammi tutti 
        return em.createQuery("select e FROM Esperienza e ORDER BY e.cognome ", Esperienza.class)
                .getResultList();
    }
    
    /**
     * per salvare nuovo record su DB
     * 
     * @param esperienza
     * @return 
     */
   public List<Esperienza> findByAnagrafica(Integer id ){
        return em.createQuery("select e from Esperienza e "
                + "where e.anagrafica.id= :id_anagrafica "
                + "order by e.nome", Esperienza.class)
                .setParameter("id_anagrafica", id)
                .getResultList();
    }
    
    /**
     * *
     * Insert o Update su DB
     *
     * @param esperienza
     * @return
     */
    public Esperienza save(Esperienza esperienza) {
        return em.merge(esperienza);
    }

    
    /**
     * Ritorna il tag con ID passato
     *
     * @param id
     * @return
     */
    public Esperienza find(Integer id) {
        return em.find(Esperienza.class, id);
    }

    /**
     * Cancella il record passando l'ID
     *
     * @param id
     */
    public void remove(Integer id) {
        // prima si cerca per ID e poi si cancella
        em.remove(find(id));
    }

    public List<Tag> findTags(Integer id) {
        return em.createQuery(
                "select e.tags from Esperienza e where e.id= :id",Tag.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Esperienza> findByAnagrafica() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

    

