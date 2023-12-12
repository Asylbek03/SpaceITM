package com.example.spaceitm.dto;

import java.time.LocalDate;
import java.util.Date;

public class UserDto {

    private String email;
    private String password;
    private String role;
    private String fullname;
    private String avatar;
    private String bio;
    private Date dateJoined;
    private Date lastActive;


    public UserDto(String email, String password, String role, String fullname, String avatar, String bio, Date dateJoined, Date lastActive) {
        super();
        this.email = email;
        this.password = password;
        this.role = role;
        setRole("USER");
        this.fullname = fullname;
        this.avatar = avatar;
        this.bio = bio;
        this.dateJoined = dateJoined;
        this.lastActive = lastActive;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setDateJoined(Date dateJoined){
        this.dateJoined = dateJoined;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public Date getLastActive() {
        return lastActive;
    }

}
