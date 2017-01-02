/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evenement;
import entities.Post;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mkass
 */
@Stateless
public class PostFacade extends AbstractFacade<Post> implements PostFacadeLocal {

    @PersistenceContext(unitName = "ServiceWebAndroid-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }

    @Override
    public List<Post> findAllByEvenement(Evenement ev) {
        Query q = em.createNamedQuery("Post.findAllByEvenement");
        q.setParameter("evenement", ev);
        
        return (List <Post>) q.getResultList();
    }
}