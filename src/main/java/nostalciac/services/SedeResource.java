/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nostalciac.services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import nostalciac.business.CorsoStore;
import nostalciac.business.SedeStore;
import nostalciac.entity.Sede;

/**
 *
 * @author tss
 */
// non deve avere annotazioni Path perchè è una sottorisorsa di SediResource
public class SedeResource {
    
    private Integer id;
    
    @Inject
    private  SedeStore store;
    
    @Inject
    private  CorsoStore corsoStore;
    
    @Context
    ResourceContext rc;
  
     
    

    

    // Espongo il metodo di ricerca GET 
    // per ID
    @GET
    // non serve più il path (perchè glielo passa SediResource) @Path("{id}")
    public Sede find() {
        return store.find(id);
    }

    // Espongo il metodo di update PUT 
    // aggiorna su DB il record indicato con id
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Sede sede) {
        // se id esiste nel DB faccio un aggiornamento
        // altrimenti lo creo nuovo
        sede.setId(id);
        store.save(sede);
    }

    // Espongo il metodo di update DELETE
    // cancello il record indicato con id
    @DELETE
    public void delete() {
        store.remove(id);
    }
    
    @Path("/corsi")
    public CorsiResource getCorsi(){
        CorsiResource resource = rc.getResource(CorsiResource.class);
     //   resource.setSedeId(id);
        return resource;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
}
