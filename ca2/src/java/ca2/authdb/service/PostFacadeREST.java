/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.authdb.service;

import ca2.authdb.Post;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author edint_000
 */
@Stateless
@Path("post")
public class PostFacadeREST extends AbstractFacade<Post> {

    @PersistenceContext(unitName = "ca2PU")
    private EntityManager em;

    public PostFacadeREST() {
        super(Post.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response create(@FormParam("userid") String userid, 
            @FormParam("title") String title,
            @FormParam("content") String content,
            @FormParam("category") String category,
            @FormParam("dt") String dt) {

        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCategory(category);
        post.setUserid(userid);
        
        try {
            Date dateTime = new SimpleDateFormat("MM/dd/yyyy").parse(dt);
            post.setDt(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        em.persist(post);

        return Response.ok("OK", MediaType.TEXT_PLAIN).build();
    }

    @GET
    @Path("{userid}")
    @Produces({MediaType.APPLICATION_JSON})
    public Post find(@PathParam("id") String userid) {
        return super.find(userid);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
