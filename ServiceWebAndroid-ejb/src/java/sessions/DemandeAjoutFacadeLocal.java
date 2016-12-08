/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.DemandeAjout;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ilias
 */
@Local
public interface DemandeAjoutFacadeLocal {

    void create(DemandeAjout demandeAjout);

    void edit(DemandeAjout demandeAjout);

    void remove(DemandeAjout demandeAjout);

    DemandeAjout find(Object id);

    List<DemandeAjout> findAll();

    List<DemandeAjout> findRange(int[] range);

    int count();
    
}
