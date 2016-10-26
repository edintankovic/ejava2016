/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week03_ca.service;

import java.util.List;
import java.util.UUID;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import week03_ca.Appointment;
import week03_ca.People;

/**
 *
 * @author edint_000
 */
@Stateless
@Path("people")
public class PeopleFacadeREST extends AbstractFacade<People> {

    @PersistenceContext(unitName = "week03_caPU")
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
    @Produces({MediaType.APPLICATION_JSON})
    public List<Appointment> getAppointmentsByEmail(@QueryParam("email") String email) {
        TypedQuery<Appointment> query = em.createQuery("select a from People p join p.appointmentCollection a where p.email = :email", 
                Appointment.class).setParameter("email", email);
        
        return query.getResultList();
    }



    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
