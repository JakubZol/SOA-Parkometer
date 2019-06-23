package pl.soa.parkometer.carpark_mock.bean;


import pl.soa.parkometer.carpark_mock.ZonesInitializer;
import pl.soa.parkometer.carpark_mock.soap_client.SOAPClient;
import pl.soa.parkometer.entities.Spot;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.*;

@ManagedBean(name = "carpark", eager = true)
@ApplicationScoped
public class CarParkMock {

    private int currentSpotId;
    private SOAPClient soapClient = new SOAPClient();
    private List<Spot> spots = new ArrayList<>();//soapClient.getSpots();
    private Map<String, Vector<String>> zones = ZonesInitializer.initializeZones();
    private Set<String> zonesList = zones.keySet();
    private String currentZone = zonesList.iterator().next();
    private String currentLot = zones.get(currentZone).firstElement();

    public List<Spot> getSpots() {
        return spots;
    }

    public int getCurrentSpotId(){ return currentSpotId; };

    public void setCurrentSpotId(int currentSpotId) {
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


}
