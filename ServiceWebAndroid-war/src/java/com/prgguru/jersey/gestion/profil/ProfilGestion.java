/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prgguru.jersey.gestion.profil;

import com.prgguru.jersey.Register;
import com.prgguru.jersey.Utility;
import entities.Personne;
import java.io.IOException;
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
import sessions.PersonneFacadeLocal;

/**
 *
 * @author ilias
 */
//Path: http://localhost/<appln-folder-name>/register
@Path("/updateprofil")
public class ProfilGestion {
    
    
    

    PersonneFacadeLocal personneFacade = lookupPersonneFacadeLocal();
    
    
    
      // HTTP Get Method
    @GET
    // Path: http://localhost/<appln-folder-name>/register/doregister
    @Path("/doupdateprofil")  
    // Produces JSON as response
    @Produces(MediaType.APPLICATION_JSON) 
    // Query parameters are parameters: http://localhost/<appln-folder-name>/register/doregister?name=pqrs&username=abc&password=xyz
    public String doLogin(@QueryParam("personne") String personne){
        String response = "";
        //System.out.println("Inside doLogin "+uname+"  "+pwd);
        ObjectMapper mapper = new ObjectMapper();
        int retCode;
        try {
            retCode = updateUser(mapper.readValue(personne, Personne.class));
      
        if(retCode == 0){
            response = Utility.constructJSON("updateprofil",true);
        }else {
            response = Utility.constructJSON("updateprofil",false, "Cet E-mail est déja utilisé par un utilisateur enregistré.");
        }
          } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
 
    }
    
    
    
    
    
    
    
    private int updateUser(Personne P){
        System.out.println("Inside checkCredentials");
        int result = 1;
        
           try{
                personneFacade.edit(P);
                    System.out.println("RegisterUSer if");
                    result = 0;} catch (Exception  e){
                        System.out.println("user not registered");
                    result = 1;
                    }
                return result;
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
