package pl.soa.parkometer.ejb.impl.security;

import pl.soa.parkometer.ejb.database.UserManagerInterface;
import pl.soa.parkometer.ejb.security.UsersControllerInterface;
import pl.soa.parkometer.entities.User;

import javax.annotation.Resource;
import javax.ejb.*;
import java.security.Principal;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class UserController implements UsersControllerInterface {

    @EJB(lookup = "java:global/parkometer-ejb-impl-1.0-SNAPSHOT/UserManager")
    UserManagerInterface userManager;

    @Resource
    SessionContext ctx;



    public User getUser(){
        Principal principal = ctx.getCallerPrincipal();
        return userManager.getUserByLogin(principal.getName());
    }


    public List<User> getUsers(){
        if(ctx.isCallerInRole("Admin")) {
            String login = ctx.getCallerPrincipal().getName();
            return userManager.getAllUsers(login);
        }
        else{
            return new LinkedList<>();
        }
    }


    public void updateUsersPassword(String passwd){
        User u = this.getUser();
        u.setPasswd(passwd);
        userManager.updateUser(u);
    }

    public void updateOtherUserPassword(User u, String passwd){
        u.setPasswd(passwd);
        userManager.updateUser(u);
    }

}
