package bean;

import bean.Connectivityandsharing;
import bean.Cpucoretype;
import bean.Cpumanufacturer;
import bean.Flash;
import bean.Flashmemorycard;
import bean.Keyboardtype;
import bean.Machineinterface;
import bean.Merchandise;
import bean.Mobilenetworkstandard;
import bean.Mobiletype;
import bean.Navigation;
import bean.Operatingsystem;
import bean.Screenmaterial;
import bean.Sensortype;
import bean.Simcardtype;
import bean.Touchscreentype;
import bean.Wlantype;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-02T12:35:57")
@StaticMetamodel(Mobile.class)
public class Mobile_ { 

    public static volatile SingularAttribute<Mobile, Double> screenSize;
    public static volatile SingularAttribute<Mobile, Double> weight;
    public static volatile SingularAttribute<Mobile, Cpumanufacturer> cPUManufacturerID;
    public static volatile SingularAttribute<Mobile, Merchandise> merchandise;
    public static volatile CollectionAttribute<Mobile, Navigation> navigationCollection;
    public static volatile SingularAttribute<Mobile, Screenmaterial> screenMaterialID;
    public static volatile SingularAttribute<Mobile, Integer> standByTime;
    public static volatile CollectionAttribute<Mobile, Wlantype> wlantypeCollection;
    public static volatile SingularAttribute<Mobile, Double> fontCameraPixel;
    public static volatile CollectionAttribute<Mobile, Connectivityandsharing> connectivityandsharingCollection;
    public static volatile CollectionAttribute<Mobile, Sensortype> sensortypeCollection;
    public static volatile SingularAttribute<Mobile, Cpucoretype> cPUCoreTypeID;
    public static volatile SingularAttribute<Mobile, Double> rearCameraPixel;
    public static volatile SingularAttribute<Mobile, String> gPUModel;
    public static volatile SingularAttribute<Mobile, String> mobileFormFactor;
    public static volatile SingularAttribute<Mobile, Integer> sIMNumber;
    public static volatile SingularAttribute<Mobile, String> mobileSize;
    public static volatile SingularAttribute<Mobile, Integer> batteryCapacity;
    public static volatile SingularAttribute<Mobile, Integer> screenPixelDensity;
    public static volatile CollectionAttribute<Mobile, Mobiletype> mobiletypeCollection;
    public static volatile SingularAttribute<Mobile, Touchscreentype> touchScreenTypeID;
    public static volatile SingularAttribute<Mobile, Integer> rom;
    public static volatile SingularAttribute<Mobile, Integer> resolutionX;
    public static volatile SingularAttribute<Mobile, Integer> resolutionY;
    public static volatile SingularAttribute<Mobile, Integer> talkTime;
    public static volatile SingularAttribute<Mobile, String> batteryType;
    public static volatile SingularAttribute<Mobile, Flash> flashID;
    public static volatile CollectionAttribute<Mobile, Simcardtype> simcardtypeCollection;
    public static volatile CollectionAttribute<Mobile, Keyboardtype> keyboardtypeCollection;
    public static volatile SingularAttribute<Mobile, Operatingsystem> osid;
    public static volatile SingularAttribute<Mobile, Integer> cPUFrequency;
    public static volatile CollectionAttribute<Mobile, Machineinterface> machineinterfaceCollection;
    public static volatile CollectionAttribute<Mobile, Mobilenetworkstandard> mobilenetworkstandardCollection;
    public static volatile SingularAttribute<Mobile, Integer> mobileID;
    public static volatile SingularAttribute<Mobile, Integer> ram;
    public static volatile SingularAttribute<Mobile, Flashmemorycard> flashMemoryCardID;
    public static volatile SingularAttribute<Mobile, String> cPUModel;

}