/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evenement;
import entities.Participation;
import entities.Personne;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ilias
 */
@Stateless
public class ParticipationFacade extends AbstractFacade<Participation> implements ParticipationFacadeLocal {

    @PersistenceContext(unitName = "ServiceWebAndroid-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParticipationFacade() {
        super(Participation.class);
    }
      public List<Evenement> evenementsPersonne(Personne P){
     List<Evenement> E= null;
    Query q = em.createNamedQuery("Participation.findEventByParticipant");
    q.setParameter("participant", P);
    
    List L=q.getResultList();
    if(L.size()>0){
    E=(List<Evenement>) L;}
    return E;
     
     }   
         
    
}
