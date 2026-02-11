package org.calculator;


import jakarta.xml.ws.Endpoint;


public class SoapServer {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/hello", new CalculatorWebServiceImpl());
        System.out.println("SOAP Service Running at http://localhost:8080/hello?wsdl");
        System.out.println("yay");
    }
}