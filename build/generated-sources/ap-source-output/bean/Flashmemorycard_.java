package bean;

import bean.Mobile;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-01T01:02:00")
@StaticMetamodel(Flashmemorycard.class)
public class Flashmemorycard_ { 

    public static volatile SingularAttribute<Flashmemorycard, String> flashMemoryCardName;
    public static volatile SingularAttribute<Flashmemorycard, Integer> flashMemoryCardID;
    public static volatile CollectionAttribute<Flashmemorycard, Mobile> mobileCollection;

}