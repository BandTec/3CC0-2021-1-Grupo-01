package com.carlossantana.TrelloApiTest;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class Request {

    private String baseUrl = "https://api.trello.com/1";

    private String uri;

    private User user = new User();


    public String getRequest(String uri){
        HttpResponse<JsonNode> response = Unirest.get(this.baseUrl + uri)
                .queryString("key", user.getKey())
                .queryString("token", user.getToken())
                .asJson();

        return response.getBody().toPrettyString();
    }
    public String getOneRequest(String uri, String id){
        HttpResponse<JsonNode> response = Unirest.get(this.baseUrl + uri)
                .queryString("key", user.getKey())
                .queryString("token", user.getToken())
                .asJson();

        return response.getBody().toPrettyString();
    }
}
