package pl.soa.parkometer.ejb.impl.security;

import org.jboss.security.Base64Utils;
import org.jboss.util.Base64;
import pl.soa.parkometer.ejb.database.UserManagerInterface;
import pl.soa.parkometer.ejb.security.UsersControllerInterface;
import pl.soa.parkometer.entities.User;

import javax.annotation.Resource;
import javax.ejb.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
        try {
            String newPasswd = MD5encoding(passwd);
            u.setPasswd(newPasswd);
            userManager.updateUser(u);
        }
        catch (NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateOtherUserPassword(User u, String passwd){
        try {
            String newPasswd = MD5encoding(passwd);
            u.setPasswd(newPasswd);
            userManager.updateUser(u);
        }
        catch (NoSuchAlgorithmException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private String MD5encoding(String passwd) throws NoSuchAlgorithmException{
        MessageDigest md = null;
        md = MessageDigest.getInstance("MD5");
        byte[] passwordBytes = passwd.getBytes();
        byte[] hash = md.digest(passwordBytes);
        return Base64.encodeBytes(hash);
    }

}
