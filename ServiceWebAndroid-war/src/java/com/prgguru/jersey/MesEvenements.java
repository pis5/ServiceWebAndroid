/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prgguru.jersey;

import com.google.gson.Gson;
import com.prgguru.jersey.Login;
import entities.Evenement;
import entities.Lieu;
import entities.Participation;
import entities.ParticipationPK;
import entities.Personne;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.codehaus.jackson.map.ObjectMapper;
import sessions.EvenementFacade;
import sessions.EvenementFacadeLocal;
import sessions.LieuFacadeLocal;
import sessions.ParticipationFacadeLocal;

import sessions.PersonneFacadeLocal;



/**
 *
 * @author ilias
 */
//Path: http://localhost/<appln-folder-name>/login
@Path("/mesevenements")
public class MesEvenements {

    LieuFacadeLocal lieuFacade = lookupLieuFacadeLocal();

    EvenementFacadeLocal evenementFacade = lookupEvenementFacadeLocal();

    ParticipationFacadeLocal participationFacade = lookupParticipationFacadeLocal();
    
    PersonneFacadeLocal personneFacade = lookupPersonneFacadeLocal();
    
    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/afficher")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
    public String afficherEvenements(@QueryParam("personne") String personne){
        String response = "";
        Personne P= null;
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            //récupération de la personne concernée
            
            P=personneFacade.find(mapper.readValue(personne, Personne.class).getId());
            //récupération et conversion des événements auquels la personne participe
            
            Gson gson = new Gson();
            response= gson.toJson(participationFacade.evenementsPersonne(P));
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    return response;        
    }
    
    
  @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
  @Path("/createEvent")
  // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    public String creerEvenement(@QueryParam("evenement") String event){
       Evenement a= null;
            Gson gson = new Gson();
            a=gson.fromJson(event, Evenement.class);
            Date actuelle = new Date();
             Lieu lieu = a.getLieu();
             
             lieuFacade.create(lieu);
            
          a.setDateDeCreation(actuelle);
        a.setLieu(lieu);
        evenementFacade.create(a);
        Participation P = new Participation();
        P.setDate(actuelle);
        P.setEvenement1(a);
        Personne pers=personneFacade.find(a.getOrganisateur().getId());
        P.setPersonne(pers);
        ParticipationPK pk= new ParticipationPK();
        pk.setEvenement(a.getId());
        pk.setParticipant(pers.getId());
        P.setParticipationPK(pk);
        participationFacade.create(P);
        
        return("created");
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

    private EvenementFacadeLocal lookupEvenementFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (EvenementFacadeLocal) c.lookup("java:global/ServiceWebAndroid/ServiceWebAndroid-ejb/EvenementFacade!sessions.EvenementFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private LieuFacadeLocal lookupLieuFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (LieuFacadeLocal) c.lookup("java:global/ServiceWebAndroid/ServiceWebAndroid-ejb/LieuFacade!sessions.LieuFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    


    
    
    
    
}
