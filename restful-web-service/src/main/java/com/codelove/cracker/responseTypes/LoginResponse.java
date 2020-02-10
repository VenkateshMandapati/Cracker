package com.codelove.cracker.responseTypes;

public class LoginResponse {

    private boolean isCustomerValid;

    private Integer customerId;

    private String firstName;

    private String lastName;

    public LoginResponse() {
    }

    public LoginResponse(boolean isCustomerValid, Integer customerId, String firstName, String lastName) {
        this.isCustomerValid = isCustomerValid;
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean getIsCustomerValid() {
        return isCustomerValid;
    }

    public void setIsCustomerValid(boolean isCustomerValid) {
        this.isCustomerValid = isCustomerValid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
