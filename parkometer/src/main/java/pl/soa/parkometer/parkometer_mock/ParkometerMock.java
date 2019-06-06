package pl.soa.parkometer.parkometer_mock;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

@ManagedBean(name = "parkometer", eager = true)
@ApplicationScoped
public class ParkometerMock {

    private Map<String, Vector<String>> zones = ParkometerMock.generateZones();
    private Set<String> zonesList = zones.keySet();
    private String currentZone = zonesList.iterator().next();
    private String currentLot = zones.get(currentZone).firstElement();
    private Integer expiryTime;

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
