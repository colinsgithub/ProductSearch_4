/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "wlantype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WLANType.findAll", query = "SELECT w FROM WLANType w"),
    @NamedQuery(name = "WLANType.findByWLANTypeID", query = "SELECT w FROM WLANType w WHERE w.wLANTypeID = :wLANTypeID"),
    @NamedQuery(name = "WLANType.findByWLANTypeName", query = "SELECT w FROM WLANType w WHERE w.wLANTypeName = :wLANTypeName")})
public class WLANType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WLANTypeID")
    private Integer wLANTypeID;
    @Basic(optional = false)
    @Column(name = "WLANTypeName")
    private String wLANTypeName;
    @ManyToMany(mappedBy = "wLANTypeCollection")
    private Collection<Mobile> mobileCollection;

    public WLANType() {
    }

    public WLANType(Integer wLANTypeID) {
        this.wLANTypeID = wLANTypeID;
    }

    public WLANType(Integer wLANTypeID, String wLANTypeName) {
        this.wLANTypeID = wLANTypeID;
        this.wLANTypeName = wLANTypeName;
    }

    public Integer getWLANTypeID() {
        return wLANTypeID;
    }

    public void setWLANTypeID(Integer wLANTypeID) {
        this.wLANTypeID = wLANTypeID;
    }

    public String getWLANTypeName() {
        return wLANTypeName;
    }

    public void setWLANTypeName(String wLANTypeName) {
        this.wLANTypeName = wLANTypeName;
    }

    @XmlTransient
    public Collection<Mobile> getMobileCollection() {
        return mobileCollection;
    }

    public void setMobileCollection(Collection<Mobile> mobileCollection) {
        this.mobileCollection = mobileCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wLANTypeID != null ? wLANTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WLANType)) {
            return false;
        }
        WLANType other = (WLANType) object;
        if ((this.wLANTypeID == null && other.wLANTypeID != null) || (this.wLANTypeID != null && !this.wLANTypeID.equals(other.wLANTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.WLANType[ wLANTypeID=" + wLANTypeID + " ]";
    }
    
}
