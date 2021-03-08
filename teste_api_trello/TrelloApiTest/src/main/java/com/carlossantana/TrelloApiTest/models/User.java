package com.carlossantana.TrelloApiTest.models;

import java.util.List;

public class User {

    private String id;
    private String username;
    private String fullName;
    private String email;
    private String senha;
    private List<Object> idBoards;
    private String key;
    private String token;

//    public User(String key, String token) {
//        this.key = key;
//        this.token = token;
//    }

    //Tentativa de login
    public User(String id, String username, String fullName, String email, String senha) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.senha = senha;
    }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

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

