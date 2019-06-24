package pl.soa.parkometer.ejb.impl.security;

import pl.soa.parkometer.ejb.database.UserManagerInterface;
import pl.soa.parkometer.ejb.security.NotificationsControllerInterface;
import pl.soa.parkometer.entities.User;
import pl.soa.parkometer.jms.Notification;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class NotificationsController implements NotificationsControllerInterface {

    @Resource
    SessionContext ctx;

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/UserManager")
    UserManagerInterface userManager;

    private List<Notification> notifications = new ArrayList<>();

    @RolesAllowed({"Admin"})
    @Override
    public List<Notification> getAllNotifications() {
        return notifications;
    }

    @RolesAllowed({"Admin", "Employee"})
    @Override
    public List<Notification> getNotificationsByZoneId(int zoneId) {
        return notifications.stream().filter(notification -> notification.getZone().getZoneId() == zoneId).collect(Collectors.toList());
    }

    @RolesAllowed({"Admin", "Employee"})
    @Override
    public List<Notification> getNotificationsForDashboard() {
        Principal principal = ctx.getCallerPrincipal();
        if(ctx.isCallerInRole("Admin")){
            return this.getAllNotifications();
        }
        else if(ctx.isCallerInRole("Employee")){
            String login = ctx.getCallerPrincipal().getName();
            User u = userManager.getUserByLogin(login);
            return this.getNotificationsByZoneId(u.getZone().getZoneId());
        }
        else {
            return new ArrayList<>();
        }
    }

    @PermitAll
    public void addNotification(Notification n){
        this.notifications.add(n);

    }

}
