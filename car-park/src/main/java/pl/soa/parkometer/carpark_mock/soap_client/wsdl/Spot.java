
package pl.soa.parkometer.carpark_mock.soap_client.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for spot complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="spot">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="occupationDate" type="{http://soap.parkometer.soa.pl/}timestamp" minOccurs="0"/>
 *         &lt;element name="zone" type="{http://soap.parkometer.soa.pl/}zone" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="spotId" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="spotName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="isVacancy" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "spot", propOrder = {
    "occupationDate",
    "zone"
})
public class Spot {

    protected Timestamp occupationDate;
    protected Zone zone;
    @XmlAttribute(name = "spotId", required = true)
    protected int spotId;
    @XmlAttribute(name = "spotName")
    protected String spotName;
    @XmlAttribute(name = "isVacancy", required = true)
    protected boolean isVacancy;

    /**
     * Gets the value of the occupationDate property.
     * 
     * @return
     *     possible object is
     *     {@link Timestamp }
     *     
     */
    public Timestamp getOccupationDate() {
        return occupationDate;
    }

    /**
     * Sets the value of the occupationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Timestamp }
     *     
     */
    public void setOccupationDate(Timestamp value) {
        this.occupationDate = value;
    }

    /**
     * Gets the value of the zone property.
     * 
     * @return
     *     possible object is
     *     {@link Zone }
     *     
     */
    public Zone getZone() {
        return zone;
    }

    /**
     * Sets the value of the zone property.
     * 
     * @param value
     *     allowed object is
     *     {@link Zone }
     *     
     */
    public void setZone(Zone value) {
        this.zone = value;
    }

    /**
     * Gets the value of the spotId property.
     * 
     */
    public int getSpotId() {
        return spotId;
    }

    /**
     * Sets the value of the spotId property.
     * 
     */
    public void setSpotId(int value) {
        this.spotId = value;
    }

    /**
     * Gets the value of the spotName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpotName() {
        return spotName;
    }

    /**
     * Sets the value of the spotName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpotName(String value) {
        this.spotName = value;
    }

    /**
     * Gets the value of the isVacancy property.
     * 
     */
    public boolean isIsVacancy() {
        return isVacancy;
    }

    /**
     * Sets the value of the isVacancy property.
     * 
     */
    public void setIsVacancy(boolean value) {
        this.isVacancy = value;
    }

}
