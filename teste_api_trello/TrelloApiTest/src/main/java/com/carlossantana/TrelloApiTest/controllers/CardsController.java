package com.carlossantana.TrelloApiTest.controllers;

import com.carlossantana.TrelloApiTest.models.User;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    User user = new User();

    @GetMapping("/get-cards-from-list")
    public String getCardsFromList(){

        String listId = "60288ce3900bf67ecd4c4584";

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/lists/" + listId + "/cards")
                .queryString("key", user.getKey())
                .queryString("token", user.getToken())
                .asJson();

        System.out.println(response.getBody().toPrettyString());

        return response.getBody().toPrettyString();
    }


    @GetMapping("/get-card")
    public String getCard(){

        String cardId = "60288ce3900bf67ecd4c45b4";

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/cards/" + cardId)
                .queryString("key", user.getKey())
                .queryString("token", user.getToken())
                .asJson();

//        System.out.println(response.getBody().toPrettyString());
        System.out.println(response.getBody());

        return response.getBody().toPrettyString();
    }
}
