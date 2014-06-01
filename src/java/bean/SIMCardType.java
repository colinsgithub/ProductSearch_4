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
@Table(name = "simcardtype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SIMCardType.findAll", query = "SELECT s FROM SIMCardType s"),
    @NamedQuery(name = "SIMCardType.findBySIMCardTypeID", query = "SELECT s FROM SIMCardType s WHERE s.sIMCardTypeID = :sIMCardTypeID"),
    @NamedQuery(name = "SIMCardType.findBySIMCardTypeName", query = "SELECT s FROM SIMCardType s WHERE s.sIMCardTypeName = :sIMCardTypeName")})
public class SIMCardType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SIMCardTypeID")
    private Integer sIMCardTypeID;
    @Basic(optional = false)
    @Column(name = "SIMCardTypeName")
    private String sIMCardTypeName;
    @ManyToMany(mappedBy = "sIMCardTypeCollection")
    private Collection<Mobile> mobileCollection;

    public SIMCardType() {
    }

    public SIMCardType(Integer sIMCardTypeID) {
        this.sIMCardTypeID = sIMCardTypeID;
    }

    public SIMCardType(Integer sIMCardTypeID, String sIMCardTypeName) {
        this.sIMCardTypeID = sIMCardTypeID;
        this.sIMCardTypeName = sIMCardTypeName;
    }

    public Integer getSIMCardTypeID() {
        return sIMCardTypeID;
    }

    public void setSIMCardTypeID(Integer sIMCardTypeID) {
        this.sIMCardTypeID = sIMCardTypeID;
    }

    public String getSIMCardTypeName() {
        return sIMCardTypeName;
    }

    public void setSIMCardTypeName(String sIMCardTypeName) {
        this.sIMCardTypeName = sIMCardTypeName;
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
        hash += (sIMCardTypeID != null ? sIMCardTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SIMCardType)) {
            return false;
        }
        SIMCardType other = (SIMCardType) object;
        if ((this.sIMCardTypeID == null && other.sIMCardTypeID != null) || (this.sIMCardTypeID != null && !this.sIMCardTypeID.equals(other.sIMCardTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.SIMCardType[ sIMCardTypeID=" + sIMCardTypeID + " ]";
    }
    
}
