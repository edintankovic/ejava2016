/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1.service;

import ca1.Appointment;
import ca1.Appt;
import ca1.People;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author edint_000
 */
@Stateless
@Path("people")
public class PeopleFacadeREST extends AbstractFacade<People> {

    @PersistenceContext(unitName = "ca1PU")
    private EntityManager em;

    public PeopleFacadeREST() {
        super(People.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response create(@FormParam("email") String email, @FormParam("name") String name) {
        People entity = new People(UUID.randomUUID().toString().substring(0, 8), name, email );
        super.create(entity);
        
        return Response.ok("OK", MediaType.TEXT_PLAIN).build();
    }
    
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public Response getAppointmentsByEmail(@QueryParam("email") String email) {
        TypedQuery<Appointment> query = em.createQuery("select a from People p join p.appointmentCollection a where p.email = :email", 
                Appointment.class).setParameter("email", email);
        
        List<Appointment> lst = query.getResultList();
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Appointment n : lst) {
            sb.append(new Appt(n.getApptId(), n.getDescription(), n.getApptDate().getTime(), n.getPid().getPid()).toString());
            sb.append(",");
        }
        sb.setLength(sb.length() - 1);
        sb.append("]");

        return Response.ok(sb.toString(), MediaType.TEXT_PLAIN).build();
        
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, People entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public People find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<People> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
