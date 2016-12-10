/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Evenement;
import entities.Participation;
import entities.Personne;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ilias
 */
@Local
public interface ParticipationFacadeLocal {

    void create(Participation participation);

    void edit(Participation participation);

    void remove(Participation participation);

    Participation find(Object id);

    List<Participation> findAll();

    List<Participation> findRange(int[] range);

    int count();
    public List<Evenement> evenementsPersonne(Personne P);

    public List<Evenement> evenementsAmis(Personne P, Integer offset, Integer nbre, boolean plusAncien);
    
}
