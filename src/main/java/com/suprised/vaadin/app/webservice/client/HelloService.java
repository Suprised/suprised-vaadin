
package com.suprised.vaadin.app.webservice.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "HelloService", targetNamespace = "http://webservice.app.vaadin.suprised.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface HelloService {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://webservice.app.vaadin.suprised.com/", className = "com.suprised.vaadin.app.webservice.client.SayHello")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://webservice.app.vaadin.suprised.com/", className = "com.suprised.vaadin.app.webservice.client.SayHelloResponse")
    @Action(input = "http://webservice.app.vaadin.suprised.com/HelloService/sayHelloRequest", output = "http://webservice.app.vaadin.suprised.com/HelloService/sayHelloResponse")
    public void sayHello(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "sayHello2", targetNamespace = "http://webservice.app.vaadin.suprised.com/", className = "com.suprised.vaadin.app.webservice.client.SayHello2")
    @ResponseWrapper(localName = "sayHello2Response", targetNamespace = "http://webservice.app.vaadin.suprised.com/", className = "com.suprised.vaadin.app.webservice.client.SayHello2Response")
    @Action(input = "http://webservice.app.vaadin.suprised.com/HelloService/sayHello2Request", output = "http://webservice.app.vaadin.suprised.com/HelloService/sayHello2Response")
    public void sayHello2(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}