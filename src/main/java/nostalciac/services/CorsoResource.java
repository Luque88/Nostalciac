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
import javax.ws.rs.core.MediaType;
import nostalciac.business.CorsoStore;
import nostalciac.business.SedeStore;
import nostalciac.business.TagStore;
import nostalciac.entity.Corso;
import nostalciac.entity.Tag;

/**
 *
 * @author tss
 */

public class CorsoResource {
    
    @Inject
    private CorsoStore store;

    @Inject
    private SedeStore sedeStore;

    @Inject
    private TagStore tagStore;
    
    private Integer id;
    private Integer idSede;
    
    @GET
    public Corso find(){
        return store.find(id);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Corso c) {
        c.setId(id);
        c.setSede(sedeStore.find(idSede));
        store.save(c);
    }

    @DELETE
    public void delete() {
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
        Corso finded = store.find(id);
        Set<Tag> tosave = idTags.stream()
                .map(t -> tagStore.find(t))
                .collect(Collectors.toSet());
        finded.setTags(tosave);
        store.save(finded);
    }
    
    /*
    get e set
    */

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    void setIdSede() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
