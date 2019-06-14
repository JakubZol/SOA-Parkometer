package pl.soa.parkometer.rest;


import pl.soa.parkometer.ejb.database.SpotManagerInterface;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/")
@Produces("application/json")
@Stateless
public class SpotsController {

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/SpotManager")
    SpotManagerInterface spotManager;


    @GET
    @Path("spots/{id}")
    public Response getSpotById(@PathParam("id") int id) {
        return Response.status(200).entity(spotManager.getSpotById(id)).build();
    }

    @GET
    @Path("spots")
    public Response getAllSpots(){
        return Response.status(200).entity(spotManager.getAllSpots()).build();
    }

}
