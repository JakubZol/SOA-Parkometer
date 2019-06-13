package pl.soa.parkometer.rest;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

@Path("/")
@Produces("application/json")
@Stateless
public class SpotsController {

    /*@EJB //(lookup = "java:global/parking-ejb-impl-1.0/ParkingSpotsStorage")
*/



    @GET
    @Path("spots")
    public Response getSpots() {
        return Response.status(200).entity("TO DO").build();
    }

}
