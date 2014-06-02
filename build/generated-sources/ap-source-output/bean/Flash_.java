package bean;

import bean.Mobile;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-02T12:35:57")
@StaticMetamodel(Flash.class)
public class Flash_ { 

    public static volatile SingularAttribute<Flash, String> flashName;
    public static volatile SingularAttribute<Flash, Integer> flashID;
    public static volatile CollectionAttribute<Flash, Mobile> mobileCollection;

}