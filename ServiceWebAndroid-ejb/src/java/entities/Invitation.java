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
@Table(name = "invitation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invitation.findAll", query = "SELECT i FROM Invitation i"),
    @NamedQuery(name = "Invitation.findByPersonne", query = "SELECT i FROM Invitation i WHERE i.invitationPK.personne = :personne"),
    @NamedQuery(name = "Invitation.findByEmail", query = "SELECT i FROM Invitation i WHERE i.invitationPK.email = :email")})
public class Invitation implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InvitationPK invitationPK;
    @JoinColumn(name = "personne", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personne personne1;

    public Invitation() {
    }

    public Invitation(InvitationPK invitationPK) {
        this.invitationPK = invitationPK;
    }

    public Invitation(int personne, String email) {
        this.invitationPK = new InvitationPK(personne, email);
    }

    public InvitationPK getInvitationPK() {
        return invitationPK;
    }

    public void setInvitationPK(InvitationPK invitationPK) {
        this.invitationPK = invitationPK;
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
        hash += (invitationPK != null ? invitationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invitation)) {
            return false;
        }
        Invitation other = (Invitation) object;
        if ((this.invitationPK == null && other.invitationPK != null) || (this.invitationPK != null && !this.invitationPK.equals(other.invitationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Invitation[ invitationPK=" + invitationPK + " ]";
    }
    
}
