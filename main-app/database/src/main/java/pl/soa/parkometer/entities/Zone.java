package pl.soa.parkometer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "zone", schema = "public", catalog = "admin")
@XmlRootElement(name="zone")
public class Zone implements Serializable {
    private int zoneId;
    private String zoneName;
    private List<User> users;

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
    @XmlAttribute(name="zoneName")
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
        Zone that = (Zone) o;
        return zoneId == that.zoneId &&
                Objects.equals(zoneName, that.zoneName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zoneId, zoneName);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "zone")
    @XmlTransient
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
