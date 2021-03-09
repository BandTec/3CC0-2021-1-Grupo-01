package com.carlossantana.TrelloApiTest.controllers;

import com.carlossantana.TrelloApiTest.models.Developer;
import com.carlossantana.TrelloApiTest.models.User;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.web.bind.annotation.GetMapping;

public class ListsController {

//    User user = new User();
    User dev = new Developer("1239120", "vicentin123", "Victor Vicente",
            "123", "321");

    String boardId  = AppConfig.idBoardMonitored;


    @GetMapping("/get-lists-from-board")
    public String getListsFromBoard(){

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/boards/" + boardId + "/lists")
                .queryString("key", dev.getKey())
                .queryString("token", dev.getToken())
                .asJson();

        System.out.println(response.getBody().toPrettyString());

        return response.getBody().toPrettyString();
    }

    @GetMapping("/get-list")
    public String getList(){

        String listId = "60288ce3900bf67ecd4c4584";

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/lists/" + listId)
                .queryString("key", dev.getKey())
                .queryString("token", dev.getToken())
                .asJson();

        System.out.println(response.getBody().toPrettyString());

        return response.getBody().toPrettyString();
    }
}
