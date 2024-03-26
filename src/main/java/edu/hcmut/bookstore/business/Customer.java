package edu.hcmut.bookstore.business;

import java.io.Serializable;

public class Customer implements Serializable {
    private long id;
    private String username;
    private String customerName;
    private String email;
    private String phoneNumber;

    // Constructor
    public Customer(long id, String username, String customerName, String email, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setters
    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
