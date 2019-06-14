package pl.soa.parkometer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Entity
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "worker")
public class Zone implements Serializable {
    private int zoneId;
    private String zoneName;
    private List<Worker> workers;
    private List<Spot> spots;

    @Id
    @Column(name = "zone_id", nullable = false)
    @XmlAttribute(name = "zoneId")
    public int getZoneId() {
        return zoneId;
    }

    public void setZoneId(int zoneId) {
        this.zoneId = zoneId;
    }

    @Basic
    @Column(name = "zone_name", nullable = false, length = 20)
    @XmlAttribute(name = "zoneName")
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

    @JsonIgnore
    @OneToMany(mappedBy = "zone")
    @XmlAttribute(name = "workers")
    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "zone")
    @XmlAttribute(name = "spots")
    public List<Spot> getSpots() {
        return spots;
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }
}
