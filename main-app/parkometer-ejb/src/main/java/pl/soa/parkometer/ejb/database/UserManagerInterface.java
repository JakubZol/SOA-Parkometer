package pl.soa.parkometer.ejb.database;

import pl.soa.parkometer.entities.User;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface UserManagerInterface {

    public User getUserByLogin(String login);

    public List<User> getAllUsers(String login);

    public void updateUser(User u);
}
