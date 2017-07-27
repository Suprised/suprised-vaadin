package com.suprised.vaadin.app.webservice;

import com.suprised.vaadin.app.webservice.client.HelloServiceService;

public class HelloClient {

	public static void main(String[] args) {
		HelloServiceService service = new HelloServiceService();
		com.suprised.vaadin.app.webservice.client.HelloService s = service.getHelloServicePort();
		s.sayHello("刘金喜");
		s.sayHello("刘金喜2");
	}
	
}
