/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Detailevent;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author HAMZA
 */
@Local
public interface DetaileventFacadeLocal {

    void create(Detailevent detailevent);

    void edit(Detailevent detailevent);

    void remove(Detailevent detailevent);

    Detailevent find(Object id);

    List<Detailevent> findAll();

    List<Detailevent> findRange(int[] range);

    int count();
    
}
