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
@XmlRootElement(name = "ticketType")
@Table(name = "ticket_type", schema = "public", catalog = "parkometer")
public class TicketType implements Serializable {
    private int typeId;
    private String time;
    private int price;
    private List<Ticket> tickets;

    @Id
    @Column(name = "type_id", nullable = false)
    @XmlAttribute(name = "typeId")
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "time", nullable = false)
    @XmlAttribute(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Basic
    @Column(name = "price", nullable = false)
    @XmlAttribute(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketType that = (TicketType) o;

        if (typeId != that.typeId) return false;
        if (price != that.price) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = typeId;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    @XmlAttribute(name = "tickets")
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
