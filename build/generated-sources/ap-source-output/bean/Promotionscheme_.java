package bean;

import bean.Promotionpreference;
import bean.PromotionschemePK;
import bean.Store;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-02T12:35:57")
@StaticMetamodel(Promotionscheme.class)
public class Promotionscheme_ { 

    public static volatile SingularAttribute<Promotionscheme, Date> startDate;
    public static volatile SingularAttribute<Promotionscheme, Store> store;
    public static volatile SingularAttribute<Promotionscheme, String> description;
    public static volatile SingularAttribute<Promotionscheme, PromotionschemePK> promotionschemePK;
    public static volatile SingularAttribute<Promotionscheme, Date> endDate;
    public static volatile CollectionAttribute<Promotionscheme, Promotionpreference> promotionpreferenceCollection;

}