package pl.soa.parkometer.ejb.impl.database;

import pl.soa.parkometer.database.DatabaseService;
import pl.soa.parkometer.ejb.database.UserManagerInterface;
import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UserManager implements UserManagerInterface {

    private final DatabaseService databaseService = new DatabaseService("SOA-Parkometer");
    private EntityManager entityManager = databaseService.getEntityManager();

    public User getUserByLogin(String user_login){

        TypedQuery<User> typedQuery = entityManager.createQuery("select u from User u where u.login = :login", User.class);
        return typedQuery.setParameter("login", user_login).getSingleResult();

    }

    public List<User> getAllUsers(String userLogin){

        TypedQuery<User> typedQuery = entityManager.createQuery("select u from User u where u.login != :login", User.class);
        return typedQuery.setParameter("login", userLogin).getResultList();
    }

    public void updateUsersPassword(User u, String passwd){

        entityManager.getTransaction().begin();
        u.setPasswd(passwd);
        entityManager.getTransaction().commit();

    }


}

