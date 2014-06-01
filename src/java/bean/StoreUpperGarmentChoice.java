/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "storeuppergarmentchoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreUpperGarmentChoice.findAll", query = "SELECT s FROM StoreUpperGarmentChoice s"),
    @NamedQuery(name = "StoreUpperGarmentChoice.findByStoreID", query = "SELECT s FROM StoreUpperGarmentChoice s WHERE s.storeUpperGarmentChoicePK.storeID = :storeID"),
    @NamedQuery(name = "StoreUpperGarmentChoice.findByUpperGarmentID", query = "SELECT s FROM StoreUpperGarmentChoice s WHERE s.storeUpperGarmentChoicePK.upperGarmentID = :upperGarmentID"),
    @NamedQuery(name = "StoreUpperGarmentChoice.findByStyleID", query = "SELECT s FROM StoreUpperGarmentChoice s WHERE s.storeUpperGarmentChoicePK.styleID = :styleID"),
    @NamedQuery(name = "StoreUpperGarmentChoice.findByUpperGarmentSizeID", query = "SELECT s FROM StoreUpperGarmentChoice s WHERE s.storeUpperGarmentChoicePK.upperGarmentSizeID = :upperGarmentSizeID"),
    @NamedQuery(name = "StoreUpperGarmentChoice.findByUpperGarmentSize", query = "SELECT s FROM StoreUpperGarmentChoice s WHERE s.upperGarmentSize = :upperGarmentSize"),
    @NamedQuery(name = "StoreUpperGarmentChoice.findByQuantity", query = "SELECT s FROM StoreUpperGarmentChoice s WHERE s.quantity = :quantity")})
public class StoreUpperGarmentChoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StoreUpperGarmentChoicePK storeUpperGarmentChoicePK;
    @Basic(optional = false)
    @Column(name = "upperGarmentSize")
    private String upperGarmentSize;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @JoinColumns({
        @JoinColumn(name = "storeID", referencedColumnName = "storeID", insertable = false, updatable = false),
        @JoinColumn(name = "upperGarmentID", referencedColumnName = "merchandiseID", insertable = false, updatable = false),
        @JoinColumn(name = "categoryID", referencedColumnName = "categoryID"),
        @JoinColumn(name = "styleID", referencedColumnName = "styleID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private StoreMerchandise storeMerchandise;

    public StoreUpperGarmentChoice() {
    }

    public StoreUpperGarmentChoice(StoreUpperGarmentChoicePK storeUpperGarmentChoicePK) {
        this.storeUpperGarmentChoicePK = storeUpperGarmentChoicePK;
    }

    public StoreUpperGarmentChoice(StoreUpperGarmentChoicePK storeUpperGarmentChoicePK, String upperGarmentSize, int quantity) {
        this.storeUpperGarmentChoicePK = storeUpperGarmentChoicePK;
        this.upperGarmentSize = upperGarmentSize;
        this.quantity = quantity;
    }

    public StoreUpperGarmentChoice(int storeID, int upperGarmentID, int styleID, int upperGarmentSizeID) {
        this.storeUpperGarmentChoicePK = new StoreUpperGarmentChoicePK(storeID, upperGarmentID, styleID, upperGarmentSizeID);
    }

    public StoreUpperGarmentChoicePK getStoreUpperGarmentChoicePK() {
        return storeUpperGarmentChoicePK;
    }

    public void setStoreUpperGarmentChoicePK(StoreUpperGarmentChoicePK storeUpperGarmentChoicePK) {
        this.storeUpperGarmentChoicePK = storeUpperGarmentChoicePK;
    }

    public String getUpperGarmentSize() {
        return upperGarmentSize;
    }

    public void setUpperGarmentSize(String upperGarmentSize) {
        this.upperGarmentSize = upperGarmentSize;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public StoreMerchandise getStoreMerchandise() {
        return storeMerchandise;
    }

    public void setStoreMerchandise(StoreMerchandise storeMerchandise) {
        this.storeMerchandise = storeMerchandise;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeUpperGarmentChoicePK != null ? storeUpperGarmentChoicePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreUpperGarmentChoice)) {
            return false;
        }
        StoreUpperGarmentChoice other = (StoreUpperGarmentChoice) object;
        if ((this.storeUpperGarmentChoicePK == null && other.storeUpperGarmentChoicePK != null) || (this.storeUpperGarmentChoicePK != null && !this.storeUpperGarmentChoicePK.equals(other.storeUpperGarmentChoicePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.StoreUpperGarmentChoice[ storeUpperGarmentChoicePK=" + storeUpperGarmentChoicePK + " ]";
    }
    
}
