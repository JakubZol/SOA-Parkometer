package pl.soa.parkometer.ejb.impl.security;

import pl.soa.parkometer.ejb.database.SpotManagerInterface;
import pl.soa.parkometer.ejb.database.TicketManagerInterface;
import pl.soa.parkometer.ejb.database.UserManagerInterface;
import pl.soa.parkometer.ejb.security.DashboardControllerInterface;
import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.Ticket;
import pl.soa.parkometer.entities.User;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Stateful
public class DashboardController implements DashboardControllerInterface {

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/SpotManager")
    SpotManagerInterface spotManager;

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/UserManager")
    UserManagerInterface userManager;

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/TicketManager")
    TicketManagerInterface ticketManager;

    @Resource
    SessionContext ctx;


    @RolesAllowed({"Admin"})
    @Lock(LockType.READ)
    public List<Spot> getAllSpots() {
        return spotManager.getAllSpots();
    }

    @RolesAllowed({"Employee"})
    @Lock(LockType.READ)
    public List<Spot> getSpotsByZone(int zoneId) {
        return spotManager.getSpotsByZone(zoneId);
    }

    @RolesAllowed({"Admin","Employee"})
    @Lock(LockType.READ)
    public List<Spot> getSpotsForDashboard() {
        Principal principal = ctx.getCallerPrincipal();
        if(ctx.isCallerInRole("Admin")){
            return this.getAllSpots();
        }
        else if(ctx.isCallerInRole("Employee")){
            String login = ctx.getCallerPrincipal().getName();
            User u = userManager.getUserByLogin(login);
            return this.getSpotsByZone(u.getZone().getZoneId());
        }

        return new LinkedList<>();
    }


    @RolesAllowed({"Admin", "Employee"})
    @Lock(LockType.READ)
    public List<Ticket> getTicketsBySpot(int spotId){
        return ticketManager.getTicketsBySpot(spotId);
    }


}
