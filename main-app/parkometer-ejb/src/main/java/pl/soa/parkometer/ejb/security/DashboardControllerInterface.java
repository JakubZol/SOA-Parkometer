package pl.soa.parkometer.ejb.security;

import pl.soa.parkometer.entities.Spot;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface DashboardControllerInterface {

    public List<Spot> getAllSpots();

    public List<Spot> getSpotsByZone(int zoneId);

    public List<Spot> getSpotsForDashboard();
}
