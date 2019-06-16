package pl.soa.parkometer.dashboard.beans;

import pl.soa.parkometer.ejb.security.DashboardControllerInterface;
import pl.soa.parkometer.ejb.security.UsersControllerInterface;
import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.User;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@ManagedBean(name = "dashboard", eager = true)
public class DashboardBean implements Serializable {

    @EJB(lookup="java:global/parkometer-ejb-impl-1.0-SNAPSHOT/UserController")
    UsersControllerInterface usersController;

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/DashboardController")
    DashboardControllerInterface dashboardController;

    private String currentPassword;
    private String currentPasswordRepeated;
    private User currentUser;

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setCurrentPasswordRepeated(String currentPasswordRepeated) {
        this.currentPasswordRepeated = currentPasswordRepeated;
    }

    public User getUser(){
        return usersController.getUser();
    }

    public List<Spot> getSpots(){
        return dashboardController.getSpotsForDashboard();
    }

    public List<User> getUsers(){
        return usersController.getUsers();
    }

    public void updatePassword(){
        if(currentPassword.equals(currentPasswordRepeated) && currentUser != null){
            usersController.updateUsersPassword(currentUser, currentPassword);
            System.out.println("Password changed!");
        }
        System.out.println("NOPE!!");    }

}
