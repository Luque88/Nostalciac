/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nostalciac.services;

import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import nostalciac.business.CorsoStore;
import nostalciac.business.SedeStore;
import nostalciac.business.TagStore;
import nostalciac.entity.Sede;

/**
 *
 * @author tss
 */
@Path("/sedi")
public class SediResource {

    // Tramite Pajara istanzio l'oggetto
    @Inject
    SedeStore store;
    
    @Inject
    CorsoStore corsoStore;
    
    @Inject
    TagStore tagStore;
    
    @Context
    ResourceContext rc;
    

    // Espongo il metodo di ricerca GET 
    // che restituisce tutti i record
    @GET
    public List<Sede> findAll() {
        return store.all();
    }

    // Espongo il metodo di ricerca GET 
    // che restituisce per SEDE e CITTA'
   
   

    // Espongo il metodo di ricerca GET 
    // per ID
    // quando cerco una singola sede restituisco non più una Sede,
    // ma passo il controllo alla risorsa SedeResource
    // quando passo il controllo ad una sotto risorsa non devo più esporre il metodo @GET
    @Path("{id}")
    // restituisce l'oggetto SedeResource
    public SedeResource find(@PathParam("id") int id) {
        // restituisco una nuova istanza SedeResource
        SedeResource resource = rc.getResource(SedeResource.class);
        resource.setId(id);
        return resource;
    }

    // Espongo il metodo di salvataggio POST 
    // carica su DB il record
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Sede sede, @Context UriInfo uriInfo) {
        Sede saved = store.create(sede);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path("/" + saved.getId())
                .build();
//         return Response.ok(uri).build();
        return Response.ok(uri).build();
    }

    /*
    get e set
    */

    public ResourceContext getRc() {
        return rc;
    }

    public void setRc(ResourceContext rc) {
        this.rc = rc;
    }
    
}

    
            
    

