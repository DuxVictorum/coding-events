package org.launchcode.codingevents.dto;

public class RegisterFormDTO extends LoginFormDTO {

    private String verifyPassword;

//    Getter Setter Salad
    public String getVerifyPassword() { return verifyPassword; }
    public void setVerifyPassword(String verifyPassword) { this.verifyPassword = verifyPassword; }
}
