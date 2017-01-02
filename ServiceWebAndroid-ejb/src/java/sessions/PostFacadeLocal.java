/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evenement;
import entities.Post;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author mkass
 */
@Local
public interface PostFacadeLocal {

    void create(Post post);

    void edit(Post post);

    void remove(Post post);

    Post find(Object id);

    List<Post> findAll();

    List<Post> findRange(int[] range);

    public List<Post> findAllByEvenement(Evenement ev);
    
    int count();
    
}
