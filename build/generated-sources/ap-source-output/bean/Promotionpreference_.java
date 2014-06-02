package bean;

import bean.PromotionpreferencePK;
import bean.Promotionscheme;
import bean.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-02T12:35:57")
@StaticMetamodel(Promotionpreference.class)
public class Promotionpreference_ { 

    public static volatile SingularAttribute<Promotionpreference, Promotionscheme> promotionscheme;
    public static volatile SingularAttribute<Promotionpreference, PromotionpreferencePK> promotionpreferencePK;
    public static volatile SingularAttribute<Promotionpreference, User> user;
    public static volatile SingularAttribute<Promotionpreference, Boolean> notificationSelection;

}