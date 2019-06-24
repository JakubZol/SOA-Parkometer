package pl.soa.parkometer.jms;

import org.jgroups.protocols.SYM_ENCRYPT;
import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.Ticket;
import pl.soa.parkometer.entities.Zone;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {

    private Spot spot;
    private Ticket ticket;
    private Zone zone;
    private Date date;

    public Notification(Spot spot){
        this.spot = spot;
        this.zone = spot.getZone();
        this.date = new Date();
    }

    public Notification(Ticket ticket){
        this.ticket = ticket;
        this.zone = ticket.getSpot().getZone();
        this.date = new Date();
    }

    public Date getDate(){
        return this.date;
    }

    public Spot getSpot() {
        return spot;
    }

    public Zone getZone(){
        return zone;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getMessagge(){
        if(spot != null){
            return "Spot " + spot.getSpotName() + ", " + zone.getZoneName() + " is occupied without a ticket for at least 5 minutes!";
        }
        else if(ticket != null){
            return "Ticket " + ticket.getTicketId() + " for spot " + ticket.getSpot().getSpotName() + ", " + zone.getZoneName() + " has expired at " + ticket.getExpiryDate() + ".";
        }
        else{
            return "This notification is empty!";
        }
    }

    @Override
    public String toString(){
        return this.getMessagge();
    }

    @Override
    public boolean equals(Object o){
        Notification n = (Notification) o;
        if(this.date.equals(n.date)) {
            if (n.getSpot() != null) {
                return n.getSpot().getSpotId() == this.getSpot().getSpotId();
            }
            else if (n.getTicket() != null) {
                return n.getTicket().getTicketId() == this.getTicket().getTicketId();
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}
