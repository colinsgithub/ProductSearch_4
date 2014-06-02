package bean;

import bean.Mobile;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-02T12:35:57")
@StaticMetamodel(Wlantype.class)
public class Wlantype_ { 

    public static volatile SingularAttribute<Wlantype, Integer> wLANTypeID;
    public static volatile SingularAttribute<Wlantype, String> wLANTypeName;
    public static volatile CollectionAttribute<Wlantype, Mobile> mobileCollection;

}