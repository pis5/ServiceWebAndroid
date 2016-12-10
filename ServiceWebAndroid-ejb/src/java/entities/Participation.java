/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ilias
 */
@Entity
@Table(name = "participation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Participation.findAll", query = "SELECT p FROM Participation p"),
    @NamedQuery(name = "Participation.findByEvenement", query = "SELECT p FROM Participation p WHERE p.participationPK.evenement = :evenement"),
    @NamedQuery(name = "Participation.findByParticipant", query = "SELECT p FROM Participation p WHERE p.participationPK.participant = :participant"),
    @NamedQuery(name = "Participation.findByDate", query = "SELECT p FROM Participation p WHERE p.date = :date"),
    @NamedQuery(name = "Participation.findEventByParticipant", query = "SELECT p.evenement1 FROM Participation p WHERE p.personne = :participant"),
    @NamedQuery(name = "Participation.findOldEventByParticipantAmis", query = "SELECT p.evenement1 FROM Participation p WHERE (p.personne IN :listAmis) AND p.evenement1.id< :offset order by p.evenement1.id DESC"),
    @NamedQuery(name = "Participation.findNewEventByParticipantAmis", query = "SELECT p.evenement1 FROM Participation p WHERE (p.personne IN :listAmis) AND p.evenement1.id > :offset order by p.evenement1.id ASC")
})
public class Participation implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ParticipationPK participationPK;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "evenement", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Evenement evenement1;
    @JoinColumn(name = "participant", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personne personne;

    public Participation() {
    }

    public Participation(ParticipationPK participationPK) {
        this.participationPK = participationPK;
    }

    public Participation(int evenement, int participant) {
        this.participationPK = new ParticipationPK(evenement, participant);
    }

    public ParticipationPK getParticipationPK() {
        return participationPK;
    }

    public void setParticipationPK(ParticipationPK participationPK) {
        this.participationPK = participationPK;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (participationPK != null ? participationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Participation)) {
            return false;
        }
        Participation other = (Participation) object;
        if ((this.participationPK == null && other.participationPK != null) || (this.participationPK != null && !this.participationPK.equals(other.participationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Participation[ participationPK=" + participationPK + " ]";
    }
    
}
