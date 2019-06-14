package pl.soa.parkometer.parkometer_mock;

import pl.soa.parkometer.entities.Spot;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@ManagedBean(name = "parkometer", eager = true)
@ApplicationScoped
public class ParkometerMock {

    private Spot currentSpot = this.getSpots().get(0);
    private Integer expiryTime;

    public List<Spot> getSpots(){
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/REST-1.0-SNAPSHOT/spots").request().accept(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus()!= 200){
            LinkedList<Spot> list = new LinkedList<>();
            Spot s = new Spot();
            s.setSpotName("ERROR " + response.getStatus());
            list.add(s);
            return list;
        }
        return response.readEntity(new GenericType<List<Spot>>(){});
    }

    public Spot getCurrentSpot() {
        return currentSpot;
    }

    public void setCurrentSpot(Spot spot) {
        this.currentSpot = spot;
    }

    public Integer getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Integer expiryTime) {
        this.expiryTime = expiryTime;
    }


}
