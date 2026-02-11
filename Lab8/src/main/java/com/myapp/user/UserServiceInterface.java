package com.myapp.user;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

// @WebService = marks this as a SOAP web service interface
// targetNamespace = a unique identifier for this service
@WebService(targetNamespace = "http://user.myapp.com/")
public interface UserServiceInterface {

    // Task 1.1: Create User
    // Input: username, id, password → Output: Confirmation message
    @WebMethod
    String createUser(
            @WebParam(name = "username") String username,
            @WebParam(name = "id") int id,
            @WebParam(name = "password") String password
    );

    // Task 1.2: Retrieve User
    // Input: id → Output: User details or error message
    @WebMethod
    String retrieveUser(@WebParam(name = "id") int id);

    // Task 1.3: Update User
    // Input: id, updated username, updated password → Output: Update status message
    @WebMethod
    String updateUser(
            @WebParam(name = "id") int id,
            @WebParam(name = "username") String username,
            @WebParam(name = "password") String password
    );

    // Task 1.4: Delete User
    // Input: id → Output: Deletion confirmation message
    @WebMethod
    String deleteUser(@WebParam(name = "id") int id);
}