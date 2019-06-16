package pl.soa.parkometer.parkometer_mock.beans;

import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.TicketType;
import pl.soa.parkometer.parkometer_mock.data.DataFetcher;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "parkometer", eager = true)
@SessionScoped
public class ParkometerMock implements Serializable {


    private List<Spot> spots = DataFetcher.getSpots();
    private List<TicketType> ticketTypes = DataFetcher.getTicketTypes();
    private int currentSpotId;
    private int currentTypeId;

    public List<Spot> getSpots(){
        return this.spots;
    }

    public List<TicketType> getTicketTypes() {
        return ticketTypes;
    }

    public String transformTime(int minutes){
        if(minutes >= 1440){
            String rest = minutes % (24 * 60) == 0 ? "" : minutes % (24 * 60) + "minute(s)";
            return Math.floor(minutes / (24 * 60)) + " day(s)";
        }
        else if(minutes >= 60){
            String rest = minutes % 60 == 0 ? "" : minutes % 60 + "minute(s)";
            return Math.floor(minutes / 60) + " hour(s)" + rest;
        }
        else return minutes + " minutes";
    }


    public int getCurrentSpotId() {
        return currentSpotId;
    }

    public void setCurrentSpotId(int spotId) {
        System.out.println("Setting spote id to: " + spotId);
        this.currentSpotId = spotId;
    }

    public int getCurrentTypeId() {
        return currentTypeId;
    }

    public void setCurrentTypeId(int typeId) {
        System.out.println("Setting type id to: " + typeId);
        this.currentTypeId = typeId;
    }

    public void buyTicket(){
        System.out.println(currentSpotId);
        System.out.println(currentTypeId);
        if(this.currentSpotId != 0 && this.currentTypeId != 0) {
            List<Spot> finalSpots = this.spots.stream().filter(spot -> spot.getSpotId() == this.currentSpotId).collect(Collectors.toList());
            this.spots = this.spots.stream().filter(spot -> spot.getSpotId() != this.currentSpotId).collect(Collectors.toList());
            Spot currentSpot = finalSpots.get(0);

            List<TicketType> finalTypes = this.ticketTypes.stream().filter(spot -> spot.getTypeId() == this.currentTypeId).collect(Collectors.toList());
            TicketType currentType = finalTypes.get(0);

            DataFetcher.sendTicket(currentSpot, currentType);
        }
    }

}
