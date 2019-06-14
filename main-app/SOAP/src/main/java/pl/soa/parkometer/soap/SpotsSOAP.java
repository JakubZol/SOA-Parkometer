package pl.soa.parkometer.soap;

import pl.soa.parkometer.ejb.database.SpotManagerInterface;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class SpotsSOAP {

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/SpotManager")
    SpotManagerInterface spotManager;

    @WebMethod
    public void testSOAP(){
        System.out.println("this is working SOAP");
    }


}
