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
import javax.persistence.Lob;
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
@Table(name = "userstatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserStatus.findAll", query = "SELECT u FROM UserStatus u"),
    @NamedQuery(name = "UserStatus.findByStatusID", query = "SELECT u FROM UserStatus u WHERE u.userStatusPK.statusID = :statusID"),
    @NamedQuery(name = "UserStatus.findByUserID", query = "SELECT u FROM UserStatus u WHERE u.userStatusPK.userID = :userID")})
public class UserStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserStatusPK userStatusPK;
    @Basic(optional = false)
    @Lob
    @Column(name = "statusName")
    private String statusName;
    @Basic(optional = false)
    @Lob
    @Column(name = "statusColor")
    private String statusColor;
    @Lob
    @Column(name = "statusDesc")
    private String statusDesc;
    @JoinColumn(name = "userID", referencedColumnName = "userID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;

    public UserStatus() {
    }

    public UserStatus(UserStatusPK userStatusPK) {
        this.userStatusPK = userStatusPK;
    }

    public UserStatus(UserStatusPK userStatusPK, String statusName, String statusColor) {
        this.userStatusPK = userStatusPK;
        this.statusName = statusName;
        this.statusColor = statusColor;
    }

    public UserStatus(int statusID, String userID) {
        this.userStatusPK = new UserStatusPK(statusID, userID);
    }

    public UserStatusPK getUserStatusPK() {
        return userStatusPK;
    }

    public void setUserStatusPK(UserStatusPK userStatusPK) {
        this.userStatusPK = userStatusPK;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusColor() {
        return statusColor;
    }

    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userStatusPK != null ? userStatusPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserStatus)) {
            return false;
        }
        UserStatus other = (UserStatus) object;
        if ((this.userStatusPK == null && other.userStatusPK != null) || (this.userStatusPK != null && !this.userStatusPK.equals(other.userStatusPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.UserStatus[ userStatusPK=" + userStatusPK + " ]";
    }
    
}
