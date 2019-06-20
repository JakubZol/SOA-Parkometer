package pl.soa.parkometer.ejb.security;

import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.Ticket;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface DashboardControllerInterface {

    public List<Spot> getAllSpots();

    public List<Spot> getSpotsByZone(int zoneId);

    public List<Spot> getSpotsForDashboard();

    public List<Ticket> getTicketsBySpot(int spotId);
}
