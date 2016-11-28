/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ilias
 */
@Entity
@Table(name = "evenement_invitation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EvenementInvitation.findAll", query = "SELECT e FROM EvenementInvitation e"),
    @NamedQuery(name = "EvenementInvitation.findByEvenement", query = "SELECT e FROM EvenementInvitation e WHERE e.evenementInvitationPK.evenement = :evenement"),
    @NamedQuery(name = "EvenementInvitation.findByEmetteur", query = "SELECT e FROM EvenementInvitation e WHERE e.evenementInvitationPK.emetteur = :emetteur"),
    @NamedQuery(name = "EvenementInvitation.findByRecepteur", query = "SELECT e FROM EvenementInvitation e WHERE e.evenementInvitationPK.recepteur = :recepteur")})
public class EvenementInvitation implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EvenementInvitationPK evenementInvitationPK;
    @JoinColumn(name = "evenement", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Evenement evenement1;
    @JoinColumn(name = "emetteur", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personne personne;
    @JoinColumn(name = "recepteur", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personne personne1;

    public EvenementInvitation() {
    }

    public EvenementInvitation(EvenementInvitationPK evenementInvitationPK) {
        this.evenementInvitationPK = evenementInvitationPK;
    }

    public EvenementInvitation(int evenement, int emetteur, int recepteur) {
        this.evenementInvitationPK = new EvenementInvitationPK(evenement, emetteur, recepteur);
    }

    public EvenementInvitationPK getEvenementInvitationPK() {
        return evenementInvitationPK;
    }

    public void setEvenementInvitationPK(EvenementInvitationPK evenementInvitationPK) {
        this.evenementInvitationPK = evenementInvitationPK;
    }

    public Evenement getEvenement1() {
        return evenement1;
    }

    public void setEvenement1(Evenement evenement1) {
        this.evenement1 = evenement1;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Personne getPersonne1() {
        return personne1;
    }

    public void setPersonne1(Personne personne1) {
        this.personne1 = personne1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (evenementInvitationPK != null ? evenementInvitationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EvenementInvitation)) {
            return false;
        }
        EvenementInvitation other = (EvenementInvitation) object;
        if ((this.evenementInvitationPK == null && other.evenementInvitationPK != null) || (this.evenementInvitationPK != null && !this.evenementInvitationPK.equals(other.evenementInvitationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EvenementInvitation[ evenementInvitationPK=" + evenementInvitationPK + " ]";
    }
    
}
