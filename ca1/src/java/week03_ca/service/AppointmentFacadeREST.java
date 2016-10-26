/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week03_ca.service;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import week03_ca.Appointment;
import week03_ca.People;

/**
 *
 * @author edint_000
 */
@Stateless
@Path("appointment")
public class AppointmentFacadeREST extends AbstractFacade<Appointment> {

    @PersistenceContext(unitName = "week03_caPU")
    private EntityManager em;
    
    private People getPersonIdByEmail(String email) {
        TypedQuery<People> query = em.createQuery("select p from People p where p.email = :email", 
                People.class).setParameter("email", email);
        
        return query.getSingleResult();
    }

    public AppointmentFacadeREST() {
        super(Appointment.class);
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{email}")
    public List<Appointment> getAppointmentsByEmail2(@PathParam("email") String email) {
        TypedQuery<Appointment> query = em.createQuery("select a from People p join p.appointmentCollection a where p.email = :email", 
                Appointment.class).setParameter("email", email);
        
        return query.getResultList();
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response create(@FormParam("date") String date, @FormParam("description") String description, 
            @FormParam("email") String email) {
        
        Long l = Long.parseLong(date);
        Date d = new Date(l);
        People p = getPersonIdByEmail(email);
        
        Appointment entity = new Appointment();
        entity.setDateTime(d);
        entity.setDescription(description);
        entity.setPerson(p);
        
        super.create(entity);
        
        return Response.ok("OK", MediaType.TEXT_PLAIN).build(); 
    }



    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
