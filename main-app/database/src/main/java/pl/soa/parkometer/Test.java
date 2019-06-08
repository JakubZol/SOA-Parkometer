package pl.soa.parkometer;

import pl.soa.parkometer.entities.Spot;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {

    public static void main(String[] args){
        System.out.println("Testing database connection!!!");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SOA-Parkometer");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Spot s = entityManager.find(Spot.class, 1);
        entityManager.getTransaction().commit();

        System.out.println(s.getSpotId());
        System.out.println(s.getSpotName());
        System.out.println(s.getZone().getZoneName());


    }
}
