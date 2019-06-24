package pl.soa.parkometer.ejb.impl.core;

import pl.soa.parkometer.ejb.core.ParkingStateControllerInterface;
import pl.soa.parkometer.ejb.database.SpotManagerInterface;
import pl.soa.parkometer.ejb.database.TicketManagerInterface;
import pl.soa.parkometer.ejb.jms.MessageServiceInterface;
import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.Ticket;
import pl.soa.parkometer.jms.Notification;

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

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/SpotManager")
    SpotManagerInterface spotManager;

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/TicketManager")
    TicketManagerInterface ticketManager;

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/MessageService")
    MessageServiceInterface messagePublisher;

    private Set<Ticket> ticketsQueue = new HashSet<>();
    private Set<Spot> spotsQueue = new HashSet<>(); //tutaj później pobierać dane


    @PostConstruct
    public void initialize() {
        ticketsQueue.addAll(ticketManager.getActiveTickets());
        spotsQueue.addAll(spotManager.getOccupiedSpots());
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                List<Ticket> delayed = ticketsQueue.stream().filter(ticket -> ticket.getExpiryDate().before(new Timestamp(new Date().getTime()))).collect(Collectors.toList());

                for (Ticket t : delayed) {
                    messagePublisher.sendMessage(new Notification(t));
                    deleteTicket(t.getTicketId());
                }

                List<Spot> unpaid = spotsQueue.stream().filter(spot -> System.currentTimeMillis() - spot.getOccupationDate().getTime() > 300000).collect(Collectors.toList());
                for (Spot s : unpaid) {
                    messagePublisher.sendMessage(new Notification(s));
                    deleteSpot(s.getSpotId());
                }
            }
        }, 0, 180, TimeUnit.SECONDS);
    }

    public void deleteSpot(int id){
        this.spotsQueue = this.spotsQueue.stream().filter(spot -> spot.getSpotId() != id).collect(Collectors.toSet());
    }

    public void deleteTicket(int id){
        this.ticketsQueue = this.ticketsQueue.stream().filter(ticket -> ticket.getTicketId() != id).collect(Collectors.toSet());
    }

    @Lock(LockType.WRITE)
    public void setTicketsQueue(List<Ticket> tickets){
        ticketsQueue.addAll(tickets);
    }

    @Lock(LockType.WRITE)
    public void setSpotsQueue(List<Spot> spots){
        spotsQueue.addAll(spots);

    }


}
