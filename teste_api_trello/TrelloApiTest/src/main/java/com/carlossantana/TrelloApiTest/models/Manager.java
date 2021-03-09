package com.carlossantana.TrelloApiTest.models;

import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

public class Manager extends User{
    public Manager(String id, String username, String fullName, String email, String senha) {
        super(id, username, fullName, email, senha);
    }


    public Double getTaskPontuation(JSONObject jsonCard) {
        return null;
    }

    @Override
    public Double getTaskPontuation(, Integer humor) {
        System.out.println(jsonCard.length());
        return null;
    }
}
