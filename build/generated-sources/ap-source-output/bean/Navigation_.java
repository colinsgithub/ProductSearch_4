package bean;

import bean.Mobile;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-05-31T15:28:44")
@StaticMetamodel(Navigation.class)
public class Navigation_ { 

    public static volatile SingularAttribute<Navigation, Integer> navigationID;
    public static volatile SingularAttribute<Navigation, String> navigationName;
    public static volatile CollectionAttribute<Navigation, Mobile> mobileCollection;

}