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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "keyboardtype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KeyboardType.findAll", query = "SELECT k FROM KeyboardType k"),
    @NamedQuery(name = "KeyboardType.findByKeyboardTypeID", query = "SELECT k FROM KeyboardType k WHERE k.keyboardTypeID = :keyboardTypeID"),
    @NamedQuery(name = "KeyboardType.findByKeyboardTypeName", query = "SELECT k FROM KeyboardType k WHERE k.keyboardTypeName = :keyboardTypeName")})
public class KeyboardType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "keyboardTypeID")
    private Integer keyboardTypeID;
    @Basic(optional = false)
    @Column(name = "keyboardTypeName")
    private String keyboardTypeName;
    @JoinTable(name = "mobile_keyboardtype", joinColumns = {
        @JoinColumn(name = "keyboardTypeID", referencedColumnName = "keyboardTypeID")}, inverseJoinColumns = {
        @JoinColumn(name = "mobileID", referencedColumnName = "mobileID")})
    @ManyToMany
    private Collection<Mobile> mobileCollection;

    public KeyboardType() {
    }

    public KeyboardType(Integer keyboardTypeID) {
        this.keyboardTypeID = keyboardTypeID;
    }

    public KeyboardType(Integer keyboardTypeID, String keyboardTypeName) {
        this.keyboardTypeID = keyboardTypeID;
        this.keyboardTypeName = keyboardTypeName;
    }

    public Integer getKeyboardTypeID() {
        return keyboardTypeID;
    }

    public void setKeyboardTypeID(Integer keyboardTypeID) {
        this.keyboardTypeID = keyboardTypeID;
    }

    public String getKeyboardTypeName() {
        return keyboardTypeName;
    }

    public void setKeyboardTypeName(String keyboardTypeName) {
        this.keyboardTypeName = keyboardTypeName;
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
        hash += (keyboardTypeID != null ? keyboardTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KeyboardType)) {
            return false;
        }
        KeyboardType other = (KeyboardType) object;
        if ((this.keyboardTypeID == null && other.keyboardTypeID != null) || (this.keyboardTypeID != null && !this.keyboardTypeID.equals(other.keyboardTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.KeyboardType[ keyboardTypeID=" + keyboardTypeID + " ]";
    }
    
}
