package pl.soa.parkometer.parkometer_mock.beans;

import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.TicketType;
import pl.soa.parkometer.parkometer_mock.data.DataRESTInitializer;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "parkometer", eager = true)
@RequestScoped
public class ParkometerMock implements Serializable {


    private String currentSpotId;
    private String currentTypeId;
    private List<Spot> spots = DataRESTInitializer.getSpots();
    private List<TicketType> ticketTypes = DataRESTInitializer.getTicketTypes();

    public List<Spot> getSpots(){
        return this.spots;
    }

    public List<TicketType> getTicketTypes() {
        return ticketTypes;
    }

    public String minutesToTimeString(int minutes){
        if(minutes >= 1440){
            return Math.floor((float)minutes / (24 * 60)) + " day(s)";
        }
        else if(minutes >= 60){
            String rest = minutes % 60 == 0 ? "" : minutes % 60 + "minute(s)";
            return Math.floor((float)minutes / 60) + " hour(s)" + rest;
        }
        else return minutes + " minutes";
    }

    public String getCurrentSpotId() {
        return currentSpotId;
    }

    public String getCurrentTypeId() {
        return currentTypeId;
    }

    public void setCurrentSpotId(String spotId) {
        this.currentSpotId = spotId;
    }


    public void setCurrentTypeId(String typeId) {
        this.currentTypeId = typeId;
    }

    public void buyTicket(){
        int spotId = Integer.parseInt(currentSpotId);
        int typeId = Integer.parseInt(currentTypeId);
        if(spotId != 0 && typeId != 0) {
            List<Spot> finalSpots = this.spots.stream().filter(spot -> spot.getSpotId() == spotId).collect(Collectors.toList());
            this.spots = this.spots.stream().filter(spot -> spot.getSpotId() != spotId).collect(Collectors.toList());
            Spot currentSpot = finalSpots.get(0);

            List<TicketType> finalTypes = this.ticketTypes.stream().filter(spot -> spot.getTypeId() == typeId).collect(Collectors.toList());
            TicketType currentType = finalTypes.get(0);

            DataRESTInitializer.sendTicket(currentSpot, currentType);
        }
    }

}
