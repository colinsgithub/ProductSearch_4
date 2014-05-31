package bean;

import bean.Merchandise;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-01T01:02:00")
@StaticMetamodel(Brand.class)
public class Brand_ { 

    public static volatile SingularAttribute<Brand, Integer> brandID;
    public static volatile SingularAttribute<Brand, String> brandIcon;
    public static volatile CollectionAttribute<Brand, Merchandise> merchandiseCollection;
    public static volatile SingularAttribute<Brand, String> brandRegion;
    public static volatile SingularAttribute<Brand, String> brandName;

}