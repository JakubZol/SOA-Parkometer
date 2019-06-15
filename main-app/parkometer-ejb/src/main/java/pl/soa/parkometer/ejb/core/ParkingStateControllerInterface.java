package pl.soa.parkometer.ejb.core;

import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.Ticket;

import javax.ejb.Remote;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

@Remote
public interface ParkingStateControllerInterface {

    public void updateTicketQueue(Ticket t);

    public void updateSpotQueue(Spot s);

    public void deleteSpot(int id);

    public void deleteTicket(int id);

    public void sendMessage();

    public void resetTimer();
}
