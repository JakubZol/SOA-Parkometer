package pl.soa.parkometer.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class SpotsRestApplication extends Application {

    private Set<Class<?>> classes = new HashSet<Class<?>>();

    public SpotsRestApplication() {
        classes.add(SpotsController.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

}
