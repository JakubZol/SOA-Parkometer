package pl.soa.parkometer.ejb.jms;

import javax.ejb.Remote;

@Remote
public interface MessageReceiverInterface {

    public void receiveMessage();
}
