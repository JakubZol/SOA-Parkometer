package pl.soa.parkometer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


//select * from spot where vacancy = false and spot_id not in (select spot_id from ticket where expiry_date > NOW());
@Entity
@XmlRootElement(name = "spot")
@NamedQuery(query = "Select s from Spot s where s.spotId not in (select t.spot.spotId from Ticket t where t.expiryDate > current_timestamp) and s.vacancy = false", name = "get occupied spots")
public class Spot implements Serializable {
    private int spotId;
    private String spotName;
    private boolean vacancy;
    private Timestamp occupationDate;
    private List<Ticket> tickets;
    private Zone zone;

    @Id
    @Column(name = "spot_id", nullable = false)
    @XmlAttribute(name = "spotId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    @Basic
    @Column(name = "spot_name", nullable = false, length = 10)
    @XmlAttribute(name = "spotName")
    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    @Basic
    @Column(name = "vacancy", nullable = false)
    @XmlAttribute(name = "isVacancy")
    public boolean isVacancy() {
        return vacancy;
    }

    public void setVacancy(boolean vacancy) {
        this.vacancy = vacancy;
    }

    @Basic
    @Column(name = "occupation_date", nullable = true)
    @XmlElement(name = "occupationDate")
    public Timestamp getOccupationDate() {
        return occupationDate;
    }

    public void setOccupationDate(Timestamp occupationDate) {
        this.occupationDate = occupationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Spot spot = (Spot) o;

        if (spotId != spot.spotId) return false;
        if (vacancy != spot.vacancy) return false;
        if (spotName != null ? !spotName.equals(spot.spotName) : spot.spotName != null) return false;
        if (occupationDate != null ? !occupationDate.equals(spot.occupationDate) : spot.occupationDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = spotId;
        result = 31 * result + (spotName != null ? spotName.hashCode() : 0);
        result = 31 * result + (vacancy ? 1 : 0);
        result = 31 * result + (occupationDate != null ? occupationDate.hashCode() : 0);
        return result;
    }

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY)
    @XmlElement(name = "tickets")
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "zone_id", referencedColumnName = "zone_id", nullable = false)
    @XmlElement(name = "zone")
    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
