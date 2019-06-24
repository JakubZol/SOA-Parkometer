package pl.soa.parkometer.ejb.core;

import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.Ticket;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface ParkingStateControllerInterface {

    public void deleteSpot(int id);

    public void deleteTicket(int id);

    public void setTicketsQueue(List<Ticket> tickets);

    public void setSpotsQueue(List<Spot> spots);
}
