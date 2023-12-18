package com.bank.entity;

import java.time.LocalDate;

public class Customer {

    public enum CustomerType {
        INTERNAL,
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
    private LocalDate createdDate;
    private LocalDate deactivatedDate;
    private CustomerType type = null;
    private BankAccount account = null;

    // Constructors
    // Empty constructor for instantiating empty/null objects for testing etc.
    public Customer() {

    }

    // Parametrised constructor taking mandatory fields
    public Customer(long id, String name, String address, LocalDate dob, String phoneNumber, String email, CustomerType type)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.type = type;
        this.isActive = true;
        this.createdDate = LocalDate.now();
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getDeactivatedDate() {
        return deactivatedDate;
    }

    public void setDeactivatedDate(LocalDate deactivatedDate) {
        this.deactivatedDate = deactivatedDate;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }
    
    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", dob=" + dob + ", phoneNumber="
                + phoneNumber + ", email=" + email + ", isActive=" + isActive + ", createdDate=" + createdDate
                + ", deactivatedDate=" + deactivatedDate + ", type=" + type + ", account=" + account + "]";
    }


}
