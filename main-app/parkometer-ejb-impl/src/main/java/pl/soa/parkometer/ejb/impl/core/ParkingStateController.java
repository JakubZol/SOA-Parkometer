package pl.soa.parkometer.ejb.impl.core;

import pl.soa.parkometer.ejb.core.ParkingStateControllerInterface;
import pl.soa.parkometer.ejb.database.TicketManagerInterface;
import pl.soa.parkometer.ejb.impl.database.TicketManager;
import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.Ticket;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Startup
@Singleton
public class ParkingStateController implements ParkingStateControllerInterface {


    private Set<Ticket> ticketsQueue = new HashSet<>();
    private List<Spot> spotsQuery = new LinkedList<>(); //tutaj później pobierać dane


    @PostConstruct
    public void initialize() {
        System.out.println("Hello!");
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("Run!!");
                List<Ticket> delayed = ticketsQueue.stream().filter(ticket -> ticket.getExpiryDate().before(new Timestamp(new Date().getTime()))).collect(Collectors.toList());
                for (Ticket t : delayed) {
                    System.out.println("Ticket " + t.getTicketId() + " has expired!");
                    deleteTicket(t.getTicketId());
                }
            }
        }, 0, 180, TimeUnit.SECONDS);
    }

    public void updateTicketQueue(Ticket t){
        System.out.println("Adding ticket...");
        if(!this.ticketsQueue.contains(t)) {
            this.ticketsQueue.add(t);
            //this.ticketsQueue.sort(Comparator.comparing(Ticket::getExpiryDate).reversed());
            System.out.println(t.getTicketId() + " added!");
        }
        else{
            System.out.println("Nope!");
        }

    }

    public void updateSpotQueue(Spot s){
        this.spotsQuery.add(s);
        this.spotsQuery.sort(Comparator.comparing(Spot::getOccupationDate).reversed());

    }

    public void deleteSpot(int id){
        this.spotsQuery = this.spotsQuery.stream().filter(spot -> spot.getSpotId() != id).collect(Collectors.toList());
    }

    public void deleteTicket(int id){
        this.ticketsQueue = this.ticketsQueue.stream().filter(ticket -> ticket.getTicketId() != id).collect(Collectors.toSet());
    }

    @Lock(LockType.WRITE)
    public void setTicketsQueue(List<Ticket> tickets){
        ticketsQueue.addAll(tickets);
        System.out.println(ticketsQueue.size());
    }

    public void sendMessage() { //TO DO
    }

    public void resetTimer(){ //TO DO
    }

}
