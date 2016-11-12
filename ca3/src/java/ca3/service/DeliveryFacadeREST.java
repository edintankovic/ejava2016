/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca3.service;

import ca3.Delivery;
import ca3.Pod;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author edint_000
 */
@Stateless
@Path("delivery")
public class DeliveryFacadeREST extends AbstractFacade<Delivery> {

    @PersistenceContext(unitName = "ca3PU")
    private EntityManager em;

    public DeliveryFacadeREST() {
        super(Delivery.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response create(@FormParam("name") String name, 
            @FormParam("address") String address, 
            @FormParam("phone") String phone) {

        Delivery d = new Delivery(name, address, phone);
        super.create(d);
        
        Pod p = new Pod();
        p.setPkgId(d);
        p.setDeliveryDate(new Date());
        em.persist(p);
        
        return Response.ok("OK", MediaType.TEXT_PLAIN).build();
    }

 
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
