package pl.soa.parkometer.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Zone {
    private int zoneId;
    private String zoneName;
    private List<Worker> workers;
    private List<Spot> spots;

    @Id
    @Column(name = "zone_id", nullable = false)
    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    @Basic
    @Column(name = "zone_name", nullable = false, length = 20)
    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Zone zone = (Zone) o;

        if (zoneId != zone.zoneId) return false;
        if (zoneName != null ? !zoneName.equals(zone.zoneName) : zone.zoneName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = zoneId;
        result = 31 * result + (zoneName != null ? zoneName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "zone")
    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    @OneToMany(mappedBy = "zone")
    public List<Spot> getSpots() {
        return spots;
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }
}
