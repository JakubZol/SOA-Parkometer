package pl.soa.parkometer.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@NamedQuery(query = "Select s from Spot s where vacancy = false", name = "get occupied spots")
public class Spot implements Serializable {
    private int spotId;
    private String spotName;
    private boolean vacancy;
    private Timestamp occupationDate;
    private List<Ticket> tickets;
    private Zone zone;

    @Id
    @Column(name = "spot_id", nullable = false)
    public int getSpotId() {
        return spotId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    @Basic
    @Column(name = "spot_name", nullable = false, length = 10)
    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    @Basic
    @Column(name = "vacancy", nullable = false)
    public boolean isVacancy() {
        return vacancy;
    }

    public void setVacancy(boolean vacancy) {
        this.vacancy = vacancy;
    }

    @Basic
    @Column(name = "occupation_date", nullable = true)
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

    @OneToMany(mappedBy = "spot")
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @ManyToOne
    @JoinColumn(name = "zone_id", referencedColumnName = "zone_id", nullable = false)
    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
