package pl.soa.parkometer.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class RestApplication extends Application {

    private Set<Class<?>> classes = new HashSet<Class<?>>();

    public RestApplication() {
        classes.add(SpotsController.class);
        classes.add(TicketsController.class);
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

}
