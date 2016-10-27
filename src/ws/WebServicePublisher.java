package ws;

import javax.xml.ws.Endpoint;

/**
 * Created by zsjanos on 2016.10.21..
 */

public class WebServicePublisher {

    /**
     * curl http://localhost:8080/webservice/helloworld?wsdl
     *
     * @param args
     */
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/helloworld", new WebServiceImp());
    }
}
