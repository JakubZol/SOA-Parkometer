package pl.soa.parkometer.rest;

import pl.soa.parkometer.ejb.database.TicketManagerInterface;
import pl.soa.parkometer.entities.Ticket;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")
@Produces("application/json")
@Stateless
public class TicketsController {

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/TicketManager")
    TicketManagerInterface ticketManager;

    @PUT
    @Path("/tickets")
    public void createTicket(Ticket t){
        this.ticketManager.createTicket(t);
    }

}
