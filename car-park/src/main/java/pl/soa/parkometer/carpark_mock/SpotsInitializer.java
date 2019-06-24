package pl.soa.parkometer.carpark_mock;

import pl.soa.parkometer.carpark_mock.soap_client.SOAPClient;
import pl.soa.parkometer.carpark_mock.soap_client.wsdl.Spot;

import java.util.List;

public class SpotsInitializer {

    private static SOAPClient soapClient = new SOAPClient("http://localhost:8080/SOAP-1.0-SNAPSHOT/SpotService?wsdl");

    public static List<Spot> getSpots(){
        return soapClient.getFreeSpots();
    }

    public static List<Spot> getOccupiedSpots(){
        return soapClient.getOccupiedSpots();
    }

    public static void updateSpot(Spot s){
        soapClient.updateSpot(s);
    }

}
