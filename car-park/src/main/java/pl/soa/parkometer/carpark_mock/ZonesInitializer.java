package pl.soa.parkometer.carpark_mock;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class ZonesInitializer {

    public static Map<String, Vector<String>> initializeZones(){
        Map<String, Vector<String>> zones = new HashMap<String, Vector<String>>();
        String zone;

        for(int i = 1; i < 10; i++){
            zone = "Zone" + i;
            Vector<String> lots = new Vector<String>();
            for(int j = 1; j < 10; j++){
                lots.add("Lot" + i + j);
            }
            zones.put(zone, lots);
        }


        return zones;
    }
}
