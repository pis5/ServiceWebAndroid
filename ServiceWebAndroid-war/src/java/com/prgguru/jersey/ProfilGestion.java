/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prgguru.jersey;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.prgguru.jersey.Register;
import com.prgguru.jersey.Utility;
import entities.Personne;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import sessions.PersonneFacadeLocal;


/**
 *
 * @author ilias
 */
//Path: http://localhost/<appln-folder-name>/register
@Path("/profile")
public class ProfilGestion {
    
    
    

    PersonneFacadeLocal personneFacade = lookupPersonneFacadeLocal();
    
    
 @GET
    // Path: http://localhost/<appln-folder-name>/login/dologin
    @Path("/update")
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/login/dologin?username=abc&password=xyz
     public String updateProfil(@QueryParam("personne") String personne){
        String response = "";
        Personne P;
           Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .setDateFormat("MMM d, yyyy HH:mm:ss")
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
           
          P=gson.fromJson(personne, Personne.class);
          System.out.println(P.getNom()+" : "+P.getId());
            personneFacade.edit(P); 
            response= "updated";
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
}
