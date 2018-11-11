package com.uottawa.shize.homerepairapp;

import java.util.Locale;

public class Service {
    private String name;
    private float price;

    public Service() {
        // for DataSnapshot.getValue(Service.class)
    }

    public Service(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getServiceName() {
        return name;
    }

    public void setServiceName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String toString() {
        return name + ", hourly rate: $" + String.format(Locale.CANADA, "%.2f", price);
    }
}
