/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Detailevent;
import entities.Evenement;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author HAMZA
 */
@Stateless
public class DetaileventFacade extends AbstractFacade<Detailevent> implements DetaileventFacadeLocal {
    @PersistenceContext(unitName = "ServiceWebAndroid-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetaileventFacade() {
        super(Detailevent.class);
    }

    @Override
    public String DescriptionEvent(Evenement e) {
     
        String Desc = null;
      
      Query q = em.createNamedQuery("Detailevent.findDescription");
       
      q.setParameter("event",e);
      Desc= (String) q.getSingleResult();
      return Desc ;
        
        
    
    }
    
}
