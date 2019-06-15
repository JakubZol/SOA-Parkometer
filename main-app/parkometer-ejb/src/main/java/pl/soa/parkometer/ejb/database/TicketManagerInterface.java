package pl.soa.parkometer.ejb.database;

import pl.soa.parkometer.entities.Ticket;
import pl.soa.parkometer.entities.TicketType;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.List;

@Remote
public interface TicketManagerInterface {

    public Ticket getTicketById(int id);

    public List<Ticket> getAllTickets();

    public void updateTicket(Ticket t);

    public void deleteTicket(int id);

    public void createTicket(Ticket t);

    public List<TicketType> getTicketTypes();

    public List<Ticket> getActiveTickets();
}
