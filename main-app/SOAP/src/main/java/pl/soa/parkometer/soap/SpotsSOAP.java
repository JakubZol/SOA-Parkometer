package pl.soa.parkometer.soap;

import pl.soa.parkometer.ejb.database.SpotManagerInterface;
import pl.soa.parkometer.entities.Spot;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.sql.Timestamp;
import java.util.List;

@Stateless
@WebService(name="SpotService", portName = "SpotService")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SpotsSOAP {

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/SpotManager")
    SpotManagerInterface spotManager;

    @WebMethod(operationName = "getFreeSpots")
    @WebResult(name = "getFreeSpotsResponse")
    public List<Spot> getFreeSpots(){
        return spotManager.getFreeSpots();
    }

    @WebMethod(operationName = "updateSpot")
    public void updateSpot(@WebParam(name = "Spot")Spot s) {
        s.setOccupationDate(new Timestamp(System.currentTimeMillis()));
        spotManager.updateSpot(s);
    }

}
