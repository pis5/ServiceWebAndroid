/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Invitation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ilias
 */
@Local
public interface InvitationFacadeLocal {

    void create(Invitation invitation);

    void edit(Invitation invitation);

    void remove(Invitation invitation);

    Invitation find(Object id);

    List<Invitation> findAll();

    List<Invitation> findRange(int[] range);

    int count();
    
}
