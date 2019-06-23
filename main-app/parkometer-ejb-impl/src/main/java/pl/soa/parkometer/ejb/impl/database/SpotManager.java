package pl.soa.parkometer.ejb.impl.database;

import pl.soa.parkometer.database.DatabaseService;
import pl.soa.parkometer.ejb.database.SpotManagerInterface;
import pl.soa.parkometer.entities.Spot;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class SpotManager implements SpotManagerInterface {

    private final DatabaseService databaseService = new DatabaseService("SOA-Parkometer");
    private EntityManager entityManager = databaseService.getEntityManager();

    public Spot getSpotById(int id){

        entityManager.getTransaction().begin();
        Spot s = entityManager.find(Spot.class, id);
        entityManager.getTransaction().commit();

        return s;
    }

    public List<Spot> getFreeSpots(){
        TypedQuery<Spot> typedQuery = entityManager.createQuery("select s from  Spot s where s.vacancy = true", Spot.class);
        return typedQuery.getResultList();
    }

    public List<Spot> getAllSpots(){

        TypedQuery<Spot> typedQuery = entityManager.createQuery("select s from  Spot s", Spot.class);
        return typedQuery.getResultList();

    }

    public void updateSpot(Spot s){
        entityManager.getTransaction().begin();
        entityManager.merge(s);
        entityManager.getTransaction().commit();
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

    public List<Spot> getAllOccupiedSpots(){
        TypedQuery<Spot> typedQuery = entityManager.createQuery("select s from  Spot s where s.vacancy = false", Spot.class);
        return typedQuery.getResultList();
    }

    public List<Spot> getSpotsByZone(int zoneId){

        TypedQuery<Spot> typedQuery = entityManager.createQuery("select s from  Spot s where s.zone.zoneId = :id", Spot.class);
        typedQuery.setParameter("id", zoneId);
        return typedQuery.getResultList();

    }

}
