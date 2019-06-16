package pl.soa.parkometer.ejb.impl.jms;

import pl.soa.parkometer.ejb.jms.MessageSerivceInterface;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.*;

@Stateless
public class MessageService implements MessageSerivceInterface {

    @Resource(mappedName = "java:/jms/queue/SOA_Parkometer")
    private Queue queue;
    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory cf;

    public void sendMessage(String msg) {
        Connection con = null;
        try{
            con = cf.createConnection();

            Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publisher = session.createProducer(queue);

            con.start();

            ObjectMessage message = session.createObjectMessage();
            message.setObject(msg);
            publisher.send(message);
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (JMSException ignored) {
                }
            }
        }
    }
}
