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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "mobile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mobile.findAll", query = "SELECT m FROM Mobile m"),
    @NamedQuery(name = "Mobile.findByMobileID", query = "SELECT m FROM Mobile m WHERE m.mobileID = :mobileID"),
    @NamedQuery(name = "Mobile.findByScreenSize", query = "SELECT m FROM Mobile m WHERE m.screenSize = :screenSize"),
    @NamedQuery(name = "Mobile.findByResolutionX", query = "SELECT m FROM Mobile m WHERE m.resolutionX = :resolutionX"),
    @NamedQuery(name = "Mobile.findByResolutionY", query = "SELECT m FROM Mobile m WHERE m.resolutionY = :resolutionY"),
    @NamedQuery(name = "Mobile.findByScreenPixelDensity", query = "SELECT m FROM Mobile m WHERE m.screenPixelDensity = :screenPixelDensity"),
    @NamedQuery(name = "Mobile.findByMobileFormFactor", query = "SELECT m FROM Mobile m WHERE m.mobileFormFactor = :mobileFormFactor"),
    @NamedQuery(name = "Mobile.findByCPUModel", query = "SELECT m FROM Mobile m WHERE m.cPUModel = :cPUModel"),
    @NamedQuery(name = "Mobile.findByCPUFrequency", query = "SELECT m FROM Mobile m WHERE m.cPUFrequency = :cPUFrequency"),
    @NamedQuery(name = "Mobile.findByGPUModel", query = "SELECT m FROM Mobile m WHERE m.gPUModel = :gPUModel"),
    @NamedQuery(name = "Mobile.findByRam", query = "SELECT m FROM Mobile m WHERE m.ram = :ram"),
    @NamedQuery(name = "Mobile.findByRom", query = "SELECT m FROM Mobile m WHERE m.rom = :rom"),
    @NamedQuery(name = "Mobile.findBySIMNumber", query = "SELECT m FROM Mobile m WHERE m.sIMNumber = :sIMNumber"),
    @NamedQuery(name = "Mobile.findByBatteryType", query = "SELECT m FROM Mobile m WHERE m.batteryType = :batteryType"),
    @NamedQuery(name = "Mobile.findByBatteryCapacity", query = "SELECT m FROM Mobile m WHERE m.batteryCapacity = :batteryCapacity"),
    @NamedQuery(name = "Mobile.findByTalkTime", query = "SELECT m FROM Mobile m WHERE m.talkTime = :talkTime"),
    @NamedQuery(name = "Mobile.findByStandByTime", query = "SELECT m FROM Mobile m WHERE m.standByTime = :standByTime"),
    @NamedQuery(name = "Mobile.findByFontCameraPixel", query = "SELECT m FROM Mobile m WHERE m.fontCameraPixel = :fontCameraPixel"),
    @NamedQuery(name = "Mobile.findByRearCameraPixel", query = "SELECT m FROM Mobile m WHERE m.rearCameraPixel = :rearCameraPixel"),
    @NamedQuery(name = "Mobile.findByMobileSize", query = "SELECT m FROM Mobile m WHERE m.mobileSize = :mobileSize"),
    @NamedQuery(name = "Mobile.findByWeight", query = "SELECT m FROM Mobile m WHERE m.weight = :weight")})
