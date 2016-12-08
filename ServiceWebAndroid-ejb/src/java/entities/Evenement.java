/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ilias
 */
@Entity
@Table(name = "evenement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evenement.findAll", query = "SELECT e FROM Evenement e"),
    @NamedQuery(name = "Evenement.findById", query = "SELECT e FROM Evenement e WHERE e.id = :id"),
    @NamedQuery(name = "Evenement.findByTitre", query = "SELECT e FROM Evenement e WHERE e.titre = :titre"),
    @NamedQuery(name = "Evenement.findByDateDeCreation", query = "SELECT e FROM Evenement e WHERE e.dateDeCreation = :dateDeCreation"),
    @NamedQuery(name = "Evenement.findByDateEvenement", query = "SELECT e FROM Evenement e WHERE e.dateEvenement = :dateEvenement"),
    @NamedQuery(name = "Evenement.findByHeure", query = "SELECT e FROM Evenement e WHERE e.heure = :heure"),
    @NamedQuery(name = "Evenement.findByNombreInvitesMax", query = "SELECT e FROM Evenement e WHERE e.nombreInvitesMax = :nombreInvitesMax")})
public class Evenement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titre")
    private String titre;
    @Lob
    @Size(max = 65535)
    @Column(name = "text")
    private String text;
    @Column(name = "date_de_creation")
    @Temporal(TemporalType.DATE)
    private Date dateDeCreation;
    @Column(name = "date_evenement")
    @Temporal(TemporalType.DATE)
    private Date dateEvenement;
    @Column(name = "heure")
    @Temporal(TemporalType.TIME)
    private Date heure;
    @Column(name = "nombre_invites_max")
    private Integer nombreInvitesMax;
  //  @OneToMany(cascade = CascadeType.ALL, mappedBy = "evenement1", fetch = FetchType.LAZY)
  //  private List<Participation> participationList;
    @JoinColumn(name = "genre", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private GenreDEvenement genre;
    @JoinColumn(name = "lieu", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lieu lieu;
    @JoinColumn(name = "organisateur", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personne organisateur;
    

    public Evenement() {
    }

    public Evenement(Integer id) {
        this.id = id;
    }

    public Evenement(Integer id, String titre) {
        this.id = id;
        this.titre = titre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateDeCreation() {
        return dateDeCreation;
    }

    public void setDateDeCreation(Date dateDeCreation) {
        this.dateDeCreation = dateDeCreation;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public Date getHeure() {
        return heure;
    }

    public void setHeure(Date heure) {
        this.heure = heure;
    }

    public Integer getNombreInvitesMax() {
        return nombreInvitesMax;
    }

    public void setNombreInvitesMax(Integer nombreInvitesMax) {
        this.nombreInvitesMax = nombreInvitesMax;
    }

    

    public GenreDEvenement getGenre() {
        return genre;
    }

    public void setGenre(GenreDEvenement genre) {
        this.genre = genre;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public Personne getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(Personne organisateur) {
        this.organisateur = organisateur;
    }

    /*@XmlTransient
    public List<EvenementInvitation> getEvenementInvitationList() {
        return evenementInvitationList;
    }

    public void setEvenementInvitationList(List<EvenementInvitation> evenementInvitationList) {
        this.evenementInvitationList = evenementInvitationList;
    }*/

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evenement)) {
            return false;
        }
        Evenement other = (Evenement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Evenement[ id=" + id + " ]";
    }
    
}
