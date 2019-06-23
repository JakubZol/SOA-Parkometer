package pl.soa.parkometer.soap;

import pl.soa.parkometer.ejb.database.SpotManagerInterface;
import pl.soa.parkometer.entities.Spot;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@Stateless
@WebService(name="SpotService", portName = "SpotService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SpotsSOAP {

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/SpotManager")
    SpotManagerInterface spotManager;

    @WebMethod
    public List<Spot> getFreeSpots(){
        return spotManager.getFreeSpots();
    }

   /* @WebMethod
    public void updateSpot(Spot s) {
        spotManager.updateSpot(s);
    }*/

}
