package pl.soa.parkometer.ejb.security;

import pl.soa.parkometer.entities.User;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface UsersControllerInterface {

    public User getUser();

    public List<User> getUsers();

    public void updateUsersPassword(String passwd);

    public void updateOtherUserPassword(User u, String passwd);
}
