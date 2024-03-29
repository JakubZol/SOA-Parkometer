package pl.soa.parkometer.rest;

import pl.soa.parkometer.ejb.core.ParkingStateControllerInterface;
import pl.soa.parkometer.ejb.database.SpotManagerInterface;
import pl.soa.parkometer.ejb.database.TicketManagerInterface;
import pl.soa.parkometer.entities.Ticket;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Produces("application/json")
@Stateless
public class TicketsController {

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/TicketManager")
    TicketManagerInterface ticketManager;

    @EJB(lookup= "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/ParkingStateController")
    ParkingStateControllerInterface parkingStateController;

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/SpotManager")
    SpotManagerInterface spotManager;

    @GET
    @Path("tickets")
    public Response getTicketTypes() {
        return Response.status(200).entity(ticketManager.getTicketTypes()).build();
    }

    @POST
    @Path("tickets")
    @Consumes({MediaType.APPLICATION_JSON})
    public void createTicket(Ticket t){
        this.ticketManager.createTicket(t);
        parkingStateController.setTicketsQueue(this.ticketManager.getActiveTickets());
        parkingStateController.setSpotsQueue(this.spotManager.getOccupiedSpots());
    }

}
