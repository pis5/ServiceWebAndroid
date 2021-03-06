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
@Path("/mesevenements")
public class MesEvenements {

    PostFacadeLocal postFacade = lookupPostFacadeLocal();

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
    public String afficherEvenements(@QueryParam("personne") String personne, @QueryParam("offset") String offset, @QueryParam("nbre") String nbre, @QueryParam("plusAncien") String plusAncien){
        String response = "";
        Personne P= null;
        System.out.println("hi you");

         Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .setDateFormat("MMM d, yyyy HH:mm:ss")
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
            P=gson.fromJson(personne, Personne.class);
            P=personneFacade.find(P.getId());
             
            List<Evenement> evenements = participationFacade.mesEvenements(P,gson.fromJson(offset,Integer.class),gson.fromJson(nbre,Integer.class),gson.fromJson(plusAncien,boolean.class));
            System.out.println(evenements.size());
            
            response= gson.toJson(evenements);
      

        return response;          
    }
    
  
  @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
  @Path("/createEvent")
  // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    public String creerEvenement(@QueryParam("evenement") String event){
       Evenement a= null;
            Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .setDateFormat("MMM d, yyyy HH:mm:ss")
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
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
    
     @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
  @Path("/add")
  // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    public String participer(@QueryParam("personne") String pers,@QueryParam("evenement") String event){
       Evenement a= null;
       Personne P= null;
            Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .setDateFormat("MMM d, yyyy HH:mm:ss")
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
            a=evenementFacade.find(gson.fromJson(event, Evenement.class).getId());
            P=personneFacade.find(gson.fromJson(pers, Personne.class).getId());
            Date actuelle = new Date();
             
             
        Participation Par = new Participation();
        Par.setDate(actuelle);
        Par.setEvenement1(a);
        
        Par.setPersonne(P);
        ParticipationPK pk= new ParticipationPK();
        pk.setEvenement(a.getId());
        pk.setParticipant(P.getId());
        Par.setParticipationPK(pk);
        participationFacade.create(Par);
        
        return("added");
}
    
    
      @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
  @Path("/createPost")
  // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    public String creerPost(@QueryParam("post") String event){
       Post a= null;
            Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .setDateFormat("MMM d, yyyy HH:mm:ss")
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
            a=gson.fromJson(event, Post.class);
            Date actuelle = new Date();
             
            
          a.setDate(actuelle);
          postFacade.create(a);
        
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
