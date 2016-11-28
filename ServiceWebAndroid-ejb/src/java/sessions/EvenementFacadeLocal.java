/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evenement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ilias
 */
@Local
public interface EvenementFacadeLocal {

    void create(Evenement evenement);

    void edit(Evenement evenement);

    void remove(Evenement evenement);

    Evenement find(Object id);

    List<Evenement> findAll();

    List<Evenement> findRange(int[] range);

    int count();
    
}
