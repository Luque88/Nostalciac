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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import nostalciac.business.AnagraficaStore;
import nostalciac.business.EsperienzaStore;
import nostalciac.entity.Anagrafica;
import nostalciac.entity.Esperienza;

/**
 *
 * @author tss
 */

public class EsperienzeResource {
    
    @Inject
    EsperienzaStore store;
    
     @Inject
    AnagraficaStore anagraficaStore;
    
    @Context
    ResourceContext rc;

    private Integer idAnagrafica;
    
    @GET
    public List<Esperienza> findByAnagrafica() {
        return store.findByAnagrafica();
    }
    
   
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Esperienza esperienza, @Context UriInfo uriInfo) {
         esperienza.setAnagrafica(anagraficaStore.find(idAnagrafica));
        Esperienza saved = store.save(esperienza);
        // gli vogliamo restituire il percorso della risorsa esposta
        // del tipo "/resources/esperienze/5"
        URI uri=uriInfo
                .getAbsolutePathBuilder()
                .path("/"+saved.getId())
                .build();
        
        return Response.ok(uri).build();
         
    }
    
     @Path("{id}")
    public EsperienzaResource find(@PathParam("id") Integer id) {
        EsperienzaResource resource = rc.getResource(EsperienzaResource.class);
        resource.setId(id);
        resource.setIdAnagrafica(idAnagrafica);
        return resource;
    }

    /*
    get e set
     */
    public void setIdAnagrafica(Integer idAnagrafica) {
        this.idAnagrafica = idAnagrafica;
    }

}

    
    