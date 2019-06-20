package pl.soa.parkometer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@XmlRootElement(name = "ticket")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Ticket implements Serializable {
    private int ticketId;
    private Timestamp purchaseDate;
    private Timestamp expiryDate;
    private TicketType type;
    private Spot spot;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false)
    @XmlAttribute(name = "ticketId")
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Basic
    @Column(name = "purchase_date", nullable = false)
    @XmlElement(name = "purchaseDate")
    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Basic
    @Column(name = "expiry_date", nullable = false)
    @XmlElement(name = "expiryDate")
    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (ticketId != ticket.ticketId && ticket.getTicketId() != 0 && this.getTicketId() != 0) return false;
        if(ticket.getSpot().getSpotId() == this.getSpot().getSpotId()) {
            if (purchaseDate != null ? !purchaseDate.equals(ticket.purchaseDate) : ticket.purchaseDate != null)
                return false;
            if (expiryDate != null ? !expiryDate.equals(ticket.expiryDate) : ticket.expiryDate != null) return false;

            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = ticketId;
        result = 31 * result + (purchaseDate != null ? purchaseDate.hashCode() : 0);
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        return result;
    }

    //@JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    @XmlElement(name = "ticketType")
    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    @JsonIgnore
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "spot_id", referencedColumnName = "spot_id", nullable = false)
    @XmlElement(name = "spot")
    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }
}
