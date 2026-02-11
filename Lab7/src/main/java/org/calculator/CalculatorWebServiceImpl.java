package org.calculator;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(endpointInterface = "org.calculator.CalculatorWebService")
public class CalculatorWebServiceImpl implements CalculatorWebService {
    @WebMethod
    public int add( int num1,  int num2) {
        System.out.println(num1+""+num2);
        return num1 + num2;
    }
    @WebMethod
    public int subtract(int num1, int num2) {
        return num1 - num2;
    }

    @WebMethod
    public int multiply(int num1, int num2) {
        return num1 * num2;
    }


}
