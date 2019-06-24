package pl.soa.parkometer.carpark_mock.bean;


import pl.soa.parkometer.carpark_mock.ZonesInitializer;
import pl.soa.parkometer.carpark_mock.soap_client.wsdl.Spot;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.*;
import java.util.stream.Collectors;

@ManagedBean(name = "carpark", eager = true)
@ApplicationScoped
public class CarParkMock {

    private String currentFreeSpotId;
    private String currentOccupiedSpotId;

    public List<Spot> getFreeSpots() {
        return ZonesInitializer.getSpots();
    }

    public List<Spot> getOccupiedSpots(){
        return ZonesInitializer.getOccupiedSpots();
    }

    public String getCurrentOccupiedSpotId() {
        return currentOccupiedSpotId;
    }

    public void setCurrentOccupiedSpotId(String currentOccupiedSpotId){
        this.currentOccupiedSpotId = currentOccupiedSpotId;
    }

    public String getCurrentFreeSpotId(){ return currentFreeSpotId; };

    public void setCurrentFreeSpotId(String currentSpotId) {
        this.currentFreeSpotId = currentSpotId;
    }

    public void takeSpot(){
        int spotId = Integer.parseInt(currentFreeSpotId);

        List<Spot> spots = this.getFreeSpots().stream().filter(spot -> spot.getSpotId() == spotId).collect(Collectors.toList());
        if(spots.size() > 0){
            Spot s = spots.get(0);
            s.setIsVacancy(false);
            ZonesInitializer.updateSpot(s);
        }
    }

    public void leaveSpot(){
        int spotId = Integer.parseInt(currentOccupiedSpotId);

        List<Spot> spots = this.getOccupiedSpots().stream().filter(spot -> spot.getSpotId() == spotId).collect(Collectors.toList());
        if(spots.size() > 0){
            Spot s = spots.get(0);
            s.setIsVacancy(true);
            s.setOccupationDate(null);
            ZonesInitializer.updateSpot(s);
        }
    }


}
