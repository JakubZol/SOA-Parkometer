
package pl.soa.parkometer.carpark_mock.soap_client.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for zone complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="zone">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="zoneId" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="zoneName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "zone")
public class Zone {

    @XmlAttribute(name = "zoneId", required = true)
    protected int zoneId;
    @XmlAttribute(name = "zoneName")
    protected String zoneName;

    /**
     * Gets the value of the zoneId property.
     * 
     */
    public int getZoneId() {
        return zoneId;
    }

    /**
     * Sets the value of the zoneId property.
     * 
     */
    public void setZoneId(int value) {
        this.zoneId = value;
    }

    /**
     * Gets the value of the zoneName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZoneName() {
        return zoneName;
    }

    /**
     * Sets the value of the zoneName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZoneName(String value) {
        this.zoneName = value;
    }

}
