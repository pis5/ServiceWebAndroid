/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evenement;
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
public class EvenementFacade extends AbstractFacade<Evenement> implements EvenementFacadeLocal {

    @PersistenceContext(unitName = "ServiceWebAndroid-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvenementFacade() {
        super(Evenement.class);
    }

     public List<Evenement> evenementsPersonne(Personne P){
     List<Evenement> E= null;
    Query q = em.createNamedQuery("Evenement.findByParticipant");
    q.setParameter("personne", P);
    
    List L=q.getResultList();
    if(L.size()>0){
    E=(List<Evenement>) L;}
    return E;
     
     }
    
}
