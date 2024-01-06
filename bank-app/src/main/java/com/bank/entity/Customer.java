package com.bank.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Customer {

    public enum CustomerType {
        INDIVIDUAL,
        COMPANY
    }

    private long id;
    private String name;
    private String address;
    private LocalDate dob;
    private String phoneNumber;
    private String email;
    private boolean isActive;
    private LocalDateTime createdDate;
    private LocalDateTime deactivatedDate;
    private CustomerType type = null;

    // Empty constructor for instantiating empty/null objects for testing etc.
    public Customer() {

    }

    // Parametrised constructor taking mandatory fields
    public Customer(String name, String address, LocalDate dob, String phoneNumber, String email, CustomerType type)
    {
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.type = type;
    }

    // Parametrised constructor taking all fields from a customer object
    public Customer(Customer other) {
        this.id = other.id;
        this.name = other.name;
        this.address = other.address;
        this.dob = other.dob;
        this.phoneNumber = other.phoneNumber;
        this.email = other.email;
        this.type = other.type;
        this.isActive = other.isActive;
        this.createdDate = other.createdDate;
        this.deactivatedDate = other.deactivatedDate;
        this.type = other.type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
         this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getDeactivatedDate() {
        return deactivatedDate;
    }

    public void setDeactivatedDate(LocalDateTime deactivatedDate) {
        this.deactivatedDate = deactivatedDate;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((dob == null) ? 0 : dob.hashCode());
        result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + (isActive ? 1231 : 1237);
        result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
        result = prime * result + ((deactivatedDate == null) ? 0 : deactivatedDate.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (dob == null) {
            if (other.dob != null)
                return false;
        } else if (!dob.equals(other.dob))
            return false;
        if (phoneNumber == null) {
            if (other.phoneNumber != null)
                return false;
        } else if (!phoneNumber.equals(other.phoneNumber))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (isActive != other.isActive)
            return false;
        if (createdDate == null) {
            if (other.createdDate != null)
                return false;
        } else if (!createdDate.equals(other.createdDate))
            return false;
        if (deactivatedDate == null) {
            if (other.deactivatedDate != null)
                return false;
        } else if (!deactivatedDate.equals(other.deactivatedDate))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String createdDateStr = null;
        String deactivatedDateStr = null;
        if (createdDate != null) {
            createdDateStr = createdDate.format(formatter);
        }
        else {
            createdDateStr = "N/A";
        }
        if (deactivatedDate != null) {
            deactivatedDateStr = deactivatedDate.format(formatter);
        }
        else {
            deactivatedDateStr = "N/A";
        }
        return "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\nID: " + id + "\nName: " + name + "\nAddress: " + address + "\nDob: " + dob + "\nPhone Number: "
                + phoneNumber + "\nEmail: " + email + "\nActive: " + isActive + "\nCreated Date: " + createdDateStr
                + "\nDactivated Date: " + deactivatedDateStr + "\nCustomer Type: " + type + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }
    
}
