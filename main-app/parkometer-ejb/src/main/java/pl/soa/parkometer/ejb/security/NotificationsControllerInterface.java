package pl.soa.parkometer.ejb.security;

import pl.soa.parkometer.jms.Notification;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface NotificationsControllerInterface {

    public List<Notification> getNotificationsByZoneId(int zoneId);

    public List<Notification> getAllNotifications();

    public List<Notification> getNotificationsForDashboard();

    public void addNotification(Notification n);

    public void deleteNotification(Notification n);


}
