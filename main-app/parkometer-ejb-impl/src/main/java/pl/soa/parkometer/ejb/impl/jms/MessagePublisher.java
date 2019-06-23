package pl.soa.parkometer.ejb.impl.jms;
import pl.soa.parkometer.ejb.jms.MessagePublisherInterface;
import pl.soa.parkometer.jms.Notification;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
public class MessagePublisher implements MessagePublisherInterface {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory cf;

    @Resource(mappedName = "java:/jms/queue/SOA_Parkometer")
    private Queue queue;

    private Connection connection;


    public void sendMessage(Notification notification){
        try {
            connection = cf.createConnection();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publisher = session.createProducer(queue);

            connection.start();

            ObjectMessage message = session.createObjectMessage();
            message.setObject(notification);
            publisher.send(message);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
