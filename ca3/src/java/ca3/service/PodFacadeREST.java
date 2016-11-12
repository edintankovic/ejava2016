/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca3.service;

import ca3.Pod;
import ca3.ePodRecord;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.File;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

/**
 *
 * @author edint_000
 */
@Stateless
@Path("pod")
public class PodFacadeREST extends AbstractFacade<Pod> {

    @PersistenceContext(unitName = "ca3PU")
    private EntityManager em;

    public PodFacadeREST() {
        super(Pod.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public void create(Pod entity) {
        super.create(entity);
    }
    
    
    @GET
    @Path("/callback")
    public Response callback( @QueryParam("podId") Integer podId, @QueryParam("ackId") String ackId){
        
        // Update the database
        
        Pod p = em.find(Pod.class, podId);
        p.setAckId(ackId);
        
        em.persist(p);

        return Response.ok("OK").build();
    }
    
    
    @POST
    @Path("/upload")
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    public Response upload( 
            @FormParam("podId") Integer podId,
            @FormParam("note") String note,
            @FormParam("image") File image,
            @FormParam("time") String time) {

        Client client = ClientBuilder.newBuilder()
			.register(MultiPartFeature.class)
			.build();


	//If uploading a file
	FileDataBodyPart imgPart = new FileDataBodyPart("image", 
			new File("/Pictures/ca3.png"),
			MediaType.APPLICATION_OCTET_STREAM_TYPE);

	//or if upload a byte array
	//byte[] buffer = ... //Get bits from somewhere
	//FileDataBodyPart imgPart = new BodyPart(buffer, MediaType.APPLICATION_OCTET_STREAM_TYPE)

	//This the the form field name
	//<input type="file" name="image">
	imgPart.setContentDisposition(
			FormDataContentDisposition.name("image")
			.fileName("ca3.png").build());

	//Add other fields
	MultiPart formData = new FormDataMultiPart()
			.field("epodId", "abc", MediaType.TEXT_PLAIN_TYPE)
			.field("note", note, MediaType.TEXT_PLAIN_TYPE)
			.field("time", Long.toString(System.currentTimeMillis()), MediaType.TEXT_PLAIN_TYPE)
                        .field("callback", "http://10.10.12.54:8080/ca3/callback", MediaType.TEXT_PLAIN_TYPE)
			.bodyPart(imgPart);
	formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

        
        
        
	WebTarget target = client.target("http://10.10.0.48:8080/epod/upload");
	Invocation.Builder inv = target.request();

	Response callResp = inv.post(Entity.entity(formData, formData.getMediaType()));

	System.out.println(">> call resp:" + callResp.getStatus());

        return Response.ok("OK").build();
    }



    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String get() {
        
        List<Object[]> recs = new ArrayList<Object[]>();
        recs = em.createQuery("SELECT \"abc\" as teamId, p.podId as podId,  p.pkgId.name as name,  p.pkgId.address as address, p.pkgId.phone as phone FROM Pod p")
                              .getResultList(); 
        

        List<ePodRecord> lst = new ArrayList<ePodRecord>();
        for (Object[] e : recs){
            lst.add(new ePodRecord(e[0].toString(), Integer.parseInt(e[1].toString()), e[2].toString(), e[3].toString(), e[4].toString()));
        }
        
        StringBuilder sb = new StringBuilder("["); 
        
        for (ePodRecord e : lst ){
            sb.append(e.toString() + ", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append("]");
        
        
        return sb.toString();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
