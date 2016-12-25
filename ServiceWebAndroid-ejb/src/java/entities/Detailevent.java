/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HAMZA
 */
@Entity
@Table(name = "detailevent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detailevent.findAll", query = "SELECT d FROM Detailevent d"),
    @NamedQuery(name = "Detailevent.findByIdDetail", query = "SELECT d FROM Detailevent d WHERE d.idDetail = :idDetail")})
public class Detailevent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Detail")
    private Integer idDetail;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "Texte")
    private String texte;
    @Lob
    @Column(name = "media")
    private byte[] media;

    public Detailevent() {
    }

    public Detailevent(Integer idDetail) {
        this.idDetail = idDetail;
    }

    public Integer getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(Integer idDetail) {
        this.idDetail = idDetail;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public byte[] getMedia() {
        return media;
    }

    public void setMedia(byte[] media) {
        this.media = media;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetail != null ? idDetail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detailevent)) {
            return false;
        }
        Detailevent other = (Detailevent) object;
        if ((this.idDetail == null && other.idDetail != null) || (this.idDetail != null && !this.idDetail.equals(other.idDetail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Detailevent[ idDetail=" + idDetail + " ]";
    }
    
}
