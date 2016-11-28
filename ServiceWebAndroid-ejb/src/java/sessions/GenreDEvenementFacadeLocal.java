/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.GenreDEvenement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ilias
 */
@Local
public interface GenreDEvenementFacadeLocal {

    void create(GenreDEvenement genreDEvenement);

    void edit(GenreDEvenement genreDEvenement);

    void remove(GenreDEvenement genreDEvenement);

    GenreDEvenement find(Object id);

    List<GenreDEvenement> findAll();

    List<GenreDEvenement> findRange(int[] range);

    int count();
    
}
