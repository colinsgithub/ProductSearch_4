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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "storemerchandise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoreMerchandise.findAll", query = "SELECT s FROM StoreMerchandise s"),
    @NamedQuery(name = "StoreMerchandise.findByStoreID", query = "SELECT s FROM StoreMerchandise s WHERE s.storeMerchandisePK.storeID = :storeID"),
    @NamedQuery(name = "StoreMerchandise.findByMerchandiseID", query = "SELECT s FROM StoreMerchandise s WHERE s.storeMerchandisePK.merchandiseID = :merchandiseID"),
    @NamedQuery(name = "StoreMerchandise.findByCategoryID", query = "SELECT s FROM StoreMerchandise s WHERE s.storeMerchandisePK.categoryID = :categoryID"),
    @NamedQuery(name = "StoreMerchandise.findByStyleID", query = "SELECT s FROM StoreMerchandise s WHERE s.storeMerchandisePK.styleID = :styleID"),
    @NamedQuery(name = "StoreMerchandise.findByPrice", query = "SELECT s FROM StoreMerchandise s WHERE s.price = :price"),
    @NamedQuery(name = "StoreMerchandise.findByMerchandiseImage1", query = "SELECT s FROM StoreMerchandise s WHERE s.merchandiseImage1 = :merchandiseImage1"),
    @NamedQuery(name = "StoreMerchandise.findByMerchandiseImage2", query = "SELECT s FROM StoreMerchandise s WHERE s.merchandiseImage2 = :merchandiseImage2"),
    @NamedQuery(name = "StoreMerchandise.findByMerchandiseImage3", query = "SELECT s FROM StoreMerchandise s WHERE s.merchandiseImage3 = :merchandiseImage3")})
public class StoreMerchandise implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StoreMerchandisePK storeMerchandisePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Lob
    @Column(name = "color")
    private String color;
    @Basic(optional = false)
    @Column(name = "merchandiseImage1")
    private String merchandiseImage1;
    @Column(name = "merchandiseImage2")
    private String merchandiseImage2;
    @Column(name = "merchandiseImage3")
    private String merchandiseImage3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "storeMerchandise")
    private Collection<StoreUpperGarmentChoice> storeUpperGarmentChoiceCollection;
    @JoinColumn(name = "storeID", referencedColumnName = "storeID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Store store;
    @JoinColumns({
        @JoinColumn(name = "merchandiseID", referencedColumnName = "merchandiseID", insertable = false, updatable = false),
        @JoinColumn(name = "categoryID", referencedColumnName = "categoryID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Merchandise merchandise;

    public StoreMerchandise() {
    }

    public StoreMerchandise(StoreMerchandisePK storeMerchandisePK) {
        this.storeMerchandisePK = storeMerchandisePK;
    }

    public StoreMerchandise(StoreMerchandisePK storeMerchandisePK, String merchandiseImage1) {
        this.storeMerchandisePK = storeMerchandisePK;
        this.merchandiseImage1 = merchandiseImage1;
    }

    public StoreMerchandise(int storeID, int merchandiseID, int categoryID, int styleID) {
        this.storeMerchandisePK = new StoreMerchandisePK(storeID, merchandiseID, categoryID, styleID);
    }

    public StoreMerchandisePK getStoreMerchandisePK() {
        return storeMerchandisePK;
    }

    public void setStoreMerchandisePK(StoreMerchandisePK storeMerchandisePK) {
        this.storeMerchandisePK = storeMerchandisePK;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMerchandiseImage1() {
        return merchandiseImage1;
    }

    public void setMerchandiseImage1(String merchandiseImage1) {
        this.merchandiseImage1 = merchandiseImage1;
    }

    public String getMerchandiseImage2() {
        return merchandiseImage2;
    }

    public void setMerchandiseImage2(String merchandiseImage2) {
        this.merchandiseImage2 = merchandiseImage2;
    }

    public String getMerchandiseImage3() {
        return merchandiseImage3;
    }

    public void setMerchandiseImage3(String merchandiseImage3) {
        this.merchandiseImage3 = merchandiseImage3;
    }

    @XmlTransient
    public Collection<StoreUpperGarmentChoice> getStoreUpperGarmentChoiceCollection() {
        return storeUpperGarmentChoiceCollection;
    }

    public void setStoreUpperGarmentChoiceCollection(Collection<StoreUpperGarmentChoice> storeUpperGarmentChoiceCollection) {
        this.storeUpperGarmentChoiceCollection = storeUpperGarmentChoiceCollection;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Merchandise getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeMerchandisePK != null ? storeMerchandisePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StoreMerchandise)) {
            return false;
        }
        StoreMerchandise other = (StoreMerchandise) object;
        if ((this.storeMerchandisePK == null && other.storeMerchandisePK != null) || (this.storeMerchandisePK != null && !this.storeMerchandisePK.equals(other.storeMerchandisePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.StoreMerchandise[ storeMerchandisePK=" + storeMerchandisePK + " ]";
    }
    
}
