package pl.soa.parkometer.soap;

import pl.soa.parkometer.ejb.database.SpotManagerInterface;
import pl.soa.parkometer.entities.Spot;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class SpotsSOAP {

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/SpotManager")
    SpotManagerInterface spotManager;

    @WebMethod
    public List<Spot> getFreeSpots(){
        return spotManager.getFreeSpots();
    }


}
