
package pl.soa.parkometer.carpark_mock.soap_client.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ticket complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ticket">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="expiryDate" type="{http://soap.parkometer.soa.pl/}timestamp" minOccurs="0"/>
 *         &lt;element name="purchaseDate" type="{http://soap.parkometer.soa.pl/}timestamp" minOccurs="0"/>
 *         &lt;element name="spot" type="{http://soap.parkometer.soa.pl/}spot" minOccurs="0"/>
 *         &lt;element name="ticketType" type="{http://soap.parkometer.soa.pl/}ticketType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ticketId" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ticket", propOrder = {
    "expiryDate",
    "purchaseDate",
    "spot",
    "ticketType"
})
public class Ticket {

    protected Timestamp expiryDate;
    protected Timestamp purchaseDate;
    protected Spot spot;
    protected TicketType ticketType;
    @XmlAttribute(name = "ticketId", required = true)
    protected int ticketId;

    /**
     * Gets the value of the expiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link Timestamp }
     *     
     */
    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the value of the expiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Timestamp }
     *     
     */
    public void setExpiryDate(Timestamp value) {
        this.expiryDate = value;
    }

    /**
     * Gets the value of the purchaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link Timestamp }
     *     
     */
    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the value of the purchaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Timestamp }
     *     
     */
    public void setPurchaseDate(Timestamp value) {
        this.purchaseDate = value;
    }

    /**
     * Gets the value of the spot property.
     * 
     * @return
     *     possible object is
     *     {@link Spot }
     *     
     */
    public Spot getSpot() {
        return spot;
    }

    /**
     * Sets the value of the spot property.
     * 
     * @param value
     *     allowed object is
     *     {@link Spot }
     *     
     */
    public void setSpot(Spot value) {
        this.spot = value;
    }

    /**
     * Gets the value of the ticketType property.
     * 
     * @return
     *     possible object is
     *     {@link TicketType }
     *     
     */
    public TicketType getTicketType() {
        return ticketType;
    }

    /**
     * Sets the value of the ticketType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TicketType }
     *     
     */
    public void setTicketType(TicketType value) {
        this.ticketType = value;
    }

    /**
     * Gets the value of the ticketId property.
     * 
     */
    public int getTicketId() {
        return ticketId;
    }

    /**
     * Sets the value of the ticketId property.
     * 
     */
    public void setTicketId(int value) {
        this.ticketId = value;
    }

}
