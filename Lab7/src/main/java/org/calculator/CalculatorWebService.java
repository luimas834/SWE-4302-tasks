package org.calculator;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface CalculatorWebService {
    @WebMethod
    int add(@WebParam(name = "num1") int num1, @WebParam(name = "num2") int num2);
    @WebMethod
    int subtract(@WebParam(name="num1") int num1,
                 @WebParam(name="num2") int num2);

    @WebMethod
    int multiply(@WebParam(name="num1") int num1,
                 @WebParam(name="num2") int num2);

}