/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prgguru.jersey;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Evenement;
import entities.Lieu;
import entities.Participation;
import entities.ParticipationPK;
import entities.Personne;
import entities.Post;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import sessions.EvenementFacadeLocal;
import sessions.LieuFacadeLocal;
import sessions.ParticipationFacadeLocal;

import sessions.PersonneFacadeLocal;
import sessions.PostFacadeLocal;



/**
 *
 * @author ilias
 */
//Path: http://localhost/<appln-folder-name>/login
@Path("/posts")
public class Posts {

    PostFacadeLocal postFacade = lookupPostFacadeLocal();


    
    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/afficher")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
    public String afficherPosts(@QueryParam("evenement") String evenement){
        List<Post> listPost = new ArrayList<Post>();
        String response = "";
        Evenement ev= null;
        
        Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .setDateFormat("MMM d, yyyy HH:mm:ss")
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
        
            ev=gson.fromJson(evenement, Evenement.class);
            listPost.addAll(postFacade.findAllByEvenement(ev));
 
        response= gson.toJson(listPost);
    
        return response;          
    }
    
  
 

    private PostFacadeLocal lookupPostFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PostFacadeLocal) c.lookup("java:global/ServiceWebAndroid/ServiceWebAndroid-ejb/PostFacade!sessions.PostFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    

}
