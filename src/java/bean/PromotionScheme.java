/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author user
 */
@Entity
@Table(name = "promotionscheme")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PromotionScheme.findAll", query = "SELECT p FROM PromotionScheme p"),
    @NamedQuery(name = "PromotionScheme.findByPromotionID", query = "SELECT p FROM PromotionScheme p WHERE p.promotionSchemePK.promotionID = :promotionID"),
    @NamedQuery(name = "PromotionScheme.findByStartDate", query = "SELECT p FROM PromotionScheme p WHERE p.startDate = :startDate"),
    @NamedQuery(name = "PromotionScheme.findByEndDate", query = "SELECT p FROM PromotionScheme p WHERE p.endDate = :endDate"),
    @NamedQuery(name = "PromotionScheme.findByStoreID", query = "SELECT p FROM PromotionScheme p WHERE p.promotionSchemePK.storeID = :storeID")})
public class PromotionScheme implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PromotionSchemePK promotionSchemePK;
    @Basic(optional = false)
    @Column(name = "startDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "endDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @Lob
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "storeID", referencedColumnName = "storeID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Store store;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promotionScheme")
    private Collection<PromotionPreference> promotionPreferenceCollection;

    public PromotionScheme() {
    }

    public PromotionScheme(PromotionSchemePK promotionSchemePK) {
        this.promotionSchemePK = promotionSchemePK;
    }

    public PromotionScheme(PromotionSchemePK promotionSchemePK, Date startDate, Date endDate, String description) {
        this.promotionSchemePK = promotionSchemePK;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    public PromotionScheme(int promotionID, int storeID) {
        this.promotionSchemePK = new PromotionSchemePK(promotionID, storeID);
    }

    public PromotionSchemePK getPromotionSchemePK() {
        return promotionSchemePK;
    }

    public void setPromotionSchemePK(PromotionSchemePK promotionSchemePK) {
        this.promotionSchemePK = promotionSchemePK;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @XmlTransient
    public Collection<PromotionPreference> getPromotionPreferenceCollection() {
        return promotionPreferenceCollection;
    }

    public void setPromotionPreferenceCollection(Collection<PromotionPreference> promotionPreferenceCollection) {
        this.promotionPreferenceCollection = promotionPreferenceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promotionSchemePK != null ? promotionSchemePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PromotionScheme)) {
            return false;
        }
        PromotionScheme other = (PromotionScheme) object;
        if ((this.promotionSchemePK == null && other.promotionSchemePK != null) || (this.promotionSchemePK != null && !this.promotionSchemePK.equals(other.promotionSchemePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.PromotionScheme[ promotionSchemePK=" + promotionSchemePK + " ]";
    }
    
}
