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
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import nostalciac.business.AnagraficaStore;
import nostalciac.business.EsperienzaStore;
import nostalciac.business.TagStore;
import nostalciac.entity.Esperienza;
import nostalciac.entity.Tag;

/**
 *
 * @author tss
 */
public class EsperienzaResource {
    
    @Inject
    EsperienzaStore store;

    @Inject
    AnagraficaStore anagraficaStore;
    
    @Inject
    TagStore tagStore;
    
    @Context
    ResourceContext rc;

    @PathParam("id")
    private Integer id;
    
    private Integer idAnagrafica;
    
    
    
    @GET
    public Esperienza find(){
        return store.find(id);
    }
    
    @PUT
    public void update(Esperienza e){
        e.setId(id);
        e.setAnagrafica(anagraficaStore.find(idAnagrafica));
        store.save(e);
    }
    
    @DELETE
    public void delete(){
        store.remove(id);
    }
    
    @GET
    @Path("tags")
    public List<Tag> findTags() {
        return store.findTags(id);
    }

    @PUT
    @Path("tags")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateTags(List<Integer> idTags) {
        Esperienza finded = store.find(id);
        Set<Tag> tosave = idTags.stream()
                .map(t -> tagStore.find(t))
                .collect(Collectors.toSet());
        finded.setTags(tosave);
        store.save(finded);
    }
    /*
    get e set
    */

    public void setIdAnagrafica(Integer idAnagrafica) {
        this.idAnagrafica = idAnagrafica;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
}
