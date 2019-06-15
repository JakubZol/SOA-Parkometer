package pl.soa.parkometer.ejb.impl.core;

import pl.soa.parkometer.ejb.core.ParkingStateControllerInterface;
import pl.soa.parkometer.ejb.impl.database.TicketManager;
import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.Ticket;

import javax.ejb.Singleton;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Singleton
public class ParkingStateController implements ParkingStateControllerInterface {


    private TicketManager ticketManager = new TicketManager();
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private List<Ticket> ticketsQuery;
    private List<Spot> spotsQuery = new LinkedList<>(); //tutaj później pobierać dane

    ParkingStateController(){
        System.out.println("I am here!!");
        ticketsQuery = ticketManager.getActiveTickets();
        for(Ticket t : ticketsQuery){
            System.out.println("Ticket: " + t.getTicketId());
        }
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("I am working");
                List<Ticket> delayed = ticketsQuery.stream().filter(spot -> spot.getExpiryDate().before(new Timestamp(new Date().getTime()))).collect(Collectors.toList());
                for(Ticket t: delayed){
                    System.out.println("Ticket " + t.getTicketId() + " has expired!");
                    deleteTicket(t.getTicketId());
                }
            }
        }, 0, 180, TimeUnit.SECONDS);
    }

    public void updateTicketQueue(Ticket t){
        System.out.println("Ticket id: " + t.getTicketId());
        if(!this.ticketsQuery.contains(t)) {
            System.out.println("added!");
            this.ticketsQuery.add(t);
            this.ticketsQuery.sort(Comparator.comparing(Ticket::getExpiryDate).reversed());
        }
        else{
            System.out.println("This ticket is already there!!!");
        }
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
