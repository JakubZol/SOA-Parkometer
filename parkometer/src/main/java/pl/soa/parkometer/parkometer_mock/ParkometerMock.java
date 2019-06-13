package pl.soa.parkometer.parkometer_mock;

import pl.soa.parkometer.entities.Spot;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@ManagedBean(name = "parkometer", eager = true)
@ApplicationScoped
public class ParkometerMock {

    private Map<String, Vector<String>> zones = ParkometerMock.generateZones();
    private Set<String> zonesList = zones.keySet();
    private String currentZone = zonesList.iterator().next();
    private String currentLot = zones.get(currentZone).firstElement();
    private Integer expiryTime;
    private List<Spot> spots = this.getSpots();


    public List<Spot> getSpots() {
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/REST_war/spots").request().accept(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus()!= 200){
            throw new RuntimeException("Error");
        }
        List<Spot> spots = response.readEntity(new GenericType<List<Spot>>(){});
        System.out.println(spots.get(0));
        return spots;
    }


    private static Map<String, Vector<String>> generateZones(){
        Map<String, Vector<String>> zones = new HashMap<String, Vector<String>>();
        String zone;

        for(int x = 1; x < 10; x++){
            Vector<String> lots = new Vector<String>();
            zone = "Zone" + x;
            for(int j = 1; j < 10; j++){
                lots.add("Lot" + x + j);
            }
            zones.put(zone, lots);
        }


        return zones;
    }

    public Vector<String> getLots(){
        return this.zones.get(currentZone);
    }

    public Map<String, Vector<String>> getZones() {
        return zones;
    }

    public void setZones(Map<String, Vector<String>> zones) {
        this.zones = zones;
    }

    public Set<String> getZonesList() {
        return zonesList;
    }

    public void setZonesList(Set<String> zonesList) {
        this.zonesList = zonesList;
    }

    public String getCurrentZone() {
        return currentZone;
    }

    public void setCurrentZone(String currentZone) {
        this.currentZone = currentZone;
    }

    public String getCurrentLot() {
        return currentLot;
    }

    public void setCurrentLot(String currentLot) {
        this.currentLot = currentLot;
    }

    public Integer getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Integer expiryTime) {
        this.expiryTime = expiryTime;
    }


}
