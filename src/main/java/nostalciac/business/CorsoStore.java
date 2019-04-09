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
import nostalciac.entity.Corso;
import nostalciac.entity.Tag;

/**
 *
 * @author tss
 */
  @Stateless
public class CorsoStore {

    @PersistenceContext()
    EntityManager em;

    /**
     * Restituisce tutti i Corsi da DB
     *
     * @return tutti i Corsi
     */
  
  public Corso find(Integer id){
        return em.find(Corso.class, id);
    }
    
    public Corso save(Corso c){
        return em.merge(c);
    }
    
    public void remove(Integer id){
        em.remove(find(id));
    }
    
    public List<Corso> findBySede(Integer sedeId){
        return em.createQuery("select e from Corso e where e.sede.id= :sede_id order by e.nome", Corso.class)
                .setParameter("sede_id", sedeId)
                .getResultList();
    }
    
    public List<Corso> findAll(){
        return em.createQuery("select e from Corso e order by e.nome", Corso.class)
                .getResultList();
    }

    
    public List<Tag> findTags(Integer id) {
        return em.createQuery(
                "select e.tags from Corso e where e.id= :idCorso",Tag.class)
                .setParameter("idCorso", id)
                .getResultList();
    }
}

        
        
   
    
    


 
    