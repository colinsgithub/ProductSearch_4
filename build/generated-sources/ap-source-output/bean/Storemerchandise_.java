package bean;

import bean.Merchandise;
import bean.Store;
import bean.StoremerchandisePK;
import bean.Storeuppergarmentchoice;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-01T01:02:00")
@StaticMetamodel(Storemerchandise.class)
public class Storemerchandise_ { 

    public static volatile CollectionAttribute<Storemerchandise, Storeuppergarmentchoice> storeuppergarmentchoiceCollection;
    public static volatile SingularAttribute<Storemerchandise, Double> price;
    public static volatile SingularAttribute<Storemerchandise, Store> store;
    public static volatile SingularAttribute<Storemerchandise, Merchandise> merchandise;
    public static volatile SingularAttribute<Storemerchandise, StoremerchandisePK> storemerchandisePK;
    public static volatile SingularAttribute<Storemerchandise, String> color;
    public static volatile SingularAttribute<Storemerchandise, String> merchandiseImage1;
    public static volatile SingularAttribute<Storemerchandise, String> merchandiseImage2;
    public static volatile SingularAttribute<Storemerchandise, String> merchandiseImage3;

}