/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prgguru.jersey;

import com.google.gson.Gson;
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

/**
 *
 * @author ilias
 */
@Path("/amis")
public class MesAmis {

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
            
            response= gson.toJson(amisFacade.findAmis(P));
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
    
}