public class Mobile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "mobileID")
    private Integer mobileID;
    @Basic(optional = false)
    @Column(name = "screenSize")
    private double screenSize;
    @Basic(optional = false)
    @Column(name = "resolutionX")
    private int resolutionX;
    @Basic(optional = false)
    @Column(name = "resolutionY")
    private int resolutionY;
    @Column(name = "screenPixelDensity")
    private Integer screenPixelDensity;
    @Basic(optional = false)
    @Column(name = "mobileFormFactor")
    private String mobileFormFactor;
    @Column(name = "CPUModel")
    private String cPUModel;
    @Column(name = "CPUFrequency")
    private Integer cPUFrequency;
    @Column(name = "GPUModel")
    private String gPUModel;
    @Column(name = "RAM")
    private Integer ram;
    @Column(name = "ROM")
    private Integer rom;
    @Basic(optional = false)
    @Column(name = "SIMNumber")
    private int sIMNumber;
    @Basic(optional = false)
    @Column(name = "batteryType")
    private String batteryType;
    @Basic(optional = false)
    @Column(name = "batteryCapacity")
    private int batteryCapacity;
    @Column(name = "talkTime")
    private Integer talkTime;
    @Column(name = "standByTime")
    private Integer standByTime;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "fontCameraPixel")
    private Double fontCameraPixel;
    @Column(name = "rearCameraPixel")
    private Double rearCameraPixel;
    @Basic(optional = false)
    @Column(name = "mobileSize")
    private String mobileSize;
    @Basic(optional = false)
    @Column(name = "weight")
    private double weight;
    @JoinTable(name = "mobile_navigation", joinColumns = {
        @JoinColumn(name = "mobileID", referencedColumnName = "mobileID")}, inverseJoinColumns = {
        @JoinColumn(name = "navigationID", referencedColumnName = "navigationID")})
    @ManyToMany
    private Collection<Navigation> navigationCollection;
    @JoinTable(name = "mobile_wlantype", joinColumns = {
        @JoinColumn(name = "mobileID", referencedColumnName = "mobileID")}, inverseJoinColumns = {
        @JoinColumn(name = "WLANTypeID", referencedColumnName = "WLANTypeID")})
    @ManyToMany
    private Collection<WLANType> wLANTypeCollection;
    @ManyToMany(mappedBy = "mobileCollection")
    private Collection<KeyboardType> keyboardTypeCollection;
    @ManyToMany(mappedBy = "mobileCollection")
    private Collection<MachineInterface> machineInterfaceCollection;
    @JoinTable(name = "mobile_simcardtype", joinColumns = {
        @JoinColumn(name = "mobileID", referencedColumnName = "mobileID")}, inverseJoinColumns = {
        @JoinColumn(name = "SIMCardTypeID", referencedColumnName = "SIMCardTypeID")})
    @ManyToMany
    private Collection<SIMCardType> sIMCardTypeCollection;
    @ManyToMany(mappedBy = "mobileCollection")
    private Collection<MobileNetworkStandard> mobileNetworkStandardCollection;
    @ManyToMany(mappedBy = "mobileCollection")
    private Collection<MobileType> mobileTypeCollection;
    @ManyToMany(mappedBy = "mobileCollection")
    private Collection<ConnectivityAndSharing> connectivityAndSharingCollection;
    @JoinTable(name = "mobile_sensortype", joinColumns = {
        @JoinColumn(name = "mobileID", referencedColumnName = "mobileID")}, inverseJoinColumns = {
        @JoinColumn(name = "sensorTypeID", referencedColumnName = "sensorTypeID")})
    @ManyToMany
    private Collection<SensorType> sensorTypeCollection;
    @JoinColumn(name = "touchScreenTypeID", referencedColumnName = "touchScreenTypeID")
    @ManyToOne
    private TouchScreenType touchScreenTypeID;
    @JoinColumn(name = "screenMaterialID", referencedColumnName = "screenMaterialID")
    @ManyToOne(optional = false)
    private ScreenMaterial screenMaterialID;
    @JoinColumn(name = "OSID", referencedColumnName = "OSID")
    @ManyToOne(optional = false)
    private OperatingSystem osid;
    @JoinColumn(name = "flashMemoryCardID", referencedColumnName = "flashMemoryCardID")
    @ManyToOne
    private FlashMemoryCard flashMemoryCardID;
    @JoinColumn(name = "flashID", referencedColumnName = "flashID")
    @ManyToOne
    private Flash flashID;
    @JoinColumn(name = "CPUManufacturerID", referencedColumnName = "CPUManufacturerID")
    @ManyToOne
    private CPUManufacturer cPUManufacturerID;
    @JoinColumn(name = "CPUCoreTypeID", referencedColumnName = "CPUCoreTypeID")
    @ManyToOne(optional = false)
    private CPUCoreType cPUCoreTypeID;
    @JoinColumns({
        @JoinColumn(name = "mobileID", referencedColumnName = "merchandiseID", insertable = false, updatable = false),
        @JoinColumn(name = "categoryID", referencedColumnName = "categoryID")})
    @ManyToOne(optional = false)
    private Merchandise merchandise;

    public Mobile() {
    }

    public Mobile(Integer mobileID) {
        this.mobileID = mobileID;
    }

    public Mobile(Integer mobileID, double screenSize, int resolutionX, int resolutionY, String mobileFormFactor, int sIMNumber, String batteryType, int batteryCapacity, String mobileSize, double weight) {
        this.mobileID = mobileID;
        this.screenSize = screenSize;
        this.resolutionX = resolutionX;
        this.resolutionY = resolutionY;
        this.mobileFormFactor = mobileFormFactor;
        this.sIMNumber = sIMNumber;
        this.batteryType = batteryType;
        this.batteryCapacity = batteryCapacity;
        this.mobileSize = mobileSize;
        this.weight = weight;
    }

    public Integer getMobileID() {
        return mobileID;
    }

    public void setMobileID(Integer mobileID) {
        this.mobileID = mobileID;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }

    public int getResolutionX() {
        return resolutionX;
    }

    public void setResolutionX(int resolutionX) {
        this.resolutionX = resolutionX;
    }

    public int getResolutionY() {
        return resolutionY;
    }

    public void setResolutionY(int resolutionY) {
        this.resolutionY = resolutionY;
    }

    public Integer getScreenPixelDensity() {
        return screenPixelDensity;
    }

    public void setScreenPixelDensity(Integer screenPixelDensity) {
        this.screenPixelDensity = screenPixelDensity;
    }

    public String getMobileFormFactor() {
        return mobileFormFactor;
    }

    public void setMobileFormFactor(String mobileFormFactor) {
        this.mobileFormFactor = mobileFormFactor;
    }

    public String getCPUModel() {
        return cPUModel;
    }

    public void setCPUModel(String cPUModel) {
        this.cPUModel = cPUModel;
    }

    public Integer getCPUFrequency() {
        return cPUFrequency;
    }

    public void setCPUFrequency(Integer cPUFrequency) {
        this.cPUFrequency = cPUFrequency;
    }

    public String getGPUModel() {
        return gPUModel;
    }

    public void setGPUModel(String gPUModel) {
        this.gPUModel = gPUModel;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getRom() {
        return rom;
    }

    public void setRom(Integer rom) {
        this.rom = rom;
    }

    public int getSIMNumber() {
        return sIMNumber;
    }

    public void setSIMNumber(int sIMNumber) {
        this.sIMNumber = sIMNumber;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public Integer getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(Integer talkTime) {
        this.talkTime = talkTime;
    }

    public Integer getStandByTime() {
        return standByTime;
    }

    public void setStandByTime(Integer standByTime) {
        this.standByTime = standByTime;
    }

    public Double getFontCameraPixel() {
        return fontCameraPixel;
    }

    public void setFontCameraPixel(Double fontCameraPixel) {
        this.fontCameraPixel = fontCameraPixel;
    }

    public Double getRearCameraPixel() {
        return rearCameraPixel;
    }

    public void setRearCameraPixel(Double rearCameraPixel) {
        this.rearCameraPixel = rearCameraPixel;
    }

    public String getMobileSize() {
        return mobileSize;
    }

    public void setMobileSize(String mobileSize) {
        this.mobileSize = mobileSize;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @XmlTransient
    public Collection<Navigation> getNavigationCollection() {
        return navigationCollection;
    }

    public void setNavigationCollection(Collection<Navigation> navigationCollection) {
        this.navigationCollection = navigationCollection;
    }

    @XmlTransient
    public Collection<WLANType> getWLANTypeCollection() {
        return wLANTypeCollection;
    }

    public void setWLANTypeCollection(Collection<WLANType> wLANTypeCollection) {
        this.wLANTypeCollection = wLANTypeCollection;
    }

    @XmlTransient
    public Collection<KeyboardType> getKeyboardTypeCollection() {
        return keyboardTypeCollection;
    }

    public void setKeyboardTypeCollection(Collection<KeyboardType> keyboardTypeCollection) {
        this.keyboardTypeCollection = keyboardTypeCollection;
    }

    @XmlTransient
    public Collection<MachineInterface> getMachineInterfaceCollection() {
        return machineInterfaceCollection;
    }

    public void setMachineInterfaceCollection(Collection<MachineInterface> machineInterfaceCollection) {
        this.machineInterfaceCollection = machineInterfaceCollection;
    }

    @XmlTransient
    public Collection<SIMCardType> getSIMCardTypeCollection() {
        return sIMCardTypeCollection;
    }

    public void setSIMCardTypeCollection(Collection<SIMCardType> sIMCardTypeCollection) {
        this.sIMCardTypeCollection = sIMCardTypeCollection;
    }

    @XmlTransient
    public Collection<MobileNetworkStandard> getMobileNetworkStandardCollection() {
        return mobileNetworkStandardCollection;
    }

    public void setMobileNetworkStandardCollection(Collection<MobileNetworkStandard> mobileNetworkStandardCollection) {
        this.mobileNetworkStandardCollection = mobileNetworkStandardCollection;
    }

    @XmlTransient
    public Collection<MobileType> getMobileTypeCollection() {
        return mobileTypeCollection;
    }

    public void setMobileTypeCollection(Collection<MobileType> mobileTypeCollection) {
        this.mobileTypeCollection = mobileTypeCollection;
    }

    @XmlTransient
    public Collection<ConnectivityAndSharing> getConnectivityAndSharingCollection() {
        return connectivityAndSharingCollection;
    }

    public void setConnectivityAndSharingCollection(Collection<ConnectivityAndSharing> connectivityAndSharingCollection) {
        this.connectivityAndSharingCollection = connectivityAndSharingCollection;
    }

    @XmlTransient
    public Collection<SensorType> getSensorTypeCollection() {
        return sensorTypeCollection;
    }

    public void setSensorTypeCollection(Collection<SensorType> sensorTypeCollection) {
        this.sensorTypeCollection = sensorTypeCollection;
    }

    public TouchScreenType getTouchScreenTypeID() {
        return touchScreenTypeID;
    }

    public void setTouchScreenTypeID(TouchScreenType touchScreenTypeID) {
        this.touchScreenTypeID = touchScreenTypeID;
    }

    public ScreenMaterial getScreenMaterialID() {
        return screenMaterialID;
    }

    public void setScreenMaterialID(ScreenMaterial screenMaterialID) {
        this.screenMaterialID = screenMaterialID;
    }

    public OperatingSystem getOsid() {
        return osid;
    }

    public void setOsid(OperatingSystem osid) {
        this.osid = osid;
    }

    public FlashMemoryCard getFlashMemoryCardID() {
        return flashMemoryCardID;
    }

    public void setFlashMemoryCardID(FlashMemoryCard flashMemoryCardID) {
        this.flashMemoryCardID = flashMemoryCardID;
    }

    public Flash getFlashID() {
        return flashID;
    }

    public void setFlashID(Flash flashID) {
        this.flashID = flashID;
    }

    public CPUManufacturer getCPUManufacturerID() {
        return cPUManufacturerID;
    }

    public void setCPUManufacturerID(CPUManufacturer cPUManufacturerID) {
        this.cPUManufacturerID = cPUManufacturerID;
    }

    public CPUCoreType getCPUCoreTypeID() {
        return cPUCoreTypeID;
    }

    public void setCPUCoreTypeID(CPUCoreType cPUCoreTypeID) {
        this.cPUCoreTypeID = cPUCoreTypeID;
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
        hash += (mobileID != null ? mobileID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mobile)) {
            return false;
        }
        Mobile other = (Mobile) object;
        if ((this.mobileID == null && other.mobileID != null) || (this.mobileID != null && !this.mobileID.equals(other.mobileID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bean.Mobile[ mobileID=" + mobileID + " ]";
    }
    
}
