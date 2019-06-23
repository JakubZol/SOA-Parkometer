package pl.soa.parkometer.ejb.jms;


import pl.soa.parkometer.jms.Notification;

import javax.ejb.Remote;

@Remote
public interface MessagePublisherInterface {

    public void sendMessage(Notification notification);
}
