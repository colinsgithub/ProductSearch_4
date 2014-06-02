package bean;

import bean.Chatroom;
import bean.MessagePK;
import bean.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-06-02T12:35:57")
@StaticMetamodel(Message.class)
public class Message_ { 

    public static volatile SingularAttribute<Message, Chatroom> chatroom;
    public static volatile SingularAttribute<Message, String> msgContent;
    public static volatile SingularAttribute<Message, User> user;
    public static volatile SingularAttribute<Message, MessagePK> messagePK;
    public static volatile SingularAttribute<Message, Date> postTime;

}