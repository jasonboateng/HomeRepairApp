package com.uottawa.shize.homerepairapp;

import android.support.annotation.NonNull;

import java.util.Locale;

public class Service {
    private String serviceName;
    private double rate;

    public Service() {
        // for DataSnapshot.getValue(Service.class)
    }

    public Service(String serviceName, double rate) {
        this.serviceName = serviceName;
        this.rate = rate;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @NonNull
    public String toString() {
        return serviceName + "\nHourly rate: $" + String.format(Locale.CANADA, "%.2f", rate);
    }
}
