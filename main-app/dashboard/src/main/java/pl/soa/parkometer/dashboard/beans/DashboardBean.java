package pl.soa.parkometer.dashboard.beans;

import pl.soa.parkometer.ejb.security.DashboardControllerInterface;
import pl.soa.parkometer.ejb.security.NotificationsControllerInterface;
import pl.soa.parkometer.ejb.security.UsersControllerInterface;
import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.Ticket;
import pl.soa.parkometer.entities.User;
import pl.soa.parkometer.jms.Notification;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SessionScoped
@ManagedBean(name = "dashboard", eager = true)
public class DashboardBean implements Serializable {

    @EJB(lookup="java:global/parkometer-ejb-impl-1.0-SNAPSHOT/UserController")
    UsersControllerInterface usersController;

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/DashboardController")
    DashboardControllerInterface dashboardController;

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/NotificationsController")
    private NotificationsControllerInterface notificationsController;

    private String currentUserPassword;
    private String currentUserPasswordRepeated;
    private String currentOtherPassword;
    private String currentOtherPasswordRepeated;
    private String currentUserLogin;
    private List<Ticket> tickets;
    private Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());


    public void setCurrentUserPassword(String currentUserPassword) {
        this.currentUserPassword = currentUserPassword;
    }

    public String getCurrentUserPassword(){
        return currentUserPassword;
    }

    public String getCurrentUserPasswordRepeated(){
        return currentUserPasswordRepeated;
    }

    public void setCurrentUserLogin(String currentUserLogin) {
        this.currentUserLogin = currentUserLogin;
    }

    public void setCurrentUserPasswordRepeated(String currentUserPasswordRepeated) {
        this.currentUserPasswordRepeated = currentUserPasswordRepeated;
    }

    public String getCurrentUserLogin() {
        return currentUserLogin;
    }

    public User getUser(){
        return usersController.getUser();
    }

    public List<Spot> getSpots(){
        return dashboardController.getSpotsForDashboard().stream().sorted(Comparator.comparing(Spot::getSpotId)).collect(Collectors.toList());
    }

    public List<User> getUsers(){
        return usersController.getUsers();
    }

    public void updatePassword(){
        if(currentUserPassword != null && currentUserPassword.equals(currentUserPasswordRepeated)){
            usersController.updateUsersPassword(currentUserPassword);
            currentUserPassword = null;
            currentUserPasswordRepeated = null;
        }
    }

    public String getCurrentOtherPassword() {
        return currentOtherPassword;
    }

    public String getCurrentOtherPasswordRepeated() {
        return currentOtherPasswordRepeated;
    }

    public void setCurrentOtherPassword(String currentOtherPassword) {
        this.currentOtherPassword = currentOtherPassword;
    }

    public void setCurrentOtherPasswordRepeated(String currentOtherPasswordRepeated) {
        this.currentOtherPasswordRepeated = currentOtherPasswordRepeated;
    }

    public void updateUsersPassword(){
        if(currentUserLogin != null && currentOtherPassword != null && currentOtherPassword.equals(currentOtherPasswordRepeated)) {
            List<User> users = this.getUsers();
            User currentUser = users.size() > 0 ? users.stream().filter(user -> user.getLogin().equals(currentUserLogin)).collect(Collectors.toList()).get(0) : null;
            if(currentUser != null) {
                System.out.println(currentUser.getLogin());
                usersController.updateOtherUserPassword(currentUser, currentOtherPassword);
                currentOtherPassword = null;
                currentOtherPasswordRepeated = null;
            }
        }
    }

    public String fetchTickets(int spotId){
        this.tickets = dashboardController.getTicketsBySpot(spotId);
        if(tickets.size() > 0){
            return "tickets";
        }
        else{
            return "";
        }
    }

    public List<Notification> getNotifications(){
        return notificationsController.getNotificationsForDashboard();
    }

    public List<Ticket> getTickets(){
        return this.tickets;
    }

    public Timestamp getCurrentTimestamp() {
        return currentTimestamp;
    }

    public void logout(){
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteNotification(Notification n){
        notificationsController.deleteNotification(n);
    }
}
