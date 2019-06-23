package pl.soa.parkometer.carpark_mock.bean;


import pl.soa.parkometer.carpark_mock.ZonesInitializer;
import pl.soa.parkometer.carpark_mock.soap_client.SOAPClient;
import pl.soa.parkometer.carpark_mock.soap_client.wsdl.Spot;
import pl.soa.parkometer.carpark_mock.soap_client.wsdl.Timestamp;
import pl.soa.parkometer.carpark_mock.soap_client.wsdl.Zone;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@ManagedBean(name = "carpark", eager = true)
@ApplicationScoped
public class CarParkMock {

    private String currentSpotId;
    private Map<String, Vector<String>> zones = ZonesInitializer.initializeZones();
    private Set<String> zonesList = zones.keySet();
    private String currentZone = zonesList.iterator().next();
    private String currentLot = zones.get(currentZone).firstElement();
    private List<Spot> spots = ZonesInitializer.getSpots();

    public List<Spot> getSpots() {
        return spots;
    }

    public String getCurrentSpotId(){ return currentSpotId; };

    public void setCurrentSpotId(String currentSpotId) {
        this.currentSpotId = currentSpotId;
    }

    public Set<String> getZonesList(){
        return this.zonesList;
    }

    public Vector<String> getLots(){
        return this.zones.get(currentZone);
    }

    public String getCurrentLot() {
        return currentLot;
    }

    public void setCurrentLot(String currentLot) {
        this.currentLot = currentLot;
    }

    public Map<String, Vector<String>> getZones() {
        return zones;
    }

    public String getCurrentZone() {
        return currentZone;
    }

    public void setCurrentZone(String currentZone) {
        this.currentZone = currentZone;
    }

    public void setZones(Map<String, Vector<String>> zones) {
        this.zones = zones;
    }

    public String getMessage(){
        return "Zajęte miejsce: " + currentLot;
    }

    public void takeSpot(){
        int spotId = Integer.parseInt(currentSpotId);

        List<Spot> spots = this.spots.stream().filter(spot -> spot.getSpotId() == spotId).collect(Collectors.toList());
        if(spots.size() > 0){
            Spot s = spots.get(0);
            s.setIsVacancy(false);
            ZonesInitializer.updateSpot(s);
        }
    }


}
