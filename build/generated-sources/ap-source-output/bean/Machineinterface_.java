package bean;

import bean.Mobile;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-02T12:35:57")
@StaticMetamodel(Machineinterface.class)
public class Machineinterface_ { 

    public static volatile SingularAttribute<Machineinterface, String> machineInterfaceName;
    public static volatile SingularAttribute<Machineinterface, Integer> machineInterfaceID;
    public static volatile CollectionAttribute<Machineinterface, Mobile> mobileCollection;

}