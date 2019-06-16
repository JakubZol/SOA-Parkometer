package pl.soa.parkometer.ejb.jms;

import javax.ejb.Remote;

@Remote
public interface MessageSerivceInterface {

    public void sendMessage(String message);
}
