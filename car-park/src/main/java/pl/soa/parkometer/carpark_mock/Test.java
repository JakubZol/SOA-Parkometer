package pl.soa.parkometer.carpark_mock;

import pl.soa.parkometer.carpark_mock.soap_client.SOAPClient;
import pl.soa.parkometer.carpark_mock.soap_client.wsdl.Spot;
import pl.soa.parkometer.carpark_mock.soap_client.wsdl.SpotArray;
import pl.soa.parkometer.carpark_mock.soap_client.wsdl.SpotsSOAPService;

import java.net.MalformedURLException;
import java.util.List;

public class Test {

    public static void main(String[] args){
        SOAPClient soapClient = new SOAPClient("http://localhost:8080/SOAP-1.0-SNAPSHOT/SpotService?wsdl");
        List<Spot> spots = soapClient.getFreeSpots();
        System.out.println(spots.get(0).getSpotName());

    }
}
