package bean;

import bean.Mobile;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-02T12:35:57")
@StaticMetamodel(Touchscreentype.class)
public class Touchscreentype_ { 

    public static volatile SingularAttribute<Touchscreentype, Integer> touchScreenTypeID;
    public static volatile SingularAttribute<Touchscreentype, String> touchScreenTypeName;
    public static volatile CollectionAttribute<Touchscreentype, Mobile> mobileCollection;

}