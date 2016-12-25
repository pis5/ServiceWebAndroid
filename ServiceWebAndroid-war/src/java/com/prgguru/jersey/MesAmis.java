/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prgguru.jersey;

import com.google.gson.Gson;
import entities.AmisPK;
import entities.Evenement;
import entities.Personne;
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
import sessions.AmisFacadeLocal;
import sessions.PersonneFacadeLocal;

/**
 *
 * @author ilias
 */
@Path("/amis")
public class MesAmis {

    PersonneFacadeLocal personneFacade = lookupPersonneFacadeLocal();

    AmisFacadeLocal amisFacade = lookupAmisFacadeLocal();
    // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/afficher")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
     public String listeMesAmis(@QueryParam("personne") String personne){
        String response = "";
        Personne P= null;
            Gson gson = new Gson();
            P=gson.fromJson(personne, Personne.class);
            P=personneFacade.find(P.getId());
            response= gson.toJson(amisFacade.findAmis(P));
    return response;
     }
     
      // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
     @Path("/delete")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
     public String deleteAmi(@QueryParam("personne") String personne, @QueryParam("personneasupprimer") String ASupprimer){
        String response = "";
        System.out.println("how you doing");
        Personne P= null;
        Personne P2=null;
            Gson gson = new Gson();
            P=gson.fromJson(personne, Personne.class);
            P2=gson.fromJson(ASupprimer, Personne.class);
            P=personneFacade.find(P.getId());
            P2=personneFacade.find(P2.getId());
            System.out.println(P2.getNom());
            AmisPK A = new AmisPK();
            A.setPersonne(P.getId());
            A.setAmi(P2.getId()); 
            
            
            
            amisFacade.remove(amisFacade.find(A));
            A.setPersonne(P2.getId());
            A.setAmi(P.getId());
            amisFacade.remove(amisFacade.find(A));
            response= "deleted";
            System.out.println(response);
    return response;
     }

    private AmisFacadeLocal lookupAmisFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (AmisFacadeLocal) c.lookup("java:global/ServiceWebAndroid/ServiceWebAndroid-ejb/AmisFacade!sessions.AmisFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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
    
}
