package com.example.citycyclerentals;

public class PaymentMethod {
    private String method;
    private String details;

    public PaymentMethod(String method, String details) {
        this.method = method;
        this.details = details;
    }

    public String getMethod() {
        return method;
    }

    public String getDetails() {
        return details;
    }
}