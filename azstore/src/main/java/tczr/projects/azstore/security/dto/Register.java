package tczr.projects.azstore.security.dto;

import tczr.projects.azstore.shared.Status;

import java.time.LocalDateTime;

public class Register {
    private String email;
    private String username;
    private String password;
    private Status status;
    private LocalDateTime registerAt;

    public Register(){
        registerAt=LocalDateTime.now();
        status=Status.ACTIVE;
    }
    public Register(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.status = Status.ACTIVE;
        this.registerAt=LocalDateTime.now();
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }


    public LocalDateTime getRegisterAt() {
        return registerAt;
    }

}
