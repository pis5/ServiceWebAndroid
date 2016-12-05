/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.EvenementInvitation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ilias
 */
@Local
public interface EvenementInvitationFacadeLocal {

    void create(EvenementInvitation evenementInvitation);

    void edit(EvenementInvitation evenementInvitation);

    void remove(EvenementInvitation evenementInvitation);

    EvenementInvitation find(Object id);

    List<EvenementInvitation> findAll();

    List<EvenementInvitation> findRange(int[] range);

    int count();
   
    
}
