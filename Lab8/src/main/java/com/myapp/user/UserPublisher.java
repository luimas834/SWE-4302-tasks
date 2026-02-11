package com.myapp.user;

import jakarta.xml.ws.Endpoint;

public class UserPublisher {

    // Publishes the SOAP service at localhost:9090/userservice
    public static void startService() {
        String url = "http://localhost:9090/userservice";
        Endpoint.publish(url, new UserServiceImpl());
        System.out.println("User SOAP Web Service is running at: " + url);
        System.out.println("WSDL available at: " + url + "?wsdl");
    }

    public static void main(String[] args) {
        startService();
    }
}