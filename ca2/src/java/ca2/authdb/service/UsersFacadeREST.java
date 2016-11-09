/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca2.authdb.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ca2.authdb.Users;
import ca2.authdb.Groups;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author edint_000
 */
@Stateless
@Path("register")
public class UsersFacadeREST {

    @PersistenceContext(unitName = "ca2PU")
    private EntityManager em;

    public UsersFacadeREST() {
    }
    
    private String getHashInB64(String algo, String password){
        String outEncoded = "";
        
        try {
            MessageDigest md = MessageDigest.getInstance(algo);
            md.update(password.getBytes("UTF-8")); // Change this to "UTF-16" if needed
            byte[] digest = md.digest();
            outEncoded = Base64.getEncoder().encodeToString( digest );
        }
        catch (NoSuchAlgorithmException e) {
            System.err.println("Not a valid message digest algorithm..");
        }   
        catch (UnsupportedEncodingException e){
            System.err.println("Unsupported encoding.");
        }
   
        return outEncoded;
    }
    
    
    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    public Response create(@FormParam("userid") String userid, @FormParam("password") String password) {

        String hash = getHashInB64("SHA-256", password);

        Users u = new Users(userid, hash);
        em.persist(u);
        
        Groups g = new Groups(userid, "user");
        em.persist(g);

        return Response.ok("OK", MediaType.TEXT_PLAIN).build();
    }

    
}
