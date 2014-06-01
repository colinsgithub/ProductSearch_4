/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "uppergarment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UpperGarment.findAll", query = "SELECT u FROM UpperGarment u"),
    @NamedQuery(name = "UpperGarment.findByUpperGarmentID", query = "SELECT u FROM UpperGarment u WHERE u.upperGarmentID = :upperGarmentID"),
    @NamedQuery(name = "UpperGarment.findByUpperGarmentType", query = "SELECT u FROM UpperGarment u WHERE u.upperGarmentType = :upperGarmentType"),
    @NamedQuery(name = "UpperGarment.findBySleeveStyle", query = "SELECT u FROM UpperGarment u WHERE u.sleeveStyle = :sleeveStyle"),
    @NamedQuery(name = "UpperGarment.findBySleeveType", query = "SELECT u FROM UpperGarment u WHERE u.sleeveType = :sleeveType"),
    @NamedQuery(name = "UpperGarment.findByCollar", query = "SELECT u FROM UpperGarment u WHERE u.collar = :collar")})
public class UpperGarment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "upperGarmentID")
    private Integer upperGarmentID;
    @Basic(optional = false)
    @Column(name = "upperGarmentType")
    private String upperGarmentType;
    @Basic(optional = false)
    @Column(name = "sleeveStyle")
    private String sleeveStyle;
    @Column(name = "sleeveType")
    private String sleeveType;
    @Column(name = "collar")
    private String collar;
    @JoinColumns({
        @JoinColumn(name = "upperGarmentID", referencedColumnName = "clothID", insertable = false, updatable = false),
        @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")})
    @ManyToOne(optional = false)
    private Cloth cloth;

    public UpperGarment() {
    }

    public UpperGarment(Integer upperGarmentID) {
        this.upperGarmentID = upperGarmentID;
    }

    public UpperGarment(Integer upperGarmentID, String upperGarmentType, String sleeveStyle) {
        this.upperGarmentID = upperGarmentID;
        this.upperGarmentType = upperGarmentType;
        this.sleeveStyle = sleeveStyle;
    }

    public Integer getUpperGarmentID() {
        return upperGarmentID;
    }

    public void setUpperGarmentID(Integer upperGarmentID) {
        this.upperGarmentID = upperGarmentID;
    }

    public String getUpperGarmentType() {
        return upperGarmentType;
    }

    public void setUpperGarmentType(String upperGarmentType) {
        this.upperGarmentType = upperGarmentType;
    }

    public String getSleeveStyle() {
        return sleeveStyle;
    }

    public void setSleeveStyle(String sleeveStyle) {
        this.sleeveStyle = sleeveStyle;
    }

    public String getSleeveType() {
        return sleeveType;
    }

    public void setSleeveType(String sleeveType) {
        this.sleeveType = sleeveType;
    }

    public String getCollar() {
        return collar;
    }

    public void setCollar(String collar) {
        this.collar = collar;
    }

    public Cloth getCloth() {
        return cloth;
    }

    public void setCloth(Cloth cloth) {
        this.cloth = cloth;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (upperGarmentID != null ? upperGarmentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UpperGarment)) {
            return false;
        }
        UpperGarment other = (UpperGarment) object;
        if ((this.upperGarmentID == null && other.upperGarmentID != null) || (this.upperGarmentID != null && !this.upperGarmentID.equals(other.upperGarmentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.UpperGarment[ upperGarmentID=" + upperGarmentID + " ]";
    }
    
}
