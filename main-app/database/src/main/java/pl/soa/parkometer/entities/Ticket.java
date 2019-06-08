package pl.soa.parkometer.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Ticket {
    private int ticketId;
    private Timestamp purchaseDate;
    private Timestamp expiryDate;
    private TicketType type;
    private Spot spot;

    @Id
    @Column(name = "ticket_id", nullable = false)
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Basic
    @Column(name = "purchase_date", nullable = false)
    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Basic
    @Column(name = "expiry_date", nullable = false)
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

        if (ticketId != ticket.ticketId) return false;
        if (purchaseDate != null ? !purchaseDate.equals(ticket.purchaseDate) : ticket.purchaseDate != null)
            return false;
        if (expiryDate != null ? !expiryDate.equals(ticket.expiryDate) : ticket.expiryDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ticketId;
        result = 31 * result + (purchaseDate != null ? purchaseDate.hashCode() : 0);
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    @ManyToOne
    @JoinColumn(name = "spot_id", referencedColumnName = "spot_id", nullable = false)
    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }
}
