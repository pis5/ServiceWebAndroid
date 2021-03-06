/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prgguru.jersey;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prgguru.jersey.Login;
import entities.Evenement;
import entities.Personne;
import java.io.IOException;
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
import org.codehaus.jackson.map.ObjectMapper;
import sessions.ParticipationFacadeLocal;
import sessions.PersonneFacadeLocal;

/**
 *
 * @author ilias
 */
//Path: http://localhost/<appln-folder-name>/login
@Path("/evenementsamis")
public class EvenementsAmis {

    ParticipationFacadeLocal participationFacade = lookupParticipationFacadeLocal();

    PersonneFacadeLocal personneFacade = lookupPersonneFacadeLocal();
    
    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/afficher")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
     public String afficherEvenements(@QueryParam("personne") String personne, @QueryParam("offset") String offset, @QueryParam("nbre") String nbre, @QueryParam("plusAncien") String plusAncien){
        String response = "";
        Personne P= null;
        System.out.println("hi you");
        //try {
            //récupération de la personne concernée
           
            Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .setDateFormat("MMM d, yyyy HH:mm:ss")
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
            P=gson.fromJson(personne, Personne.class);
            P=personneFacade.find(P.getId());
            
            List<Evenement> evenements = participationFacade.evenementsAmis(P,gson.fromJson(offset,Integer.class),gson.fromJson(nbre,Integer.class),gson.fromJson(plusAncien,boolean.class));
            
            response= gson.toJson(evenements);
           
// } catch (IOException ex) {
        //    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
       // }
        System.out.println("bye you");
       
    return response;        
    
    }

    private PersonneFacadeLocal lookupPersonneFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (PersonneFacadeLocal) c.lookup("java:global/ServiceWebAndroid/ServiceWebAndroid-ejb/PersonneFacade!sessions.PersonneFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ParticipationFacadeLocal lookupParticipationFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (ParticipationFacadeLocal) c.lookup("java:global/ServiceWebAndroid/ServiceWebAndroid-ejb/ParticipationFacade!sessions.ParticipationFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
