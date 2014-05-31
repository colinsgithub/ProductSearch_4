package bean;

import bean.Store;
import bean.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-01T01:02:00")
@StaticMetamodel(Tag.class)
public class Tag_ { 

    public static volatile SingularAttribute<Tag, User> userID;
    public static volatile SingularAttribute<Tag, Date> creationTime;
    public static volatile SingularAttribute<Tag, Integer> tagID;
    public static volatile SingularAttribute<Tag, Store> storeID;

}