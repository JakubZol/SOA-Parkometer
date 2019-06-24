package pl.soa.parkometer.carpark_mock.soap_client;


import pl.soa.parkometer.carpark_mock.soap_client.wsdl.Spot;
import pl.soa.parkometer.carpark_mock.soap_client.wsdl.SpotsSOAPService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class SOAPClient {

    private SpotsSOAPService spotsSOAPService;


    public SOAPClient(String serviceUrl){
        try {
            URL url = new URL(serviceUrl);
            spotsSOAPService = new SpotsSOAPService(url);
        }
        catch (MalformedURLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Spot> getFreeSpots(){
        return spotsSOAPService.getSpotService().getFreeSpots().getItem();
    }

    public void updateSpot(Spot spot){
        spotsSOAPService.getSpotService().updateSpot(spot);
    }

    public List<Spot> getOccupiedSpots(){
        return spotsSOAPService.getSpotService().getAllOccupiedSpots().getItem();
    }

}
