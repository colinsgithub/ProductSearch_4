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
@Table(name = "lowergarment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LowerGarment.findAll", query = "SELECT l FROM LowerGarment l"),
    @NamedQuery(name = "LowerGarment.findByLowergarmentID", query = "SELECT l FROM LowerGarment l WHERE l.lowergarmentID = :lowergarmentID"),
    @NamedQuery(name = "LowerGarment.findByOutseam", query = "SELECT l FROM LowerGarment l WHERE l.outseam = :outseam"),
    @NamedQuery(name = "LowerGarment.findByWaistHeight", query = "SELECT l FROM LowerGarment l WHERE l.waistHeight = :waistHeight"),
    @NamedQuery(name = "LowerGarment.findByLowerGarmentType", query = "SELECT l FROM LowerGarment l WHERE l.lowerGarmentType = :lowerGarmentType")})
public class LowerGarment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "lowergarmentID")
    private Integer lowergarmentID;
    @Basic(optional = false)
    @Column(name = "outseam")
    private String outseam;
    @Basic(optional = false)
    @Column(name = "waistHeight")
    private String waistHeight;
    @Basic(optional = false)
    @Column(name = "lowerGarmentType")
    private String lowerGarmentType;
    @JoinColumns({
        @JoinColumn(name = "lowergarmentID", referencedColumnName = "clothID", insertable = false, updatable = false),
        @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")})
    @ManyToOne(optional = false)
    private Cloth cloth;

    public LowerGarment() {
    }

    public LowerGarment(Integer lowergarmentID) {
        this.lowergarmentID = lowergarmentID;
    }

    public LowerGarment(Integer lowergarmentID, String outseam, String waistHeight, String lowerGarmentType) {
        this.lowergarmentID = lowergarmentID;
        this.outseam = outseam;
        this.waistHeight = waistHeight;
        this.lowerGarmentType = lowerGarmentType;
    }

    public Integer getLowergarmentID() {
        return lowergarmentID;
    }

    public void setLowergarmentID(Integer lowergarmentID) {
        this.lowergarmentID = lowergarmentID;
    }

    public String getOutseam() {
        return outseam;
    }

    public void setOutseam(String outseam) {
        this.outseam = outseam;
    }

    public String getWaistHeight() {
        return waistHeight;
    }

    public void setWaistHeight(String waistHeight) {
        this.waistHeight = waistHeight;
    }

    public String getLowerGarmentType() {
        return lowerGarmentType;
    }

    public void setLowerGarmentType(String lowerGarmentType) {
        this.lowerGarmentType = lowerGarmentType;
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
        hash += (lowergarmentID != null ? lowergarmentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LowerGarment)) {
            return false;
        }
        LowerGarment other = (LowerGarment) object;
        if ((this.lowergarmentID == null && other.lowergarmentID != null) || (this.lowergarmentID != null && !this.lowergarmentID.equals(other.lowergarmentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.LowerGarment[ lowergarmentID=" + lowergarmentID + " ]";
    }
    
}
