package pl.soa.parkometer.managers;

import pl.soa.parkometer.database.DatabaseService;
import pl.soa.parkometer.entities.Spot;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class SpotManager {

    private final DatabaseService databaseService = new DatabaseService("SOA-Parkometer");
    private EntityManager entityManager = databaseService.getEntityManager();

    public Spot getSpotById(int id){

        entityManager.getTransaction().begin();
        Spot s = entityManager.find(Spot.class, id);
        entityManager.getTransaction().commit();

        return s;
    }

    public List<Spot> getAllSpots(){

        TypedQuery<Spot> typedQuery = entityManager.createQuery("select s from  Spot s", Spot.class);
        return typedQuery.getResultList();

    }

    public void UpdateSpots(){
        System.out.println("TO DO!");
    }

    public void deleteSpot(int id){
        entityManager.getTransaction().begin();
        Spot s = entityManager.find(Spot.class, id);
        entityManager.remove(s);
        entityManager.getTransaction().commit();
    }

    public List<Spot> getOccupiedSpots(){
        Query query = entityManager.createNamedQuery("get occupied spots");
        return query.getResultList();
    }

}
