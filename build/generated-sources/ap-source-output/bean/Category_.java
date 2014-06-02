package bean;

import bean.Category;
import bean.Merchandise;
import bean.Store;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-02T12:35:57")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile SingularAttribute<Category, String> categoryName;
    public static volatile SingularAttribute<Category, Category> parentID;
    public static volatile SingularAttribute<Category, Integer> categoryID;
    public static volatile CollectionAttribute<Category, Merchandise> merchandiseCollection;
    public static volatile CollectionAttribute<Category, Store> storeCollection;
    public static volatile SingularAttribute<Category, String> categoryImage;
    public static volatile SingularAttribute<Category, String> categoryDesc;
    public static volatile CollectionAttribute<Category, Category> categoryCollection;

}