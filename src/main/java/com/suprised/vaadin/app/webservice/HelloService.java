package com.suprised.vaadin.app.webservice;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class HelloService {

	public void sayHello(String name) {
		System.out.println("hello: " + name);
	}
	
	public void sayHello2(String name) {
		System.out.println("hello: " + name);
	}
	
	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8888/test", new HelloService());
	}
}
