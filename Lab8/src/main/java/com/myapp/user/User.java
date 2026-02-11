package com.myapp.user;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

// These annotations tell Java: "this class can be converted to XML and back"
// SOAP sends data as XML, so this is required
@XmlRootElement(name = "com/myapp")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    @XmlElement
    private String username;

    @XmlElement
    private int id;

    @XmlElement
    private String password;

    // Empty constructor - REQUIRED by JAX-WS (it creates objects from XML using this)
    public User() {}

    // Constructor with all fields
    public User(String username, int id, String password) {
        this.username = username;
        this.id = id;
        this.password = password;
    }

    // Getters and Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', password='" + password + "'}";
    }
}