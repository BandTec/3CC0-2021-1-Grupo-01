package com.carlossantana.TrelloApiTest.controllers;

import com.carlossantana.TrelloApiTest.models.User;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardsController{

    User user = new User();
//    User user = new User("1239120", "vicentin123", "Victor Vicente",
//            "123", "321");
    

    @GetMapping("/all-boards")
    public String getAllBoards(){

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/members/me/boards")
                .queryString("key", user.getKey())
                .queryString("token", user.getToken())
                .asJson();

        System.out.println(response.getBody().toPrettyString());

        return response.getBody().toPrettyString();
    }

    @GetMapping("/get-board")
    public String getSpecificBoard(){

        String boardId = "60288ce3900bf67ecd4c4583";

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/boards/" + boardId)
                .queryString("key", user.getKey())
                .queryString("token", user.getToken())
                .asJson();

        System.out.println(response.getBody().toPrettyString());

        return response.getBody().toPrettyString();
    }
}
