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
@Table(name = "machineinterface")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MachineInterface.findAll", query = "SELECT m FROM MachineInterface m"),
    @NamedQuery(name = "MachineInterface.findByMachineInterfaceID", query = "SELECT m FROM MachineInterface m WHERE m.machineInterfaceID = :machineInterfaceID"),
    @NamedQuery(name = "MachineInterface.findByMachineInterfaceName", query = "SELECT m FROM MachineInterface m WHERE m.machineInterfaceName = :machineInterfaceName")})
public class MachineInterface implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "machineInterfaceID")
    private Integer machineInterfaceID;
    @Basic(optional = false)
    @Column(name = "machineInterfaceName")
    private String machineInterfaceName;
    @JoinTable(name = "mobile_machineinterface", joinColumns = {
        @JoinColumn(name = "machineInterfaceID", referencedColumnName = "machineInterfaceID")}, inverseJoinColumns = {
        @JoinColumn(name = "mobileID", referencedColumnName = "mobileID")})
    @ManyToMany
    private Collection<Mobile> mobileCollection;

    public MachineInterface() {
    }

    public MachineInterface(Integer machineInterfaceID) {
        this.machineInterfaceID = machineInterfaceID;
    }

    public MachineInterface(Integer machineInterfaceID, String machineInterfaceName) {
        this.machineInterfaceID = machineInterfaceID;
        this.machineInterfaceName = machineInterfaceName;
    }

    public Integer getMachineInterfaceID() {
        return machineInterfaceID;
    }

    public void setMachineInterfaceID(Integer machineInterfaceID) {
        this.machineInterfaceID = machineInterfaceID;
    }

    public String getMachineInterfaceName() {
        return machineInterfaceName;
    }

    public void setMachineInterfaceName(String machineInterfaceName) {
        this.machineInterfaceName = machineInterfaceName;
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
        hash += (machineInterfaceID != null ? machineInterfaceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MachineInterface)) {
            return false;
        }
        MachineInterface other = (MachineInterface) object;
        if ((this.machineInterfaceID == null && other.machineInterfaceID != null) || (this.machineInterfaceID != null && !this.machineInterfaceID.equals(other.machineInterfaceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.MachineInterface[ machineInterfaceID=" + machineInterfaceID + " ]";
    }
    
}
