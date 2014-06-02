package bean;

import bean.Mobile;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-02T12:35:57")
@StaticMetamodel(Cpumanufacturer.class)
public class Cpumanufacturer_ { 

    public static volatile SingularAttribute<Cpumanufacturer, Integer> cPUManufacturerID;
    public static volatile SingularAttribute<Cpumanufacturer, String> cPUManufacturerName;
    public static volatile CollectionAttribute<Cpumanufacturer, Mobile> mobileCollection;

}