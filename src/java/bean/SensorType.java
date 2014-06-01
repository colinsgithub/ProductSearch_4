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
@Table(name = "sensortype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SensorType.findAll", query = "SELECT s FROM SensorType s"),
    @NamedQuery(name = "SensorType.findBySensorTypeID", query = "SELECT s FROM SensorType s WHERE s.sensorTypeID = :sensorTypeID"),
    @NamedQuery(name = "SensorType.findBySensorTypeName", query = "SELECT s FROM SensorType s WHERE s.sensorTypeName = :sensorTypeName")})
public class SensorType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sensorTypeID")
    private Integer sensorTypeID;
    @Basic(optional = false)
    @Column(name = "sensorTypeName")
    private String sensorTypeName;
    @ManyToMany(mappedBy = "sensorTypeCollection")
    private Collection<Mobile> mobileCollection;

    public SensorType() {
    }

    public SensorType(Integer sensorTypeID) {
        this.sensorTypeID = sensorTypeID;
    }

    public SensorType(Integer sensorTypeID, String sensorTypeName) {
        this.sensorTypeID = sensorTypeID;
        this.sensorTypeName = sensorTypeName;
    }

    public Integer getSensorTypeID() {
        return sensorTypeID;
    }

    public void setSensorTypeID(Integer sensorTypeID) {
        this.sensorTypeID = sensorTypeID;
    }

    public String getSensorTypeName() {
        return sensorTypeName;
    }

    public void setSensorTypeName(String sensorTypeName) {
        this.sensorTypeName = sensorTypeName;
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
        hash += (sensorTypeID != null ? sensorTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SensorType)) {
            return false;
        }
        SensorType other = (SensorType) object;
        if ((this.sensorTypeID == null && other.sensorTypeID != null) || (this.sensorTypeID != null && !this.sensorTypeID.equals(other.sensorTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.SensorType[ sensorTypeID=" + sensorTypeID + " ]";
    }
    
}
