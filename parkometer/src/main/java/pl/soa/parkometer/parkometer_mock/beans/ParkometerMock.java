package pl.soa.parkometer.parkometer_mock.beans;

import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.TicketType;
import pl.soa.parkometer.parkometer_mock.data.DataFetcher;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import java.util.List;
import java.util.stream.Collectors;

@ManagedBean(name = "parkometer", eager = true)
@ApplicationScoped
public class ParkometerMock {


    private List<Spot> spots = DataFetcher.getSpots();
    private List<TicketType> ticketTypes = DataFetcher.getTicketTypes();
    private Integer currentSpotId = this.spots.size() > 0 ? this.spots.get(0).getSpotId() : null;
    private Integer currentTypeId = this.ticketTypes.size() > 0 ? this.ticketTypes.get(0).getTypeId() : null;

    public List<Spot> getSpots(){
        return this.spots;
    }

    public List<TicketType> getTicketTypes() {
        return ticketTypes;
    }


    public Integer getCurrentSpotId() {
        return currentSpotId;
    }

    public void setCurrentSpotId(Integer spotId) {
        this.currentSpotId = spotId;
    }

    public Integer getCurrentTypeId() {
        return currentTypeId;
    }

    public void setCurrentTypeId(Integer currentTypeId) {
        this.currentTypeId = currentTypeId;
    }

    public void buyTicket(){
        if(this.currentSpotId != null && this.currentTypeId != null) {
            List<Spot> finalSpots = this.spots.stream().filter(spot -> spot.getSpotId() == this.currentSpotId).collect(Collectors.toList());
            Spot currentSpot = finalSpots.get(0);

            List<TicketType> finalTypes = this.ticketTypes.stream().filter(spot -> spot.getTypeId() == this.currentTypeId).collect(Collectors.toList());
            TicketType currentType = finalTypes.get(0);

            DataFetcher.sendTicket(currentSpot, currentType);
        }
    }

}
