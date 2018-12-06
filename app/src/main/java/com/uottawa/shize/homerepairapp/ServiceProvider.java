package com.uottawa.shize.homerepairapp;

public class ServiceProvider extends User {

    private String address;
    private String phone;
    private String companyName;
    private String description;
    private boolean isLicensed;

    public ServiceProvider() {
        // required empty constructor
    }

    public ServiceProvider(String username, String password, String userType,
                           String address, String phone, String companyName,
                           String description, boolean isLicensed) {
        super(username, password, userType);
        this.address = address;
        this.phone = phone;
        this.companyName = companyName;
        this.description = description;
        this.isLicensed = isLicensed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLicensed() {
        return isLicensed;
    }

    public void setLicensed(boolean licensed) {
        isLicensed = licensed;
    }
}
