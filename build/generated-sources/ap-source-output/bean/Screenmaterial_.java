package bean;

import bean.Mobile;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-05-31T15:28:44")
@StaticMetamodel(Screenmaterial.class)
public class Screenmaterial_ { 

    public static volatile SingularAttribute<Screenmaterial, String> screenMaterialName;
    public static volatile SingularAttribute<Screenmaterial, Integer> screenMaterialID;
    public static volatile CollectionAttribute<Screenmaterial, Mobile> mobileCollection;

}