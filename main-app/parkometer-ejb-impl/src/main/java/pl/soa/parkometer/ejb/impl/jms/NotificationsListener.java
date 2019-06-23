package pl.soa.parkometer.ejb.impl.jms;



import pl.soa.parkometer.ejb.jms.NotificationsListenerInterface;
import pl.soa.parkometer.ejb.security.NotificationsControllerInterface;
import pl.soa.parkometer.jms.Notification;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;


@MessageDriven(messageListenerInterface=MessageListener.class, activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destination",
                propertyValue = "java:/jms/queue/SOA_Parkometer"),
        @ActivationConfigProperty(
                propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})

public class NotificationsListener implements MessageListener, NotificationsListenerInterface {

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/NotificationsController")
    private NotificationsControllerInterface notificationsController;

    @Override
    public void onMessage(Message message) {
        ObjectMessage objectMessage = (ObjectMessage) message;
        try{
            Notification notification = (Notification) objectMessage.getObject();
            System.out.println("Message: " + notification.getMessagge());
            notificationsController.addNotification(notification);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
