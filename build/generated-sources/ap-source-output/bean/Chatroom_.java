package bean;

import bean.Message;
import bean.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-01T01:02:00")
@StaticMetamodel(Chatroom.class)
public class Chatroom_ { 

    public static volatile SingularAttribute<Chatroom, String> roomDesc;
    public static volatile SingularAttribute<Chatroom, String> roomName;
    public static volatile SingularAttribute<Chatroom, String> roomImage;
    public static volatile SingularAttribute<Chatroom, User> administrator;
    public static volatile SingularAttribute<Chatroom, User> owner;
    public static volatile SingularAttribute<Chatroom, Date> creationTime;
    public static volatile CollectionAttribute<Chatroom, Message> messageCollection;
    public static volatile SingularAttribute<Chatroom, Integer> roomID;

}