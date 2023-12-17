package com.bank.entity;

import java.time.LocalDate;
import org.apache.commons.validator.routines.EmailValidator;
import com.bank.exceptions.CustomerException;

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
    private CustomerType customerType;
    private BankAccount account;

    private static final EmailValidator EMAIL_VALIDATOR = EmailValidator.getInstance();

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

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }
    
    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }
    
    public Customer() {
    }

    public Customer(String name, String address, LocalDate dob, String phoneNumber, String email, CustomerType customerType)
    {
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.phoneNumber = phoneNumber;

        if (!EMAIL_VALIDATOR.isValid(email)) {
            throw new CustomerException("Invalid email. The email must be in xxx@yyy.zzz format.");
        }
        else 
        {
            this.email = email;
        }
        
        this.customerType = customerType;
        this.isActive = true;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", dob=" + dob + ", phoneNumber="
                + phoneNumber + ", email=" + email + ", isActive=" + isActive + ", createdDate=" + createdDate
                + ", deactivatedDate=" + deactivatedDate + ", customerType=" + customerType.name() + "]";
    }


}
