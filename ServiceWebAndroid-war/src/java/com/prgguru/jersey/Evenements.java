/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prgguru.jersey;

import com.google.gson.Gson;
import com.prgguru.jersey.Login;
import entities.Detailevent;
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
import sessions.DetaileventFacade;
import sessions.EvenementFacade;
import sessions.EvenementFacadeLocal;
import sessions.ParticipationFacadeLocal;

import sessions.PersonneFacadeLocal;



/**
 *
 * @author ilias
 */
//Path: http://localhost/<appln-folder-name>/login
@Path("/evenement")
public class Evenements {

    EvenementFacadeLocal evenementFacade = lookupEvenementFacadeLocal();

   
    
    PersonneFacadeLocal personneFacade = lookupPersonneFacadeLocal();
    DetaileventFacade detailFacade = new DetaileventFacade();


     @GET
     @Path("/DetailEvent")
      @Produces(MediaType.APPLICATION_JSON) 
    public String DescriptionEvent(@QueryParam("evenement") int idEvent){
        Evenement e= evenementFacade.find(idEvent);
       return detailFacade.DescriptionEvent(e);
        
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

    private EvenementFacadeLocal lookupEvenementFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (EvenementFacadeLocal) c.lookup("java:global/ServiceWebAndroid/ServiceWebAndroid-ejb/EvenementFacade!sessions.EvenementFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    

    

     
    
    
    
}
