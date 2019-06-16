package pl.soa.parkometer.ejb.impl.jms;

import pl.soa.parkometer.ejb.jms.MessageReceiverInterface;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(
                propertyName = "destination",
                propertyValue = "java:/jms/queue/SOA_Parkometer"),
        @ActivationConfigProperty(
                propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class MessageReceiver implements MessageReceiverInterface, MessageListener {

    public void receiveMessage(){

    }


    public void onMessage(Message message) {
        System.out.println(message);
    }
}

