
package ws;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WebServiceImpService", targetNamespace = "http://ws/", wsdlLocation = "http://localhost:8080/ws/ws?wsdl")
public class WebServiceImpService
    extends Service
{

    private final static URL MAGYARLANCWEBSERVICEIMPSERVICE_WSDL_LOCATION;
    private final static WebServiceException MAGYARLANCWEBSERVICEIMPSERVICE_EXCEPTION;
    private final static QName MAGYARLANCWEBSERVICEIMPSERVICE_QNAME = new QName("http://ws/", "WebServiceImpService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/ws/ws?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MAGYARLANCWEBSERVICEIMPSERVICE_WSDL_LOCATION = url;
        MAGYARLANCWEBSERVICEIMPSERVICE_EXCEPTION = e;
    }

    public WebServiceImpService() {
        super(__getWsdlLocation(), MAGYARLANCWEBSERVICEIMPSERVICE_QNAME);
    }

    public WebServiceImpService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MAGYARLANCWEBSERVICEIMPSERVICE_QNAME, features);
    }

    public WebServiceImpService(URL wsdlLocation) {
        super(wsdlLocation, MAGYARLANCWEBSERVICEIMPSERVICE_QNAME);
    }

    public WebServiceImpService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MAGYARLANCWEBSERVICEIMPSERVICE_QNAME, features);
    }

    public WebServiceImpService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WebServiceImpService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WebServiceInterface
     */
    @WebEndpoint(name = "WebServiceImpPort")
    public WebServiceInterface getWebServiceImpPort() {
        return super.getPort(new QName("http://ws/", "WebServiceImpPort"), WebServiceInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebServiceInterface
     */
    @WebEndpoint(name = "WebServiceImpPort")
    public WebServiceInterface getWebServiceImpPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws/", "WebServiceImpPort"), WebServiceInterface.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MAGYARLANCWEBSERVICEIMPSERVICE_EXCEPTION!= null) {
            throw MAGYARLANCWEBSERVICEIMPSERVICE_EXCEPTION;
        }
        return MAGYARLANCWEBSERVICEIMPSERVICE_WSDL_LOCATION;
    }

}