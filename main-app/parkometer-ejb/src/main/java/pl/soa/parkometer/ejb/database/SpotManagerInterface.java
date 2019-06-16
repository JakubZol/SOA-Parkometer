package pl.soa.parkometer.ejb.database;

import pl.soa.parkometer.entities.Spot;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface SpotManagerInterface {

    public Spot getSpotById(int id);

    public List<Spot> getAllSpots();

    public void updateSpot(Spot s);

    public void deleteSpot(int id);

    public List<Spot> getOccupiedSpots();

    public List<Spot> getSpotsByZone(int zoneId);
}
