package pl.soa.parkometer.parkometer_mock.data;

import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.Ticket;
import pl.soa.parkometer.entities.TicketType;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class DataFetcher {

    public static List<Spot> getSpots(){
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/REST-1.0-SNAPSHOT/spots").request().accept(MediaType.APPLICATION_JSON_TYPE).get();

        if (response.getStatus()!= 200){
            return new LinkedList<Spot>();
        }

        return response.readEntity(new GenericType<List<Spot>>(){});
    }

    public static List<TicketType> getTicketTypes(){
        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/REST-1.0-SNAPSHOT/tickets").request().accept(MediaType.APPLICATION_JSON_TYPE).get();

        if (response.getStatus()!= 200){
            return new LinkedList<TicketType>();
        }

        return response.readEntity(new GenericType<List<TicketType>>(){});
    }

    public static void sendTicket(Spot spot, TicketType type){
        Ticket t = new Ticket();
        t.setSpot(spot);
        t.setPurchaseDate(new Timestamp(System.currentTimeMillis()));
        t.setExpiryDate(new Timestamp(System.currentTimeMillis() + type.getTime() * 60 * 1000));
        t.setType(type);
        Client client = ClientBuilder.newClient();
        client.target("http://localhost:8080/REST-1.0-SNAPSHOT/tickets").request(MediaType.APPLICATION_JSON).post(Entity.entity(t, MediaType.APPLICATION_JSON));

    }
}
