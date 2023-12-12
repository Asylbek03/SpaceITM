package com.example.spaceitm.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity

@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)


    private Long id;

    private String email;
    private String password;
    private String role;
    private String fullname;
    private String avatar;
    private String bio;
    private Date dateJoined;
    private Date lastActive;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Topic> topics;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "user_likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )


    private Set<Comment> likedComments;

    @ManyToMany
    @JoinTable(
            name = "user_follows",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id")
    )


    private Set<User> following;


    public User() {
        super();
    }

    public User(String email, String password, String role, String fullname, String avatar, String bio, Date dateJoined, Date lastActive) {

        this.email = email;
        this.password = password;
        this.role = role;
        this.fullname = fullname;
        this.avatar = avatar;
        this.bio = bio;
        this.dateJoined = dateJoined;
        this.lastActive = lastActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Date getLastActive() {
        return lastActive;
    }

    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Set<Comment> getPosts() {
        return comments;
    }

    public void setPosts(Set<Comment> posts) {
        this.comments = comments;
    }

    public Set<Comment> getLikedPosts() {
        return likedComments;
    }

    public void setLikedPosts(Set<Comment> likedPosts) {
        this.likedComments = likedPosts;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }


}
