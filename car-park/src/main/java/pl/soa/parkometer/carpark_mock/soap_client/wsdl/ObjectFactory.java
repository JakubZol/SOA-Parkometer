
package pl.soa.parkometer.carpark_mock.soap_client.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the pl.soa.parkometer.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Spot_QNAME = new QName("http://soap.parkometer.soa.pl/", "spot");
    private final static QName _Zone_QNAME = new QName("http://soap.parkometer.soa.pl/", "zone");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: pl.soa.parkometer.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Zone }
     * 
     */
    public Zone createZone() {
        return new Zone();
    }

    /**
     * Create an instance of {@link Spot }
     * 
     */
    public Spot createSpot() {
        return new Spot();
    }

    /**
     * Create an instance of {@link SpotArray }
     * 
     */
    public SpotArray createSpotArray() {
        return new SpotArray();
    }

    /**
     * Create an instance of {@link Timestamp }
     * 
     */
    public Timestamp createTimestamp() {
        return new Timestamp();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Spot }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.parkometer.soa.pl/", name = "spot")
    public JAXBElement<Spot> createSpot(Spot value) {
        return new JAXBElement<Spot>(_Spot_QNAME, Spot.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Zone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.parkometer.soa.pl/", name = "zone")
    public JAXBElement<Zone> createZone(Zone value) {
        return new JAXBElement<Zone>(_Zone_QNAME, Zone.class, null, value);
    }

}
