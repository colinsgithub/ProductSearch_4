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
@Table(name = "touchscreentype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TouchScreenType.findAll", query = "SELECT t FROM TouchScreenType t"),
    @NamedQuery(name = "TouchScreenType.findByTouchScreenTypeID", query = "SELECT t FROM TouchScreenType t WHERE t.touchScreenTypeID = :touchScreenTypeID"),
    @NamedQuery(name = "TouchScreenType.findByTouchScreenTypeName", query = "SELECT t FROM TouchScreenType t WHERE t.touchScreenTypeName = :touchScreenTypeName")})
public class TouchScreenType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "touchScreenTypeID")
    private Integer touchScreenTypeID;
    @Basic(optional = false)
    @Column(name = "touchScreenTypeName")
    private String touchScreenTypeName;
    @OneToMany(mappedBy = "touchScreenTypeID")
    private Collection<Mobile> mobileCollection;

    public TouchScreenType() {
    }

    public TouchScreenType(Integer touchScreenTypeID) {
        this.touchScreenTypeID = touchScreenTypeID;
    }

    public TouchScreenType(Integer touchScreenTypeID, String touchScreenTypeName) {
        this.touchScreenTypeID = touchScreenTypeID;
        this.touchScreenTypeName = touchScreenTypeName;
    }

    public Integer getTouchScreenTypeID() {
        return touchScreenTypeID;
    }

    public void setTouchScreenTypeID(Integer touchScreenTypeID) {
        this.touchScreenTypeID = touchScreenTypeID;
    }

    public String getTouchScreenTypeName() {
        return touchScreenTypeName;
    }

    public void setTouchScreenTypeName(String touchScreenTypeName) {
        this.touchScreenTypeName = touchScreenTypeName;
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
        hash += (touchScreenTypeID != null ? touchScreenTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TouchScreenType)) {
            return false;
        }
        TouchScreenType other = (TouchScreenType) object;
        if ((this.touchScreenTypeID == null && other.touchScreenTypeID != null) || (this.touchScreenTypeID != null && !this.touchScreenTypeID.equals(other.touchScreenTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.TouchScreenType[ touchScreenTypeID=" + touchScreenTypeID + " ]";
    }
    
}
