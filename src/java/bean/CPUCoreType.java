/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "cpucoretype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CPUCoreType.findAll", query = "SELECT c FROM CPUCoreType c"),
    @NamedQuery(name = "CPUCoreType.findByCPUCoreTypeID", query = "SELECT c FROM CPUCoreType c WHERE c.cPUCoreTypeID = :cPUCoreTypeID"),
    @NamedQuery(name = "CPUCoreType.findByCPUCoreTypeName", query = "SELECT c FROM CPUCoreType c WHERE c.cPUCoreTypeName = :cPUCoreTypeName")})
public class CPUCoreType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CPUCoreTypeID")
    private Integer cPUCoreTypeID;
    @Basic(optional = false)
    @Column(name = "CPUCoreTypeName")
    private String cPUCoreTypeName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cPUCoreTypeID")
    private Collection<Mobile> mobileCollection;

    public CPUCoreType() {
    }

    public CPUCoreType(Integer cPUCoreTypeID) {
        this.cPUCoreTypeID = cPUCoreTypeID;
    }

    public CPUCoreType(Integer cPUCoreTypeID, String cPUCoreTypeName) {
        this.cPUCoreTypeID = cPUCoreTypeID;
        this.cPUCoreTypeName = cPUCoreTypeName;
    }

    public Integer getCPUCoreTypeID() {
        return cPUCoreTypeID;
    }

    public void setCPUCoreTypeID(Integer cPUCoreTypeID) {
        this.cPUCoreTypeID = cPUCoreTypeID;
    }

    public String getCPUCoreTypeName() {
        return cPUCoreTypeName;
    }

    public void setCPUCoreTypeName(String cPUCoreTypeName) {
        this.cPUCoreTypeName = cPUCoreTypeName;
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
        hash += (cPUCoreTypeID != null ? cPUCoreTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CPUCoreType)) {
            return false;
        }
        CPUCoreType other = (CPUCoreType) object;
        if ((this.cPUCoreTypeID == null && other.cPUCoreTypeID != null) || (this.cPUCoreTypeID != null && !this.cPUCoreTypeID.equals(other.cPUCoreTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.CPUCoreType[ cPUCoreTypeID=" + cPUCoreTypeID + " ]";
    }
    
}
