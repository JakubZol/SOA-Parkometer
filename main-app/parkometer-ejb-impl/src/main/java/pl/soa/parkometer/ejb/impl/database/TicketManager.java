package pl.soa.parkometer.ejb.impl.database;

import pl.soa.parkometer.database.DatabaseService;
import pl.soa.parkometer.ejb.database.TicketManagerInterface;
import pl.soa.parkometer.entities.Ticket;
import pl.soa.parkometer.entities.TicketType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class TicketManager implements TicketManagerInterface {

    private final DatabaseService databaseService = new DatabaseService("SOA-Parkometer");
    private EntityManager entityManager = databaseService.getEntityManager();

    public Ticket getTicketById(int id){

        entityManager.getTransaction().begin();
        Ticket t = entityManager.find(Ticket.class, id);
        entityManager.getTransaction().commit();

        return t;
    }

    public List<Ticket> getAllTickets(){

        TypedQuery<Ticket> typedQuery = entityManager.createQuery("select t from  Ticket t", Ticket.class);
        return typedQuery.getResultList();

    }

    public void updateTicket(Ticket s){ System.out.println("TO DO!"); }

    public void deleteTicket(int id){
        entityManager.getTransaction().begin();
        Ticket t = entityManager.find(Ticket.class, id);
        entityManager.remove(t);
        entityManager.getTransaction().commit();
    }

    public void createTicket(Ticket t){
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(t);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            System.out.println(e.toString());
            entityManager.getTransaction().rollback();
        }
    }

    public List<TicketType> getTicketTypes(){
        TypedQuery<TicketType> typedQuery = entityManager.createQuery("select tt from  TicketType tt", TicketType.class);
        return typedQuery.getResultList();
    }

    public List<Ticket> getActiveTickets(){
        TypedQuery<Ticket> typedQuery = entityManager.createQuery("select t from Ticket t where t.expiryDate > current_timestamp ", Ticket.class);
        return typedQuery.getResultList();
    }
}
