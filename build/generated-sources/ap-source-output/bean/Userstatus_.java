package bean;

import bean.User;
import bean.UserstatusPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-01T01:02:00")
@StaticMetamodel(Userstatus.class)
public class Userstatus_ { 

    public static volatile SingularAttribute<Userstatus, String> statusName;
    public static volatile SingularAttribute<Userstatus, String> statusColor;
    public static volatile SingularAttribute<Userstatus, UserstatusPK> userstatusPK;
    public static volatile SingularAttribute<Userstatus, User> user;
    public static volatile SingularAttribute<Userstatus, String> statusDesc;

}