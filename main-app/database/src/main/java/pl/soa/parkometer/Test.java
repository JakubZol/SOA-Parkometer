package pl.soa.parkometer;

import pl.soa.parkometer.entities.Spot;
import pl.soa.parkometer.managers.SpotManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Test {

    public static void main(String[] args){
        SpotManager spotManager = new SpotManager();

        List<Spot> spots = spotManager.getOccupiedSpots();

        for(Spot s : spots){
            System.out.println(s.getSpotId() + ". " + s.getSpotName());
        }


    }
}
