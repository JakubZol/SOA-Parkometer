package pl.soa.parkometer.soap;

import pl.soa.parkometer.ejb.core.ParkingStateControllerInterface;
import pl.soa.parkometer.ejb.database.SpotManagerInterface;
import pl.soa.parkometer.ejb.database.TicketManagerInterface;
import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.Ticket;

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

    @EJB(lookup= "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/ParkingStateController")
    ParkingStateControllerInterface parkingStateController;

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/TicketManager")
    TicketManagerInterface ticketManager;

    @WebMethod(operationName = "getFreeSpots")
    @WebResult(name = "getFreeSpotsResponse")
    public List<Spot> getFreeSpots(){
        return spotManager.getFreeSpots();
    }

    @WebMethod(operationName = "getAllOccupiedSpots")
    @WebResult(name = "getAllOccupiedSpotsResponse")
    public List<Spot> getAllOccupiedSpots(){ return spotManager.getAllOccupiedSpots();}

    @WebMethod(operationName = "updateSpot")
    public void updateSpot(@WebParam(name = "Spot") Spot s) {
        if(s.isVacancy()){
            Ticket t = ticketManager.getActiveTicketBySpot(s.getSpotId());
            if(t != null) {
                t.setExpiryDate(new Timestamp(System.currentTimeMillis()));
                ticketManager.updateTicket(t);
            }
        }else {
            s.setOccupationDate(new Timestamp(System.currentTimeMillis()));
        }
        spotManager.updateSpot(s);
        parkingStateController.setSpotsQueue(spotManager.getOccupiedSpots());

    }

}
