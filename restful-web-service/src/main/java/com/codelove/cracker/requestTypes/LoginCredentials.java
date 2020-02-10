package com.codelove.cracker.requestTypes;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class LoginCredentials {

    @Email(message="Invalid Email Id")
    @NotEmpty(message="Email cannot be empty")
    private String email;

    @NotEmpty(message="password cannot be empty")
    private String password;

    LoginCredentials(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginCredentials{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
