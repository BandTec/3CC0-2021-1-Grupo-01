package com.carlossantana.TrelloApiTest;

import kong.unirest.Body;
import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AppController {

    //    Optional<Body> response = Unirest.get("http://youtube.com").getBody();
// This code sample uses the  'Unirest' library:
// http://unirest.io/java.html
    User user = new User();
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

        System.out.println(response.getBody().toPrettyString());

        return response.getBody().toPrettyString();
    }

}
