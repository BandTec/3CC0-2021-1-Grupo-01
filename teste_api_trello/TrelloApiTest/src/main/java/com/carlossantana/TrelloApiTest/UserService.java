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

    //Tentativa de um login

    public String checkUser(User user, List<User> listUser) {
        String message = null;
        for (User userList : listUser) {
            if (user.getEmail().equals(userList.getEmail()) && user.getSenha().equals(userList.getSenha())) {
                message = "Usuário logado com sucesso!";
                break;
            }else{ message = "Usuário não encontrado!"; }
        }
        return message;
    }

    public User getLoggedUser(User user, List<User> listUser) {
        User userLogged = null;
        for (User userList : listUser) {
            if (user.getEmail().equals(userList.getEmail()) && user.getSenha().equals(userList.getSenha())) {
                userLogged = userList;
                break;
            }else{ userLogged = null;}
        }
        return userLogged;
    }
}
