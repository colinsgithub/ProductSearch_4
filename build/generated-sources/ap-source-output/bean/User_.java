package bean;

import bean.Chatroom;
import bean.Comment;
import bean.Message;
import bean.Promotionpreference;
import bean.Store;
import bean.Tag;
import bean.Userstatus;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-05-31T15:28:44")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> userDesc;
    public static volatile SingularAttribute<User, String> sex;
    public static volatile CollectionAttribute<User, Comment> commentCollection;
    public static volatile CollectionAttribute<User, Chatroom> chatroomCollection1;
    public static volatile CollectionAttribute<User, Store> storeCollection;
    public static volatile CollectionAttribute<User, Message> messageCollection;
    public static volatile CollectionAttribute<User, Promotionpreference> promotionpreferenceCollection;
    public static volatile CollectionAttribute<User, Tag> tagCollection;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> userID;
    public static volatile CollectionAttribute<User, Userstatus> userstatusCollection;
    public static volatile SingularAttribute<User, String> preference;
    public static volatile SingularAttribute<User, String> email;
    public static volatile CollectionAttribute<User, Chatroom> chatroomCollection;
    public static volatile SingularAttribute<User, Integer> age;
    public static volatile SingularAttribute<User, String> role;
    public static volatile SingularAttribute<User, String> userName;
    public static volatile SingularAttribute<User, Double> credit;

}