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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import nostalciac.business.TagStore;
import nostalciac.entity.Tag;

/**
 *
 * @author tss
 */
@Path("/tags")
public class TagsResource {

    @Inject
    TagStore store;
    
    @GET
    public List<Tag> findAll() {
        return store.all();
    }
    
    // ricerca per TAG e TIPO
    @GET
    @Path("search")
    public List<Tag> search(
            @QueryParam("tag") String searchTag,
            @QueryParam("tipo") String searchTipo) {
        return store.search(searchTag,searchTipo);
    }
    
    @GET
    @Path("{id}")
    // "./resources/tags/2 "   <--- esempio
    public Tag find(@PathParam("id") int id){
        return store.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Tag tag, @Context UriInfo uriInfo) {
        
        Tag saved = store.create(tag);
        // gli vogliamo restituire il percorso della risorsa esposta
        // del tipo "/resources/tags/25"
        URI uri=uriInfo
                .getAbsolutePathBuilder()
                .path("/"+saved.getId())
                .build();
        
        return Response.ok(uri).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public void update(@PathParam("id") int id,Tag tag){
        // se id esiste nel DB faccio un aggiornamento
        // altrimenti lo creo nuovo
        tag.setId(id);
        store.save(tag);
    }
    
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id){
        store.remove(id);
    }
}
