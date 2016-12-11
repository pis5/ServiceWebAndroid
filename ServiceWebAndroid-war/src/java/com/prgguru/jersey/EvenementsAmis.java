/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prgguru.jersey;

import com.google.gson.Gson;
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
        ObjectMapper mapper = new ObjectMapper();
        //try {
            //récupération de la personne concernée
            
           // P=personneFacade.find(mapper.readValue(personne, Personne.class).getId());
            //récupération et conversion des événements auquels la personne participe
            Gson gson = new Gson();
            P=gson.fromJson(personne, Personne.class);
            System.out.println(P.getId());
            List<Evenement> le= participationFacade.evenementsAmis(P,gson.fromJson(offset,Integer.class),gson.fromJson(nbre,Integer.class),gson.fromJson(plusAncien,boolean.class));
            System.out.println(le.size());
            response= gson.toJson(participationFacade.evenementsAmis(P,gson.fromJson(offset,Integer.class),gson.fromJson(nbre,Integer.class),gson.fromJson(plusAncien,boolean.class)));
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
