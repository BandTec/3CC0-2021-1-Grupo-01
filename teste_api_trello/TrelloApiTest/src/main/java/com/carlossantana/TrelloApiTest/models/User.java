package com.carlossantana.TrelloApiTest.models;

import java.util.List;

public class User {

    private String id;
    private String username;
    private String fullName;
    private String email;
    private List<Object> idBoards;
    private String key = "fafa203c2eb7040d43c24ad7ca66ba32";
    private String token = "9156f73352b670b11ad66b6321f9a0833bd1daf8351859ef497fe13a8b2bb3ad";

    public User() {
    }

    public User(String key, String token) {
        this.key = key;
        this.token = token;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Object> getIdBoards() {
        return idBoards;
    }

    public void setIdBoards(List<Object> idBoards) {
        this.idBoards = idBoards;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getKey() {
        return key;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "User{" +
                "key='" + key + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}

