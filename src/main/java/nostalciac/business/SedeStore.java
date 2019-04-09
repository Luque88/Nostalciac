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

    @PersistenceContext()
    EntityManager em;

    /**
     * Restituisce tutti i Tag da DB
     *
     * @return tutti i Tag
     */
    public List<Sede> all() {
        return em.createQuery("SELECT e FROM Sede e ORDER BY e.nome", Sede.class)
                .getResultList();
    }

    /**
     * Insert o Update su DB
     *
     * @param sede
     * @return
     */
    public Sede save(Sede sede) {
        return em.merge(sede);
    }

    /**
     * Restituisce la Sede con id
     *
     * @param id
     * @return
     */
    public Sede find(int id) {
        return em.find(Sede.class, id);
    }

    /**
     * Rimuove da DB la Sede tramite id
     *
     * @param id
     */
    public void remove(int id) {
        em.remove(find(id));
        // oppure:
        // em.remove(em.find(Sede.class, id));
    }

    /**
     * Restituisce le sedi trovate in base alla ricerca
     *
     * @param searchNome
     * @param searchCitta
     * @return
     */
    public List<Sede> search(String searchNome, String searchCitta) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Sede> query = cb.createQuery(Sede.class);
        Root<Sede> root = query.from(Sede.class);

        Predicate condition = cb.conjunction();

        if (searchNome != null && !searchNome.isEmpty()) {
            condition = cb.and(condition,
                    cb.like(root.get("nome"), "%" + searchNome + "%"));
        }

        if (searchCitta != null && !searchCitta.isEmpty()) {
            condition = cb.and(condition,
                    cb.like(root.get("citta"), "%" + searchCitta + "%"));
        }

        query.select(root)
                .where(condition)
                .orderBy(cb.asc(root.get("nome")));

        return em.createQuery(query)
                .getResultList();
    }
}