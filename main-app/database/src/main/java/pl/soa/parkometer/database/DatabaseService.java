package pl.soa.parkometer.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseService {

    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;

    public DatabaseService(String persistenceUnit){
        this.entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnit);
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
