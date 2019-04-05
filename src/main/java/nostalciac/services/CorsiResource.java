/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nostalciac.services;

/**
 *
 * @author tss
 */
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
import nostalciac.entity.Corso;
import nostalciac.entity.Sede;

/**
 *
 * @author tss
 */

   public class CorsiResource {
    
    @Inject  
    private  CorsoStore store;
    
    @Inject
    private  SedeStore sedeStore;
    
    @Context
    ResourceContext rc;
    
    private  Integer sedeId;
    private Integer idSede;
    

    

    @GET
    public List<Corso> findAll() {
        return store.findBySede(sedeId);
    }

   
    @Path("{id}")
    // "./resources/tags/2 "   <--- esempio
   public CorsoResource find(@PathParam("id") Integer id) {
        CorsoResource resource = rc.getResource(CorsoResource.class);
        resource.setId(id);
        resource.setIdSede();
        return resource;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Corso c, @Context UriInfo uriInfo, int idSede) {
        
        Sede sede= sedeStore.find(idSede);
        c.setSede(sede);
        Corso saved = store.save(c);
         URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path("/" + saved.getId())
                .build();
//         return Response.ok(uri).build();
        return Response.ok(uri).build();
        
    }

   
  //   @PUT
  //   @Path("{id}/tags")
  //  @Consumes(MediaType.APPLICATION_JSON)
     // public void updateTags(@PathParam("id")Integer id, List<Integer> idTags){
   //    Corso finded = store.find(id);
   //     Set<Tag> tosave = idTags.stream()
   //              .map(t -> tagStore.find(t)),
   //             .collect(Collectors.toSet());
   //     finded.setTags(tosave);
   //   store.save(finded);
     //  }

    public void setIdSede(Integer idSede) {
        this.idSede = idSede;
    }

    
  }

  
    
     
     

    
 