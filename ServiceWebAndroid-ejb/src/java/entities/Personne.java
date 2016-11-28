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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "personne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personne.findAll", query = "SELECT p FROM Personne p"),
    @NamedQuery(name = "Personne.findById", query = "SELECT p FROM Personne p WHERE p.id = :id"),
    @NamedQuery(name = "Personne.findByNom", query = "SELECT p FROM Personne p WHERE p.nom = :nom"),
    @NamedQuery(name = "Personne.findByPrenom", query = "SELECT p FROM Personne p WHERE p.prenom = :prenom"),
    @NamedQuery(name = "Personne.findByEmail", query = "SELECT p FROM Personne p WHERE p.email = :email"),
    @NamedQuery(name = "Personne.findByTelephone", query = "SELECT p FROM Personne p WHERE p.telephone = :telephone"),
    @NamedQuery(name = "Personne.findByHabite", query = "SELECT p FROM Personne p WHERE p.habite = :habite"),
    @NamedQuery(name = "Personne.findByLieuDeNaissance", query = "SELECT p FROM Personne p WHERE p.lieuDeNaissance = :lieuDeNaissance"),
    @NamedQuery(name = "Personne.findByDateDeNaissance", query = "SELECT p FROM Personne p WHERE p.dateDeNaissance = :dateDeNaissance"),
    @NamedQuery(name = "Personne.findByDateDEnregistrement", query = "SELECT p FROM Personne p WHERE p.dateDEnregistrement = :dateDEnregistrement"),
    @NamedQuery(name = "Personne.findByMotDePasse", query = "SELECT p FROM Personne p WHERE p.motDePasse = :motDePasse"),
    @NamedQuery(name = "Personne.findByMailAndPass", query = "SELECT p FROM Personne p WHERE p.email = :mail AND p.motDePasse = :pass")})
public class Personne implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "prenom")
    private String prenom;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 22)
    @Column(name = "telephone")
    private String telephone;
    @Size(max = 100)
    @Column(name = "habite")
    private String habite;
    @Size(max = 100)
    @Column(name = "lieu_de_naissance")
    private String lieuDeNaissance;
    @Column(name = "date_de_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateDeNaissance;
    @Column(name = "date_d_enregistrement")
    @Temporal(TemporalType.DATE)
    private Date dateDEnregistrement;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "mot_de_passe")
    private String motDePasse;
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @JoinTable(name = "demande_ajout", joinColumns = {
        @JoinColumn(name = "emetteur", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "recepteur", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Personne> personneList;
    @ManyToMany(mappedBy = "personneList", fetch = FetchType.LAZY)
    private List<Personne> personneList1;
    @JoinTable(name = "amis", joinColumns = {
        @JoinColumn(name = "ami", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "personne", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Personne> personneList2;
    @ManyToMany(mappedBy = "personneList2", fetch = FetchType.LAZY)
    private List<Personne> personneList3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personne1", fetch = FetchType.LAZY)
    private List<Invitation> invitationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organisateur", fetch = FetchType.LAZY)
    private List<Evenement> evenementList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personne", fetch = FetchType.LAZY)
    private List<EvenementInvitation> evenementInvitationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personne1", fetch = FetchType.LAZY)
    private List<EvenementInvitation> evenementInvitationList1;

    public Personne() {
    }

    public Personne(Integer id) {
        this.id = id;
    }

    public Personne(Integer id, String nom, String prenom, String email, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getHabite() {
        return habite;
    }

    public void setHabite(String habite) {
        this.habite = habite;
    }

    public String getLieuDeNaissance() {
        return lieuDeNaissance;
    }

    public void setLieuDeNaissance(String lieuDeNaissance) {
        this.lieuDeNaissance = lieuDeNaissance;
    }

    public Date getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(Date dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public Date getDateDEnregistrement() {
        return dateDEnregistrement;
    }

    public void setDateDEnregistrement(Date dateDEnregistrement) {
        this.dateDEnregistrement = dateDEnregistrement;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @XmlTransient
    public List<Personne> getPersonneList() {
        return personneList;
    }

    public void setPersonneList(List<Personne> personneList) {
        this.personneList = personneList;
    }

    @XmlTransient
    public List<Personne> getPersonneList1() {
        return personneList1;
    }

    public void setPersonneList1(List<Personne> personneList1) {
        this.personneList1 = personneList1;
    }

    @XmlTransient
    public List<Personne> getPersonneList2() {
        return personneList2;
    }

    public void setPersonneList2(List<Personne> personneList2) {
        this.personneList2 = personneList2;
    }

    @XmlTransient
    public List<Personne> getPersonneList3() {
        return personneList3;
    }

    public void setPersonneList3(List<Personne> personneList3) {
        this.personneList3 = personneList3;
    }

    @XmlTransient
    public List<Invitation> getInvitationList() {
        return invitationList;
    }

    public void setInvitationList(List<Invitation> invitationList) {
        this.invitationList = invitationList;
    }

    @XmlTransient
    public List<Evenement> getEvenementList() {
        return evenementList;
    }

    public void setEvenementList(List<Evenement> evenementList) {
        this.evenementList = evenementList;
    }

    @XmlTransient
    public List<EvenementInvitation> getEvenementInvitationList() {
        return evenementInvitationList;
    }

    public void setEvenementInvitationList(List<EvenementInvitation> evenementInvitationList) {
        this.evenementInvitationList = evenementInvitationList;
    }

    @XmlTransient
    public List<EvenementInvitation> getEvenementInvitationList1() {
        return evenementInvitationList1;
    }

    public void setEvenementInvitationList1(List<EvenementInvitation> evenementInvitationList1) {
        this.evenementInvitationList1 = evenementInvitationList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Personne[ id=" + id + " ]";
    }
    
}
