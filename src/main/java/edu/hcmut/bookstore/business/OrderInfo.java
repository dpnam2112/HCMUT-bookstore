package edu.hcmut.bookstore.business;

import java.io.Serializable;

public class OrderInfo {

    private String fullName;
    private String email;
    private String address;
    private String phoneNumber;
    private String paymentMethod;

    // Constructor
    public OrderInfo() {

    }

    // Getters
    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter
    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    // Setter
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
