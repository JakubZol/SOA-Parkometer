package pl.soa.parkometer.ejb.impl.core;

import pl.soa.parkometer.ejb.core.ParkingStateControllerInterface;
import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.Ticket;

import javax.ejb.Singleton;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;

@Singleton
public class ParkingStateController implements ParkingStateControllerInterface {

    private Timer timer;
    private List<Ticket> ticketsQuery = new LinkedList<>(); //tutaj później pobierać dane
    private List<Spot> spotsQuery = new LinkedList<>(); //tu też

    public void updateTicketQueue(Ticket t){
        this.ticketsQuery.add(t);
        this.ticketsQuery.sort(Comparator.comparing(Ticket::getExpiryDate).reversed());

    }

    public void updateSpotQueue(Spot s){
        this.spotsQuery.add(s);
        this.spotsQuery.sort(Comparator.comparing(Spot::getOccupationDate).reversed());

    }

    public void deleteSpot(int id){
        this.spotsQuery = this.spotsQuery.stream().filter(spot -> spot.getSpotId() != id).collect(Collectors.toList());
        System.out.println("deleted spot with id: " + id);
    }

    public void deleteTicket(int id){
        this.ticketsQuery = this.ticketsQuery.stream().filter(ticket -> ticket.getTicketId() != id).collect(Collectors.toList());
        System.out.println("deleted ticket with id: " + id);
    }

    public void sendMessage(){
        System.out.println("Send Message");
    }

    public void resetTimer(){
        System.out.println("Reset timer");
    }
}
