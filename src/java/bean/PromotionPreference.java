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
@Table(name = "promotionpreference")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PromotionPreference.findAll", query = "SELECT p FROM PromotionPreference p"),
    @NamedQuery(name = "PromotionPreference.findByUserID", query = "SELECT p FROM PromotionPreference p WHERE p.promotionPreferencePK.userID = :userID"),
    @NamedQuery(name = "PromotionPreference.findByPromotionID", query = "SELECT p FROM PromotionPreference p WHERE p.promotionPreferencePK.promotionID = :promotionID"),
    @NamedQuery(name = "PromotionPreference.findByStoreID", query = "SELECT p FROM PromotionPreference p WHERE p.promotionPreferencePK.storeID = :storeID"),
    @NamedQuery(name = "PromotionPreference.findByNotificationSelection", query = "SELECT p FROM PromotionPreference p WHERE p.notificationSelection = :notificationSelection")})
public class PromotionPreference implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PromotionPreferencePK promotionPreferencePK;
    @Basic(optional = false)
    @Column(name = "notificationSelection")
    private boolean notificationSelection;
    @JoinColumn(name = "userID", referencedColumnName = "userID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumns({
        @JoinColumn(name = "promotionID", referencedColumnName = "promotionID", insertable = false, updatable = false),
        @JoinColumn(name = "storeID", referencedColumnName = "storeID", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private PromotionScheme promotionScheme;

    public PromotionPreference() {
    }

    public PromotionPreference(PromotionPreferencePK promotionPreferencePK) {
        this.promotionPreferencePK = promotionPreferencePK;
    }

    public PromotionPreference(PromotionPreferencePK promotionPreferencePK, boolean notificationSelection) {
        this.promotionPreferencePK = promotionPreferencePK;
        this.notificationSelection = notificationSelection;
    }

    public PromotionPreference(String userID, int promotionID, int storeID) {
        this.promotionPreferencePK = new PromotionPreferencePK(userID, promotionID, storeID);
    }

    public PromotionPreferencePK getPromotionPreferencePK() {
        return promotionPreferencePK;
    }

    public void setPromotionPreferencePK(PromotionPreferencePK promotionPreferencePK) {
        this.promotionPreferencePK = promotionPreferencePK;
    }

    public boolean getNotificationSelection() {
        return notificationSelection;
    }

    public void setNotificationSelection(boolean notificationSelection) {
        this.notificationSelection = notificationSelection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PromotionScheme getPromotionScheme() {
        return promotionScheme;
    }

    public void setPromotionScheme(PromotionScheme promotionScheme) {
        this.promotionScheme = promotionScheme;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (promotionPreferencePK != null ? promotionPreferencePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PromotionPreference)) {
            return false;
        }
        PromotionPreference other = (PromotionPreference) object;
        if ((this.promotionPreferencePK == null && other.promotionPreferencePK != null) || (this.promotionPreferencePK != null && !this.promotionPreferencePK.equals(other.promotionPreferencePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.PromotionPreference[ promotionPreferencePK=" + promotionPreferencePK + " ]";
    }
    
}
