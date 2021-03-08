package com.carlossantana.TrelloApiTest.controllers;

import com.carlossantana.TrelloApiTest.models.User;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActionsController {

    User user = new User();
//    User user = new User("1239120", "vicentin123", "Victor Vicente",
//            "123", "321");


    @GetMapping("/all-actions-of-board")
    public String getAllActionsBoard(){
        String boardId = "60288ce3900bf67ecd4c4583";

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/boards/" + boardId + "/actions")
                .queryString("key", user.getKey())
                .queryString("token", user.getToken())
                .asJson();

        System.out.println(response.getBody().toPrettyString());

        return response.getBody().toPrettyString();
    }

    @GetMapping("/last-action-of-board")
    public String getLastActionsBoard(){
        String boardId = "60288ce3900bf67ecd4c4583";

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/boards/" + boardId + "/actions")
                .queryString("key", user.getKey())
                .queryString("token", user.getToken())
                .queryString("limit", 1)
                .asJson();

        System.out.println(response.getBody().toPrettyString());

        return response.getBody().toPrettyString();
    }

    @GetMapping("/all-actions-of-member")
    public String getAllActionsMember(){

        String memberId = "60391afe8cf7ef17518fdb63";

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/members/" + memberId + "/actions")
                .queryString("key", user.getKey())
                .queryString("token", user.getToken())
                .asJson();

        System.out.println(response.getBody().toPrettyString());

        return response.getBody().toPrettyString();
    }

    @GetMapping("/all-actions-of-member-of-board")
    public String getAllActionsMemberBoard(){
        //TODO: implementar resquisição com retorno de todas as ações de um usuário de um quadro
        return "";
    }

///Definir o que fazer ocm dados e como gameficar?
    //

}
