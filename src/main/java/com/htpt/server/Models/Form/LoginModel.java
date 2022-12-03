package com.htpt.server.Models.Form;

public class LoginModel {
    
    private String email;
    private String pass;

    public LoginModel() {}

    public LoginModel(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }


}
