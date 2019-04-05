/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nostalciac.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import nostalciac.business.AnagraficaStore;
import nostalciac.business.CorsoStore;
import nostalciac.business.EsperienzaStore;
import nostalciac.business.SedeStore;
import nostalciac.entity.Anagrafica;
import nostalciac.entity.Corso;
import nostalciac.entity.Sede;

/**
 *
 * @author tss
 */

// non deve avere annotazioni Path perchè è una sottorisorsa di AnagraficheResource
public class AnagraficaResource {
    
    @Inject
    AnagraficaStore store;
    
    @Inject
    EsperienzaStore esperienzaStore;
    
    @Inject
     CorsoStore corsoStore;
    
    @Context
    ResourceContext rc;
    
      private  Integer id;
    
   
    
      // Espongo il metodo di ricerca GET 
    // per ID
    @GET
    // non serve più il path (perchè glielo passa AnagraficheResource) @Path("{id}")
    public Anagrafica find() {
        return store.find(id);
    }

    // Espongo il metodo di update PUT 
    // aggiorna su DB il record indicato con id
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Anagrafica a) {
        // se id esiste nel DB faccio un aggiornamento
        // altrimenti lo creo nuovo
        a.setId(id);
        store.save(a);
    }

    // Espongo il metodo di update DELETE
    // cancello il record indicato con id
    @DELETE
    public void delete() {
        store.remove(id);
    }
    
    @Path("/esperienze")
   public EsperienzeResource esperienze() {
        EsperienzeResource resource = rc.getResource(EsperienzeResource.class);
        resource.setIdAnagrafica(id);
        return resource;
    }

    @GET
    @Path("corsi")
    public List<Corso> corsi(){
        return store.corsi(id);
    }
    
    @PUT
    @Path("corsi")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateCorsi(List<Integer> ids){
        Anagrafica finded = store.find(id);
        Set<Corso> tosave = ids.stream()
                .map(c -> corsoStore.find(c))
                .collect(Collectors.toSet());
        finded.setCorsi(tosave);
        store.save(finded);
    }
    /*
    get e set
     */
    public void setId(Integer id) {
        this.id = id;
    }

}