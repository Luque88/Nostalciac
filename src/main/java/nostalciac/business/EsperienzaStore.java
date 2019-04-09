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
import nostalciac.entity.Esperienza;
import nostalciac.entity.Tag;

/**
 *
 * @author tss
 */
@Stateless
public class EsperienzaStore {
    
    @PersistenceContext()
    EntityManager em;

    /**
     * Restituisce tutte le Esperienze da DB
     *
     * @return tutte le Esperienze
     */
    public List<Esperienza> all() {
        return em.createQuery("SELECT e FROM Esperienza e ORDER BY e.nome", Esperienza.class)
                .getResultList();
    }

    /**
     * Insert o Update su DB
     *
     * @param esperienza
     * @return
     */
    public Esperienza save(Esperienza esperienza) {
        return em.merge(esperienza);
    }
    
    /**
     * Restituisce l'Esperienza con id
     *
     * @param id
     * @return
     */
    public Esperienza find(int id) {
        return em.find(Esperienza.class, id);
    }
    
    /**
     * Restituisce l'Esperienza a partire dall'id dell'Anagrafica
     *
     * @param anagraficaId
     * @return
     */
    public List<Esperienza> findByAnagrafica(int anagraficaId) {
        return em.createQuery("select e from Esperienza e where e.anagrafica.id= :anagrafica_id order by e.nome", Esperienza.class)
                .setParameter("anagrafica_id", anagraficaId)
                .getResultList();
    }
    /**
     * Rimuove da DB l'Esperienza tramite id
     *
     * @param id
     */
    public void remove(int id) {
        em.remove(find(id));
    }

    /**
     * Restituisce le esperienze trovate in base alla ricerca
     *
     * @param searchNome
     * @return
     */
    public List<Esperienza> search(String searchNome, String searchLuogo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Esperienza> query = cb.createQuery(Esperienza.class);
        Root<Esperienza> root = query.from(Esperienza.class);

        Predicate condition = cb.conjunction();

        if (searchNome != null && !searchNome.isEmpty()) {
            condition = cb.and(condition,
                    cb.like(root.get("nome"), "%" + searchNome + "%"));
        }

        if (searchLuogo != null && !searchLuogo.isEmpty()) {
            condition = cb.and(condition,
                    cb.like(root.get("luogo"), "%" + searchLuogo + "%"));
        }
        
        query.select(root)
                .where(condition)
                .orderBy(cb.asc(root.get("nome")));

        return em.createQuery(query)
                .getResultList();
    }

    public List<Tag> findTags(int id) {
        return em.createQuery("select e.tags from Esperienza e where e.id= :esperienza_id", Tag.class)
                .setParameter("esperienza_id", id)
                .getResultList();
    }
}

    

