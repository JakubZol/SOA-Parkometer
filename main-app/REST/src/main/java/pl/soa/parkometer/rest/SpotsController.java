package pl.soa.parkometer.rest;


import pl.soa.parkometer.ejb.database.SpotManagerInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/")
@Produces("application/json")
@Stateless
public class SpotsController {

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/SpotManager")
    SpotManagerInterface spotManager;


    @GET
    @Path("spots")
    public Response getSpots(){
        return Response.status(200).entity(spotManager.getOccupiedSpots()).build();
    }

}
