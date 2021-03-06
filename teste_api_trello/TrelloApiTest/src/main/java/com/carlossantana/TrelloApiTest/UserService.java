package com.carlossantana.TrelloApiTest;

import com.carlossantana.TrelloApiTest.controllers.UserController;
import com.carlossantana.TrelloApiTest.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

//    UserController userController = new UserController();
    public UserService() {
    }

    public void getBoardMemberships(User user) {

        String boardId = "60288ce3900bf67ecd4c4583";

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/boards/" + boardId + "/memberships")
                .queryString("key",  user.getKey())
                .queryString("token", user.getToken())
                .asJson();

        System.out.println(response.getBody().getArray().getJSONObject(0).get("id"));
        System.out.println(response.getBody().getArray().getJSONObject(0).get("id"));

//        getUserInfo();
    }

    public void getUserInfo(User loggedUser) {

        HttpResponse<JsonNode> response = Unirest.get("https://api.trello.com/1/members/me")
                .queryString("key",  loggedUser.getKey())
                .queryString("token", loggedUser.getToken())
                .asJson();

        System.out.println(response.getBody().getArray().getJSONObject(0).get("id"));
        System.out.println(response.getBody().getArray().getJSONObject(0).get("username"));
        System.out.println(response.getBody().getArray().getJSONObject(0).get("fullName"));
        System.out.println(response.getBody().getArray().getJSONObject(0).get("email"));
        System.out.println(response.getBody().getArray().getJSONObject(0).get("idBoards"));

        loggedUser.setId((String) response.getBody().getArray().getJSONObject(0).get("id"));
        loggedUser.setUsername((String) response.getBody().getArray().getJSONObject(0).get("username"));
        loggedUser.setFullName((String) response.getBody().getArray().getJSONObject(0).get("fullName"));
        loggedUser.setEmail((String) response.getBody().getArray().getJSONObject(0).get("email"));
    //        loggedUser.setIdBoards((List<Object>) response.getBody().getArray().getJSONObject(0).get("idBoards"));
    }
}
