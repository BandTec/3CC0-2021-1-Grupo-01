package com.carlossantana.TrelloApiTest;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.web.bind.annotation.GetMapping;

public class ListsController {
    User user = new User();
    @GetMapping("/get-lists-from-board")
    public String getListsFromBoard(){

        String boardId = "60288ce3900bf67ecd4c4583";

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/boards/" + boardId + "/lists")
                .queryString("key", user.getKey())
                .queryString("token", user.getToken())
                .asJson();

        System.out.println(response.getBody().toPrettyString());

        return response.getBody().toPrettyString();
    }

    @GetMapping("/get-list")
    public String getList(){

        String listId = "60288ce3900bf67ecd4c4584";

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/lists/" + listId)
                .queryString("key", user.getKey())
                .queryString("token", user.getToken())
                .asJson();

        System.out.println(response.getBody().toPrettyString());

        return response.getBody().toPrettyString();
    }
}