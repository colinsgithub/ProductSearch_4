package bean;

import bean.CommentPK;
import bean.Store;
import bean.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-01T01:02:00")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, String> feedback;
    public static volatile SingularAttribute<Comment, Short> rank;
    public static volatile SingularAttribute<Comment, CommentPK> commentPK;
    public static volatile SingularAttribute<Comment, Store> store;
    public static volatile SingularAttribute<Comment, User> user;

}