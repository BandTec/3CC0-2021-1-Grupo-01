package com.carlossantana.TrelloApiTest.controllers;

import com.carlossantana.TrelloApiTest.models.Developer;
import com.carlossantana.TrelloApiTest.models.User;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardsController{

//    User user = new User();
    User dev = new Developer("1239120", "vicentin123", "Victor Vicente",
            "123", "321");

    String boardId = AppConfig.idBoardMonitored;
    

    @GetMapping("/all-boards")
    public String getAllBoards(){

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/members/me/boards")
                .queryString("key", dev.getKey())
                .queryString("token", dev.getToken())
                .asJson();

        System.out.println(response.getBody().toPrettyString());

        return response.getBody().toPrettyString();
    }

    @GetMapping("/get-board")
    public String getSpecificBoard(){


        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/boards/" + boardId)
                .queryString("key", dev.getKey())
                .queryString("token", dev.getToken())
                .asJson();

        System.out.println(response.getBody().toPrettyString());

        return response.getBody().toPrettyString();
    }
}
